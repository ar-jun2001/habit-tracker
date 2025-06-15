package com.habit.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/habits-tracker/save").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                // Implement your encoding logic here
                return bCryptPasswordEncoder.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // Implement your matching logic here
                return  bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
            }
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/habits-tracker/save").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

