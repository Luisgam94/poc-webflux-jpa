package com.dev.gam.mswebfluxjpa.controller;

import com.dev.gam.mswebfluxjpa.business.LibraryService;
import com.dev.gam.mswebfluxjpa.model.BookDto;
import com.dev.gam.mswebfluxjpa.model.LibraryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

  @Autowired
  LibraryService libraryService;

  @PostMapping
  public Mono<ResponseEntity<LibraryDto>> saveLibrary(@Valid @RequestBody LibraryDto libraryDto) {
    return libraryService.saveLibrary(libraryDto)
        .map((l) -> ResponseEntity.status(HttpStatus.CREATED).body(l));
  }

  @GetMapping//(produces = MediaType.APPLICATION_NDJSON_VALUE) sale sin corchetes
  public ResponseEntity<Flux<LibraryDto>> findAllLibraries() {
    return ResponseEntity.ok(libraryService.findAll());
  }

  @PatchMapping(value = "/{libraryId}/books")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<ResponseEntity<Void>> getBookDetails(@PathVariable Long libraryId, @RequestBody List<BookDto> bookDtos) {
    return libraryService.saveBooksToLibrary(libraryId,bookDtos)
        .map(d -> ResponseEntity.noContent().build());
  }

}
