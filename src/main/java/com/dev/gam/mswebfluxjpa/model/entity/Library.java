package com.dev.gam.mswebfluxjpa.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column
  private String name;

  @OneToOne()
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "library", cascade = CascadeType.ALL)
  private List<Book> books;

}
