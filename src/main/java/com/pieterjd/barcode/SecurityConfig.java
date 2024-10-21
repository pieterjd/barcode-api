package com.pieterjd.barcode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(userManager())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ahr -> ahr.requestMatchers("/login","/hello").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsManager userManager() {
        return new InMemoryUserDetailsManager(
                new User("admin", "$2a$10$j37/78fF9Eld9UeoTSbafe0TlGFCx3/flDwoNVe6ByH8bG9T2yhs.", AuthorityUtils.createAuthorityList("USER", "ADMIN")),
                new User("user", "$2a$10$zwtfASO2cW0Opqx.FXq.wui3eMgTDf.cjcvlLnUXBjTRh1E2q32eW", AuthorityUtils.createAuthorityList("USER")));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
