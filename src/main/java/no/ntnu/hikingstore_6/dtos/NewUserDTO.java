package no.ntnu.hikingstore_6.dtos;

public class NewUserDTO {
    private final String email;
    private final String password;
    private final String address;
    private final int zipcode;

    public NewUserDTO(String email, String username, String password,String address, int zipcode) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public int getZipcode() {return zipcode;}
}
