package com.codeiners.derp;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by razorhead on 10/26/14.
 */
public class Contact {

    @NotBlank
    @Email
    private String email;

    public Contact() {}

    public Contact(String email){
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
