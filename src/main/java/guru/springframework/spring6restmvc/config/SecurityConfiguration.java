package guru.springframework.spring6restmvc.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.filter.CorsFilter;

import static guru.springframework.spring6restmvc.model.auth.Permission.*;
import static guru.springframework.spring6restmvc.model.auth.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity//esta anotacion habilita la seguridad
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST = {//la ruta para crear usuario o logearse no requieren autenticacion
            "/auth/**",//todo lo que esta en esta ruta no se le aplica la politica de seguridad
            "/"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final CorsFilter corsFilter;
    @Bean//configura la seguridad de la app
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST)
                        .permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/favicon.ico", "/swagger-resources/**", "/webjars/**")
                        .permitAll()//rutas con permiso..las de login y registro
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate", "/api/v1/auth/refresh-token").permitAll()
                        //todo lo que puede hacerse con el rol admin
                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//esto mantiene el stateles entre peticiones
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
                );


        return http.build();
    }
}