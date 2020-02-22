package com.algorithmpresenter.dal.sorting;

import com.algorithmpresenter.domain.sorting.model.CollectionContainer;

public interface ICollectionRepository {
  
  void setMainCollectionContainer(CollectionContainer collectionContainer);

  CollectionContainer getMainCollectionContainer();
}
