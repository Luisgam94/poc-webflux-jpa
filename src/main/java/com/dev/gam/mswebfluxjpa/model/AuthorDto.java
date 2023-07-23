package com.dev.gam.mswebfluxjpa.model;

import com.dev.gam.mswebfluxjpa.model.entity.Book;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

  private long id;
  private String name;
  private List<BookDto> books;
}
