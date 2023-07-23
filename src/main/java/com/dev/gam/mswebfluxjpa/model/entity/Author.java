package com.dev.gam.mswebfluxjpa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "author_id",
          referencedColumnName = "id"))
  private List<Book> books;

}
