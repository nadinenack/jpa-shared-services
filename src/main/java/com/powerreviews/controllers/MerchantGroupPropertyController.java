package com.powerreviews.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.powerreviews.controllers.helpers.ConfigServiceControllerHelper;
import com.powerreviews.controllers.pojos.AggregatedProperty;
import com.powerreviews.services.MerchantGroupPropertyService;
import com.powerreviews.services.MerchantPropertyService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nadinenack on 9/23/16.
 */
@BasePathAwareController
@RequestMapping(path = "config-service/properties")
public class MerchantGroupPropertyController {

    @Resource
    private MerchantGroupPropertyService merchantGroupPropertyService;

    @RequestMapping(path = "g", method = RequestMethod.GET)
    public ResponseEntity<Object> getConfig() {
        return new ResponseEntity<>(merchantGroupPropertyService.getConfig(), HttpStatus.OK);
    }
    @Resource
    private MerchantPropertyService merchantPropertyService;

    @Resource
    private ConfigServiceControllerHelper helper;

    private static final Logger logger = Logger.getLogger(MerchantGroupPropertyController.class);

    /**
     * Gets merchant group properties. Possible flags are key and excludeDefaults. Merchant group id must be provided in
     * path.
     *
     * If no flags are set, the method returns all properties set for given merchant group id. This does not include any
     * provider properties or system default properties.
     *
     * The keys flag defines one or more property keys to be fetched. If using multiple keys, they must be in a
     * comma-separated list. If no merchant group property exists with given key, the service fetches the provider level
     * or system default config value, with provider properties taking precedence over system defaults.
     *
     * If the excludeDefaults flag is set to false, this method will search the provider property and config tables to
     * get any property values the merchant group doesn't have set. If the excludeDefaults flag is set to true, only the
     * merchant group properties will be returned. No provider properties or config values will be included in the
     * result.
     *
     * @param mgid
     * @param keysString
     * @param excludeDefaults If true, only returns merchant group properties. If false, returns provider properties and
     *                        config values, if necessary.
     * @return
     */
    @RequestMapping(path = "/g{mgid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getByMerchantGroup(@PathVariable("mgid") Long mgid,
                                                     @RequestParam(value = "keys", defaultValue = "", required = false) String keysString,
                                                     @RequestParam(value = "excludeDefaults", defaultValue = "false", required = false) Boolean excludeDefaults) {

        Map<String, AggregatedProperty> mgps;

        Set<String> keySet = ConfigServiceControllerHelper.parseStringToSet(keysString);
        if(keySet.isEmpty()) {
            logger.info(String.format("Getting properties for merchantGroupId %d", mgid));
            mgps = merchantGroupPropertyService.getByMerchantGroup(mgid);
        }else if(excludeDefaults) {
            logger.info(String.format("Getting properties for merchantGroupId %d and %d keys specified", mgid, keySet.size()));
            mgps = merchantGroupPropertyService.getByMerchantGroupAndKey(mgid, keySet);
        }else {
            logger.info(String.format("Getting properties for merchantGroupId %d and %d keys specified", mgid, keySet.size()));
            mgps = merchantGroupPropertyService.getByMerchantGroupAndKeyWithDefaults(mgid, keySet);
        }

        return new ResponseEntity<>(mgps.values(), HttpStatus.OK);
    }

    /**
     * Gets merchant properties. Possible flags are key and excludeDefaults. Merchant id must be provided in path.
     *
     * If no flags are set, the method returns all properties set for given merchant id. This does not include any
     * merchant group, provider properties, or system default properties.
     *
     * The keys flag defines one or more property keys to be fetched. If using multiple keys, they must be in a
     * comma-separated list. If no merchant  property exists with given key, the service fetches the merchant
     * group, provider level or system default config value, with merchant group taking precidence over provider and
     * provider properties taking precedence over system defaults.
     *
     * If the excludeDefaults flag is set to false, this method will search the merchant group property, provider
     * property, and config tables to get any property values the merchant doesn't have set. If the excludeDefaults flag
     * is set to true, only the merchant group properties will be returned. No provider properties or config values will
     * be included in the result.
     *
     * @param mid
     * @param keysString
     * @param excludeDefaults If false, returns any relevant merchant group properties, provider properties, or config
     *                        values, if necessary. If true, only returns merchant properties
     * @return
     */
    @RequestMapping(path = "/m{mid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getByMerchant(@PathVariable("mid") Long mid,
                                                @RequestParam(value = "keys", defaultValue = "", required = false) String keysString,
                                                @RequestParam(value = "excludeDefaults", defaultValue = "false", required = false) Boolean excludeDefaults) {
        Map<String, AggregatedProperty> properties;

        Set<String> keySet = ConfigServiceControllerHelper.parseStringToSet(keysString);
        if(keySet.isEmpty()) {
            logger.info(String.format("Getting properties for merchantId %d", mid));
            properties = merchantPropertyService.getByMerchant(mid);
        }else if(excludeDefaults) {
            logger.info(String.format("Getting properties for merchantId %d with %d keys specified", mid, keySet.size()));
            properties = merchantPropertyService.getByMerchantAndKey(mid, keySet);
        }else {
            logger.info(String.format("Getting properties for merchantId %d with %d keys specified", mid, keySet.size()));
            properties = merchantPropertyService.getByMerchantAndKeyWithDefaults(mid, keySet);
        }
        return new ResponseEntity<>(properties.values(), HttpStatus.OK);
    }

    /**
     * Creates or updates one or more merchant group properties.
     * @param mgid
     * @param properties
     * @return
     */
//    @DatasourceRouting(target=Target.Master)
    @RequestMapping(path = "/g{mgid}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateForMerchantGroup(@PathVariable("mgid") Long mgid,
                                                         @RequestBody List<AggregatedProperty> properties) {
        if(properties == null || properties.isEmpty()) {
            return new ResponseEntity<>(helper.createErrorMap("No properties were provided"), HttpStatus.BAD_REQUEST);
        }
        logger.info(String.format("Updating %d merchant group properties for merchantGroupId %d", properties.size(), mgid));
        List<AggregatedProperty> result = merchantGroupPropertyService.addOrUpdateMerchantGroupProperties(mgid, properties);
        if(result == null || result.isEmpty()) {
            return new ResponseEntity<>(helper.createErrorMap("Problem updating merchant group property"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Creates or updates one or more merchant properties.
     * @param mid
     * @param properties
     * @return
     */
//    @DatasourceRouting(target= Target.Master)
    @RequestMapping(path = "/m{mid}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateForMerchant(@PathVariable("mid") Long mid,
                                                    @RequestBody List<AggregatedProperty> properties) {
        if(properties == null || properties.isEmpty()) {
            return new ResponseEntity<>(helper.createErrorMap("No properties were provided"), HttpStatus.BAD_REQUEST);
        }
        logger.info(String.format("Updating %d merchant  properties for merchantId %d", properties.size(), mid));
        List<AggregatedProperty> result = merchantPropertyService.addOrUpdateMerchantProperties(mid, properties);
        if(result == null || result.isEmpty()) {
            return new ResponseEntity<>(helper.createErrorMap("Problem updating merchant group property"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public void handleIllegalArgumentException(Exception e, HttpServletResponse httpResponse) {
        logger.error(e);
        try {
            httpResponse.getWriter().write(helper.createErrorJson(e.getMessage()));
        } catch (IOException e1) {
            throw new RuntimeException("Error writing HttpResponse.", e1);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletResponse httpResponse) {
        logger.error(e);
        try {
            String error = String.format("Invalid input type. Expecting %s", e.getRequiredType().getSimpleName());
            httpResponse.getWriter().write(helper.createErrorJson(error));
        } catch (IOException e1) {
            throw new RuntimeException("Error writing HttpResponse.", e1);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleBadDataTypeException(Exception e, HttpServletResponse httpResponse) {
        logger.error(e);
        try {
            httpResponse.getWriter().write(helper.createErrorJson(e.getMessage()));
        }catch (IOException e1) {
            throw new RuntimeException("Error writing HttpResponse.", e1);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleBadRequestException(HttpMessageNotReadableException e, HttpServletResponse httpServletResponse) {
        logger.error(e);
        try {
            if(e instanceof HttpMessageNotReadableException) {
                httpServletResponse.getWriter().write(helper.createErrorJson("Unable to parse RequestBody."));
            }else {
                httpServletResponse.getWriter().write(helper.createErrorJson(e.getMessage()));
            }
        }catch (IOException e1) {
            throw new RuntimeException("Error writing HttpResponse", e1);
        }
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({JsonProcessingException.class,IllegalStateException.class,Exception.class})
    public void handleInternalServerError(Exception e, HttpServletResponse httpResponse) {
        logger.error(e);
        try {
            httpResponse.getWriter().write(helper.createErrorJson(e.getMessage()));
        }catch (IOException e1) {
            throw new RuntimeException("Error writing HttpResponse.", e1);
        }
    }
}
