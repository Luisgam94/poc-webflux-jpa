package com.dev.gam.mswebfluxjpa.controller;

import com.dev.gam.mswebfluxjpa.business.AddressService;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AddressController.class)
class AddressControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private AddressService addressService;

  @Test
  void saveAddress() {

    AddressDto addressDto = new AddressDto();
    addressDto.setId(1L);
    addressDto.setLocation("S.M.P");

    Mockito.when(addressService.saveAddress(addressDto)).thenReturn(Mono.just(addressDto));

    // when - action or behaviour that we are going test
    webTestClient.post().uri("/address")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(addressDto), AddressDto.class)
        .exchange()
        .expectStatus().isCreated();
        //.expectBody()
        //.consumeWith(s -> System.out.println(s));

    //Mockito.verify(addressService, times(1)).saveAddress(addressDto);
  }

  @Test
  void findAllAddress() {

    List<AddressDto> addressDtoList = new ArrayList<>();
    AddressDto addressDto = new AddressDto();
    addressDto.setId(2L);
    addressDto.setLocation("ab");

    AddressDto addressDto1 = new AddressDto();
    addressDto1.setId(1L);
    addressDto.setLocation("ac");

    Mockito.when(addressService.findAll()).thenReturn(Flux.fromIterable(addressDtoList));

    // when - action or behaviour that we are going test
    webTestClient.get()
        .uri("/address")
        .exchange().expectStatus().isOk()
        .expectBody();

    Mockito.verify(addressService, times(1)).findAll();

  }
}
