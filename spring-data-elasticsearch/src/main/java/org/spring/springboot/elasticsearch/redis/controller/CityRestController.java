package org.spring.springboot.elasticsearch.redis.controller;

import java.util.List;
import org.spring.springboot.elasticsearch.document.City;
import org.spring.springboot.elasticsearch.redis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quiclsandzn@gmail.com on 2017/7/19.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/getCity", method = RequestMethod.GET)
    public List<City> getCity(@RequestParam(value = "searchContent") String searchContent) {
        return cityService.searchCity(searchContent);
    }

    @RequestMapping(value = "/api/getCityByDesc", method = RequestMethod.GET)
    public List<City> getCityByDesc(@RequestParam(value = "desc") String desc) {
        return cityService.findByDescription(desc);
    }

    @RequestMapping(value = "/api/getCityByName", method = RequestMethod.GET)
    public List<City> getCityByName(@RequestParam(value = "name") String name) {
        return cityService.findByName(name);
    }

    @RequestMapping(value = "/api/save", method = RequestMethod.GET)
    public void save() {
        for (int i = 0; i < 100; i++) {
            City city = new City();
            city.setId((long) i);
            city.setName("吉林" + i);
            city.setDesc("雪花雪花雪花雪花雪花雪花" + i);
            cityService.insertCity(city);
        }

    }
}
