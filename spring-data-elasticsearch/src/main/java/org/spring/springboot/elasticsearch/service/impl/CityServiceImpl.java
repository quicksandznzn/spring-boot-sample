package org.spring.springboot.elasticsearch.service.impl;

import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.elasticsearch.document.City;
import org.spring.springboot.elasticsearch.repository.CityRepository;
import org.spring.springboot.elasticsearch.service.CityService;
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
    public List<City> searchCity(String searchContent) {
        // 构建搜索查询
        SearchQuery searchQuery = getCitySearchQuery(searchContent);

        LOG.info("searchQuery:\t" + searchQuery.getQuery().toString());

        Page<City> cityPage = cityRepository.search(searchQuery);
        return cityPage.getContent();
    }

    @Override
    public List<City> findByName(String name) {
        return cityRepository.findByName(name, pageable).getContent();
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public void batchInsert(List<City> cityList) {
        cityRepository.save(cityList);
    }

    @Override
    public List<City> multiMatchQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.multiMatchQuery(searchContent, "name", "desc");

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> matchQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.matchQuery("name", searchContent);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> termQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.termQuery("desc", searchContent);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> booleanQuery(String must, String mustNot, String should) {
        QueryBuilder qb = QueryBuilders.boolQuery()
            .must(QueryBuilders.termQuery("name", must))
            .mustNot(QueryBuilders.termQuery("desc", mustNot))
            .should(QueryBuilders.termQuery("desc", should));

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> idsQuery(String... ids) {
        QueryBuilder qb = QueryBuilders.idsQuery().ids(ids);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> constantScoreQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders
            .constantScoreQuery(QueryBuilders.termQuery("name", searchContent)).boost(2.0f);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> disMaxQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders
            .disMaxQuery()
            .add(QueryBuilders.termQuery("name", searchContent))
            .boost(1.2f)
            .tieBreaker(0.7f);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> fuzzyQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders
            .fuzzyQuery("name", searchContent);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> hasChildQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders
            .hasChildQuery("name", QueryBuilders.termQuery("name", searchContent));

//        QueryBuilder qb = QueryBuilders
//            .hasParentQuery("name", QueryBuilders.termQuery("name", searchContent));

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> matchAllQuery() {
        QueryBuilder qb = QueryBuilders.matchAllQuery();

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> moreLikeThisQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.moreLikeThisQuery("name")
            .likeText(searchContent)
            .minTermFreq(1)
            .maxQueryTerms(12);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> prefixQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.prefixQuery("name", searchContent);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> queryString(String searchContent) {
        QueryBuilder qb = QueryBuilders.queryStringQuery(searchContent).field("name");

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> rangeQuery(Integer from, Integer to) {
        QueryBuilder qb = QueryBuilders
            .rangeQuery("id")
            .from(from)
            .to(to)
            .includeLower(true)     //包括下界
            .includeUpper(false); //包括上界

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> spanQueries(String searchContent) {
        // Span First
        QueryBuilder qb = QueryBuilders.spanFirstQuery(
            QueryBuilders.spanTermQuery("name", searchContent),  // Query
            30000                                             // Max查询范围的结束位置
        );

//        // Span Near TODO NotSolved
//        QueryBuilders.spanNearQuery()
//            .clause(QueryBuilders.spanTermQuery("name", "")) // Span Term Queries
//            .clause(QueryBuilders.spanTermQuery("name", ""))
//            .clause(QueryBuilders.spanTermQuery("name", ""))
//            .slop(30000)                                               // Slop factor
//            .inOrder(false)
//            .collectPayloads(false);
//
//        // Span Not TODO NotSolved
//        QueryBuilders.spanNotQuery()
//            .include(QueryBuilders.spanTermQuery("name", ""))
//            .exclude(QueryBuilders.spanTermQuery("desc", ""));

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> wildcardQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.wildcardQuery("name", searchContent);

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
    }

    @Override
    public List<City> nestedQuery(String searchContent) {
        QueryBuilder qb = QueryBuilders.nestedQuery("location",               // Path
            QueryBuilders.boolQuery()                      // Your query
                .must(QueryBuilders.matchQuery("location.lat", 0.962590433140581))
                .must(QueryBuilders.rangeQuery("location.lon").lt(0.00000000000000000003))
        )
            .scoreMode("total");                  // max, total, avg or none

        Page<City> cityPage = cityRepository.search(getSearchQuery(qb));
        return cityPage.getContent();
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

    private SearchQuery getSearchQuery(QueryBuilder qb) {
        return new NativeSearchQueryBuilder()
            .withPageable(pageable)
            .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
            .withQuery(qb).build();
    }

}
