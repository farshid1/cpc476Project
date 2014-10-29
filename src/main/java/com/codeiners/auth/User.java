package com.codeiners.auth;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by razorhead on 10/25/14.
 */
public class User {


    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    public User() {}

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
