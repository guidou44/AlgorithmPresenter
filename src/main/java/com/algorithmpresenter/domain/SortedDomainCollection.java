package com.algorithmpresenter.domain;

public class SortedDomainCollection extends DomainCollection {

  private int currentSortingIndex;
  private boolean isSortingDone;

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
