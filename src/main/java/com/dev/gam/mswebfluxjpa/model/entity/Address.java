package com.dev.gam.mswebfluxjpa.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY) //IDENTITY, el valor del ID se genera solo después de que la entidad se persiste en la base de datos.
  private long id;

  @Column(nullable = false)
  private String location;

  @OneToOne(mappedBy = "address")
  private Library library;

}
