package com.algorithmpresenter.repositories.sorting;

import java.util.List;

public interface ICollectionRepository {
  
  void updateMainCollection(List<Integer> collection);

  List<Integer> getMainCollection();
}
