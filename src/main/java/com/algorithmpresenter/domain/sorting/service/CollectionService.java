package com.algorithmpresenter.domain.sorting.service;

import com.algorithmpresenter.dal.sorting.ICollectionRepository;
import com.algorithmpresenter.domain.sorting.model.CollectionContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

  private final int defaultCollectionMax = 100;
  private final int defaultCollectionMin = 3;
  private ICollectionRepository collectionRepository;

  @Autowired
  public CollectionService(ICollectionRepository repository) {
    collectionRepository = repository;
  }

  public void setNewRandomMainCollection(int desiredLength) {
    List<Integer> newCollection = generateNewRandomList(desiredLength);
    updateRepository(newCollection);
  }

  public CollectionContainer getCollectionContainer() {
    return collectionRepository.getMainCollectionContainer();
  }

  private List<Integer> generateNewRandomList(int desiredLength) {
    List<Integer> mainCollection = new ArrayList<Integer>();
    Random random = new Random();

    int collectionMax = getAcceptedCollectionSize(desiredLength);

    for (int i = 0; i < collectionMax; i++) {
      int randomNumber = random.nextInt(collectionMax + 1);
      mainCollection.add(randomNumber);
    }

    return mainCollection;
  }

  private int getAcceptedCollectionSize(int desiredLength) {
    int collectionMax;
    if (desiredLength < defaultCollectionMax) {
      collectionMax = Math.max(desiredLength, defaultCollectionMin);
    } else {
      collectionMax = defaultCollectionMax;
    }
    return collectionMax;
  }

  private void updateRepository(List<Integer> mainCollection) {
    CollectionContainer collectionContainer = collectionRepository.getMainCollectionContainer();
    collectionContainer.setMainCollection(mainCollection);
    collectionContainer.setCollectionDimension(mainCollection.size());
    collectionRepository.setMainCollectionContainer(collectionContainer);
  }
}
