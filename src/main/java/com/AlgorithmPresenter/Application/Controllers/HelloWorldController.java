package com.AlgorithmPresenter.Application.Controllers;

import com.AlgorithmPresenter.Application.Models.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HelloWorldController extends ControllerBase {

    Test myTestDi;

    public HelloWorldController(Test testDi) {
        myTestDi = testDi;
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                           String name, Model model) {
        model.addAttribute("name", name); //makes available for view
        return getMainViewName();
    }

}
