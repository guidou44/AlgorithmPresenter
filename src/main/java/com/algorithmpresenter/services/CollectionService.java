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

  private CollectionContainer collectionContainer;
  private ICollectionRepository collectionRepository;

  @Autowired
  public CollectionService(CollectionContainer container, ICollectionRepository repository) {
    collectionContainer = container;
    collectionRepository = repository;
  }

  public void setNewRandomMainCollection(int desiredLength) {
    List<Integer> newCollection = generateNewRandomList(desiredLength);
    updateRepository(newCollection);
    setContainerUpToDateWithRepository();
  }

  public CollectionContainer getCollectionContainer() {
    return collectionContainer;
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
    collectionRepository.updateMainCollection(mainCollection);
  }

  private void setContainerUpToDateWithRepository() {
    collectionContainer.setCollectionDimension(collectionRepository.getMainCollection().size());
    collectionContainer.setMainCollection(collectionRepository.getMainCollection());
  }
}
