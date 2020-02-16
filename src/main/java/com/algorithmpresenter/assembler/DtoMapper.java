package com.algorithmpresenter.assembler;

import com.algorithmpresenter.domain.DomainCollection;
import com.algorithmpresenter.dtos.CollectionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

  private ModelMapper modelMapper;

  @Autowired
  public DtoMapper(ModelMapper mapper) {
    modelMapper = mapper;
  }

  public CollectionDto mapFromDomainToDto(DomainCollection domainCollection) {
    return modelMapper.map(domainCollection, CollectionDto.class);
  }

  public DomainCollection mapFromDtoToDomain(CollectionDto collectionDto) {
    return modelMapper.map(collectionDto, DomainCollection.class);
  }
}
