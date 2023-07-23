package com.dev.gam.mswebfluxjpa.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {

  @NotNull
  private long id;

  private String location;

}
