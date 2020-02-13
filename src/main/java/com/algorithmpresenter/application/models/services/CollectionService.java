package com.algorithmpresenter.application.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;


@Service
public class CollectionService {

  private List<Integer> mainCollection;

  //region public Methods

  public List<Integer> getMainCollection() {
    return mainCollection;
  }

  public List<Integer> getNewRandomMainCollection(int desiredLength) {
    generateNewRandomList(desiredLength);
    return mainCollection;
  }

  //endregion

  //region private Methods

  private void generateNewRandomList(int desiredLength) {
    mainCollection = new ArrayList<Integer>();
    Random random = new Random();
    int defaultCollectionMax = 100;
    int collectionMax = Math.min(desiredLength, defaultCollectionMax);

    for (int i = 0; i < collectionMax; i++) {
      int randomNumber = random.nextInt(collectionMax + 1);
      mainCollection.add(randomNumber);
    }
  }

  //endregion
}
