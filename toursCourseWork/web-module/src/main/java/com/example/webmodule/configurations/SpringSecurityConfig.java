package com.example.webmodule.configurations;


import com.example.servicemodule.s.services.person.PersonService;
import com.example.webmodule.filters.JwtRequestFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {
    private PersonService personService;
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity  http) throws Exception{
      http.csrf(AbstractHttpConfigurer::disable)
              .cors(Customizer.withDefaults())
              .authorizeHttpRequests(request ->
                  request
                          .requestMatchers("/myOrder").authenticated()
                          .requestMatchers("/info").authenticated()
                          .requestMatchers("/order/add").authenticated()
                          .requestMatchers("/order/status").authenticated()
                          .requestMatchers("/person/**").authenticated()
                          .requestMatchers("/admin").hasRole("ADMIN")
                          .anyRequest().permitAll())
                          /*request.requestMatchers("/**").permitAll()
                                  .anyRequest().authenticated())*/
              .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
              .exceptionHandling(exception->
              {exception.authenticationEntryPoint( new HttpStatusEntryPoint( HttpStatus.UNAUTHORIZED));})
              .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
      return http.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(personService);
        return daoAuthenticationProvider;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
