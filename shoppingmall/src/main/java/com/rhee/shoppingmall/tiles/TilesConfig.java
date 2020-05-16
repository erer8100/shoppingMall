package com.rhee.shoppingmall.tiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
	
	@Bean
	public TilesConfigurer TilesConfigur() {
		final TilesConfigurer configurer =new TilesConfigurer();
		
		String[] definition={"/WEB-INF/tiles/mainLayout.xml"};
		configurer.setDefinitions(definition);
		configurer.setCheckRefresh(true);
		
		return configurer;
	}
	
	@Bean
	public TilesViewResolver viewResolver() {
		final TilesViewResolver viewResolver = new TilesViewResolver();
		
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(1);
		
		return viewResolver;
	}
}
