package com.dev.gam.mswebfluxjpa.model;

import com.dev.gam.mswebfluxjpa.model.entity.Library;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

  private long id;
  private String title;

  @JsonIgnore
  private Library library;

}
