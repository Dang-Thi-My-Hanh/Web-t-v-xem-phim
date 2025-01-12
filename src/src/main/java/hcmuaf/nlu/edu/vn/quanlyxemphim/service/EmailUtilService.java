package hcmuaf.nlu.edu.vn.quanlyxemphim.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtilService {



    //Gửi email trong luồng riêng biệt
    public void sendEmailAsync(String email, String subject, String content) {
        new Thread(() -> {
            try {
                sendEmail(email, subject,content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        final String fromEmail = "mailpost102@gmail.com";
        final String fromPassword = "ygitxreincgpqnbc";
        final String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(body, "text/html; charset=UTF-8");  // Đảm bảo nội dung email là HTML

        Transport.send(message);
    }


}


