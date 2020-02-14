package com.algorithmpresenter.application.controllers;

import com.algorithmpresenter.application.models.dtos.CollectionDto;
import com.algorithmpresenter.application.models.services.CollectionService;
import java.util.List;
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

  @Autowired
  public SortingController(CollectionService service) {
    collectionService = service;
  }

  @GetMapping("/SortingAlgorithm")
  public String getSortingView() {
    return getMainViewName();
  }

  @PostMapping(path = "/SortingAlgorithm/SetNewCollection", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public CollectionDto setNewMainCollectionAndReturnIt(
      @RequestBody CollectionDto collectionDto) {
    return collectionService
        .getNewRandomMainCollection(collectionDto.getCollectionDimension());
  }
}
