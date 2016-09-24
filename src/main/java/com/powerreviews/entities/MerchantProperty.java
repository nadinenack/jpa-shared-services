package com.powerreviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nadinenack on 9/24/16.
 */
@Entity
@Table(name = "merchant_property")
public class MerchantProperty implements Serializable {

    private Long merchantPropertyId;
    private Merchant merchant;
    private String key;
    private String value;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_property_id_seq")
    @SequenceGenerator(name = "merchant_property_id_seq", sequenceName = "merchant_property_id_seq", allocationSize = 1)
    @Column(name = "merchant_property_id")
    public Long getMerchantPropertyId() {
        return merchantPropertyId;
    }

    public void setMerchantPropertyId(Long merchantPropertyId) {
        this.merchantPropertyId = merchantPropertyId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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
}
