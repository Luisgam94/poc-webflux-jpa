package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.repository.AddressRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {

  @InjectMocks
  AddressServiceImpl addressService;

  @Mock
  AddressRepository addressRepository;

  @Mock
  AddressBuilder builder;

  @Test
  void findAll() {

    AddressDto addressDto = new AddressDto();
    addressDto.setId(1L);
    addressDto.setLocation("AAA");

    /*AddressDto addressDto2 = new AddressDto();
    addressDto2.setId(2L);
    addressDto2.setLocation("S.M.P");*/

    List<AddressDto> addressDtoList = new ArrayList<>();
    addressDtoList.add(addressDto);
    //addressDtoList.add(addressDto2);

    Address address = new Address();
    address.setId(1L);
    address.setLocation("AAA");

    List<Address> addressList = new ArrayList<>();
    addressList.add(address);

    Mockito.when(addressRepository.findAll()).thenReturn(addressList);
    Mockito.when(builder.buildAddressesDto(addressList)).thenReturn(addressDtoList);

    Flux<AddressDto> addressDtoFlux = addressService.findAll();
    StepVerifier.create(addressDtoFlux)
        .expectNext(addressDto)
        //.expectNext(addressDto2)
        .verifyComplete();

    Mockito.verify(addressRepository, Mockito.times(1)).findAll();

  }

  @Test
  void saveAddress() {

    Address address = new Address();
    address.setId(2L);
    address.setLocation("AC");

    Mockito.when(builder.buildAddressEntity(any(AddressDto.class))).thenReturn(address);
    Mockito.when(addressRepository.save(address)).thenReturn(address);

    AddressDto addressDto = new AddressDto();
    addressDto.setId(1L);

    Mono<AddressDto> addressDtoMono = addressService.saveAddress(addressDto);
    StepVerifier.create(addressDtoMono)
        .expectNext(addressDto)
        .verifyComplete();

    Mockito.verify(addressRepository, Mockito.times(1)).save(address);


  }
}
