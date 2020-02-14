package com.algorithmpresenter.application.models.dtos;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CollectionDto {

  private int collectionDimension;
  private List<Integer> mainCollection;

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
