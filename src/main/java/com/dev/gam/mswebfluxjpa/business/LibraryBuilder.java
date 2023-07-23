package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.BookDto;
import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.model.entity.Library;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.model.LibraryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryBuilder {

  public Library buildLibraryEntity(LibraryDto libraryDto) {

    return Library.builder()
        .name(libraryDto.getName())
        .address(Address.builder()
            .id(libraryDto.getAddress().getId()).build())
        .build();
  }

  public List<LibraryDto> buildLibrariesDto(List<Library> libraries) {

    return libraries.stream()
        .map((l)-> LibraryDto.builder()
            .id(l.getId())
            .name(l.getName())
            .address(AddressDto.builder()
                .id(l.getAddress().getId())
                .location(l.getAddress().getLocation())
                .build())
            .books(l.getBooks()
                .stream()
                .map(b-> BookDto.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .build())
                .collect(Collectors.toList()))
            .build()).collect(Collectors.toList());
  }

}
