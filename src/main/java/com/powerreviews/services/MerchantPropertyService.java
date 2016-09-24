package com.powerreviews.services;

import com.powerreviews.controllers.pojos.AggregatedProperty;
import com.powerreviews.entities.*;
import com.powerreviews.repositories.ConfigRepository;
import com.powerreviews.repositories.MerchantPropertyChangeLogRepository;
import com.powerreviews.repositories.MerchantPropertyRepository;
import com.powerreviews.repositories.MerchantRepository;
import com.powerreviews.services.helpers.PropertyHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by nadinenack on 9/24/16.
 */
@Service
public class MerchantPropertyService {

    @Resource
    private MerchantPropertyRepository merchantPropertyRepository;

    @Autowired
    private MerchantGroupPropertyService merchantGroupPropertyService;

    @Autowired
    private ProviderPropertyService providerPropertyService;

    @Autowired
    private PropertyConfigService configService;

    @Resource
    private ConfigRepository configRepository;

    @Resource
    private MerchantRepository merchantRepository;

    @Resource
    private MerchantPropertyChangeLogRepository changeLogRepository;

    @Resource
    private PropertyHelper helper;

    private static final Logger logger = Logger.getLogger(MerchantPropertyService.class);

    /**
     * Gets all merchant properties for a specified merchant id. Does not get any defaults from the merchant group
     * property, provider property, or config values
     * @param merchantId
     * @return
     */
    public Map<String, AggregatedProperty> getByMerchant(Long merchantId) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Getting all merchant level properties for merchant id %d", merchantId));
        }
        Merchant merchant = merchantRepository.findOne(merchantId);
        List<MerchantProperty> properties = merchantPropertyRepository.findByMerchant(merchant);
        Map<String, AggregatedProperty> propertyMap =  createPropertyMap(properties);

        List<Config> configs = configService.getConfigValues(propertyMap.keySet());
        for(Config config : configs) {
            AggregatedProperty property = propertyMap.get(config.getKey());
            property.setDataType(config.getFieldType());
            property.setPossibleValues(configService.getPossibleValues(config));
        }

        return propertyMap;
    }

    /**
     * Gets properties for a merchant with specified keys. Does not get any defaults from the merchant group
     * property, provider property, or config values
     * @param merchantId
     * @param keys
     * @return
     */
    public Map<String, AggregatedProperty> getByMerchantAndKey(final Long merchantId, final Set<String> keys) {
        if(keys.isEmpty()) {
            logger.debug("No keys provided for method.");
            return new HashMap<>();
        }

        final Merchant merchant = merchantRepository.findOne(merchantId);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Getting %d merchant properties without defaults for merchant id %d",
                    keys.size(), merchant.getMerchantId()));
        }

        List<MerchantProperty> properties = merchantPropertyRepository.findByMerchant(merchant);
        Map<String, AggregatedProperty> propertyMap = new HashMap<>();
        for(MerchantProperty property : properties) {
            AggregatedProperty aggregatedProperty = new AggregatedProperty();
            aggregatedProperty.setKey(property.getKey());
            aggregatedProperty.setValue(property.getValue());
            propertyMap.put(aggregatedProperty.getKey(), aggregatedProperty);
        }

        List<Config> configs = configService.getConfigValues(propertyMap.keySet());
        for(Config config : configs) {
            AggregatedProperty property = propertyMap.get(config.getKey());
            property.setDataType(config.getFieldType());
            property.setPossibleValues(configService.getPossibleValues(config));
        }

        return propertyMap;
    }

    /**
     * Gets properties by key for merchant id. If a merchant property is not set for a key, this returns the
     * merchant group property, provider property, or config property, whichever can be found first, in that order.
     * @param merchantId
     * @param keys
     * @return
     */
    public Map<String, AggregatedProperty> getByMerchantAndKeyWithDefaults(final Long merchantId, final Set<String> keys) {
        if(keys.isEmpty()) {
            logger.debug("No keys provided for method");
            return new HashMap<>();
        }
        final Merchant merchant = merchantRepository.findOne(merchantId);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Getting %d properties for merchant id %d", keys.size(), merchant.getMerchantId()));
        }

        final MerchantGroup merchantGroup = merchant.getMerchantGroup();
        final Provider provider = merchantGroup.getProvider();

        Map<String, AggregatedProperty> allProperties = new HashMap<>();

        final List<Config> configProperties = configRepository.findByKeyIn(keys);
        addConfigPropertiesToList(configProperties, allProperties);

        Set<String> keysToBeProcessed = new HashSet<>(keys);
        final List<MerchantProperty> merchantProperties = merchantPropertyRepository.findByMerchantAndKeyIn(merchant, keys);
        keysToBeProcessed = processMerchantProperties(allProperties, merchantProperties, keysToBeProcessed);

        final Map<String, AggregatedProperty> merchantGroupProperties =
                merchantGroupPropertyService.getByMerchantGroupAndKey(merchantGroup, keysToBeProcessed);
        addPropertiesToMap(allProperties, merchantGroupProperties);
        keysToBeProcessed.removeAll(merchantGroupProperties.keySet());

        final Map<String, AggregatedProperty> providerProperties =
                providerPropertyService.getByProviderAndKey(provider, keysToBeProcessed);
        addPropertiesToMap(allProperties, providerProperties);

        if(logger.isDebugEnabled()) {
            logger.debug(String.format("Found %d properties for merchant id %d, merchant group id %d, provider id %d, and the default system level",
                    allProperties.size(), merchant.getMerchantId(), merchantGroup.getMerchantGroupId(), provider.getId()));
        }
        return allProperties;
    }

    /**
     * Creates or updates one or more merchant properties
     * @param mid
     * @param aggregatedProperties
     * @return
     */
    @Transactional
    public List<AggregatedProperty> addOrUpdateMerchantProperties(Long mid, List<AggregatedProperty> aggregatedProperties) {
        Merchant merchant = merchantRepository.findOne(mid);
        if(merchant == null) {
            throw new IllegalArgumentException(String.format("Unable to locate merchant with id %d", mid));
        }
        List<AggregatedProperty> result = new ArrayList<>();
        for(AggregatedProperty property : aggregatedProperties) {
            result.add(addOrUpdateMerchantProperty(merchant, property));
        }
        return result;
    }

    /**
     * Creates or updates a merchant property
     * @param merchant
     * @param aggregatedProperty
     * @return
     */
    @Transactional
    public AggregatedProperty addOrUpdateMerchantProperty(Merchant merchant, AggregatedProperty aggregatedProperty) {
        AggregatedProperty newAggregatedProperty = null;
        Config config = configService.getConfigValue(aggregatedProperty.getKey());
        if(config == null) {
            throw new IllegalArgumentException(String.format("Unable to locate default config with key %s", aggregatedProperty.getKey()));
        }
        Optional<String> errorResponse = helper.verifyDataType(aggregatedProperty, config);
        if(errorResponse.isPresent()) {
            throw new IllegalArgumentException(errorResponse.get());
        }

        MerchantProperty existingProperty = merchantPropertyRepository.findByMerchantAndKey(merchant, aggregatedProperty.getKey());
        if(existingProperty != null) {
            newAggregatedProperty = update(existingProperty, aggregatedProperty, merchant);
        }else {
            newAggregatedProperty = create(aggregatedProperty, merchant);
        }
        newAggregatedProperty.setPossibleValues(configService.getPossibleValues(config));
        newAggregatedProperty.setDataType(config.getFieldType());
        return newAggregatedProperty;
    }

    public boolean merchantHasExistingProperties(final Merchant merchant) {
        return merchantPropertyRepository.findFirstByMerchant(merchant).isPresent();
    }

    /**
     * Creates a new merchant property. Change is logged in the merchant property change log table
     * @param merchant
     * @param key
     * @param value
     * @return
     */
    @Transactional
    public AggregatedProperty createProperty(final Merchant merchant, final String key, final String value) {
        validate(merchant);
        validate(key);
        validate(value);

        MerchantProperty property = new MerchantProperty();
        property.setMerchant(merchant);
        property.setKey(key);
        property.setValue(value);

        merchantPropertyRepository.save(property);
        final AggregatedProperty aggregatedProperty = createAggregatedProperty(property);
        createChangeLogEntry(aggregatedProperty, null, merchant);

        return aggregatedProperty;
    }

    AggregatedProperty create(final AggregatedProperty aggregatedProperty, final Merchant merchant) {
        if(logger.isDebugEnabled()) {
            logger.debug(String.format("Creating new merchant property for merchant id [%d] and key [%s]",
                    merchant.getMerchantId(), aggregatedProperty.getKey()));
        }
        MerchantProperty property = createMerchantProperty(merchant, aggregatedProperty);
        property = merchantPropertyRepository.save(property);
        AggregatedProperty newAggregatedProperty = createAggregatedProperty(property);
        createChangeLogEntry(aggregatedProperty, null, merchant);
        return newAggregatedProperty;
    }

    AggregatedProperty update(final MerchantProperty existingProperty, final AggregatedProperty aggregatedProperty,
                              final Merchant merchant) {
        if(logger.isDebugEnabled()) {
            logger.debug(String.format("Found merchant property for merchant id [%d] and key [%s]. Updating existing.",
                    merchant.getMerchantId(), aggregatedProperty.getKey()));
        }
        String oldValue = existingProperty.getValue();
        existingProperty.setValue(aggregatedProperty.getValue());
        merchantPropertyRepository.save(existingProperty);

        AggregatedProperty newAggregatedProperty = createAggregatedProperty(existingProperty);
        createChangeLogEntry(aggregatedProperty, oldValue, merchant);

        return newAggregatedProperty;
    }

    void createChangeLogEntry(AggregatedProperty newProperty, String oldValue, Merchant merchant) {
        logger.debug("Creating merchant property change log entry");
        MerchantPropertyChangeLog changeLog = new MerchantPropertyChangeLog();
        changeLog.setKey(newProperty.getKey());
        changeLog.setCreatedDate(new Timestamp(new Date().getTime()));
        changeLog.setType(MerchantPropertyChangeLog.PropertyType.Merchant);
        changeLog.setMerchant(merchant);
        changeLog.setMerchantGroup(merchant.getMerchantGroup());
        changeLog.setKey(newProperty.getKey());
        if(oldValue != null) {
            changeLog.setOldValue(oldValue);
        }
        changeLog.setNewValue(newProperty.getValue());
        changeLog.setSource(MerchantPropertyService.class.getSimpleName());
        changeLogRepository.save(changeLog);
    }

    private MerchantProperty createMerchantProperty(Merchant merchant, AggregatedProperty aggregatedProperty) {
        Config config = configRepository.findByKey(aggregatedProperty.getKey());
        if(config == null) {
            throw new IllegalArgumentException(String.format("No default Config value exists for key %s", aggregatedProperty.getKey()));
        }
        if(!canOverrideAtThisLevel(config)) {
            throw new IllegalArgumentException(String.format("Property with key %s isn't allowed to be overridden at merchant level",
                    aggregatedProperty.getKey()));
        }
        MerchantProperty property = new MerchantProperty();
        property.setKey(aggregatedProperty.getKey());
        property.setMerchant(merchant);
        property.setValue(aggregatedProperty.getValue());

        return property;
    }

    private boolean canOverrideAtThisLevel(Config config) {
        return config.getOverrideLevel().equals(Config.OverrideLevel.merchant);
    }

    private AggregatedProperty createAggregatedProperty(MerchantProperty merchantProperty) {
        AggregatedProperty aggregatedProperty = new AggregatedProperty();
        aggregatedProperty.setKey(merchantProperty.getKey());
        aggregatedProperty.setValue(merchantProperty.getValue());
        return aggregatedProperty;
    }

    private Map<String, AggregatedProperty> createPropertyMap(final List<MerchantProperty> propertyList) {
        Map<String, AggregatedProperty> propertyMap = new HashMap<>();
        for(MerchantProperty property : propertyList) {
            AggregatedProperty aggregatedProperty = new AggregatedProperty();
            aggregatedProperty.setKey(property.getKey());
            aggregatedProperty.setValue(property.getValue());

            propertyMap.put(property.getKey(), aggregatedProperty);
        }
        return propertyMap;
    }

    private void addConfigPropertiesToList(final List<Config> configProperties, final Map<String, AggregatedProperty> properties) {
        for(final Config property : configProperties) {
            AggregatedProperty aggregatedProperty = new AggregatedProperty();
            aggregatedProperty.setKey(property.getKey());
            aggregatedProperty.setValue(property.getValue());
            aggregatedProperty.setDataType(property.getFieldType());
            aggregatedProperty.setPossibleValues(configService.getPossibleValues(property));

            properties.put(property.getKey(), aggregatedProperty);
        }
    }

    private Set<String> processMerchantProperties(final Map<String, AggregatedProperty> existingProperties,
                                                  final List<MerchantProperty> newProperties,
                                                  Set<String> keysToBeFound) {
        for(final MerchantProperty newProperty : newProperties) {
            AggregatedProperty existingProperty = existingProperties.get(newProperty.getKey());
            if(existingProperty == null) {
                logger.warn(String.format("Invalid database configuration for property with key %s. Check if property is in the config table.", newProperty.getKey()));
                throw new IllegalStateException(String.format("Invalid database configuration for property with key %s.", newProperty.getKey()));
            }
            existingProperty.setValue(newProperty.getValue());

            keysToBeFound.remove(newProperty.getKey());
        }

        return keysToBeFound;
    }

    private void addPropertiesToMap(final Map<String, AggregatedProperty> existingProperties,
                                    final Map<String, AggregatedProperty> newProperties) {
        for(final AggregatedProperty newProperty : newProperties.values()) {
            AggregatedProperty existingProperty = existingProperties.get(newProperty.getKey());
            if(existingProperty == null) {
                logger.warn(String.format("Invalid database configuration for property with key %s. Check if property is in the config table.", newProperty.getKey()));
                throw new IllegalStateException(String.format("Invalid database configuration for property with key %s.", newProperty.getKey()));
            }
            existingProperty.setValue(newProperty.getValue());
        }
    }

    private void validate(Merchant merchant) {
        if(merchant == null) {
            throw new IllegalArgumentException("Merchant is required for merchant properties");
        }
    }

    private void validate(String string) {
        if(StringUtils.isBlank(string)) {
            throw new IllegalArgumentException("Missing parameter required for merchant properties");
        }
    }

}
