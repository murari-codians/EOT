package com.eot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({"com.eot"})
public class WebConfig {

	public WebConfig()
	{
		System.out.println("inside webConfig");
	}
}
