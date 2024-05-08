package cl.govegan.msauth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
   
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(authorizeRequests ->
                       authorizeRequests
                               .requestMatchers("/api/v1/auth/**").permitAll()
                               .anyRequest().authenticated()
               )
               .formLogin(withDefaults())
               .build();

   }
}
