package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.models.AuthenticateRequest;
import no.ntnu.hikingstore_6.models.AuthenticateResponse;
import no.ntnu.hikingstore_6.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenitcationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtutil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest)
            throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),
                            authenticateRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetail = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        final String jwt = jwtutil.generateToken(userDetail);

        return ResponseEntity.ok(new AuthenticateResponse(jwt));

    }

}
