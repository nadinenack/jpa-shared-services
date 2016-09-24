package com.powerreviews.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "config")
public class Config implements Serializable {
    // Order matters here (so the different access levels are comparable)
    //
    public static enum AccessLevel {
        merchant, merchantGroup, provider, superuser, none
    }

    public static final Long VISIBILITY_UNCOMMON = 80L;       // Known uncommon configs (will not be shown initially in Dashboard UI)
    public static final Long VISIBILITY_DEFAULT = 100L;       // Unclassified / default (will not be shown initially in Dashboard UI)
    public static final Long VISIBILITY_COMMON = 150L;       // Commonly used configs (shown in Dashboard UI by default)

    private Long id;
    private String key;
    private String value;
    private boolean merchantVisible;
    private AccessLevel editableBy;
    private String fieldType;
    private long sortIndex;
//    private ConfigGroup configGroup;
    private String description;
    private OverrideLevel overrideLevel;
    private Long visibilityIndex;

    public static final String CONFIG_BASE_FOLLOW_LANDING_PAGE_URL = "BASE_FOLLOW_LANDING_PAGE_URL";
    public static final String CONFIG_PUFFERFISH_SUBMIT_URL = "PUFFERFISH_SUBMIT_URL";
    public static final String CONFIG_JS_CONTROLLER_URL = "JS_CONTROLLER_URL";
    public static final String CONFIG_CLEAR_DB_CACHE_URL = "CLEAR_DB_CACHE_URL";

    public static final String CONFIG_SEND_EMAILS = "SEND_EMAILS";
    public static final String CONFIG_MAIL_SENDER_CREDENTIAL_KEY = "MAIL_SENDER_CREDENTIAL_KEY";
    public static final String CONFIG_PUFFERFISH_ERROR_CONTACT = "PUFFERFISH_ERROR_CONTACT";
    public static final String CONFIG_SUPPORT_PAGER_EMAIL = "SUPPORT_PAGER_EMAIL";
    public static final String CONFIG_IMAGE_REPOSITORY_DIR = "IMAGE_REPOSITORY_DIR";
    public static final String CONFIG_IMAGE_WEB_SERVER = "IMAGE_WEB_SERVER";
    public static final String CONFIG_MOBILE_WRITE_A_REVIEW_JS_CSS_WEB_DIR = "MOBILE_WRITE_A_REVIEW_JS_CSS_WEB_DIR";
    public static final String CONFIG_GENERATOR_REPOSITORY_DIR = "GENERATOR_REPOSITORY_DIR";
    public static final String CONFIG_SYNDICATOR_REPOSITORY_DIR = "SYNDICATOR_REPOSITORY_DIR";
    public static final String CONFIG_ZIP_REPOSITORY_DIR = "ZIP_REPOSITORY_DIR";
    public static final String CONFIG_IS_GIFT_FINDER_ENABLED = "IS_GIFT_FINDER_ENABLED";
    public static final String CONFIG_APP_ENVIRONMENT = "APP_ENVIRONMENT";
    public static final String CONFIG_LINKSHARE_FEED_TOKEN = "LINKSHARE_FEED_TOKEN";
    public static final String CONFIG_GAS_SPEEDUP_1 = "GAS_SPEEDUP_1";

    // global ad kill switches
    public static final String CONFIG_ENABLE_SHOPZILLA = "ENABLE_SHOPZILLA";
    public static final String CONFIG_ENABLE_ACTIVE = "ENABLE_ACTIVE";
    public static final String CONFIG_ENABLE_SPORTGENIC = "ENABLE_SPORTGENIC";
    public static final String CONFIG_ENABLE_RIGHTMEDIA = "ENABLE_RIGHTMEDIA";
    public static final String CONFIG_ENABLE_VERTICAL_SPONSORSHIPS = "ENABLE_VERTICAL_SPONSORSHIPS";
    public static final String CONFIG_ENABLE_VALUECLICK = "ENABLE_VALUECLICK";
    public static final String CONFIG_ENABLE_GOOGLE_ADSENSE = "ENABLE_GOOGLE_ADSENSE";

    public static final String CONFIG_PRODUCT_ANSWERS_UNSUBSCRIBE_URL = "PRODUCT_ANSWERS_UNSUBSCRIBE_URL";
    public static final String CONFIG_PRODUCT_ANSWERS_APO_PREFERENCE_URI = "PRODUCT_ANSWERS_APO_PREFERENCE_URI";

    // todo: get rid of parature configs when no longer need them
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_SAML_POST_URL = "SALESFORCE_SINGLE_SIGN_ON_SAML_POST_URL";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_KEY_PASSWORD = "SALESFORCE_SINGLE_SIGN_ON_KEY_PASSWORD";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_KEY_ALIAS = "SALESFORCE_SINGLE_SIGN_ON_KEY_ALIAS";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_ORGANIZATION_ID = "SALESFORCE_SINGLE_SIGN_ON_ORGANIZATION_ID";

    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_PORTAL_ID_ENTERPRISE = "SALESFORCE_SINGLE_SIGN_ON_PORTAL_ID_ENTERPRISE";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_PORTAL_USER_PROFILE_ID_ENTERPRISE = "SALESFORCE_SINGLE_SIGN_ON_PORTAL_USER_PROFILE_ID_ENTERPRISE";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_PORTAL_ID_EXPRESS = "SALESFORCE_SINGLE_SIGN_ON_PORTAL_ID_EXPRESS";
    public static final String CONFIG_SALESFORCE_SINGLE_SIGN_ON_PORTAL_USER_PROFILE_ID_EXPRESS = "SALESFORCE_SINGLE_SIGN_ON_PORTAL_USER_PROFILE_ID_EXPRESS";

    public static final String CONFIG_SALESFORCE_SUPPORT_ON = "SALESFORCE_SUPPORT_ON";
    public static final String CONFIG_ZENDESK_SUPPORT_ON = "ZENDESK_SUPPORT_ON";

    public static final String CONFIG_SELFSERVICE_CYBERSOURCE_ID = "SELFSERVICE_CYBERSOURCE_ID";
    public static final String CONFIG_SELFSERVICE_CYBERSOURCE_SEND_TO_PRODUCTION = "SELFSERVICE_CYBERSOURCE_SEND_TO_PRODUCTION";

    public static final String CONFIG_SELFSERVICE_TWITTER_CONSUMER_KEY = "SELFSERVICE_TWITTER_CONSUMER_KEY";
    public static final String CONFIG_SELFSERVICE_TWITTER_CONSUMER_SECRET = "SELFSERVICE_TWITTER_CONSUMER_SECRET";
    public static final String CONFIG_SELFSERVICE_TWITTER_ACCESS_TOKEN = "SELFSERVICE_TWITTER_ACCESS_TOKEN";
    public static final String CONFIG_SELFSERVICE_TWITTER_ACCESS_TOKEN_SECRET = "SELFSERVICE_TWITTER_ACCESS_TOKEN_SECRET";

    public static final String CONFIG_BITLY_USERNAME = "BITLY_USERNAME";
    public static final String CONFIG_BITLY_API_KEY = "BITLY_API_KEY";

    public static final String CONFIG_REVIEW_CHAMPION_SLI_FTP_SERVERNAME = "REVIEW_CHAMPION_SLI_FTP_SERVERNAME";
    public static final String CONFIG_REVIEW_CHAMPION_SLI_FTP_USERNAME = "REVIEW_CHAMPION_SLI_FTP_USERNAME";
    public static final String CONFIG_REVIEW_CHAMPION_SLI_FTP_PASSWORD = "REVIEW_CHAMPION_SLI_FTP_PASSWORD";

    public static final String CONFIG_REVIEW_CHAMPION_SLI_API_URL = "REVIEW_CHAMPION_SLI_API_URL";
    public static final String CONFIG_REVIEW_CHAMPION_SLI_API_USERNAME = "REVIEW_CHAMPION_SLI_API_USERNAME";
    public static final String CONFIG_REVIEW_CHAMPION_SLI_API_PASSWORD = "REVIEW_CHAMPION_SLI_API_PASSWORD";
    public static final String CONFIG_REVIEW_CHAMPION_MERCHANT_URL = "REVIEW_CHAMPION_MERCHANT_URL";
    public static final String CONFIG_REVIEW_CHAMPION_MERCHANT_USERNAME = "REVIEW_CHAMPION_MERCHANT_USERNAME";
    public static final String CONFIG_REVIEW_CHAMPION_MERCHANT_PASSWORD = "REVIEW_CHAMPION_MERCHANT_PASSWORD";
    public static final String CONFIG_REVIEW_CHAMPION_SLI_STATS_API_URL = "REVIEW_CHAMPION_SLI_STATS_API_URL";

    public static final String CONFIG_GOOGLE_REVIEW_EXPORTER_HTTP_ACCESSIBLE_SERVER = "GOOGLE_REVIEW_EXPORTER_HTTP_ACCESSIBLE_SERVER";
    public static final String CONFIG_GOOGLE_REVIEW_EXPORTER_HTTP_ACCESSIBLE_DIRECTORY = "GOOGLE_REVIEW_EXPORTER_HTTP_ACCESSIBLE_DIRECTORY";

    public static final String CONFIG_THRIFT_HOST_FOR_PFD_API = "THRIFT_HOST_FOR_PFD_API";
    public static final String CONFIG_API_READ_REVIEW_BASE_URL = "API_READ_REVIEW_BASE_URL";
    public static final String CONFIG_API_WRITE_BASE_URL = "API_WRITE_BASE_URL";
    public static final String CONFIG_UI_LIBRARY_BASE_URL = "UI_LIBRARY_BASE_URL";
    public static final String CONFIG_API_WRITE_ACCOUNT_BASE_URL = "API_WRITE_ACCOUNT_BASE_URL";

    public static final String CONFIG_ENABLE_REPORTING20 = "ENABLE_REPORTING20";
    public static final String CONFIG_SHOW_TOPLINE_AND_DETAILED_REPORT = "SHOW_TOPLINE_AND_DETAILED_REPORT";

    public static final String FIELD_TYPE_EMAIL = "email";
    public static final String FIELD_TYPE_TEXT = "text";
    public static final String FIELD_TYPE_TEXTAREA = "textarea";
    public static final String FIELD_TYPE_BOOLEAN = "boolean";
    public static final String FIELD_TYPE_WEEKLY_RECCURING = "weekly recurring";

    public static final String FIELD_TYPE_DEFINED_CLASS = "defined class";

    public static final String CONFIG_APP_ENVIRONMENT_PROD = "prod";
    public static final String CONFIG_APP_ENVIRONMENT_UAT = "uat";

    public static final String DEFAULT_SELF_SERVICE_SYNDICATION_GROUP = "DEFAULT_SELF_SERVICE_SYNDICATION_GROUP";
    public static final String CONFIG_FLAGGED_REVIEW_ERROR_CONTACT = "FLAGGED_REVIEW_ERROR_CONTACT";

    public static final String PIVOTLINK_FTP_SERVER = "PIVOTLINK_FTP_SERVER";
    public static final String PIVOTLINK_FTP_USERNAME = "PIVOTLINK_FTP_USERNAME";
    public static final String PIVOTLINK_FTP_PASSWORD = "PIVOTLINK_FTP_PASSWORD";
    public static final String PIVOTLINK_FTP_UPLOAD_DIR = "PIVOTLINK_FTP_UPLOAD_DIR";
    public static final String PIVOTLINK_REPORT_SERVER_URL = "PIVOTLINK_REPORT_SERVER_URL";

    // Email Parser Regex
    public static final String CONFIG_EMAIL_REPLY_PARSER_EMAIL_ADDRESS_REGEX = "EMAIL_REPLY_PARSER_EMAIL_ADDRESS_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_QUOTE_SYMBOL_REGEX = "EMAIL_REPLY_PARSER_QUOTE_SYMBOL_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_EMAIL_CLIENT_INJECTED_REGEX = "EMAIL_REPLY_PARSER_EMAIL_CLIENT_INJECTED_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_SPECIAL_CHARACTERS_REGEX = "EMAIL_REPLY_PARSER_SPECIAL_CHARACTERS_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_INJECTED_SEPARATOR_REGEX = "EMAIL_REPLY_PARSER_INJECTED_SEPARATOR_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_RESERVED_REGEX = "EMAIL_REPLY_PARSER_RESERVED_REGEX";
    public static final String CONFIG_EMAIL_REPLY_PARSER_RESERVED_REPLACEMENT = "EMAIL_REPLY_PARSER_RESERVED_REPLACEMENT";
    public static final String CONFIG_EMAIL_REPLY_PARSER_CUTOFF_REGEX = "EMAIL_REPLY_PARSER_CUTOFF_REGEX";

    public static final String CONFIG_MAX_NUMBER_DMLS_AT_ONCE = "MAX_NUMBER_DMLS_AT_ONCE";
    public static final String CONFIG_PAUSE_SECONDS_AFTER_MAX_DML = "PAUSE_SECONDS_AFTER_MAX_DML";

    public static final String CONFIG_ORDER_DATA_IMPORTER_HTTP_USER_AGENT = "ORDER_DATA_IMPORTER_HTTP_USER_AGENT";

    public static final String CONFIG_CDN_ORIGIN_ENVIRONMENT = "CDN_ORIGIN_ENVIRONMENT";

    public static final String CONFIG_DASHBOARD_BASE_URL = "DASHBOARD_BASE_URL";

    public static final String CONFIG_ENABLE_BV_THIRD_PARTY_COOKIE = "ENABLE_BV_THIRD_PARTY_COOKIE";
    public static final String CONFIG_SUPPORT_FILES_DIRECTORY = "SUPPORT_FILES_DIRECTORY";
    public static final String CONFIG_ANSWERBOX_ENABLE_PRE_QUESTION = "ANSWERBOX_ENABLE_PRE_QUESTION";
    public static final String CONFIG_ZIP_FILE_NAME = "ZIP_FILE_NAME";
    public static final String CONFIG_FTP_SITE = "FTP_SITE";
    public static final String CONFIG_FTP_PASSWORD = "FTP_PASSWORD";
    public static final String CONFIG_ENABLE_PROFILES = "ENABLE_PROFILES";
    public static final String CONFIG_DONE_TXT_FILENAME = "DONE_TXT_FILENAME";
    public static final String CONFIG_ENABLE_INTEGRATED_PROFILE_DISPLAY = "ENABLE_INTEGRATED_PROFILE_DISPLAY";
    public static final String CONFIG_PA_QUESTION_LEVEL1_AUTO_APPROVE = "PA_QUESTION_LEVEL1_AUTO_APPROVE";
    public static final String CONFIG_PA_QUESTION_LEVEL2_AUTO_APPROVE = "PA_QUESTION_LEVEL2_AUTO_APPROVE";
    public static final String CONFIG_USE_MODELIZED_BRAND_NAME_IN_WAR = "USE_MODELIZED_BRAND_NAME_IN_WAR";
    public static final String CONFIG_FACE_OFF_ENABLED = "FACE_OFF_ENABLED";
    public static final String CONFIG_MICRODATA_SCHEMA_ORG = "MICRODATA_SCHEMA_ORG";
    public static final String CONFIG_INLINE_SEO_PAGES = "INLINE_SEO_PAGES";
    public static final String CONFIG_INLINE_SEO_PAGES_QUESTIONS = "INLINE_SEO_PAGES_QUESTIONS";
    public static final String CONFIG_FOLLOW_CHANNELS = "FOLLOW_CHANNELS";
    public static final String CONFIG_ENABLE_IMAGE_SNIPPET = "ENABLE_IMAGE_SNIPPET";
    public static final String CONFIG_ENABLE_SOCIAL_MEASUREMENT_TRACKING = "ENABLE_SOCIAL_MEASUREMENT_TRACKING";
    public static final String CONFIG_ERROR_CONTACT_EMAIL = "ERROR_CONTACT_EMAIL";
    public static final String CONFIG_MODERATOR_CONTACT_EMAIL = "MODERATOR_CONTACT_EMAIL";
    public static final String CONFIG_ENGINE_POLLUTE_WINDOW = "ENGINE_POLLUTE_WINDOW";
    public static final String CONFIG_GENERATE_MERCHANT_XML_FILE = "GENERATE_MERCHANT_XML_FILE";
    public static final String CONFIG_ENABLE_FOLLOW_UP_EMAIL = "ENABLE_FOLLOW_UP_EMAIL";
    public static final String CONFIG_PRODUCT_LOOKUP_SCRIPT_URL = "PRODUCT_LOOKUP_SCRIPT_URL";
    public static final String CONFIG_DOC_ROOT = "DOC_ROOT";
    public static final String CONFIG_SELFSERVICE_REPOS_HOST = "SELFSERVICE_REPOS_HOST";
    public static final String CONFIG_DEPLOY_SCRIPT = "DEPLOY_SCRIPT";
    public static final String CONFIG_SYNDICATION_GROUP = "SYNDICATION_GROUP";
    public static final String CONFIG_ENABLE_CUSTOM_BADGE_CREATION = "ENABLE_CUSTOM_BADGE_CREATION";
    public static final String CONFIG_ENABLE_REVIEW_CHAMPION = "ENABLE_REVIEW_CHAMPION";
    public static final String CONFIG_ENABLE_SERVICE_COMMENTS = "ENABLE_SERVICE_COMMENTS";
    public static final String CONFIG_ENABLE_SIZE_AND_FIT_SUMMARY = "ENABLE_SIZE_AND_FIT_SUMMARY";
    public static final String CONFIG_GENERATE_SEO_MINI_SITE = "GENERATE_SEO_MINI_SITE";
    public static final String CONFIG_INCLUDE_REVIEW_DATA_COMPLETE_IN_ZIP = "INCLUDE_REVIEW_DATA_COMPLETE_IN_ZIP";
    public static final String CONFIG_SEO_MINI_SITE_MAX_REVIEWS_PER_PAGE = "SEO_MINI_SITE_MAX_REVIEWS_PER_PAGE";

    public enum OverrideLevel {
        config, merchantGroup, merchant, category, provider
    }

    @Id
    @Column(name = "config_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "is_merchant_visible")
    public boolean isMerchantVisible() {
        return merchantVisible;
    }

    public void setMerchantVisible(boolean isMerchantVisible) {
        this.merchantVisible = isMerchantVisible;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "editable_by")
    public AccessLevel getEditableBy() {
        return editableBy;
    }

    public void setEditableBy(AccessLevel editableBy) {
        this.editableBy = editableBy;
    }

    @Column(name = "field_type")
    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Column(name = "sort_index")
    public long getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(long sortIndex) {
        this.sortIndex = sortIndex;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="config_group_id")
//    public ConfigGroup getConfigGroup() {
//        return configGroup;
//    }
//
//    public void setConfigGroup(ConfigGroup configGroup) {
//        this.configGroup = configGroup;
//    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "override_level")
    public OverrideLevel getOverrideLevel() {
        return overrideLevel;
    }

    public void setOverrideLevel(OverrideLevel overrideLevel) {
        this.overrideLevel = overrideLevel;
    }

    @Column(name = "visibility_index")
    public Long getVisibilityIndex() {
        return visibilityIndex;
    }

    public void setVisibilityIndex(Long visibilityIndex) {
        this.visibilityIndex = visibilityIndex;
    }
}
