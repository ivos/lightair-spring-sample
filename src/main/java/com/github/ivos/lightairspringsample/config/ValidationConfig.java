package com.github.ivos.lightairspringsample.config;

import com.github.ivos.lightairspringsample.time.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidationConfig {

	@Bean
	public Validator validator(TimeService timeService) {
		ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
				.clockProvider(timeService)
				.buildValidatorFactory();
		return validatorFactory.getValidator();
	}
}
