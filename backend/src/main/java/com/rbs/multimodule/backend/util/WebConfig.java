package com.rbs.multimodule.backend.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	/**
	 * Ensure client-side paths redirect to index.html because client handles
	 * routing. NOTE: Do NOT use @EnableWebMvc or it will break this.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Map "/"
		/*
		 * registry.addViewController("/") .setViewName("forward:/index.html");
		 */

		// Map "/word", "/word/word", and "/word/word/word" - except for anything
		// starting with "/api/..." or ending with
		// a file extension like ".js" - to index.html. By doing this, the client
		// receives and routes the url. It also
		// allows client-side URLs to be bookmarked.

		// Single directory level - no need to exclude "api"
		registry.addViewController("/{x:[\\w\\-]+}").setViewName("forward:/index.html");
		// Multi-level directory path, need to exclude "api" on the first part of the
		// path
		registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}").setViewName("forward:/index.html");

	}

	/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}
	*/

}