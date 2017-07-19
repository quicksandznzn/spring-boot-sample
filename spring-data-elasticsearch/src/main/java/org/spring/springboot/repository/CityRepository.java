package org.spring.springboot.repository;

import org.spring.springboot.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by quiclsandzn@gmail.com on 2017/7/19.
 */
public interface CityRepository extends ElasticsearchRepository<City, Long> {

    Page<City> findByDesc(String desc, Pageable pageable);

    Page<City> findByName(String name, Pageable pageable);

}
