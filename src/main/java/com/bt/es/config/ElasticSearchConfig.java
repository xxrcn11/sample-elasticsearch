package com.bt.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

	@Bean(destroyMethod = "close")
	public RestHighLevelClient client(){
		
		RestClientBuilder builder =RestClient.builder(new HttpHost("localhost", 32769, "http"));
	
		RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;
	} 
}
