package com.example.back.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .authorizeRequests().anyRequest().authenticated().and()
            .httpBasic();
    }


 */

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
