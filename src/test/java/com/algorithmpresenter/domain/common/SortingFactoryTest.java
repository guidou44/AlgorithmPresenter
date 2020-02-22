package com.algorithmpresenter.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.algorithmpresenter.domain.sorting.buisness.ISortingFactory;
import com.algorithmpresenter.domain.sorting.buisness.SortingAlgorithmBase;
import com.algorithmpresenter.helper.ParametrizedTestHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortingFactoryTest {

  @Autowired ISortingFactory sortingFactorySubject;

  @Test
  public void givenSortingFactory_whenAskingForBubbleInstanceWithParams_thenItReturnsProper()
      throws Exception {
    List<Integer> instanceCollection = Arrays.asList(1, 6, 7, 9, 50, 4, 96);
    SortingAlgorithmBase algorithmInstance =
        sortingFactorySubject.createInstance("BUBBLE", instanceCollection);

    assertNotNull(algorithmInstance);
    assertEquals(instanceCollection, algorithmInstance.getCurrentCollection());
    assertEquals("BUBBLE", algorithmInstance.getName());
  }

  @Test
  public void givenSortingFactory_whenAskingForInstanceNames_thenNonEmptyList() {
    assertNotEquals(0, sortingFactorySubject.getAvailableAlgorithmsName().size());
  }

  private Stream<Arguments> givenFactory_whenCreatingFromProvided_thenReturnedInstancesAreValid() {
    List<Integer> mainCollection = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    Stream<Arguments> outputTests = Stream.empty();

    for (String name : sortingFactorySubject.getAvailableAlgorithmsName()) {
      outputTests =
          Stream.concat(
              outputTests,
              Stream.of(
                  Arguments.of(
                      "givenFactory_whenCreatingFromProvided_"
                          + name
                          + "_thenReturnedInstanceIsValid",
                      name,
                      mainCollection)));
    }

    return outputTests;
  }

  @ParameterizedTest(name = ParametrizedTestHelper.TEST_TITLE)
  @MethodSource
  public void givenFactory_whenCreatingFromProvided_thenReturnedInstancesAreValid(
      String testTitle, String instanceName, List<Integer> mainCollection) throws Exception {
    assertNotNull(sortingFactorySubject.createInstance(instanceName, mainCollection));
  }
}
