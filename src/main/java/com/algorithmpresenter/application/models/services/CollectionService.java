package com.algorithmpresenter.application.models.services;

import com.algorithmpresenter.application.models.dtos.CollectionDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollectionService {

  private CollectionDto mainCollectionContainer;

  @Autowired
  public CollectionService(CollectionDto collectionDto) {
    mainCollectionContainer = collectionDto;
  }

  //region public Methods

  public CollectionDto getNewRandomMainCollection(int desiredLength) {
    generateNewRandomList(desiredLength);
    return mainCollectionContainer;
  }

  //endregion

  //region private Methods

  private void generateNewRandomList(int desiredLength) {
    List<Integer> mainCollection = new ArrayList<Integer>();
    Random random = new Random();
    int defaultCollectionMax = 100;
    int collectionMax = Math.min(desiredLength, defaultCollectionMax);

    for (int i = 0; i < collectionMax; i++) {
      int randomNumber = random.nextInt(collectionMax + 1);
      mainCollection.add(randomNumber);
    }

    mainCollectionContainer.setCollectionDimension(mainCollection.size());
    mainCollectionContainer.setMainCollection(mainCollection);
  }

  //endregion

  //region getters and setters

  public CollectionDto getMainCollectionContainer() {
    return mainCollectionContainer;
  }

  public void setMainCollectionContainer(CollectionDto collectionContainer) {
    mainCollectionContainer = collectionContainer;
  }

  //endregion
}
