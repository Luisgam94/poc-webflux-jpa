package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.AddressDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressService {

  Flux<AddressDto> findAll();
  Mono<AddressDto> saveAddress(AddressDto addressDto);

}
