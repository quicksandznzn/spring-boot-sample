package org.spring.springboot.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.elasticsearch.document.City;
import org.spring.springboot.elasticsearch.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by quicksandzn@gmail.com on 2017/7/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestES {

    private static final Logger LOG = LoggerFactory.getLogger(TestES.class);

    @Autowired
    private CityService cityService;

    //@Test
    public void testAboutSearch() {
        cityService.matchQuery("beijing").forEach(i -> LOG.info("matchQuery\t" + i.toString()));

        cityService.multiMatchQuery("good")
            .forEach(i -> LOG.info("multiMatchQuery\t" + i.toString()));

        cityService.booleanQuery("jilin","is good","is")
            .forEach(i -> LOG.info("booleanQuery\t" + i.toString()));

        cityService.termQuery("jilin")
            .forEach(i -> LOG.info("termQuery\t" + i.toString()));

               cityService.idsQuery("1","2")
            .forEach(i -> LOG.info("idsQuery\t" + i.toString()));

        cityService.constantScoreQuery("jilin")
            .forEach(i -> LOG.info("constantScoreQuery\t" + i.toString()));

        cityService.disMaxQuery("jilin")
            .forEach(i -> LOG.info("disMaxQuery\t" + i.toString()));

        cityService.fuzzyQuery("usa")
            .forEach(i -> LOG.info("fuzzyQuery\t" + i.toString()));

//        //Failed
//        cityService.hasChildQuery("usa")
//            .forEach(i -> LOG.info("hasChildQuery\t" + i.toString()));

        cityService.matchAllQuery()
           .forEach(i -> LOG.info("matchAllQuery\t" + i.toString()));

        cityService.moreLikeThisQuery("usa")
            .forEach(i -> LOG.info("moreLikeThisQuery\t" + i.toString()));

        cityService.prefixQuery("b")
            .forEach(i -> LOG.info("prefixQuery\t" + i.toString()));

        cityService.queryString("jilin usa")
            .forEach(i -> LOG.info("queryString\t" + i.toString()));

        cityService.rangeQuery(0,5)
            .forEach(i -> LOG.info("rangeQuery\t" + i.toString()));

        cityService.spanQueries("jilin")
            .forEach(i -> LOG.info("spanQueries\t" + i.toString()));

        cityService.wildcardQuery("j*")
            .forEach(i -> LOG.info("wildcardQuery\t" + i.toString()));

    }

    //@Test
    public void testDeleteAll() {
        cityService.deleteAll();
    }

    @Test
    public void testBatchInsert() {
        String[] arr = {"jilin", "changchun", "usa", "beijing", "sanya", "liaocheng"};
        String[] arr2 = {"jilin is good", "changchun is good", "usa is good", "beijing is good",
            "sanya is good", "liaocheng is good"};
        List<City> cityList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            City city = new City();
            city.setId((long) i);
            city.setName(arr[i]);
            city.setDesc(arr2[i]);
            cityList.add(city);
        }

        cityService.batchInsert(cityList);
    }

}
