package com.orange.oss.cloudfoundry.nozzle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig {


    @Value("${admin.user}")
    private String adminUser;
    @Value("${admin.password}")
    private String adminPassword;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(adminUser).password(adminPassword).roles("ADMIN");
    }



    @Configuration
    @Order(1)
    public static class AdminSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("ADMIN")
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable();
        }
    }
    

    @Configuration
    @Order(2)
    public static class CfApiConnectorSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/cf-api-connector/**")
                    .authorizeRequests()
                    .anyRequest()
                    .anonymous()
//                    .hasRole("ADMIN")
//                    .and()
//                    .httpBasic()
                    .and()
                    .csrf().disable();
        }
    }

    
}
