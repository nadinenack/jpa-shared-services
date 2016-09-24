package com.powerreviews.repositories;

import com.powerreviews.entities.Provider;
import com.powerreviews.entities.ProviderProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

/**
 * Created by nadinenack on 9/24/16.
 */
@RepositoryRestResource(collectionResourceRel =  "providerProperties", path = "providerProperties")
public interface ProviderPropertyRepository  extends PagingAndSortingRepository<ProviderProperty, Long> {

    @Query("select pp from ProviderProperty pp where pp.provider = :provider")
    List<ProviderProperty> findByProvider(@Param("provider") Provider provider);

    ProviderProperty findByProviderAndKey(@Param("provider") Provider provider,
                                          @Param("key") String key);

    @Query("select pp from ProviderProperty pp where pp.provider = :provider and pp.key in :keys")
    List<ProviderProperty> findByProviderAndKeys(@Param("provider") Provider provider,
                                                 @Param("keys") Set<String> keys);
}
