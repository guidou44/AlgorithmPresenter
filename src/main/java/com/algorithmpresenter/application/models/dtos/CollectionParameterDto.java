package com.algorithmpresenter.application.models.dtos;

public class CollectionParameterDto {

  private int collectionDimension;

  public int getCollectionDimension() {
    return collectionDimension;
  }

  public void setCollectionDimension(int collectionDimension) {
    this.collectionDimension = collectionDimension;
  }

  private String sortingMethod;

  public String getSortingMethod() {
    return sortingMethod;
  }

  public void setSortingMethod(String sortingMethod) {
    this.sortingMethod = sortingMethod;
  }
}
