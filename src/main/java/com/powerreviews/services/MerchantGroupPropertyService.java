package com.powerreviews.services;

import com.powerreviews.entities.Config;
import org.springframework.stereotype.Service;
import com.powerreviews.repositories.ConfigRepository;

import javax.annotation.Resource;

/**
 * Created by nadinenack on 9/23/16.
 */
@Service
public class MerchantGroupPropertyService {

//    @Autowired
//    private ProviderPropertyService providerPropertyService;
//
//    @Autowired
//    private PropertyConfigService configService;
//
//    @Resource
//    private MerchantGroupPropertyRepository merchantGroupPropertyRepository;
//
//    @Resource
//    private MerchantGroupRepository merchantGroupRepository;

    @Resource
    private ConfigRepository configRepository;

    public Config getConfig() {
        return configRepository.findByKey("ALLOW_CUSTOMER_IMAGES");
    }

//    @Resource
//    private PropertyHelper helper;
//
//    @Resource
//    private MerchantPropertyChangeLogRepository changeLogRepository;
//    private static final Logger logger = Logger.getLogger(MerchantGroupPropertyService.class);
//
//    /**
//     * Gets all properties for a given merchant group id. Does not return default config or provider property values.
//     * @param merchantGroupId
//     * @return
//     */
//    public Map<String, AggregatedProperty> getByMerchantGroup(final Long merchantGroupId) {
//        if (logger.isDebugEnabled()) {
//            logger.debug(String.format("Getting all properties for merchant group id %d.", merchantGroupId));
//        }
//
//        final MerchantGroup merchantGroup = merchantGroupRepository.findOne(merchantGroupId);
//        List<MerchantGroupProperty> properties = merchantGroupPropertyRepository.findByMerchantGroup(merchantGroup);
//        Map<String, AggregatedProperty> result = createPropertyMap(properties);
//        List<Config> configs = configService.getConfigValues(result.keySet());
//        if(configs != null)
//            updatePropertiesWithConfigs(result, configs);
//        return result;
//    }
//
//    /**
//     * Gets properties with given keys for given merchant group id. Does not return default config or provider property
//     * values.
//     * @param merchantGroupId
//     * @param keys
//     * @return
//     */
//    public Map<String, AggregatedProperty> getByMerchantGroupAndKey(final Long merchantGroupId, final Set<String> keys) {
//        if(keys.isEmpty()) {
//            return getByMerchantGroup(merchantGroupId);
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug(String.format("Getting %d properties for merchant group id %d.", keys.size(), merchantGroupId));
//        }
//
//        final MerchantGroup merchantGroup = merchantGroupRepository.findOne(merchantGroupId);
//        Map<String, AggregatedProperty> result = getByMerchantGroupAndKey(merchantGroup, keys);
//        List<Config> configs = configService.getConfigValues(result.keySet());
//        if(configs != null)
//            updatePropertiesWithConfigs(result, configs);
//        return result;
//    }
//
//    /**
//     * Gets properties with given keys for given merchant group. Does not return default config or provider property
//     * values.
//     * @param merchantGroup
//     * @param keys
//     * @return
//     */
//    public Map<String, AggregatedProperty> getByMerchantGroupAndKey(final MerchantGroup merchantGroup, final Set<String> keys) {
//        if(keys.isEmpty()) {
//            logger.debug("No keys provided for method.");
//            return new HashMap<>();
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug(String.format("Getting %d properties for merchant group id %d.", keys.size(), merchantGroup.getMerchantGroupId()));
//        }
//        final List<MerchantGroupProperty> propertyList = merchantGroupPropertyRepository.findByMerchantGroupAndKeyIn(merchantGroup, keys);
//
//        Map<String, AggregatedProperty> propertyMap = createPropertyMap(propertyList);
//
//        List<Config> configs = configService.getConfigValues(propertyMap.keySet());
//        for(Config config : configs) {
//            if (logger.isDebugEnabled())
//                logger.debug("Adding config table values to map");
//            AggregatedProperty property = propertyMap.get(config.getKey());
//            if(property == null) {
//                logger.warn(String.format("Invalid database configuration for property with key %s. Check if property is in the config table.", property.getKey()));
//                throw new IllegalStateException(String.format("Invalid database configuration for property with key %s.", property.getKey()));
//            }
//            property.setDataType(config.getFieldType());
//            property.setPossibleValues(configService.getPossibleValues(config));
//        }
//
//        return propertyMap;
//    }
//
//    /**
//     * Gets properties with given keys for a merchant group id. If no merchant group property is set for a key, the
//     * provider property is returned. If no provider property is available for a key, the default config property value
//     * is returned.
//     * @param merchantGroupId
//     * @param keys
//     * @return
//     */
//    public Map<String, AggregatedProperty> getByMerchantGroupAndKeyWithDefaults(final Long merchantGroupId, final Set<String> keys) {
//        if(keys.isEmpty()) {
//            logger.debug("No keys provided for method.");
//            return new HashMap<>();
//        }
//        final MerchantGroup merchantGroup = merchantGroupRepository.findOne(merchantGroupId);
//        if(merchantGroup == null) {
//            if (logger.isDebugEnabled()) {
//                logger.debug(String.format("No merchant group exists with id %d", merchantGroupId));
//            }
//            throw new IllegalArgumentException(String.format("No merchant group exists with given id %d", merchantGroupId));
//        }
//        return getByMerchantGroupAndKeyWithDefaults(merchantGroup, keys);
//    }
//
//    /**
//     * Gets properties with given keys for specified merchant group. If no merchant group property is set for a key, the
//     * provider property is returned. If no provider property is available for a key, the default config property value
//     * is returned.
//     * @param merchantGroup
//     * @param keys
//     * @return
//     */
//    public Map<String, AggregatedProperty> getByMerchantGroupAndKeyWithDefaults(final MerchantGroup merchantGroup, final Set<String> keys) {
//        if(keys.isEmpty()) {
//            logger.debug("No keys provided for method.");
//            return new HashMap<>();
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug(String.format("Getting %d properties for merchant group id %d", keys.size(), merchantGroup.getMerchantGroupId()));
//        }
//        Map<String, AggregatedProperty> allProperties = new HashMap<>();
//
//        final List<Config> configProperties = configRepository.findByKeyIn(keys);
//        addConfigPropertiesToList(allProperties, configProperties);
//
//        final List<MerchantGroupProperty> merchantGroupProperties =
//                merchantGroupPropertyRepository.findByMerchantGroupAndKeyIn(merchantGroup, keys);
//        Set<String> keysToBeProcessed = new HashSet<>(keys);
//        keysToBeProcessed = processMerchantGroupProperties(allProperties, merchantGroupProperties, keysToBeProcessed);
//
//        final Provider provider = merchantGroup.getProvider();
//        final Map<String, AggregatedProperty> providerProperties =
//                providerPropertyService.getByProviderAndKey(provider, keysToBeProcessed);
//        addPropertiesToMap(allProperties, providerProperties);
//
//        return allProperties;
//    }
//
//    /**
//     * Updates or adds a list of new properties. Tracks the change in the merchant property change log table.
//     * @param merchantGroupId
//     * @param properties
//     * @return
//     */
//    @Transactional
//    public List<AggregatedProperty> addOrUpdateMerchantGroupProperties(Long merchantGroupId, List<AggregatedProperty> properties) {
//        MerchantGroup merchantGroup = merchantGroupRepository.findOne(merchantGroupId);
//        if(merchantGroup == null) {
//            throw new IllegalArgumentException(String.format("Unable to locate merchant group with id %d", merchantGroupId));
//        }
//        List<AggregatedProperty> result = new ArrayList<>();
//        for(AggregatedProperty property : properties) {
//            result.add(addOrUpdateMerchantGroupProperty(merchantGroup, property));
//        }
//        if(logger.isDebugEnabled()) {
//            logger.debug(String.format("Created or updated %d merchant group properties", result.size()));
//        }
//        return result;
//    }
//
//    /**
//     * Updates or adds a new property. Tracks the change in the merchant property change log table.
//     * @param merchantGroup
//     * @param aggregatedProperty
//     * @return
//     */
//    @Transactional
//    public AggregatedProperty addOrUpdateMerchantGroupProperty(MerchantGroup merchantGroup, AggregatedProperty aggregatedProperty) {
//        AggregatedProperty newAggregatedProperty = null;
//        Config config = configService.getConfigValue(aggregatedProperty.getKey());
//        if(config == null) {
//            throw new IllegalArgumentException(String.format("Unable to locate default config with key %s", aggregatedProperty.getKey()));
//        }
//
//        Optional<String> errorResponse = helper.verifyDataType(aggregatedProperty, config);
//        if(errorResponse.isPresent()) {
//            throw new IllegalArgumentException(errorResponse.get());
//        }
//        MerchantGroupProperty existingProperty = merchantGroupPropertyRepository.findByMerchantGroupAndKey(merchantGroup, aggregatedProperty.getKey());
//        if(existingProperty != null) {
//            newAggregatedProperty = update(existingProperty, aggregatedProperty, merchantGroup);
//        }else {
//            newAggregatedProperty = create(aggregatedProperty, merchantGroup);
//        }
//        newAggregatedProperty.setPossibleValues(configService.getPossibleValues(config));
//        newAggregatedProperty.setDataType(config.getFieldType());
//        return newAggregatedProperty;
//    }
//
//    /**
//     * Create a merchant group property with specified key and value. Change is tracked in the merchant property change
//     * log table.
//     * @param merchantGroup
//     * @param key
//     * @param value
//     * @return
//     */
//    @Transactional
//    public AggregatedProperty createProperty(final MerchantGroup merchantGroup, final String key, final String value) {
//        MerchantGroupProperty property = new MerchantGroupProperty();
//        property.setKey(key);
//        property.setValue(value);
//        property.setMerchantGroup(merchantGroup);
//        property = merchantGroupPropertyRepository.save(property);
//        if(property == null || property.getMerchantGroupPropertyId() == null) {
//            throw new IllegalStateException("Unable to save merchant group property");
//        }
//
//        AggregatedProperty aggregatedProperty = createAggregatedProperty(property);
//        createChangeLogEntry(aggregatedProperty, null, merchantGroup);
//
//        return aggregatedProperty;
//    }
//
//    /**
//     * Checks if a merchant group has any merchant group properties associated with it. Returns true if at least one
//     * merchant group property exists for the MerchantGroup. Returns false if no merchant group properties exists
//     * @param merchantGroup
//     * @return
//     */
//    public boolean merchantGroupHasExistingProperties(final MerchantGroup merchantGroup) {
//        return merchantGroupPropertyRepository.findFirstByMerchantGroup(merchantGroup).isPresent();
//    }
//
//    AggregatedProperty create(AggregatedProperty aggregatedProperty, final MerchantGroup merchantGroup) {
//        if(logger.isDebugEnabled()) {
//            logger.debug(String.format("Creating new merchant group property with key [%s] for merchant group id [%d]",
//                    aggregatedProperty.getKey(), merchantGroup.getMerchantGroupId()));
//        }
//        MerchantGroupProperty newProperty = createMerchantGroupProperty(merchantGroup, aggregatedProperty);
//        AggregatedProperty newAggregatedProperty = createAggregatedProperty(newProperty);
//        createChangeLogEntry(aggregatedProperty, null, merchantGroup);
//        return newAggregatedProperty;
//    }
//
//    AggregatedProperty update(final MerchantGroupProperty existingProperty, final AggregatedProperty aggregatedProperty,
//                              final MerchantGroup merchantGroup) {
//        if(logger.isDebugEnabled()) {
//            logger.debug(String.format("Merchant group property already exists with key [%s] for merchant group id [%d]. Updating value.",
//                    aggregatedProperty.getKey(), merchantGroup.getMerchantGroupId()));
//        }
//        String oldValue = existingProperty.getValue();
//        existingProperty.setValue(aggregatedProperty.getValue());
//        merchantGroupPropertyRepository.save(existingProperty);
//
//        AggregatedProperty newAggregatedProperty = createAggregatedProperty(existingProperty);
//        createChangeLogEntry(aggregatedProperty, oldValue, merchantGroup);
//
//        return newAggregatedProperty;
//    }
//
//    void createChangeLogEntry(AggregatedProperty newProperty, String oldValue, MerchantGroup merchantGroup) {
//        logger.debug("Creating merchant group property change log entry");
//        MerchantPropertyChangeLog changeLog = new MerchantPropertyChangeLog();
//        changeLog.setKey(newProperty.getKey());
//        changeLog.setCreatedDate(new Timestamp(new Date().getTime()));
//        changeLog.setType(MerchantPropertyChangeLog.PropertyType.MerchantGroup);
//        changeLog.setMerchantGroup(merchantGroup);
//        changeLog.setKey(newProperty.getKey());
//        changeLog.setOldValue(oldValue);
//        changeLog.setNewValue(newProperty.getValue());
//        changeLog.setSource(MerchantGroupPropertyService.class.getSimpleName());
//        changeLogRepository.save(changeLog);
//    }
//
//    private MerchantGroupProperty createMerchantGroupProperty(MerchantGroup merchantGroup, AggregatedProperty aggregatedProperty) {
//        Config configProperty = configRepository.findByKey(aggregatedProperty.getKey());
//        if(configProperty == null) {
//            throw new IllegalArgumentException(String.format("No default Config value exists for key %s", aggregatedProperty.getKey()));
//        }
//        if(!canOverrideAtThisLevel(configProperty)) {
//            throw new IllegalArgumentException(String.format("Property with key %s isn't allowed to be overridden at merchant group level",
//                    aggregatedProperty.getKey()));
//        }
//        MerchantGroupProperty property = new MerchantGroupProperty();
//        property.setKey(aggregatedProperty.getKey());
//        property.setMerchantGroup(merchantGroup);
//        property.setValue(aggregatedProperty.getValue());
//
//        return merchantGroupPropertyRepository.save(property);
//    }
//
//    private boolean canOverrideAtThisLevel(Config config) {
//        return config.getOverrideLevel().equals(Config.OverrideLevel.merchantGroup)
//                || config.getOverrideLevel().equals(Config.OverrideLevel.merchant);
//    }
//
//    private AggregatedProperty createAggregatedProperty(MerchantGroupProperty merchantGroupProperty) {
//        AggregatedProperty aggregatedProperty = new AggregatedProperty();
//        aggregatedProperty.setKey(merchantGroupProperty.getKey());
//        aggregatedProperty.setValue(merchantGroupProperty.getValue());
//        return aggregatedProperty;
//    }
//
//    private void addConfigPropertiesToList(final Map<String, AggregatedProperty> properties, final List<Config> configProperties) {
//        for(final Config property : configProperties) {
//            AggregatedProperty aggregatedProperty = new AggregatedProperty();
//            aggregatedProperty.setKey(property.getKey());
//            aggregatedProperty.setValue(property.getValue());
//            aggregatedProperty.setDataType(property.getFieldType());
//            aggregatedProperty.setPossibleValues(configService.getPossibleValues(property));
//
//            properties.put(property.getKey(), aggregatedProperty);
//        }
//    }
//
//    private Map<String, AggregatedProperty> createPropertyMap(final List<MerchantGroupProperty> propertyList) {
//        Map<String, AggregatedProperty> propertyMap = new HashMap<>();
//        for(MerchantGroupProperty property : propertyList) {
//            AggregatedProperty newProperty = new AggregatedProperty();
//            newProperty.setKey(property.getKey());
//            newProperty.setValue(property.getValue());
//            propertyMap.put(property.getKey(), newProperty);
//        }
//        return propertyMap;
//    }
//
//    private Set<String> processMerchantGroupProperties(final Map<String, AggregatedProperty> existingProperties,
//                                                       final List<MerchantGroupProperty> propertyList,
//                                                       Set<String> keysToBeFound) {
//        for(MerchantGroupProperty property : propertyList) {
//            AggregatedProperty existingProperty = existingProperties.get(property.getKey());
//            if(existingProperty == null) {
//                logger.warn(String.format("Invalid database configuration for property with key %s. Check if property is in the config table.", property.getKey()));
//                throw new IllegalStateException(String.format("Invalid database configuration for property with key %s.", property.getKey()));
//            }
//            existingProperty.setValue(property.getValue());
//            keysToBeFound.remove(property.getKey());
//        }
//        return keysToBeFound;
//    }
//
//    private void addPropertiesToMap(final Map<String, AggregatedProperty> existingProperties,
//                                    final Map<String, AggregatedProperty> newProperties) {
//        for(final AggregatedProperty newProperty : newProperties.values()) {
//            AggregatedProperty existingProperty = existingProperties.get(newProperty.getKey());
//            if(existingProperty == null) {
//                logger.warn(String.format("Invalid database configuration for property with key %s. Check if property is in the config table.", newProperty.getKey()));
//                throw new IllegalStateException(String.format("Invalid database configuration for property with key %s.", newProperty.getKey()));
//            }
//            existingProperty.setValue(newProperty.getValue());
//        }
//    }
//
//    private void updatePropertiesWithConfigs(Map<String, AggregatedProperty> propertyMap, List<Config> configs) {
//        for(Config config : configs) {
//            AggregatedProperty property = propertyMap.get(config.getKey());
//            property.setDataType(config.getFieldType());
//            property.setPossibleValues(configService.getPossibleValues(config));
//        }
//    }

}
