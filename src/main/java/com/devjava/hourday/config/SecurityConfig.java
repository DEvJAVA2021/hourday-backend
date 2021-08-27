package com.devjava.hourday.config;

import com.devjava.hourday.common.jwt.component.JwtAuthenticationFilter;
import com.devjava.hourday.common.jwt.component.JwtResolver;
import com.devjava.hourday.common.jwt.component.JwtValidator;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServer;
    private final JwtResolver jwtResolver;
    private final JwtValidator jwtValidator;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable() // 403

//                .antMatchers("/api/**").hasRole("OWNER")
//                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("USER", "OWNER")
//                .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("USER", "OWNER")
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("USER", "OWNER")
//                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "OWNER")
//                .anyRequest().permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers(this.adminServer.path("/assets/**")).permitAll() // <1>
//                .antMatchers(this.adminServer.path("/login")).permitAll()

//                .and()
//                .cors()
//                .and()
//                .formLogin().loginPage(this.adminServer.path("/login")).successHandler(successHandler).and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .ignoringAntMatchers(
//                        this.adminServer.path("/instances"),
//                        this.adminServer.path("/actuator/**")
//                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtResolver, jwtValidator), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://127.0.0.1:3000");

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
