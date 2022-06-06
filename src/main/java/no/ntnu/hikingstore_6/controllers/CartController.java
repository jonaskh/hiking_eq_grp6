package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.dtos.AddItemToCardDTO;
import no.ntnu.hikingstore_6.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.security.RolesAllowed;

public class CartController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping
    @RolesAllowed("{ROLE_CUSTOMER}")
    public ResponseEntity<?> addItemToCart(
            @RequestHeader("Authorization") String authorization, @RequestBody AddItemToCardDTO requestBody) {

        long userID = this.getUserID(authorization);


    }



    private long getUserID(String jwtHeader) {
        String jwt = jwtHeader.substring(7);
        return jwtTokenUtil.extractUserID(jwt);
    }
}
