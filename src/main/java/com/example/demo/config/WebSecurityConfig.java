package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/h2-console/**", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/greeting", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("qwaser")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//}

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource ds) {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("password")
//                        .roles("ADMIN")
//                        .build();
//
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(ds);
//
//        manager.createUser(user);
//        manager.createUser(admin);
//
//        return manager;
//    }
@Bean
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}

@Bean
public InMemoryUserDetailsManager inMemoryUserDetailsManager()
{
    List<UserDetails> userDetailsList = new ArrayList<>();
    userDetailsList.add(User.withUsername("admin").password(passwordEncoder().encode("qwaser"))
            .roles("ADMIN", "ADMIN").build());
    userDetailsList.add(User.withUsername("manager").password(passwordEncoder().encode("password"))
            .roles("MANAGER", "USER").build());
    userDetailsList.add(User.withUsername("user").password(passwordEncoder().encode("qqqqqq"))
            .roles("MANAGER", "USER").build());

    return new InMemoryUserDetailsManager(userDetailsList);
}
}