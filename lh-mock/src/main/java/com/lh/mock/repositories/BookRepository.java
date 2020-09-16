package com.lh.mock.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lh.mock.model.Book;

@RepositoryRestResource(path = "books", itemResourceRel = "book")
public interface BookRepository extends CrudRepository<Book, Long> {

}
