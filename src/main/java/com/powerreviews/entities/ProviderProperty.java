package com.powerreviews.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "provider_property")
public class ProviderProperty implements Serializable {
    private Long id;
    private String key;
    private String value;
    private Provider provider;

    @Id
    @Column(name = "provider_property_id")
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

    @ManyToOne
    @JoinColumn(name="provider_id")
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
