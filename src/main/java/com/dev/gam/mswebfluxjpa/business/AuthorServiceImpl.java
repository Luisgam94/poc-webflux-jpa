package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.AuthorDto;
import com.dev.gam.mswebfluxjpa.model.BookDto;
import com.dev.gam.mswebfluxjpa.model.entity.Author;
import com.dev.gam.mswebfluxjpa.model.entity.Book;
import com.dev.gam.mswebfluxjpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

  @Autowired
  AuthorRepository repository;

  @Override
  public Flux<AuthorDto> findAllAuthors() {
    List<Author> authors = (List<Author>) repository.findAll();
    List<AuthorDto> authorDtos = authors.stream()
        .map((r) -> AuthorDto.builder()
            .id(r.getId())
            .name(r.getName())
            .books(r.getBooks().stream()
                .map((b)-> BookDto.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .build()).collect(Collectors.toList()))
            .build()).collect(Collectors.toList());

    return Flux.fromIterable(authorDtos);
  }

  @Override
  public Mono<Void> saveAuthor(AuthorDto authorDto) {

    Author author = new Author();
    author.setName(authorDto.getName());

    List<Book> books = authorDto.getBooks().stream()
        .map((b) -> Book.builder()
            .title(b.getTitle())
            .build())
        .collect(Collectors.toList());

    author.setBooks(books);

    repository.save(author);

    return Mono.empty();
  }

}
