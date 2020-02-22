package com.algorithmpresenter.buisness.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CollectionServiceTest {

  @Autowired private CollectionService collectionServiceSubject;

  @Test
  public void givenCollectionService_whenSetNewRandomCollection_thenItReturnsNewAndProperLength() {
    collectionServiceSubject.setNewRandomMainCollection(20);
    List<Integer> firstList = collectionServiceSubject.getCollectionContainer().getMainCollection();

    collectionServiceSubject.setNewRandomMainCollection(30);
    List<Integer> secondList =
        collectionServiceSubject.getCollectionContainer().getMainCollection();

    assertEquals(30, secondList.size());
    assertNotEquals(firstList, secondList);
  }

  @Test
  public void givenCollectionService_whenSetTooSmallCollection_thenItReturnsNewAndWithMinLength() {
    collectionServiceSubject.setNewRandomMainCollection(0);
    List<Integer> firstList = collectionServiceSubject.getCollectionContainer().getMainCollection();

    assertEquals(3, firstList.size());
  }

  @Test
  public void givenCollectionService_whenSetTooBigCollection_thenItReturnsNewAndWithMaxLength() {
    collectionServiceSubject.setNewRandomMainCollection(200);
    List<Integer> firstList = collectionServiceSubject.getCollectionContainer().getMainCollection();

    assertEquals(100, firstList.size());
  }
}
