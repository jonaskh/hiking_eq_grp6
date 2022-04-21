package no.ntnu.hikingstore_6.security;

public class AuthenticateResponse {

    private final String jwt;

    public AuthenticateResponse(String jwt) {
        this.jwt = jwt;
    }


    public String getJwt () {
        return jwt;
    }
}
