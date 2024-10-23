package com.pieterjd.barcode;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private DataSource datasource;

    public SecurityConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(userManager())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ahr -> ahr.requestMatchers("/login", "/hello", "/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .headers(headers -> headers.frameOptions().disable())
                .build();
    }

    @Bean
    public UserDetailsManager userManager() {
        UserDetailsManager userDetailsManager =  new JdbcUserDetailsManager(datasource);
        userDetailsManager.createUser(User.builder()
        .username("admin")
        .password(passwordEncoder().encode("admin"))
        .roles("USER","ADMIN")
        .build());
        return userDetailsManager;
        /*
         * return new InMemoryUserDetailsManager(
         * new User("admin",
         * "$2a$10$j37/78fF9Eld9UeoTSbafe0TlGFCx3/flDwoNVe6ByH8bG9T2yhs.",
         * AuthorityUtils.createAuthorityList("USER", "ADMIN")),
         * new User("user",
         * "$2a$10$zwtfASO2cW0Opqx.FXq.wui3eMgTDf.cjcvlLnUXBjTRh1E2q32eW",
         * AuthorityUtils.createAuthorityList("USER")));
         */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
