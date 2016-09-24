package com.powerreviews.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {
    private Long merchantId;
    private String name;
    private String baseUrl;
    private String siteId;
    private Timestamp createdDate;
    private UsageType usageType;
    //todo: uncomment the following line
//    private List<ExtranetRole> extranetRoles;
    private MerchantGroup merchantGroup;
    //todo: uncomment the following line
//    private Set<ProfileTemplate> profileTemplates = new LinkedHashSet<>();

    public enum UsageType { Internal, Production, Disabled, Test }

    @Id
    @Column(name = "merchant_id")
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "base_url")
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Basic
    @Column(name = "site_id")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "usage_type")
    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
//    public List<ExtranetRole> getExtranetRoles() {
//        return extranetRoles;
//    }
//
//    public void setExtranetRoles(List<ExtranetRole> extranetRoles) {
//        this.extranetRoles = extranetRoles;
//    }

    @ManyToOne
    @JoinColumn(name="merchant_group_id")
    public MerchantGroup getMerchantGroup() {
        return merchantGroup;
    }

    public void setMerchantGroup(MerchantGroup merchantGroup) {
        this.merchantGroup = merchantGroup;
    }

//    @OneToMany(mappedBy = "merchant")
//    public Set<ProfileTemplate> getProfileTemplates() {
//        return profileTemplates;
//    }
//
//    public void setProfileTemplates(Set<ProfileTemplate> profileTemplates) {
//        this.profileTemplates = profileTemplates;
//    }
}