package com.algorithmpresenter.domain.sorting.buisness;

import java.util.List;
import java.util.Set;

public interface ISortingFactory {

  SortingAlgorithmBase createInstance(String algorithmTypeName, List<Integer> collection)
      throws Exception;

  Set<String> getAvailableAlgorithmsName();
}
