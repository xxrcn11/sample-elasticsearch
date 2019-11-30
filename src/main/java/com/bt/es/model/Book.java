package com.bt.es.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {
	@XmlElement
	private String author;
	@XmlElement
	private String title;
	@XmlElement
	private String genre;
	@XmlElement
	private String price;
	@XmlElement
	private String publish_date;
	@XmlElement
	private String description;
	
	public Book() {}
	
	public Book(String author, String title, String genre, String price, String publish_date, String description) {
		super();
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getPrice() {
		return price;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [author=").append(author).append(", title=").append(title).append(", genre=").append(genre)
				.append(", price=").append(price).append(", publish_date=").append(publish_date)
				.append(", description=").append(description).append("]");
		return builder.toString();
	}
	
}
