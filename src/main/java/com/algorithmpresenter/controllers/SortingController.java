package com.algorithmpresenter.controllers;

import com.algorithmpresenter.assembler.CollectionAssembler;
import com.algorithmpresenter.domain.DomainCollection;
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
  private CollectionAssembler collectionAssembler;

  @Autowired
  public SortingController(CollectionService service, CollectionAssembler assembler) {
    collectionService = service;
    collectionAssembler = assembler;
  }

  @GetMapping("/SortingAlgorithm")
  public String getSortingView() {
    return getMainViewName();
  }

  @PostMapping(path = "/SortingAlgorithm/SetNewCollection", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public CollectionDto setNewMainCollectionAndReturnIt(
      @RequestBody CollectionDto collectionDto) throws Exception {
    DomainCollection domainCollection =
        collectionService.getNewRandomMainCollection(collectionDto.getCollectionDimension());
    return collectionAssembler.assembleResponse(domainCollection);
  }
}
