package ru.kata.spring.boot_security.demo.configs;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.kata.spring.boot_security.demo.service.UserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SuccessUserHandler successUserHandler;
    private final UserDetailService userDetailService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailService userDetailService) {
        this.successUserHandler = successUserHandler;
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails user = User.withUsername("root")
//                .password(passwordEncoder().encode("root"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("root"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .successHandler(successUserHandler)
                        .permitAll()
                ).userDetailsService(userDetailService)
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}