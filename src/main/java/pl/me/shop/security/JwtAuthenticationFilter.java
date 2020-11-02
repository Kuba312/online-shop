package pl.me.shop.security;

import ch.qos.logback.classic.Level;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//Dodaje możliwość logowania użytkownika do systemu i zwracania tokenu
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("login");
    }
    //Generowanie tokena

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
      //Pobieramy nazwy ról użytkownika i łączymy je przecinkiem
        String authorities = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
  //***************************************************************************
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authorities);
  //****************************************************************************
        String token = Jwts.builder().setClaims(claims)
                .setSubject(((UserDetails) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS512, "kuba123")
                .compact();
   //*****************************************************************************
        response.setHeader("Authorization","Bearer " + token);

    }
}
