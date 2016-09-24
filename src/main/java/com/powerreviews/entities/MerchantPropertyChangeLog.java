package com.powerreviews.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nadinenack on 9/24/16.
 */
@Entity
@Table(name = "merchant_property_change_log")
public class MerchantPropertyChangeLog {

    private Long id;
    private Timestamp createdDate;
    private PropertyType type;
    private MerchantGroup merchantGroup;
    private Merchant merchant;
    private Long profileId;
    private String userName;
    private String key;
    private String oldValue;
    private String newValue;
    private String source;

    public enum PropertyType {
        Merchant, MerchantGroup
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_property_change_log_id_seq")
    @SequenceGenerator(name = "merchant_property_change_log_id_seq", sequenceName = "merchant_property_change_log_id_seq", allocationSize = 1)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "type")
    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "merchant_group_id")
    public MerchantGroup getMerchantGroup() {
        return merchantGroup;
    }

    public void setMerchantGroup(MerchantGroup merchantGroup) {
        this.merchantGroup = merchantGroup;
    }

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Basic
    @Column(name = "profile_id")
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "old_value")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "new_value")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
