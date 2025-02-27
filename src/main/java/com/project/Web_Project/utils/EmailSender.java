package com.project.Web_Project.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailSender {
    private Secret secret = new Secret();

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

    public EmailSender(String email, User user) throws MessagingException {
        Session session = Session.getInstance(setProps(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(secret.getFrom(), secret.getPass());
            }
        });
        session.setDebug(true);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(secret.getFrom()));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
        message.setSubject("Registration code:");
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < 6; i++){
            code.append(new Random().nextInt(10));
        }
        user.setRegistrationCode(String.valueOf(code));
        message.setText(code + "\nЕсли это не вы запросили код подтверждения учётной записи, проигнорируйте это сообщение");
        Transport.send(message);
    }
}
