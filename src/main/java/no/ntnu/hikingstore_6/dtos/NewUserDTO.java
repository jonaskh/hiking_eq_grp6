package no.ntnu.hikingstore_6.dtos;

public class NewUserDTO {
    private final String email;
    private final String username;
    private final String password;

    public NewUserDTO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
