package com.dev.gam.mswebfluxjpa.business;

import com.dev.gam.mswebfluxjpa.model.entity.Address;
import com.dev.gam.mswebfluxjpa.model.AddressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressBuilder {

  public List<AddressDto> buildAddressesDto(List<Address> addresses) {
    return addresses.stream()
        .map((a) -> new AddressDto(a.getId(),a.getLocation()))
        .collect(Collectors.toList());
  }

  public Address buildAddressEntity(AddressDto addressDto) {
    return Address.builder()
        .location(addressDto.getLocation())
        .build();
  }

}
