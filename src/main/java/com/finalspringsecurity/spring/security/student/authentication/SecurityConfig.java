package com.finalspringsecurity.spring.security.student.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


import static com.finalspringsecurity.spring.security.student.authentication.ApplicationUserRole.*;

@Data
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()
                //.and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermissions())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermissions())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermissions())
//                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), TRAINER.name())
                .anyRequest()
                .authenticated()
                .and()
//               .httpBasic();
                .formLogin();


    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails kishor = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
//                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthority())
                .build();

        UserDetails rahim = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthority())
                .build();

        UserDetails pagol = User.builder()
                .username("trainer")
                .password(passwordEncoder.encode("trainer"))
//                .roles(TRAINER.name())
                .authorities(TRAINER.getGrantedAuthority())
                .build();
        return new InMemoryUserDetailsManager(kishor, rahim, pagol);
    }
}
