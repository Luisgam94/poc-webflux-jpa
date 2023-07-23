package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import com.dev.gam.mswebfluxjpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressBuilder addressBuilder;

  @Override
  public Flux<AddressDto> findAll() {
    List<Address> addresses = (List<Address>) addressRepository.findAll();
    return Flux.fromIterable(addressBuilder.buildAddressesDto(addresses));
  }

  @Override
  public Mono<AddressDto> saveAddress(AddressDto addressDto) {
    Address address = addressRepository.save(addressBuilder.buildAddressEntity(addressDto));
    addressDto.setId(address.getId());
    return Mono.fromCallable(()-> addressDto);
  }
}
