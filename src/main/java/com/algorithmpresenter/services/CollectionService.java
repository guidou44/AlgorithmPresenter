package com.algorithmpresenter.services;

import com.algorithmpresenter.buisness.sorting.CollectionContainer;
import com.algorithmpresenter.repositories.sorting.ICollectionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

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
    int defaultCollectionMax = 100;
    int collectionMax = Math.min(desiredLength, defaultCollectionMax);

    for (int i = 0; i < collectionMax; i++) {
      int randomNumber = random.nextInt(collectionMax + 1);
      mainCollection.add(randomNumber);
    }

    return mainCollection;
  }

  private void updateRepository(List<Integer> mainCollection) {
    CollectionContainer collectionContainer = collectionRepository.getMainCollectionContainer();
    collectionContainer.setMainCollection(mainCollection);
    collectionContainer.setCollectionDimension(mainCollection.size());
    collectionRepository.setMainCollectionContainer(collectionContainer);
  }

}
