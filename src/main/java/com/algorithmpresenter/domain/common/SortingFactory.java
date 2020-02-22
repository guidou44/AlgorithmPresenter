package com.algorithmpresenter.domain.common;

import com.algorithmpresenter.domain.sorting.algorithm.BubbleSortAlgorithm;
import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmBase;
import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SortingFactory implements ISortingFactory {

  private static final Map<SortingAlgorithmType, Class<? extends SortingAlgorithmBase>> factoryMap =
      Collections.unmodifiableMap(
          new HashMap<SortingAlgorithmType, Class<? extends SortingAlgorithmBase>>() {
            {
              put(SortingAlgorithmType.BUBBLE, BubbleSortAlgorithm.class);
            }
          });

  @Override
  public SortingAlgorithmBase createInstance(
      SortingAlgorithmType algorithmType, List<Integer> collection) throws Exception {
    Class<? extends SortingAlgorithmBase> targetClass = factoryMap.get(algorithmType);
    return targetClass
        .getDeclaredConstructor(String.class, List.class)
        .newInstance(algorithmType.toString(), collection);
  }
}
