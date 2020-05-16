package com.rhee.shoppingmall.tiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ShoppingmallConfigurer implements WebMvcConfigurer {
	@Override

	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("main/home");

	}
}
