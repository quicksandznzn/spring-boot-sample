package com.mikemunhall.hbasedaotest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by quicksandzn@gmail.com on 2017/8/3.
 */
@Configuration
@ImportResource("classpath:/hbase/hbase.xml")
public class HbaseConfig {

}
