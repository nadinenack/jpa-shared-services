package com.powerreviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "merchant_group_property")
public class MerchantGroupProperty implements Serializable {

    private Long merchantGroupPropertyId;
    private MerchantGroup merchantGroup;
    private String key;
    private String value;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_group_property_id_seq")
    @SequenceGenerator(name = "merchant_group_property_id_seq", sequenceName = "merchant_group_property_id_seq", allocationSize = 1)
    @Column(name = "merchant_group_property_id")
    public Long getMerchantGroupPropertyId() {
        return merchantGroupPropertyId;
    }

    public void setMerchantGroupPropertyId(Long merchantGroupPropertyId) {
        this.merchantGroupPropertyId = merchantGroupPropertyId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="merchant_group_id")
    public MerchantGroup getMerchantGroup() {
        return merchantGroup;
    }

    public void setMerchantGroup(MerchantGroup merchantGroup) {
        this.merchantGroup = merchantGroup;
    }

    @JoinColumn(name = "key")
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
}
