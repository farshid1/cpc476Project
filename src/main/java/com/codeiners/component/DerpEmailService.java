package com.codeiners.component;

import com.codeiners.component.EmailService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

/**
 * Created by razorhead on 10/28/14.
 */
@Component
public class DerpEmailService implements EmailService {

    @Override
    public void sendEmail(String sendFrom, String sendTo, String subject, String message) throws EmailException {
        /* Send Invite */
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2226);
        email.setFrom(sendFrom);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(sendTo);
        email.send();
    }
}
