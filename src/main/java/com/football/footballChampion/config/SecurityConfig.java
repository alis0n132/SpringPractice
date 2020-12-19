package com.football.footballChampion.config;

import com.football.footballChampion.visitors.Permission;
import com.football.footballChampion.visitors.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/deletePlayer/*").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/players/new").hasRole(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/teams/new").hasRole(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/deleteTeam/*").hasRole(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/deleteMatch/*").hasRole(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/matches/new").hasRole(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/**").hasRole(Permission.WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles(Permission.WRITE.getPermission())
                    .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles(Permission.READ.getPermission())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
