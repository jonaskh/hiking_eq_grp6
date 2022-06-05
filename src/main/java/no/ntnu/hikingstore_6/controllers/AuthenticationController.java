package no.ntnu.hikingstore_6.controllers;

import javax.validation.Valid;

import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.models.AuthenticationRequest;
import no.ntnu.hikingstore_6.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.ntnu.hikingstore_6.security.JwtTokenUtil;

@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthenticationResponse response = new AuthenticationResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
