package com.powerreviews.repositories;

import com.powerreviews.entities.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by nadinenack on 9/24/16.
 */
@RepositoryRestResource(collectionResourceRel = "providers", path = "providers")
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {

    @Query("select p " +
            "from Provider p " +
            "where p.name = 'PowerReviews'")
    Provider findPowerReviewsProvider();
}
