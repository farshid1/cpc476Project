package com.codeiners;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by razorhead on 10/27/14.
 */
@Configuration
public class EmailConfiguration {
    @Bean
    public void sendEmail(String username, String sendTo, String sendFrom) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2226);
        //email.setAuthenticator(new DefaultAuthenticator("username", "password"));
        email.setFrom(sendFrom);
        email.setSubject("DERP");
        email.setMsg("Derp");
        email.addTo(sendTo);
        email.send();
    }
}
