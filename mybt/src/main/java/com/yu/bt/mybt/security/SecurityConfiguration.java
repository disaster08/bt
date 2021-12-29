//package com.yu.bt.mybt.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    BCryptPasswordEncoder bCryptEncoder;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
//                .authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
//                .passwordEncoder(bCryptEncoder);
//
////                .withDefaultSchema() //Creates Tables and schemas automatically
////                .withUser("myuser")
////                .password("pass")
////                .roles("USER")
////                .and()
////                .withUser("admin")
////                .password("pass2")
////                .roles("USER")
////                .and()
////                .withUser("managerUser")
////                .password("pass3")
////                .roles("ADMIN");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//        //post request returns error 403, so I had to disable csrf.
////        http.cors().and().csrf().disable();
//        http.authorizeRequests()
//
//                //hasRole value must be saved in the database as ROLE_ADMIN //Check out on google about .hasAuthority
//
//                .antMatchers("/projects/new").hasRole("ADMIN")
//                .antMatchers("/projects/save").hasRole("ADMIN")
//
//                .antMatchers("/employee/new").hasRole("ADMIN")
//                .antMatchers("/employee/save").hasRole("ADMIN")
//                .antMatchers("/", "/**").permitAll()
//                .and()
//                .formLogin();
//
//
//        //for H2 DB to work. ERROR X-Frame-Options
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//}
//
