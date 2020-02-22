package com.algorithmpresenter.domain.sorting.algorithm;

import com.google.common.collect.Comparators;
import java.util.Comparator;
import java.util.List;

public abstract class SortingAlgorithmBase {

  private final String name;
  private List<Integer> currentCollection;
  private int currentIndex;

  public SortingAlgorithmBase(String name, List<Integer> collection) {
    this.name = name;
    this.currentCollection = collection;
    resetCurrentIndex();
  }

  public String getName() {
    return name;
  }

  public int getCurrentIndex() {
    return this.currentIndex;
  }

  public List<Integer> getCurrentCollection() {
    return currentCollection;
  }

  public void setCurrentCollection(List<Integer> currentCollection) {
    this.currentCollection = currentCollection;
  }

  protected void incrementCurrentIndex() {
    currentIndex++;
  }

  protected void resetCurrentIndex() {
    currentIndex = 0;
  }

  protected boolean isSorted() {
    return Comparators.isInOrder(currentCollection, Comparator.<Integer>naturalOrder());
  }

  public abstract List<Integer> fullSort();

  public abstract List<Integer> nextSortingIteration();
}
