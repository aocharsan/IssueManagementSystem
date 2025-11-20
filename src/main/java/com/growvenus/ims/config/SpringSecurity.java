package com.growvenus.ims.config;

import com.growvenus.ims.service.UserServiceDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserServiceDetailsImpl userDetailsService) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/user-service/api/**")
                                .permitAll()
                                .anyRequest().authenticated()

                )
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults());
//                .formLogin(form -> form
//                       // .loginPage("/login")
//                        .loginProcessingUrl("/user-service/api/auth-user")
//                        .defaultSuccessUrl("/user-service/api/home", true)
//                        .permitAll()
//                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }









}
