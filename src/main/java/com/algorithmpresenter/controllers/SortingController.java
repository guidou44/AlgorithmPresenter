package com.algorithmpresenter.controllers;

import com.algorithmpresenter.assembler.DtoMapper;
import com.algorithmpresenter.buisness.sorting.CollectionContainer;
import com.algorithmpresenter.dtos.CollectionDto;
import com.algorithmpresenter.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SortingController extends ControllerBase {

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
