package com.algorithmpresenter.dal.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.algorithmpresenter.domain.sorting.model.CollectionContainer;
import com.algorithmpresenter.domain.sorting.buisness.ICollectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CollectionRepositoryTest {

  @Autowired
  CollectionContainer collectionContainer;
  @Autowired
  ICollectionRepository collectionRepositorySubject;

  @Test
  public void givenCollectionRepository_whenInsertingObject_thenObjectStateDoesNotChange() {
    collectionRepositorySubject.setMainCollectionContainer(collectionContainer);

    assertEquals(collectionContainer, collectionRepositorySubject.getMainCollectionContainer());
  }
}
