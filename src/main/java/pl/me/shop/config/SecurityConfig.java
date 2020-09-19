package pl.me.shop.config;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.me.shop.security.JwtAuthenticationFilter;
import pl.me.shop.security.JwtAuthorizationFilter;

//włącza security
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Qualifier("userDetailsServiceImpl")

    private final UserDetailsService userDetailsService;

    //Konfiguracja security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/**")
                .and()
                .cors()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//REST jest zawsze STATELESS - nie przechowuje stanu
        //OpenShift jest STATEFUL

    }


    //wskazujemy jaka klasa jest odpowiedzialna za szukanie uzytkownika, jaka klasa
    // odpowiedzialna jest za haszowanie haseł
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }
}
