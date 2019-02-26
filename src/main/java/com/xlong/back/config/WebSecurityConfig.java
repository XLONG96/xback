package com.xlong.back.config;

import com.xlong.back.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = Logger.getLogger(WebSecurityConfig.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("XXX configure HTTP User detail");

        http.userDetailsService(customUserDetailsService)
                .authorizeRequests()
                .antMatchers("/**/h-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
                .defaultSuccessUrl("/home.html")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();  //支持iframe
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/lib/**","/**/h-ui.admin/**");
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
            .withUser("long").password("123").roles("USER");
    }*/
}
