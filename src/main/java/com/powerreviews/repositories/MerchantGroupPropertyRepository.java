package com.powerreviews.repositories;

import com.powerreviews.entities.MerchantGroup;
import com.powerreviews.entities.MerchantGroupProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by nadinenack on 9/23/16.
 */

@RepositoryRestResource(collectionResourceRel = "merchantGroupProperties", path = "merchantGroupProperties")
public interface MerchantGroupPropertyRepository extends PagingAndSortingRepository<MerchantGroupProperty, Long> {


    List<MerchantGroupProperty> findByMerchantGroup(@Param("merchantGroup") MerchantGroup merchantGroup);

    List<MerchantGroupProperty> findByMerchantGroupAndKeyIn(MerchantGroup merchantGroup,
                                                            Set<String> keys);

    MerchantGroupProperty findByMerchantGroupAndKey(MerchantGroup merchantGroup, String key);

    @Query("select mgp.merchantGroupPropertyId from MerchantGroupProperty mgp " +
            "where mgp.merchantGroup = :merchantGroup " +
            "and mgp.key = :key")
    Optional<Long> findIdByMerchantGroupAndKey(@Param("merchantGroup") MerchantGroup merchantGroup,
                                               @Param("key") String key);

    @Query("select mp " +
            "from MerchantGroupProperty mp " +
            "where mp.merchantGroup = :merchantGroup ")
    Optional<MerchantGroupProperty> findFirstByMerchantGroup(@Param("merchantGroup") MerchantGroup merchantgroup);
}
