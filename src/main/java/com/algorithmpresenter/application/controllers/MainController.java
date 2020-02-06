package com.algorithmpresenter.application.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController extends ControllerBase {

  @GetMapping("/")
  public String index(final Model model) {
    return "index";
  }
}
