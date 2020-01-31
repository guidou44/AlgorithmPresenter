package com.AlgorithmPresenter.Application.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SortingController extends ControllerBase {

    @GetMapping("/SortingAlgorithm")
    public String getSortingView() {
        return getMainViewName();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/SortingAlgorithm/CollectionParameters", consumes = "application/json")
    public void SetSortingCollectionParameters()
    {

    }

}
