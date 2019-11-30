package com.bt.es.controller;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/index")
public class IndexController {

	@Autowired
	private RestHighLevelClient client;
	
	
	@DeleteMapping(value = "/delete")
	public String delete() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("test");
		request.timeout("200m"); 
		AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
		log.info("response={}", response);
		
		return response.toString();
	}
	
	@GetMapping(value = "/create")
	public String create() throws IOException {
		
		XContentBuilder indexBuilder = XContentFactory.jsonBuilder()
	        .startObject()
	            .startObject("_doc")
	                .startObject("properties")
	                    .startObject("firstName")
	                        .field("type","keyword")
	                    .endObject()
	                    .startObject("lastName")
	                        .field("type","keyword")
	                    .endObject()
	                    .startObject("age")
	                        .field("type","keyword")
	                    .endObject()
	                    .startObject("addressList")
	                        .field("type","Object")
	                    .endObject()
	                    .startObject("message")
	                        .field("type","Object")
	                    .endObject()
	                .endObject()
	            .endObject()
	        .endObject();

		CreateIndexRequest request = new CreateIndexRequest("test");
		request.source(indexBuilder);
		request.alias(new Alias("test_alias"));
		
		CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

		log.info("CreateIndexResponse={}", response);
		
		return response.toString();
	}
}
	
