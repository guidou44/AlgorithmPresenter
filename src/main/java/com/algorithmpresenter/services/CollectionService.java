package com.algorithmpresenter.services;

import com.algorithmpresenter.domain.DomainCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

  private DomainCollection mainDomainCollectionContainer;

  @Autowired
  public CollectionService(DomainCollection domainCollection) {
    mainDomainCollectionContainer = domainCollection;
  }

  // region public Methods

  public DomainCollection getNewRandomMainCollection(int desiredLength) {
    generateNewRandomList(desiredLength);
    return mainDomainCollectionContainer;
  }

  // endregion

  // region private Methods

  private void generateNewRandomList(int desiredLength) {
    List<Integer> mainCollection = new ArrayList<Integer>();
    Random random = new Random();
    int defaultCollectionMax = 100;
    int collectionMax = Math.min(desiredLength, defaultCollectionMax);

    for (int i = 0; i < collectionMax; i++) {
      int randomNumber = random.nextInt(collectionMax + 1);
      mainCollection.add(randomNumber);
    }

    mainDomainCollectionContainer.setCollectionDimension(mainCollection.size());
    mainDomainCollectionContainer.setMainCollection(mainCollection);
  }

  // endregion

  // region getters and setters

  public DomainCollection getMainDomainCollectionContainer() {
    return mainDomainCollectionContainer;
  }

  public void setMainDomainCollectionContainer(DomainCollection domainCollectionContainer) {
    mainDomainCollectionContainer = domainCollectionContainer;
  }

  // endregion
}
