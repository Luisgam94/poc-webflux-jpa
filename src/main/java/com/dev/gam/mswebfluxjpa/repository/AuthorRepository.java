package com.dev.gam.mswebfluxjpa.repository;

import com.dev.gam.mswebfluxjpa.model.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
