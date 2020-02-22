package com.algorithmpresenter.presentation.controllers;

public class ControllerBase {

  protected String getMainViewName() {
    return getClass().getSimpleName().replace("Controller", "View");
  }
}
