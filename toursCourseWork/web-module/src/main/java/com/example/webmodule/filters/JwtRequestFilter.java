package com.example.webmodule.filters;



import com.example.commonmodule.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter  extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
            String phoneNumber=null;
            String jwt=null;

            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                jwt=authHeader.substring(7);
                try{
                    phoneNumber= jwtUtil.getPhoneNumber(jwt);

                }
                catch(ExpiredJwtException e){
                    log.debug("Время жизни токена вышло");
                } catch (SignatureException e){
                    log.debug("Подпись неправильная");
                }
            }
            if(phoneNumber!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        phoneNumber,
                        null,
                        jwtUtil.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(token);

            }
            filterChain.doFilter(request,response);
    }
}