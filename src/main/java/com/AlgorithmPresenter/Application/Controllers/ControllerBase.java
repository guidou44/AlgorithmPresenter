package com.AlgorithmPresenter.Application.Controllers;

public class ControllerBase {

    protected String getMainViewName() {
        return getClass().getSimpleName().replace("Controller", "View");
    }
}
