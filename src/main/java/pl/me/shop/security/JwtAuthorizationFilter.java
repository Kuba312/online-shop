package pl.me.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Klasa do sprawdzenia, czy uzytkownik jest zalogowany
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    //Konstruktor, który jako paramter przyjmuje AuthenticationManager
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);


    }

    //Filtrowanie requestów i sprawdza czy token został podany i stawia informacje o użytkowniku do security kontekstu
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
//*********************************************************************************************
        }
        Claims claims = Jwts.parser().setSigningKey("kuba123")
                .parseClaimsJws(token.replace("Bearer ", "")).getBody();
        String authorities = claims.get("authorities", String.class);
//*********************************************************************************************
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (authorities != null) {
            //String[] split = authorities.split(",");
            simpleGrantedAuthorities = Arrays.stream(authorities.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
//****************************************************************************************************************************
        }
        String login = claims.getSubject();

        if (login != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } else {
            response.setStatus(401);
        }
    }
}
