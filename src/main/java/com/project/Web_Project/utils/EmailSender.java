package com.project.Web_Project.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailSender {
    protected Secret secret = new Secret();
    private Properties setProps(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", secret.getHost());
        properties.put("mail.smtp.port", secret.getPort());
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }

    protected Session setUpEmailSender(User user) throws MessagingException {
        Session session = Session.getInstance(setProps(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(secret.getEmailFrom(), secret.getEmailPass());
            }
        });
        session.setDebug(true);
        return session;
    }
}
