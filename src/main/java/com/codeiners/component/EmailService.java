package com.codeiners.component;

import org.apache.commons.mail.EmailException;

/**
 * Created by razorhead on 10/28/14.
 */
public interface EmailService {
    public void sendEmail(String sendFrom, String sendTo, String subject, String message) throws EmailException;
}
