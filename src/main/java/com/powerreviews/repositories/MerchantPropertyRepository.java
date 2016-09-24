package com.powerreviews.repositories;

import com.powerreviews.entities.Merchant;
import com.powerreviews.entities.MerchantProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by nadinenack on 9/24/16.
 */
@RepositoryRestResource(collectionResourceRel = "merchantProperties", path = "merchantProperties")
public interface MerchantPropertyRepository extends PagingAndSortingRepository<MerchantProperty, Long> {

    @Query("select mp from MerchantProperty mp where mp.merchant = :merchant")
    List<MerchantProperty> findByMerchant(@Param("merchant") Merchant merchant);

    @Query("select mp from MerchantProperty mp where mp.merchant = :merchant and mp.key in :keys")
    List<MerchantProperty> findByMerchantAndKeyIn(@Param("merchant") Merchant merchant,
                                                  @Param("keys") Set<String> keys);

    MerchantProperty findByMerchantAndKey(Merchant merchant, String key);

    @Query("select mp.merchantPropertyId from MerchantProperty mp " +
            "where mp.merchant = :merchant " +
            "and mp.key = :key")
    Optional<Long> findIdByMerchantAndKey(@Param("merchant") Merchant merchant,
                                          @Param("key") String key);

    @Query("select mp " +
            "from MerchantProperty mp " +
            "where mp.merchant = :merchant ")
    Optional<MerchantProperty> findFirstByMerchant(@Param("merchant") Merchant merchant);
}
