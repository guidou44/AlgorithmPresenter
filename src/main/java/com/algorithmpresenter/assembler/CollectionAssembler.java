package com.algorithmpresenter.assembler;

import com.algorithmpresenter.domain.DomainCollection;
import com.algorithmpresenter.dtos.DomainCollectionDto;
import com.algorithmpresenter.factories.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionAssembler {

  private IFactory simpleFactory;

  @Autowired
  public CollectionAssembler(IFactory simpleFactory) {
    this.simpleFactory = simpleFactory;
  }

  public DomainCollectionDto assembleResponse(DomainCollection domainCollection) throws Exception {
    DomainCollectionDto domainCollectionDto =
        simpleFactory.getDirectInstance(DomainCollectionDto.class);
    domainCollectionDto.setCollectionDimension(domainCollection.getCollectionDimension());
    domainCollectionDto.setMainCollection(domainCollection.getMainCollection());

    return domainCollectionDto;
  }

  public DomainCollection assembleRequest(DomainCollectionDto domainCollectionDto)
      throws Exception {
    DomainCollection domainCollection = simpleFactory.getDirectInstance(DomainCollection.class);
    domainCollection.setCollectionDimension(domainCollectionDto.getCollectionDimension());
    domainCollection.setMainCollection(domainCollectionDto.getMainCollection());

    return domainCollection;
  }
}
