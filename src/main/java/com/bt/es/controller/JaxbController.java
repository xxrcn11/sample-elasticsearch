package com.bt.es.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bt.es.model.Book;
import com.bt.es.model.Catalog;
import com.bt.es.util.XMLUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JaxbController {

	@GetMapping("/create")
	public String create() throws JAXBException {
		Book book = new Book("Gambardella, Matthew", "XML Developer's Guide", "Computer", "44.95", "2000-10-01", 
				"An in-depth look at creating applications with XML.");
		log.info("{}", XMLUtils.fromClassToXMLString(book, Book.class));
		return XMLUtils.fromClassToXMLString(book, Book.class);
	}
	
	
	@GetMapping("/create1")
	public String create1() throws JAXBException {
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
		
		log.info("{}", XMLUtils.fromClassToXMLString(catalog, Catalog.class));
		return XMLUtils.fromClassToXMLString(catalog, Catalog.class);
	}
	
	
	
}
