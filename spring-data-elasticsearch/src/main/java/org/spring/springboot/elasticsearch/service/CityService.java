package org.spring.springboot.elasticsearch.service;

import java.util.List;
import org.spring.springboot.elasticsearch.document.City;

/**
 * Created by quiclsandzn@gmail.com on 2017/7/19.
 */
public interface CityService {

    List<City> searchCity(String searchContent);

    List<City> findByName(String name);

    void deleteAll();

    void batchInsert(List<City> cityList);

    //Query DSL

    List<City> multiMatchQuery(String searchContent);

    List<City> matchQuery(String searchContent);

    List<City> termQuery(String searchContent);

    List<City> booleanQuery(String must,String mustNot,String should);

    List<City> idsQuery(String... ids);

    List<City> constantScoreQuery(String searchContent);

    List<City> disMaxQuery(String searchContent);

    List<City> fuzzyQuery(String searchContent);

    List<City> hasChildQuery(String searchContent);

    List<City> matchAllQuery();

    List<City> moreLikeThisQuery(String searchContent);

    List<City> prefixQuery(String searchContent);

    List<City> queryString(String searchContent);

    List<City> rangeQuery(Integer from,Integer to);

    List<City> spanQueries(String searchContent);

    List<City> wildcardQuery(String searchContent);

    List<City> nestedQuery(String searchContent);


}