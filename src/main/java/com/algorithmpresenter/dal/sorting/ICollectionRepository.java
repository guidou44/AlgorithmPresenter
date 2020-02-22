package com.algorithmpresenter.dal.sorting;

import com.algorithmpresenter.domain.sorting.CollectionContainer;

public interface ICollectionRepository {
  
  void setMainCollectionContainer(CollectionContainer collectionContainer);

  CollectionContainer getMainCollectionContainer();
}
