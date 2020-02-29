package com.algorithmpresenter.domain.common;

import com.algorithmpresenter.domain.sorting.algorithm.BubbleSortAlgorithm;
import com.algorithmpresenter.domain.sorting.algorithm.InsertionSortAlgorithm;
import com.algorithmpresenter.domain.sorting.buisness.ISortingFactory;
import com.algorithmpresenter.domain.sorting.buisness.SortingAlgorithmBase;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SortingFactory implements ISortingFactory {

  private static final Map<String, Class<? extends SortingAlgorithmBase>> factoryMap =
      Collections.unmodifiableMap(
          new HashMap<String, Class<? extends SortingAlgorithmBase>>() {
            {
              put("BUBBLE", BubbleSortAlgorithm.class);
              put("INSERTION", InsertionSortAlgorithm.class);
            }
          });

  @Override
  public SortingAlgorithmBase createInstance(String algorithmTypeName, List<Integer> collection)
      throws Exception {
    Class<? extends SortingAlgorithmBase> targetClass = factoryMap.get(algorithmTypeName);
    return targetClass
        .getDeclaredConstructor(String.class, List.class)
        .newInstance(algorithmTypeName, collection);
  }

  @Override
  public Set<String> getAvailableAlgorithmsName() {
    return factoryMap.keySet();
  }
}
