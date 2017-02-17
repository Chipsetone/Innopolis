package com.semakin.loggers.emailer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Семакин Виктор
 */
public class EmailSender {
    private String fromAddress = "";
    private String login = "";
    private String password = "";
    private String smtpHost = "";
    private String smtpPort = "";

    public EmailSender(String fromAddress, String login, String password, String smtpHost, String smtpPort) {
        this.fromAddress = fromAddress;
        this.login = login;
        this.password = password;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public boolean send(String messageText, String messageTheme, String toAddress){
        try {
            Message message = getMessageTemplate(toAddress, messageTheme);
            message.setText(messageText);
            sendMessage(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected final void sendMessage(Message message) throws MessagingException {
        Transport.send(message);
    }

    protected final Message getMessageTemplate(String toAddress, String messageTheme) throws MessagingException {
        Properties props = getProperties(smtpHost, smtpPort);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddress));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toAddress));
        message.setSubject(messageTheme);

        return message;
    }

    private Properties getProperties(String smtpHost, String smtpPort){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.socketFactory.port", smtpPort);
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", smtpPort);

        return properties;
    }
}
