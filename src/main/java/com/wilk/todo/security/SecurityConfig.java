package com.wilk.todo.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(HttpMethod.POST, "/api/todos/**").hasAnyRole("ADMIN","USER");
                    authorize.requestMatchers(HttpMethod.DELETE, "/api/todos/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/todos/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/api/todos/**").permitAll();
                    authorize.requestMatchers(HttpMethod.PATCH, "/api/todos/**").hasAnyRole("ADMIN", "USER");

                    authorize.requestMatchers(HttpMethod.POST, "api/users").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "api/users/**").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers(HttpMethod.DELETE, "api/users/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "api/users/**").hasAnyRole("ADMIN", "USER");

                    authorize.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
