package com.powerreviews.repositories;

import com.powerreviews.entities.MerchantGroup;
import com.powerreviews.entities.SsAccountInformation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by nadinenack on 9/24/16.
 */
@RepositoryRestResource(collectionResourceRel = "merchantGroups", path = "merchantGroups")
public interface MerchantGroupRepository extends PagingAndSortingRepository<MerchantGroup, Long> {
    @Query("select mg from MerchantGroup mg where mg.id in " +
            "(?#{ hasRole('USER') && security.groups.size() > 0 ? security.groups : security.emptyList })")
    List<MerchantGroup> getMerchantGroups(Pageable pageable);

    @Query("select mg from MerchantGroup mg where mg.id in " +
            "(?#{ hasRole('USER') && security.groups.size() > 0 ? security.groups : security.emptyList })")
    List<MerchantGroup> getAllMerchantGroups();

    @RestResource(exported = false)
    @Query("select merchantGroupId from MerchantGroup")
    List<Long> getAllMerchantGroupIdsUnsecured();

    List<MerchantGroup> findBySsAccountInformation(SsAccountInformation ssAccountInformation);
}
