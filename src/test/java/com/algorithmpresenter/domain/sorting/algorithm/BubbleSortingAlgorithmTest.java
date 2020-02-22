package com.algorithmpresenter.domain.sorting.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.algorithmpresenter.domain.sorting.buisness.ISortingFactory;
import com.algorithmpresenter.domain.sorting.buisness.SortingAlgorithmBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BubbleSortingAlgorithmTest {

  private final List<Integer> mainCollection = Arrays.asList(60, 4, 55, 78, 3, 9, 11, 12, 1, 2, 3);
  @Autowired ISortingFactory factory;
  private SortingAlgorithmBase bubbleSortingSubject;

  @BeforeEach
  public void setUp() throws Exception {
    bubbleSortingSubject = factory.createInstance("BUBBLE", new ArrayList<>(mainCollection));
  }

  @Test
  public void givenBubbleSort_whenNotSortedYet_thenItReturnsTheTruth() throws Exception {
    assertFalse(bubbleSortingSubject.isSorted());
  }

  @Test
  public void givenBubbleSort_whenSorted_thenItReturnsTheTruth() {
    bubbleSortingSubject.fullSort();
    assertTrue(bubbleSortingSubject.isSorted());
  }

  @Test
  public void givenBubbleSort_whenSorted_thenItReallySortCollection() {
    List<Integer> orderedMainCollection = getMainCollectionOrdered();
    assertEquals(orderedMainCollection, bubbleSortingSubject.fullSort());
  }

  @Test
  public void givenBubbleSort_whenSortingOneIteration_thenItReallySortCollectionOnlyOneIteration() {
    int initialIndex = bubbleSortingSubject.getCurrentIndex();
    assertNotEquals(mainCollection, bubbleSortingSubject.nextSortingIteration());
    assertTrue(initialIndex < bubbleSortingSubject.getCurrentIndex());
  }

  @Test
  public void givenBubbleSort_whenSortIterPerIter_thenItReallySortCollectionFully() {
    List<Integer> orderedMainCollection = getMainCollectionOrdered();
    subjectSortIterationPerIteration();

    assertEquals(orderedMainCollection, bubbleSortingSubject.getCurrentCollection());
  }

  @Test
  public void givenBubbleSort_whenSortIterPerIterFully_thenNextIterDoesNotChangeCollection() {
    List<Integer> orderedMainCollection = getMainCollectionOrdered();
    subjectSortIterationPerIteration();

    assertEquals(orderedMainCollection, bubbleSortingSubject.nextSortingIteration());
  }

  private List<Integer> getMainCollectionOrdered() {
    List<Integer> orderedMainCollection = new ArrayList<>(mainCollection);
    orderedMainCollection.sort(Comparator.naturalOrder());
    return orderedMainCollection;
  }

  private void subjectSortIterationPerIteration() {
    while (!bubbleSortingSubject.isSorted()) {
      bubbleSortingSubject.nextSortingIteration();
    }
  }
}
