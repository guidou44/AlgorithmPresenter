package com.AlgorithmPresenter.Application.Models.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CollectionService {
    private List<Integer> mainCollection;
    private Random random;

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
        random = new Random();

        for (int i = 0; i < desiredLength; i++) {
            int randomNumber = random.nextInt(desiredLength + 1);
            mainCollection.add(randomNumber);
        }
    }

    //endregion
}
