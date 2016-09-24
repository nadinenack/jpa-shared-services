package com.powerreviews.repositories;

import com.powerreviews.entities.Merchant;
import com.powerreviews.entities.MerchantGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

/**
 * Created by nadinenack on 9/24/16.
 */
@RepositoryRestResource(collectionResourceRel = "merchants", path = "merchants")
public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {
    @Query("select m from Merchant m where merchantId = 100")
    Merchant getExtranetMerchant();

    @Query("select distinct m.merchantGroup from Merchant m where m.merchantId in :merchantIds")
    Set<MerchantGroup> getGroupsByMerchantIds(@Param("merchantIds") Set<Long> merchantIds);

    List<Merchant> findByMerchantGroup(MerchantGroup merchantGroup);
}
