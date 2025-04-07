package com.example.project_1.config;


import com.example.project_1.MyUserDetailsService;
import com.example.project_1.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

import static javax.management.Query.and;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/api/auth/login",
                        "/api/users/create").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);




    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/authenticate", "/api/auth/login",
//                        "/api/users/create").permitAll()
//                .anyRequest().authenticated()
//                .and().sessionManagement()
//
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                .formLogin().disable()
//
//                .logout()
//                .logoutUrl("/auth/logout")  // Set logout URL
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    response.setContentType("application/json");
//                    response.setCharacterEncoding("UTF-8");
//                    response.getWriter().write("{\"success\": true, \"message\": \"Logout Successful\"}");
//                    response.getWriter().flush();
//                });
//
//
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//
//

//    }


//


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }








    }

