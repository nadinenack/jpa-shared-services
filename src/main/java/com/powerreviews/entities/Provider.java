package com.powerreviews.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nadinenack on 9/23/16.
 */
@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    private Long id;
    private String name;
    private Set<MerchantGroup> merchantGroups;
    private Set<ProviderProperty> providerProperties = new HashSet<ProviderProperty>();

    @Id
    @Column(name = "provider_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinColumn(name="merchant_group_id")
    public Set<MerchantGroup> getMerchantGroups() {
        return merchantGroups;
    }

    public void setMerchantGroups(Set<MerchantGroup> merchantGroups) {
        this.merchantGroups = merchantGroups;
    }

    @OneToMany(mappedBy = "provider")
    public Set<ProviderProperty> getProviderProperties() {
        return providerProperties;
    }

    public void setProviderProperties(Set<ProviderProperty> providerProperties) {
        this.providerProperties = providerProperties;
    }
}
