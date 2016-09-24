package com.powerreviews.repositories;

import com.powerreviews.entities.Config;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by nadinenack on 9/23/16.
 */
@Repository
public interface ConfigRepository extends PagingAndSortingRepository<Config, Long> {

    List<Config> findByKeyIn(Set<String> keys);

    Config findByKey(String key);
}
