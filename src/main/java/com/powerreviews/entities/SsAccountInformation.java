package com.powerreviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.persistence.Table(name = "ss_account_information")
public class SsAccountInformation implements Serializable {
    private Long ssAccountInformationId;
    private List<MerchantGroup> merchantGroups;
    private String companyName;
    private String companyPhone;
    private String companyWebsiteUrl;
    private String businessContactFirstName;
    private String businessContactLastName;
    private String businessContactTitle;
    private String businessContactPhone;
    private String businessContactEmail;
    private String ecommercePlatform;
    private String webAnalytics;
    private String searchAndNavigation;
    private String revenue;
    private String pageViews;
    private String billingAddressStreet1;
    private String billingAddressStreet2;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressZip;
    private Boolean adminContactSameAsBusinessContact;
    private String creditCardProfileId;
    private String creditCardCustomerId;
    private String billingAddressCountry;
    private Timestamp createdDate;
    private AccountInformationState accountState;
    private String cancellationReason;
    private String cancellationReasonElaborated;
    private Timestamp sourceCodeDate;
    private BillingType billingType;
    private String billingTimeUnit;
    private Timestamp discountStartDate;
    private String partner;
    private AccountType accountType;
    private String salesforceAccountId;
    private Boolean passwordPolicyEnabled;
    private Boolean loginPolicyEnabled;
    private Timestamp passwordPolicyChangeDate;
    private Timestamp loginPolicyChangeDate;
    private Long passwordExpirationInterval;
    private Boolean braintreeEnabled;
    private Long pricingTierId;
    private String twofaState;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ss_account_information_id_seq")
    @SequenceGenerator(name = "ss_account_information_id_seq", sequenceName = "ss_account_information_id_seq")
    @javax.persistence.Column(name = "ss_account_information_id")
    public Long getSsAccountInformationId() {
        return ssAccountInformationId;
    }

    public void setSsAccountInformationId(Long ssAccountInformationId) {
        this.ssAccountInformationId = ssAccountInformationId;
    }

    @Basic
    @javax.persistence.Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @javax.persistence.Column(name = "company_phone")
    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    @Basic
    @javax.persistence.Column(name = "company_website_url")
    public String getCompanyWebsiteUrl() {
        return companyWebsiteUrl;
    }

    public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
        this.companyWebsiteUrl = companyWebsiteUrl;
    }

    @Basic
    @javax.persistence.Column(name = "business_contact_first_name")
    public String getBusinessContactFirstName() {
        return businessContactFirstName;
    }

    public void setBusinessContactFirstName(String businessContactFirstName) {
        this.businessContactFirstName = businessContactFirstName;
    }

    @Basic
    @javax.persistence.Column(name = "business_contact_last_name")
    public String getBusinessContactLastName() {
        return businessContactLastName;
    }

    public void setBusinessContactLastName(String businessContactLastName) {
        this.businessContactLastName = businessContactLastName;
    }

    @Basic
    @javax.persistence.Column(name = "business_contact_title")
    public String getBusinessContactTitle() {
        return businessContactTitle;
    }

    public void setBusinessContactTitle(String businessContactTitle) {
        this.businessContactTitle = businessContactTitle;
    }

    @Basic
    @javax.persistence.Column(name = "business_contact_phone")
    public String getBusinessContactPhone() {
        return businessContactPhone;
    }

    public void setBusinessContactPhone(String businessContactPhone) {
        this.businessContactPhone = businessContactPhone;
    }

    @Basic
    @javax.persistence.Column(name = "business_contact_email")
    public String getBusinessContactEmail() {
        return businessContactEmail;
    }

    public void setBusinessContactEmail(String businessContactEmail) {
        this.businessContactEmail = businessContactEmail;
    }

    @Basic
    @javax.persistence.Column(name = "ecommerce_platform")
    public String getEcommercePlatform() {
        return ecommercePlatform;
    }

    public void setEcommercePlatform(String ecommercePlatform) {
        this.ecommercePlatform = ecommercePlatform;
    }

    @Basic
    @javax.persistence.Column(name = "web_analytics")
    public String getWebAnalytics() {
        return webAnalytics;
    }

    public void setWebAnalytics(String webAnalytics) {
        this.webAnalytics = webAnalytics;
    }

    @Basic
    @javax.persistence.Column(name = "search_and_navigation")
    public String getSearchAndNavigation() {
        return searchAndNavigation;
    }

    public void setSearchAndNavigation(String searchAndNavigation) {
        this.searchAndNavigation = searchAndNavigation;
    }

    @Basic
    @javax.persistence.Column(name = "revenue")
    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    @Basic
    @javax.persistence.Column(name = "page_views")
    public String getPageViews() {
        return pageViews;
    }

    public void setPageViews(String pageViews) {
        this.pageViews = pageViews;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_street1")
    public String getBillingAddressStreet1() {
        return billingAddressStreet1;
    }

    public void setBillingAddressStreet1(String billingAddressStreet1) {
        this.billingAddressStreet1 = billingAddressStreet1;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_street2")
    public String getBillingAddressStreet2() {
        return billingAddressStreet2;
    }

    public void setBillingAddressStreet2(String billingAddressStreet2) {
        this.billingAddressStreet2 = billingAddressStreet2;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_city")
    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_state")
    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_zip")
    public String getBillingAddressZip() {
        return billingAddressZip;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    @Basic
    @javax.persistence.Column(name = "admin_contact_same_as_business_contact")
    public Boolean getAdminContactSameAsBusinessContact() {
        return adminContactSameAsBusinessContact;
    }

    public void setAdminContactSameAsBusinessContact(Boolean adminContactSameAsBusinessContact) {
        this.adminContactSameAsBusinessContact = adminContactSameAsBusinessContact;
    }

    @Basic
    @javax.persistence.Column(name = "credit_card_profile_id")
    public String getCreditCardProfileId() {
        return creditCardProfileId;
    }

    public void setCreditCardProfileId(String creditCardProfileId) {
        this.creditCardProfileId = creditCardProfileId;
    }

    @Basic
    @javax.persistence.Column(name = "credit_card_customer_id")
    public String getCreditCardCustomerId() {
        return creditCardCustomerId;
    }

    public void setCreditCardCustomerId(String creditCardCustomerId) {
        this.creditCardCustomerId = creditCardCustomerId;
    }

    @Basic
    @javax.persistence.Column(name = "billing_address_country")
    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    @Basic
    @javax.persistence.Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "account_state")
    public AccountInformationState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountInformationState accountState) {
        this.accountState = accountState;
    }

    @Basic
    @javax.persistence.Column(name = "cancelation_reason")
    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    @Basic
    @javax.persistence.Column(name = "cancelation_reason_elaborated")
    public String getCancellationReasonElaborated() {
        return cancellationReasonElaborated;
    }

    public void setCancellationReasonElaborated(String cancellationReasonElaborated) {
        this.cancellationReasonElaborated = cancellationReasonElaborated;
    }

    @Basic
    @javax.persistence.Column(name = "source_code_date")
    public Timestamp getSourceCodeDate() {
        return sourceCodeDate;
    }

    public void setSourceCodeDate(Timestamp sourceCodeDate) {
        this.sourceCodeDate = sourceCodeDate;
    }

    @Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "billing_type")
    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    @Basic
    @javax.persistence.Column(name = "billing_time_unit")
    public String getBillingTimeUnit() {
        return billingTimeUnit;
    }

    public void setBillingTimeUnit(String billingTimeUnit) {
        this.billingTimeUnit = billingTimeUnit;
    }

    @Basic
    @javax.persistence.Column(name = "discount_start_date")
    public Timestamp getDiscountStartDate() {
        return discountStartDate;
    }

    public void setDiscountStartDate(Timestamp discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    @Basic
    @javax.persistence.Column(name = "partner")
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "account_type")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Basic
    @javax.persistence.Column(name = "salesforce_account_id")
    public String getSalesforceAccountId() {
        return salesforceAccountId;
    }

    public void setSalesforceAccountId(String salesforceAccountId) {
        this.salesforceAccountId = salesforceAccountId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ssAccountInformation")
    @JsonIgnore
    public List<MerchantGroup> getMerchantGroups() {
        return merchantGroups;
    }

    public void setMerchantGroups(List<MerchantGroup> merchantGroups) {
        this.merchantGroups = merchantGroups;
    }

    @Basic
    @Column(name = "password_policy_enabled")
    public Boolean getPasswordPolicyEnabled() {
        return passwordPolicyEnabled;
    }

    public void setPasswordPolicyEnabled(Boolean passwordPolicyEnabled) {
        this.passwordPolicyEnabled = passwordPolicyEnabled;
    }

    @Basic
    @Column(name = "login_policy_enabled")
    public Boolean getLoginPolicyEnabled() {
        return loginPolicyEnabled;
    }

    public void setLoginPolicyEnabled(Boolean loginPolicyEnabled) {
        this.loginPolicyEnabled = loginPolicyEnabled;
    }

    @Basic
    @Column(name = "password_policy_change_date")
    public Timestamp getPasswordPolicyChangeDate() {
        return passwordPolicyChangeDate;
    }

    public void setPasswordPolicyChangeDate(Timestamp passwordPolicyChangeDate) {
        this.passwordPolicyChangeDate = passwordPolicyChangeDate;
    }

    @Basic
    @Column(name = "login_policy_change_date")
    public Timestamp getLoginPolicyChangeDate() {
        return loginPolicyChangeDate;
    }

    public void setLoginPolicyChangeDate(Timestamp loginPolicyChangeDate) {
        this.loginPolicyChangeDate = loginPolicyChangeDate;
    }

    @Basic
    @Column(name = "password_expiration_interval")
    public Long getPasswordExpirationInterval() {
        return passwordExpirationInterval;
    }

    public void setPasswordExpirationInterval(Long passwordExpirationInterval) {
        this.passwordExpirationInterval = passwordExpirationInterval;
    }

    @Basic
    @Column(name = "braintree_enabled")
    public Boolean getBraintreeEnabled() {
        return braintreeEnabled;
    }

    public void setBraintreeEnabled(Boolean braintreeEnabled) {
        this.braintreeEnabled = braintreeEnabled;
    }

    @Basic
    @Column(name = "ss_pricing_tier_id")
    public Long getSsPricingTierId() {
        return pricingTierId;
    }

    public void setSsPricingTierId(Long ssPricingTierId) {
        this.pricingTierId = ssPricingTierId;
    }

    @Basic
    @Column(name = "twofa_state")
    public String getTwofaState() {
        return twofaState;
    }

    public void setTwofaState(String twofaState) {
        this.twofaState = twofaState;
    }
}
