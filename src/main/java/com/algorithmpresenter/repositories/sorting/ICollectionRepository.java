package com.algorithmpresenter.repositories.sorting;

import com.algorithmpresenter.buisness.sorting.CollectionContainer;

public interface ICollectionRepository {
  
  void setMainCollectionContainer(CollectionContainer collectionContainer);

  CollectionContainer getMainCollectionContainer();
}
