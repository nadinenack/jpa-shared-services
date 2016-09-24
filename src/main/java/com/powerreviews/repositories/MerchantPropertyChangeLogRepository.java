package com.powerreviews.repositories;

import com.powerreviews.entities.MerchantPropertyChangeLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nadinenack on 9/24/16.
 */
@Repository
public interface MerchantPropertyChangeLogRepository extends PagingAndSortingRepository<MerchantPropertyChangeLog, Long> {
}
