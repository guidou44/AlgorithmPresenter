package com.algorithmpresenter.presentation.controller;

public class ControllerBase {

  protected String getMainViewName() {
    return getClass().getSimpleName().replace("Controller", "View");
  }
}
