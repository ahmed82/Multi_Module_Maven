package com.rbs.multimodule.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	http
    	 /*No session will be created or used by spring security*/
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
        .httpBasic()
        	.and()
        .authorizeRequests()
    	.antMatchers("/api/hello").permitAll()
        .antMatchers("/api/user/**").permitAll()
        .antMatchers("/api/secured").authenticated()
        //.anyRequest().authenticated() // protect all other requests
.and()
		/*disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!*/
    	.csrf().disable();
		super.configure(http);
	}

/*	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("foo").password("{noop}bar").roles("USER");
    }*/
    
    
}