package com.revature.revaturebookshelfjava.authenicator.controller;

import com.revature.revaturebookshelfjava.authenicator.model.AuthRequest;
import com.revature.revaturebookshelfjava.authenicator.model.AuthResponse;
import com.revature.revaturebookshelfjava.authenicator.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login"
    )
    public ResponseEntity<?> doLogin(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            // TODO: Attempt to forward error to ResponseEntity<?>
            throw new RuntimeException("incorrect username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse(jwt);
        return ResponseEntity.ok(authResponse);

    }
}