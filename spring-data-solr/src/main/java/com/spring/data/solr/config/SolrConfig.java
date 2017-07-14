package com.spring.data.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;

@Configuration
@EnableSolrRepositories(basePackages = { "com.spring.data.solr" }, multicoreSupport = true)
public class SolrConfig {

	@Value("${solr.host}")
	private String solrHost;

	@Value("${solr.host.maxconnect}")
	private int maxConnect;

	@Value("${solr.host.timeout}")
	private int timeout;

	@Bean
	public SolrClient solrClient() throws Exception {
		HttpSolrClientFactoryBean factory = new HttpSolrClientFactoryBean();
		factory.setUrl(solrHost);
		factory.setMaxConnections(maxConnect);
		factory.setTimeout(timeout);
		factory.afterPropertiesSet();
		return factory.getSolrClient();
	}

	// @Bean(name="solrTemplate")
	// public SolrTemplate solrBuyTemplate() throws Exception {
	// return new SolrTemplate(solrClient());
	// }
}
