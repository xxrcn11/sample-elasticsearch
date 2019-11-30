package com.bt.es.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "catalog")
public class Catalog {
	
	@XmlElement
	private List<Book> book;
	
	public Catalog() {}

	public Catalog(List<Book> book) {
		super();
		this.book = book;
	}

	public List<Book> getCatalog() {
		return book;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Catalog [book=").append(book).append("]");
		return builder.toString();
	}

}
