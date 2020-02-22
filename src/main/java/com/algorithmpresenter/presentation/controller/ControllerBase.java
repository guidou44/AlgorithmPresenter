package com.algorithmpresenter.presentation.controller;

public abstract class ControllerBase {

  protected String getMainViewName() {
    return getClass().getSimpleName().replace("Controller", "View");
  }
}
