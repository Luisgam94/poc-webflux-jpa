package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.BookDto;
import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.model.entity.Book;
import com.dev.gam.mswebfluxjpa.model.entity.Library;
import com.dev.gam.mswebfluxjpa.exceptions.CustomizedMessageException;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.model.LibraryDto;
import com.dev.gam.mswebfluxjpa.repository.AddressRepository;
import com.dev.gam.mswebfluxjpa.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService{

  @Autowired
  LibraryRepository libraryRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  LibraryBuilder libraryBuilder;

  @Override
  public Flux<LibraryDto> findAll() {
    List<Library> libraries = (List<Library>) libraryRepository.findAll();
    return Flux.fromIterable(libraryBuilder.buildLibrariesDto(libraries));
  }

  @Override
  public Mono<LibraryDto> saveLibrary(LibraryDto libraryDto) {

    Optional<Address> address = addressRepository.findById(libraryDto.getAddress().getId());

    if(address.isPresent()) {

      Optional<Library> library = libraryRepository.findByAddressId(libraryDto.getAddress().getId());

      if(library.isPresent()) {
        return Mono.error(new CustomizedMessageException("El id ya tiene un registro"));
      }

      libraryDto.setAddress(new AddressDto(address.get().getId(),address.get().getLocation()));
      Library resp = libraryRepository.save(libraryBuilder.buildLibraryEntity(libraryDto));
      libraryDto.setId(resp.getId());

      return Mono.fromCallable(() -> libraryDto);
    }

    return Mono.error(new CustomizedMessageException("El Id no existe"));
  }

  @Override
  public Mono<Void> saveLibraryVoid(LibraryDto libraryDto){
    Optional<Address> address = addressRepository.findById(libraryDto.getAddress().getId());
    if(address.isPresent()) {
      Optional<Library> library = libraryRepository.findByAddressId(libraryDto.getAddress().getId());
      if(library.isPresent()) {
        return Mono.error(new CustomizedMessageException("El id ya tiene un registro"));
      }
      libraryRepository.save(libraryBuilder.buildLibraryEntity(libraryDto));
      return Mono.empty();
    }
    return Mono.error(new CustomizedMessageException("El Id no existe"));
  }

  @Override
  public Mono<Void> saveBooksToLibrary(Long id, List<BookDto> bookList) {
    Optional<Library> library = libraryRepository.findById(id);
    if (library.isPresent()) {
      List<Book> books = bookList.stream()
          .map((b)-> Book
              .builder()
              .title(b.getTitle())
              .library(library.get())
              .build())
          .collect(Collectors.toList());
      library.get().setBooks(books);

      libraryRepository.save(library.get());
      return Mono.empty();
    }
    return Mono.error(new CustomizedMessageException("El Id no existe"));
  }
}
