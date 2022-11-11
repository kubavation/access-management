package com.durys.jakub.accessmanagement.security.config;

import com.durys.jakub.accessmanagement.authentication.service.AuthenticationService;
import com.durys.jakub.accessmanagement.security.filter.JwtFilter;
import com.durys.jakub.accessmanagement.user.service.AmUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//    prePostEnabled = true,
//    securedEnabled = true
//)

@Configuration
public class SecurityConfiguration {//extends WebSecurityConfigurerAdapter {

    private static final int PASSWORD_STRENGTH = 5;
//    private final AmUserDetailsService amUserDetailsService;
//    private final JwtFilter jwtFilter;
//
//    public SecurityConfiguration(@Lazy AmUserDetailsService amUserDetailsService,
//                                 @Lazy JwtFilter jwtFilter) {
//        this.amUserDetailsService = amUserDetailsService;
//        this.jwtFilter = jwtFilter;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/authentication").permitAll()
//                    .antMatchers("/roles").permitAll()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(amUserDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
 //   }

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }

    //todo remove
    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }
}
