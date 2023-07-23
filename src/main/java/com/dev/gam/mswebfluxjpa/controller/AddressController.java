package com.dev.gam.mswebfluxjpa.controller;

import com.dev.gam.mswebfluxjpa.business.AddressService;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  AddressService addressService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
    return addressService.saveAddress(addressDto);
  }

  @GetMapping
  public Flux<AddressDto> findAllAddress() {
    return addressService.findAll();
  }

}
