package spring.rest.microservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //all req must be authenticated
        http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
        //if a req is not authenticated, a web page to be shown
        http.httpBasic(Customizer.withDefaults());
        //disable csrf
        http.csrf().disable();
        return http.build();
    }

}
