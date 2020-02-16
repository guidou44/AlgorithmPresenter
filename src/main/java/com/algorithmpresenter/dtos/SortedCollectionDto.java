package com.algorithmpresenter.dtos;

public class SortedCollectionDto {

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
