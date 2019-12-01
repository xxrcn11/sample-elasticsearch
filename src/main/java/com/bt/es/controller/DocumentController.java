package com.bt.es.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bt.es.model.Address;
import com.bt.es.model.Book;
import com.bt.es.model.Catalog;
import com.bt.es.model.Person;
import com.bt.es.model.TelNumber;
import com.bt.es.util.JsonUtils;
import com.bt.es.util.XMLUtils;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/document")
public class DocumentController {

	@Autowired
	private RestHighLevelClient client;
	
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable("id")String id) throws IOException {
		
		DeleteRequest request = new DeleteRequest("test", "_doc", id);
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		
		log.info("delete response={}", response);
		
		return response.toString();
	}
	
	@PutMapping(value = "/{id}")
	public String update(@PathVariable("id")String id) throws IOException {
		
		
//		Map<String, TelNumber> telNumberMap = null;
//		TelNumber t = null;
//		List<Map<String, TelNumber>> telNumberList = new ArrayList<Map<String,TelNumber>>();
//		//업데이트
//		telNumberMap = new HashMap<String, TelNumber>();
//		t = new TelNumber("4", "031-225-2234");
//		telNumberMap.put("home1", t);
//		telNumberList.add(telNumberMap);
//		// 신규
//		telNumberMap = new HashMap<String, TelNumber>();
//		t = new TelNumber("3", "031-225-2235");
//		telNumberMap.put("home2", t);
//		telNumberList.add(telNumberMap);
//		
//		Person p = new Person("AA", null, 0, null, "Hi", telNumberList);
		
		GetRequest request = new GetRequest("test", "_doc", id);
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		Person p = new Gson().fromJson(response.getSourceAsString(), Person.class);
		
		List<Map<String, TelNumber>> oldTelNumbers = p.getTelNumbers();
		List<Map<String, TelNumber>> newTelNumbers = new ArrayList<Map<String,TelNumber>>();
		
		Set<String> keSet = null;
		Map<String, TelNumber> telNumberMap = null;
		TelNumber t = null;
		for (Map<String, TelNumber> map : oldTelNumbers) {
			keSet = map.keySet();
			for (String key : keSet) {
				if(key.equals("home1")) {
					t = new TelNumber("4", "031-225-2234");
					telNumberMap = new HashMap<String, TelNumber>();
					telNumberMap.put("home1", t);
					newTelNumbers.add(telNumberMap);
				} else {
					newTelNumbers.add(map);
				}
			}
		}
		
		t = new TelNumber("4", "031-225-2235");
		telNumberMap = new HashMap<String, TelNumber>();
		telNumberMap.put("home3", t);
		newTelNumbers.add(telNumberMap);
		
		p.setTelNumbers(newTelNumbers);
		p.setFirstName("budnamu");
		
		UpdateRequest updateRequest = new UpdateRequest("test", "_doc", id);
		Map<String, Object> updateMap 
			= JsonUtils.getMapFromJsonString(new Gson().toJson(p));
		
		updateRequest.doc(updateMap);
		
		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
		log.info("update result={}", updateResponse);
		
		return updateResponse.toString();
	}
	
	@GetMapping(value = "/{id}")
	public String get(@PathVariable("id")String id) throws IOException, JAXBException {
		GetRequest request = new GetRequest("test", "_doc", id);
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		Person p = new Gson().fromJson(response.getSourceAsString(), Person.class);
		
		String message = p.getMessage();
		log.info("messag : {}", message);
		
		
		Catalog catalog = XMLUtils.fromXMLStringToClass(message, Catalog.class);
		log.info("Catalog : {}", catalog.getCatalog());
		
		return catalog.toString();
	}
	
	@PostMapping(value = "/{id}")
	public String create(@PathVariable("id")String id) {
		
		
		Person p;
		try {
			p = getPerson();
		} catch (JAXBException e) {
			log.error("데이터 생성 오류 발생", e);
			return "FAIL";
		}
		
		IndexRequest request = new IndexRequest("test", "_doc", id);
		request.source(new Gson().toJson(p), XContentType.JSON);
		
		IndexResponse indexResponse = null;
		try {
			indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.error("생성요청 오류 발생", e);
			return "FAIL";
		}
		return indexResponse.toString();
	}
	
	
	@GetMapping(value = "/test")
	public void test() throws IOException, JAXBException {
		
		Book book1 = new Book("Gambardella, Matthew", "XML Developer's Guide", "Computer", "44.95", "2000-10-01", 
				"An in-depth look at creating applications with XML.");
		Book book2 = new Book("Ralls, Kim", "Midnight Rain", "Fantasy", "5.95", "2000-12-16", 
				"A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.");
		
		Book book3 = new Book("Corets, Eva", "Maeve Ascendant", "Fantasy", "5.95", "2000-11-17", 
				"After the collapse of a nanotechnology society in England, the young survivors lay the foundation for a new society.");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		Catalog catalog = new Catalog(bookList);
		
		String message = XMLUtils.fromClassToXMLString(catalog, Catalog.class);
		log.info(message);
		
		log.info("String to Object = {}", XMLUtils.fromXMLStringToClass(message, Catalog.class));
		
		
	}
	
	
	private Person getPerson() throws JAXBException {
		
		Book book1 = new Book("Gambardella, Matthew", "XML Developer's Guide", "Computer", "44.95", "2000-10-01", 
				"An in-depth look at creating applications with XML.");
		Book book2 = new Book("Ralls, Kim", "Midnight Rain", "Fantasy", "5.95", "2000-12-16", 
				"A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.");
		
		Book book3 = new Book("Corets, Eva", "Maeve Ascendant", "Fantasy", "5.95", "2000-11-17", 
				"After the collapse of a nanotechnology society in England, the young survivors lay the foundation for a new society.");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		Catalog catalog = new Catalog(bookList);
		
		String message = XMLUtils.fromClassToXMLString(catalog, Catalog.class);
		
		
		
		Address home = new Address("경기도 용인시 기흥구 연원로42번길 2", "127동 803호");
		Address worker = new Address("서울 서초구 효령로 174", "KT 서초지사 ");
		
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(home);
		addressList.add(worker);
		
		Map<String, TelNumber> telNumberMap = null;
		TelNumber t = null;
		List<Map<String, TelNumber>> telNumberList = new ArrayList<Map<String,TelNumber>>();
		telNumberMap = new HashMap<String, TelNumber>();
		t = new TelNumber("1", "010-9566-5392");
		telNumberMap.put("home1", t);
		telNumberList.add(telNumberMap);
		
		telNumberMap = new HashMap<String, TelNumber>();
		t = new TelNumber("2", "010-9566-5393");
		telNumberMap.put("mobile1", t);
		telNumberList.add(telNumberMap);
		
		telNumberMap = new HashMap<String, TelNumber>();
		t = new TelNumber("3", "010-9566-5394");
		telNumberMap.put("mobile2", t);
		telNumberList.add(telNumberMap);
		
		
		Person p = new Person("HwanJo", "Kim", 430, addressList, message, telNumberList);
		
		
		return p;
	}
	
	
}
