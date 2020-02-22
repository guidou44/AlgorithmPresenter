package com.algorithmpresenter.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmBase;
import com.algorithmpresenter.domain.sorting.algorithm.SortingAlgorithmType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortingFactoryTest {

  @Autowired ISortingFactory sortingFactorySubject;

  @Test
  public void givenSortingFactory_whenAskingForBubbleInstanceWithParams_thenItReturnsProper()
      throws Exception {
    List<Integer> instanceCollection = Arrays.asList(1, 6, 7, 9, 50, 4, 96);
    SortingAlgorithmBase algorithmInstance =
        sortingFactorySubject.createInstance(SortingAlgorithmType.BUBBLE, instanceCollection);

    assertNotNull(algorithmInstance);
    assertEquals(instanceCollection, algorithmInstance.getCurrentCollection());
    assertEquals(SortingAlgorithmType.BUBBLE.toString(), algorithmInstance.getName());
  }
}
