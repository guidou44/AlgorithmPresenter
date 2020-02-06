package com.algorithmpresenter.application;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AlgorithmPresenterApplication {

  public static void main(String[] args) {
    run(AlgorithmPresenterApplication.class, args);
  }

}
