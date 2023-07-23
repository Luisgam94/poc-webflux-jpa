package com.dev.gam.mswebfluxjpa.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO) // genera secuencias
  private long id;

  @Column(nullable=false)
  private String title;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name="library_id")
  private Library library;

  @ManyToMany(mappedBy = "books")
  private List<Author> authors;

}
