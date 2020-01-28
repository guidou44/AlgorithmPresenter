package com.AlgorithmPresenter.Application;

import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AlgorithmPresenterApplication {

	public static void main(String[] args) {
		run(AlgorithmPresenterApplication.class, args);
	}

}
