package com.project.Web_Project.service;

import com.project.Web_Project.dto.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
//Sends email with confirm code
public class EmailConfirm extends EmailSender {
    public EmailConfirm(User user) throws MessagingException {
        //configuration SMTP server
        Session session = super.setUpEmailSender(user);
        //configuration message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(super.secret.getEmailFrom()));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
        message.setSubject("Code:");
        StringBuilder code = new StringBuilder();
        //generates simple code
        for(int i = 0; i < 6; i++){
            code.append(new Random().nextInt(10));
        }
        user.setCode(String.valueOf(code));
        message.setText(code + "\nЕсли это не вы запросили код подтверждения учётной записи, проигнорируйте это сообщение");
        Transport.send(message);
    }
}
