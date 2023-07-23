package com.dev.gam.mswebfluxjpa.repository;

import com.dev.gam.mswebfluxjpa.model.entity.Library;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Library, Long> {

  Optional<Library> findByAddressId(Long Id);

}
