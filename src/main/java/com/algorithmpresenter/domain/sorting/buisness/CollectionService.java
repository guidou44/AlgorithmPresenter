package com.algorithmpresenter.domain.sorting.buisness;

import com.algorithmpresenter.domain.sorting.model.CollectionContainer;
import com.algorithmpresenter.domain.sorting.model.SortedCollectionContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

  private ICollectionRepository collectionRepository;
  private ISortingFactory algorithmFactory;
  private SortingAlgorithmBase algorithm;

  @Autowired
  public CollectionService(ICollectionRepository repository, ISortingFactory algorithmFactory) {
    this.collectionRepository = repository;
    this.algorithmFactory = algorithmFactory;
  }

  public Set<String> getAvailableAlgorithms() {
    return algorithmFactory.getAvailableAlgorithmsName();
  }

  public CollectionContainer nextSortingIteration(String sortAlgorithm) throws Exception {
    CollectionContainer mainContainer = collectionRepository.getMainCollectionContainer();

    setAlgorithm(sortAlgorithm, mainContainer);

    SortedCollectionContainer sortedContainer = new SortedCollectionContainer(mainContainer);
    sortedContainer.setMainCollection(algorithm.nextSortingIteration());
    sortedContainer.setCurrentSortingIndex(algorithm.getCurrentIndex());
    sortedContainer.setSortingDone(algorithm.isSorted());

    return sortedContainer;
  }

  private void setAlgorithm(String sortAlgorithm, CollectionContainer mainContainer)
      throws Exception {
    if (algorithm == null) {
      algorithm = algorithmFactory.createInstance(sortAlgorithm, mainContainer.getMainCollection());
    } else {
      algorithm.setCurrentCollection(mainContainer.getMainCollection());
    }
  }

  public void setNewRandomMainCollection(int desiredLength) {
    List<Integer> newCollection = generateNewRandomList(desiredLength);
    updateCollectionRepository(newCollection);
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
    final int defaultCollectionMax = 100;
    final int defaultCollectionMin = 3;
    int collectionMax;

    if (desiredLength < defaultCollectionMax) {
      collectionMax = Math.max(desiredLength, defaultCollectionMin);
    } else {
      collectionMax = defaultCollectionMax;
    }
    return collectionMax;
  }

  private void updateCollectionRepository(List<Integer> mainCollection) {
    CollectionContainer collectionContainer = collectionRepository.getMainCollectionContainer();
    collectionContainer.setMainCollection(mainCollection);
    collectionContainer.setCollectionDimension(mainCollection.size());
    collectionRepository.setMainCollectionContainer(collectionContainer);
  }
}
