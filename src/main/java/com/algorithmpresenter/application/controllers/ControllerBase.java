package com.algorithmpresenter.application.controllers;

public class ControllerBase {

  protected String getMainViewName() {
    return getClass().getSimpleName().replace("Controller", "View");
  }
}
