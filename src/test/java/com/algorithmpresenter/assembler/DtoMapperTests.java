package com.algorithmpresenter.assembler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.algorithmpresenter.domain.DomainCollection;
import com.algorithmpresenter.dtos.CollectionDto;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DtoMapperTests {

  @Autowired private DtoMapper mapperSubject;

  @Test
  public void givenDtoMapper_whenPassingCollectionDto_thenItReturnsDomainCollection() {
    CollectionDto dto = new CollectionDto();
    dto.setMainCollection(new ArrayList<>(Arrays.asList(1, 2, 2, 3)));
    dto.setCollectionDimension(dto.getMainCollection().size());
    assertNotNull(mapperSubject.mapFromDtoToDomain(dto));
  }

  @Test
  public void givenDtoMapper_whenPassingDomainCollection_thenItReturnsCollectionDto() {
    DomainCollection domainCollection = new DomainCollection();
    domainCollection.setMainCollection(new ArrayList<>(Arrays.asList(1, 2, 2, 3)));
    domainCollection.setCollectionDimension(domainCollection.getMainCollection().size());
    assertNotNull(mapperSubject.mapFromDomainToDto(domainCollection));
  }
}
