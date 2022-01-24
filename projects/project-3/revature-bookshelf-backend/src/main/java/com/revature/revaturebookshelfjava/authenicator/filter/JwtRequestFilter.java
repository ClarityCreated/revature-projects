package com.revature.revaturebookshelfjava.authenicator.filter;

import com.revature.revaturebookshelfjava.authenicator.utils.JwtUtils;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // final fields must be constructed
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private static List<String> skipFilterUrls = Arrays.asList("/api/books");

    @Autowired
    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        // CHECKPOINT: is there token?
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
            // Load userDetails based on Token
            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (UsernameNotFoundException e) {
                // TODO: ADD LOGGING?
                throw new RuntimeException("incorrect username or password");
            }
        }
        // Next filter in chain regardless of token
        filterChain.doFilter(request, response);

    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return skipFilterUrls.stream().anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
    }

}