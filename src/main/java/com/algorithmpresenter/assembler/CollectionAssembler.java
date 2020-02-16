package com.algorithmpresenter.assembler;

import com.algorithmpresenter.domain.DomainCollection;
import com.algorithmpresenter.dtos.CollectionDto;
import org.springframework.stereotype.Component;

@Component
public class CollectionAssembler {

  public CollectionDto assembleResponse(DomainCollection domainCollection) {
    CollectionDto collectionDto = new CollectionDto();
    collectionDto.setCollectionDimension(domainCollection.getCollectionDimension());
    collectionDto.setMainCollection(domainCollection.getMainCollection());

    return collectionDto;
  }

  public DomainCollection assembleRequest(CollectionDto collectionDto) {
    DomainCollection domainCollection = new DomainCollection();
    domainCollection.setCollectionDimension(collectionDto.getCollectionDimension());
    domainCollection.setMainCollection(collectionDto.getMainCollection());

    return domainCollection;
  }
}
