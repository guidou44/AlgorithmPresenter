package com.algorithmpresenter.domain.sorting.algorithm;

import com.algorithmpresenter.domain.sorting.buisness.SortingAlgorithmBase;
import java.util.List;

public class BubbleSortAlgorithm extends SortingAlgorithmBase {

  public BubbleSortAlgorithm(String name, List<Integer> collection) {
    super(name, collection);
  }

  @Override
  public List<Integer> fullSort() {
    List<Integer> collection = getCurrentCollection();

    while (!isSorted()) {
      for (int i = 0; i < collection.size() - 1; i++) {
        nextSortingIterationInternal(collection, i);
      }
      setCurrentCollection(collection);
    }

    return collection;
  }

  @Override
  public List<Integer> nextSortingIteration() {
    List<Integer> collection = getCurrentCollection();

    if (!isSorted()) {
      nextSortingIterationInternal(collection, getCurrentIndex());
      setCurrentCollection(collection);
      incrementCurrentIndex();
    } else {
      resetCurrentIndex();
    }
    resetIndexForNextPassIfNeeded();

    return getCurrentCollection();
  }

  private void resetIndexForNextPassIfNeeded() {
    if (getCurrentIndex() == getCurrentCollection().size() - 1) {
      resetCurrentIndex();
    }
  }

  private void nextSortingIterationInternal(List<Integer> collection, int i) {
    if (collection.get(i) > collection.get(i + 1)) {
      int valueToMove = collection.get(i);
      collection.set(i, collection.get(i + 1));
      collection.set(i + 1, valueToMove);
    }
  }
}
