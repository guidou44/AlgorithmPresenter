package com.algorithmpresenter.domain.sorting.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CollectionContainer {

  private int collectionDimension;
  private List<Integer> mainCollection;

  public CollectionContainer() {
    this.mainCollection = new ArrayList<Integer>();
  }

  public int getCollectionDimension() {
    return collectionDimension;
  }

  public void setCollectionDimension(int collectionDimension) {
    this.collectionDimension = collectionDimension;
  }

  public List<Integer> getMainCollection() {
    return mainCollection;
  }

  public void setMainCollection(List<Integer> mainCollection) {
    this.mainCollection = mainCollection;
  }
}
