package com.algorithmpresenter.domain.common;

import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmBase;
import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmType;
import java.util.List;

public interface ISortingFactory {

  SortingAlgorithmBase createInstance(SortingAlgorithmType algorithmType, List<Integer> collection)
      throws Exception;
}
