package no.ntnu.hikingstore_6.models;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJWT () {
        return jwt;
    }
}
