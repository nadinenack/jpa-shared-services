package com.powerreviews.services;

import com.powerreviews.controllers.pojos.AggregatedProperty;
import com.powerreviews.entities.Provider;
import com.powerreviews.entities.ProviderProperty;
import com.powerreviews.repositories.ProviderPropertyRepository;
import com.powerreviews.repositories.ProviderRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nadinenack on 9/24/16.
 */
@Service
public class ProviderPropertyService {

    @Resource
    private ProviderPropertyRepository providerPropertyRepository;

    @Resource
    private ProviderRepository providerRepository;

    private static final Logger logger = Logger.getLogger(PropertyConfigService.class);

    public AggregatedProperty getByProviderAndKey(final Provider provider, final String key) {
        ProviderProperty providerProperty = providerPropertyRepository.findByProviderAndKey(provider, key);
        if(providerProperty == null) {
            return null;
        }
        return processProviderProperty(providerProperty);
    }

    /**
     * Gets provider properties. Does not get default values
     * @param providerId
     * @param keys
     * @return
     */
    public Map<String, AggregatedProperty> getByProviderAndKey(Long providerId, Set<String> keys) {
        Provider provider = providerRepository.findOne(providerId);
        return getByProviderAndKey(provider, keys);
    }

    /**
     * Gets provider properties. Does not get default values
     * @param provider
     * @param keys
     * @return
     */
    public Map<String, AggregatedProperty> getByProviderAndKey(Provider provider, Set<String> keys) {
        if(!keys.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Getting %d for provider id %d", keys.size(), provider.getId()));
            }
            List<ProviderProperty> propertiesList = providerPropertyRepository.findByProviderAndKeys(provider, keys);
            return processProviderProperties(propertiesList);
        }
        logger.debug("No keys provided for method");
        return new HashMap<>();
    }

    private Map<String, AggregatedProperty> processProviderProperties(final List<ProviderProperty> propertyList) {
        Map<String, AggregatedProperty> propertyMap = new HashMap<>();
        for(ProviderProperty property : propertyList) {
            AggregatedProperty newProperty = processProviderProperty(property);
            propertyMap.put(property.getKey(), newProperty);
        }
        return propertyMap;
    }

    private AggregatedProperty processProviderProperty(final ProviderProperty property) {
        AggregatedProperty newProperty = new AggregatedProperty();
        newProperty.setKey(property.getKey());
        newProperty.setValue(property.getValue());

        return newProperty;
    }
}
