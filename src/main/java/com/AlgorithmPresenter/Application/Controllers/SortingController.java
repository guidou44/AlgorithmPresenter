package com.AlgorithmPresenter.Application.Controllers;

import com.AlgorithmPresenter.Application.Models.DTOs.CollectionParameterDTO;
import com.AlgorithmPresenter.Application.Models.Services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SortingController extends ControllerBase {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/SortingAlgorithm")
    public String getSortingView() {
        return getMainViewName();
    }

    @PostMapping(path = "/SortingAlgorithm/CollectionParameters", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Integer> SetSortingCollectionParameters(@RequestBody CollectionParameterDTO collectionParameterDTO)
    {
        List<Integer> newCollection = collectionService.getNewRandomMainCollection(collectionParameterDTO.getCollectionDimension());
        return newCollection;
    }

}
