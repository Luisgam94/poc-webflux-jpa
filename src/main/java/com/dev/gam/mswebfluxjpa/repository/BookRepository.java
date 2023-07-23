package com.dev.gam.mswebfluxjpa.repository;

import com.dev.gam.mswebfluxjpa.model.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
