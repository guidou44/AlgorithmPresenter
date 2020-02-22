package com.algorithmpresenter.dal.sorting;

import com.algorithmpresenter.domain.sorting.model.CollectionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionRepository implements ICollectionRepository {

  private CollectionContainer mainCollectionContainer;

  @Autowired
  public CollectionRepository(CollectionContainer mainCollectionContainer) {
    this.mainCollectionContainer = mainCollectionContainer;
  }

  @Override
  public void setMainCollectionContainer(CollectionContainer collectionContainer) {

  }

  @Override
  public CollectionContainer getMainCollectionContainer() {
    return mainCollectionContainer;
  }
}
