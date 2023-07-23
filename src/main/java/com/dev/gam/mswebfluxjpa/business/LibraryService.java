package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.BookDto;
import com.dev.gam.mswebfluxjpa.model.LibraryDto;
import com.dev.gam.mswebfluxjpa.model.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LibraryService {

  Flux<LibraryDto> findAll();
  Mono<LibraryDto> saveLibrary(LibraryDto libraryDto);
  Mono<Void> saveLibraryVoid(LibraryDto libraryDto);
  Mono<Void> saveBooksToLibrary(Long id, List<BookDto> bookList);
}
