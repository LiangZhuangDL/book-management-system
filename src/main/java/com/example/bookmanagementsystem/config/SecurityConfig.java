package com.example.bookmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: book-management-system
 * @description: Security配置类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SelfAuthenticationSuccessHandler selfAuthenticationSuccessHandler;

    @Autowired
    private SelfAuthenticationFailureHandler selfAuthenticationFailureHandler;

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Autowired
    private SelfLogoutSuccessHandler selfLogoutSuccessHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/index", "/register", "/register-user", "/active/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login-user").successHandler(selfAuthenticationSuccessHandler).failureHandler(selfAuthenticationFailureHandler)
                .and()
                .logout().logoutSuccessHandler(selfLogoutSuccessHandler)
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
