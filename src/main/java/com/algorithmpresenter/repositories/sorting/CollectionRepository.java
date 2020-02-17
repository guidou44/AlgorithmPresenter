package com.algorithmpresenter.repositories.sorting;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionRepository implements ICollectionRepository {

  private List<Integer> mainCollection;

  @Autowired
  public CollectionRepository(List<Integer> mainCollection) {
    this.mainCollection = mainCollection;
  }

  @Override
  public void updateMainCollection(List<Integer> collection) {
    mainCollection = collection;
  }

  @Override
  public List<Integer> getMainCollection() {
    return mainCollection;
  }
}
