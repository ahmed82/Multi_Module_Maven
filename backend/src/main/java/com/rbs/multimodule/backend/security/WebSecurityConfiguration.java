package com.rbs.multimodule.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter  {
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("123"))
			.roles("Admin").authorities("REPORTER")
			.and()
			.withUser("1426391").password(passwordEncoder().encode("123")).roles("frontend");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
    	http
    	 //No session will be created or used by spring security
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
        .httpBasic().and()
        .authorizeRequests()
        //.antMatchers("/").permitAll()
    	.antMatchers("/api/**").permitAll()
        .antMatchers("/api/user/**").permitAll()
        .antMatchers("/api/secured").authenticated()
        //.anyRequest().authenticated() // protect all other requests
.and()
		//disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!
    	.csrf().disable();
		super.configure(http);
	}

	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("foo").password("{noop}bar").roles("USER");
    }*/
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	// Enable CORS globally
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	  return new WebMvcConfigurerAdapter() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	      registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
	    }
	  };
	}
    
}