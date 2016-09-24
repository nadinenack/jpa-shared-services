package com.powerreviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "merchant_group")
public class MerchantGroup implements Serializable {
    private Long merchantGroupId;
    private String name;
    private Type type;
    private String baseUrl;
    private Provider provider;
    private Timestamp createdDate;
    private Boolean hidden;
    private UsageType usageType;
    //todo: the following line should be uncommented and filled in
//    private List<ExtranetRole> extranetRoles;
    private SsAccountInformation ssAccountInformation;
    private List<Merchant> merchants;

    public enum Type { Legacy, Master }

    public enum UsageType { Buzzillions, Disabled, Internal, Partner, Production, Test }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_group_id_seq")
    @SequenceGenerator(name = "merchant_group_id_seq", sequenceName = "merchant_group_id_seq", allocationSize = 1)
    @Column(name = "merchant_group_id")
    public Long getMerchantGroupId() {
        return merchantGroupId;
    }

    public void setMerchantGroupId(Long merchantGroupId) {
        this.merchantGroupId = merchantGroupId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Basic
    @Column(name = "base_url")
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "provider_id") //Perhaps make lazy-load
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "hidden")
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "usage_type")
    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchantGroup")
//    public List<ExtranetRole> getExtranetRoles() {
//        return extranetRoles;
//    }
//
//    public void setExtranetRoles(List<ExtranetRole> extranetRoles) {
//        this.extranetRoles = extranetRoles;
//    }

    @ManyToOne
    @JoinColumn(name="ss_account_information_id")
    public SsAccountInformation getSsAccountInformation() {
        return ssAccountInformation;
    }

    public void setSsAccountInformation(SsAccountInformation ssAccountInformation) {
        this.ssAccountInformation = ssAccountInformation;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchantGroup")
    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }
}

