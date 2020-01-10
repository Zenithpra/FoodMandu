package com.zenith.foodmandu.model;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmpassword;
    private String image;

    public User(String firstName, String lastName, String username, String password, String confirmpassword, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getConfirmpassword() { return confirmpassword; }

    public void setConfirmpassword(String confirmpassword) { this.confirmpassword = confirmpassword; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
