package com.dev.gam.mswebfluxjpa.controller;

import com.dev.gam.mswebfluxjpa.business.AddressService;
import com.dev.gam.mswebfluxjpa.business.AuthorService;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.model.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

  @Autowired
  AuthorService authorService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> saveAuthor(@Valid @RequestBody AuthorDto authorDto) {
    return authorService.saveAuthor(authorDto);
  }

  @GetMapping
  public Flux<AuthorDto> findAllAuthors() {
    return authorService.findAllAuthors();
  }


}
