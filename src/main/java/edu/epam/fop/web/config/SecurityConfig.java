package edu.epam.fop.web.config;

import edu.epam.fop.web.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "edu.epam.fop.web")
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                        .requestMatchers(new AntPathRequestMatcher("/users")).hasRole("ADMIN")
//                        .requestMatchers(new AntPathRequestMatcher("/users/**", "PUT")).hasAnyRole("ADMIN", "PROFESSOR")
//                        .requestMatchers(new AntPathRequestMatcher("/students/**")).authenticated()
//                        .requestMatchers(new AntPathRequestMatcher("/courses/**")).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login
//                        .loginPage("/login") // path to your login.html
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/courses"), new AntPathRequestMatcher("/courses/**", "GET")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/users/**", "PUT")).hasAnyRole("ADMIN", "PROFESSOR")
                    .requestMatchers(new AntPathRequestMatcher("/students/**")).authenticated()
                    .requestMatchers(new AntPathRequestMatcher("/courses/**", "POST")).hasRole("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/courses/**", "DELETE")).hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

    return http.build();
}
}