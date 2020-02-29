package com.algorithmpresenter.domain.sorting.model;

public class SortedCollectionContainer extends CollectionContainer {

  private int currentSortingIndex;
  private boolean isSortingDone;

  public SortedCollectionContainer(CollectionContainer container) {
    setCollectionDimension(container.getCollectionDimension());
    setMainCollection(container.getMainCollection());
  }

  public int getCurrentSortingIndex() {
    return currentSortingIndex;
  }

  public void setCurrentSortingIndex(int currentSortingIndex) {
    this.currentSortingIndex = currentSortingIndex;
  }

  public boolean isSortingDone() {
    return isSortingDone;
  }

  public void setSortingDone(boolean sortingDone) {
    isSortingDone = sortingDone;
  }
}
