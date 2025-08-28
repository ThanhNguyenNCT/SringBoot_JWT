package Cybersoft.ExJPA_Security.filter;

import Cybersoft.ExJPA_Security.util.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            Claims claims = jwtHelper.decodeToken(token);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            if(username != null && !username.isEmpty()){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                List<GrantedAuthority> authorities = new ArrayList<>();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username,"", authorities);
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
