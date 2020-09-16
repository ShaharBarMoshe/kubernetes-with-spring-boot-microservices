package com.lh.mock.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Range;

@Entity
public class Book {

	@Id
	@GeneratedValue
	Long id;

	@BPMField
	@NotEmpty(message = "Book name is required")
	String name;

	@NotEmpty(message = "isbn is required")
	@ISBN
	String isbn;
	
	@Range
	Long pages;
	
	@Range(min=1, max=5)
	Integer rating;

	public Book() {
		super();
	}

	public Book(Long id, String name, String isbn, Long pages, Integer rating) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.pages = pages;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", isbn=" + isbn + ", pages=" + pages + ", rating=" + rating + "]";
	}

}
