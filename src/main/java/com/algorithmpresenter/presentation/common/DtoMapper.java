package com.algorithmpresenter.presentation.common;

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

  public <T> T map(Object fromObject, Class<T> toClass) {
    return modelMapper.map(fromObject, toClass);
  }
}
