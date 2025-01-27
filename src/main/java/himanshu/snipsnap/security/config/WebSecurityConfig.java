package himanshu.snipsnap.security.config;


import himanshu.snipsnap.security.jwtSecurity.JwtAuthFilter;
import himanshu.snipsnap.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailServiceImpl userDetailsService ;
    private final JwtAuthFilter jwtAuthFilter ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/urls/**").authenticated()
                        .requestMatchers("/{shortURLs}").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationprovider())
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class )
                .build() ;
    }

    @Bean
    public AuthenticationProvider authenticationprovider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider ;
    }


    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(12) ;
    }
}
