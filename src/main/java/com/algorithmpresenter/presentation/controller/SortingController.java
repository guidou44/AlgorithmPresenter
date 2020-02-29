package com.algorithmpresenter.presentation.controller;

import com.algorithmpresenter.domain.sorting.buisness.CollectionService;
import com.algorithmpresenter.domain.sorting.model.CollectionContainer;
import com.algorithmpresenter.presentation.common.DtoMapper;
import com.algorithmpresenter.presentation.dto.CollectionDto;
import com.algorithmpresenter.presentation.dto.SortedCollectionDto;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SortingController extends ControllerBase {

  private static final int SORTING_ITERATION_TIME_SEC = 2;

  private CollectionService collectionService;
  private DtoMapper dtoMapper;

  @Autowired
  public SortingController(CollectionService service, DtoMapper assembler) {
    collectionService = service;
    dtoMapper = assembler;
  }

  @GetMapping("/SortingAlgorithm")
  public String getSortingView() {
    return getMainViewName();
  }

  @GetMapping(value = "/SortingAlgorithm/AvailableAlgorithms", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Set<String> getSortingAvailableAlgorithms() {
    return collectionService.getAvailableAlgorithms();
  }

  @GetMapping(value = "/SortingAlgorithm/Sort", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public SortedCollectionDto getSortedCollection(@RequestParam("algorithm") String algorithmName)
      throws Exception {
    CollectionContainer sortedCollection =
        collectionService.nextSortingIteration(algorithmName.toUpperCase());
    return dtoMapper.map(sortedCollection, SortedCollectionDto.class);
  }

  @PostMapping(path = "/SortingAlgorithm/SetNewCollection", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public CollectionDto setNewMainCollectionAndReturnIt(@RequestBody CollectionDto collectionDto)
      throws Exception {
    collectionService.setNewRandomMainCollection(collectionDto.getCollectionDimension());
    CollectionContainer collectionContainer = collectionService.getCollectionContainer();
    return dtoMapper.map(collectionContainer, CollectionDto.class);
  }
}
