package org.spring.springboot.elasticsearch.redis.service.impl;

import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.elasticsearch.document.City;
import org.spring.springboot.elasticsearch.repository.CityRepository;
import org.spring.springboot.elasticsearch.redis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * Created by quiclsandzn@gmail.com on 2017/7/19.
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOG = LoggerFactory.getLogger(CityServiceImpl.class);

    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);

    String SCORE_MODE_SUM = "sum"; // 权重分求和模式 from github
    Float MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10 from github

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City findOne(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void insertCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public List<City> searchCity(String searchContent) {
        // 构建搜索查询
        SearchQuery searchQuery = getCitySearchQuery(searchContent);

        LOG.info("searchQuery:\t" + searchQuery.getQuery().toString());

        Page<City> cityPage = cityRepository.search(searchQuery);
        return cityPage.getContent();
    }

    @Override
    public List<City> findByDescription(String desc) {
        return cityRepository.findByDesc(desc, pageable).getContent();
    }

    @Override
    public List<City> findByName(String name) {
        return cityRepository.findByName(name, pageable).getContent();
    }

    /**
     * 根据搜索词构造搜索查询语句 from github
     *
     * 代码流程：
     * - 权重分查询
     * - 短语匹配
     * - 设置权重分最小值
     * - 设置分页参数
     *
     * @param searchContent 搜索内容
     */
    private SearchQuery getCitySearchQuery(String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
            .add(QueryBuilders.matchPhraseQuery("name", searchContent),
                ScoreFunctionBuilders.weightFactorFunction(1000))
            .add(QueryBuilders.matchPhraseQuery("desc", searchContent),
                ScoreFunctionBuilders.weightFactorFunction(500))
            .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        // 分页参数
        return new NativeSearchQueryBuilder()
            .withPageable(pageable)
            .withQuery(functionScoreQueryBuilder).build();
    }

}
