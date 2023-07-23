package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.AuthorDto;
import com.dev.gam.mswebfluxjpa.model.entity.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {

  Flux<AuthorDto> findAllAuthors();
  Mono<Void> saveAuthor(AuthorDto authorDto);

}
