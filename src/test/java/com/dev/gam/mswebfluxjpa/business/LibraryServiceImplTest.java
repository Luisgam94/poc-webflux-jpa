package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.controller.LibraryController;
import com.dev.gam.mswebfluxjpa.exceptions.CustomizedMessageException;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.model.LibraryDto;
import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.model.entity.Library;
import com.dev.gam.mswebfluxjpa.repository.AddressRepository;
import com.dev.gam.mswebfluxjpa.repository.LibraryRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class LibraryServiceImplTest {

  @InjectMocks
  LibraryServiceImpl service;

  @Mock
  LibraryRepository libraryRepository;

  @Mock
  AddressRepository addressRepository;

  @Mock
  LibraryBuilder builder;

  @Test
  void findAll() {

    Library library = new Library();
    library.setId(1L);

    List<Library> libraryList = new ArrayList<>();
    libraryList.add(library);

    LibraryDto libraryDto = new LibraryDto();
    libraryDto.setId(2L);

    List<LibraryDto> libraryDtos = new ArrayList<>();
    libraryDtos.add(libraryDto);


    Mockito.when(libraryRepository.findAll()).thenReturn(libraryList);
    Mockito.when(builder.buildLibrariesDto(libraryList)).thenReturn(libraryDtos);

    StepVerifier.create(service.findAll())
        .expectNext(libraryDto)
        //.expectNext(addressDto2)
        .verifyComplete();

  }

  @Test
  void saveLibrary() {

    Address address = new Address();
    address.setId(1L);
    address.setLocation("ACA");

    Library library = new Library();
    library.setId(1L);
    library.setName("ABC");


    AddressDto addressDto = new AddressDto();
    addressDto.setId(1L);

    LibraryDto libraryDto = new LibraryDto();
    libraryDto.setId(1L);
    libraryDto.setAddress(addressDto);

    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
    Mockito.when(libraryRepository.findByAddressId(1L)).thenReturn(Optional.empty());
    Mockito.when(builder.buildLibraryEntity(any(LibraryDto.class))).thenReturn(library);
    Mockito.when(libraryRepository.save(library)).thenReturn(library);

    StepVerifier.create(service.saveLibrary(libraryDto))
        .expectNext(libraryDto)
        //.expectNext(addressDto2)
        .verifyComplete();

  }

  @Test
  void saveLibraryErrorAlready() {

    Address address = new Address();
    address.setId(1L);
    address.setLocation("ACA");

    Library library = new Library();
    library.setId(1L);
    library.setName("ABC");


    AddressDto addressDto = new AddressDto();
    addressDto.setId(1L);

    LibraryDto libraryDto = new LibraryDto();
    libraryDto.setId(1L);
    libraryDto.setAddress(addressDto);

    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
    Mockito.when(libraryRepository.findByAddressId(1L)).thenReturn(Optional.of(new Library()));
    Mockito.when(builder.buildLibraryEntity(any(LibraryDto.class))).thenReturn(library);
    Mockito.when(libraryRepository.save(library)).thenReturn(library);

    StepVerifier.create(service.saveLibrary(libraryDto))
        .expectError(CustomizedMessageException.class)
        .verify();

  }

  @Test
  void saveLibraryVoid() {
  }

  @Test
  void saveBooksToLibrary() {
  }
}
