package com.algorithmpresenter.domain.sorting.buisness;

import com.algorithmpresenter.domain.sorting.model.CollectionContainer;

public interface ICollectionRepository {

  CollectionContainer getMainCollectionContainer();

  void setMainCollectionContainer(CollectionContainer collectionContainer);
}
