package com.semakin.loggers.emailer;

import com.semakin.loggers.emailer.EmailSender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * @author Семакин Виктор
 */
public class EmailFileSender extends EmailSender {
    public EmailFileSender(String fromAddress, String login, String password, String smtpHost, String smtpPort) {
        super(fromAddress, login, password, smtpHost, smtpPort);
    }

    public boolean send(String messageText, String messageTheme, String toAddress, String filePath) {
        try {
            Message message = getMessageTemplate(toAddress, messageTheme);
            Multipart textWithFileMultipart = getMessageMultipart(messageText, filePath);
            message.setContent(textWithFileMultipart);

            sendMessage(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // закинуть текст сообщения
        // закинуть файлы

        // отправить
        return true;
    }


    private Multipart getMessageMultipart(String filePath, String messageText) throws MessagingException {
        Multipart multipart = new MimeMultipart();

        BodyPart messageBodyPart = getMessageTextPart(messageText);
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = getFilePart(filePath);
        multipart.addBodyPart(messageBodyPart);

        return multipart;
    }
    private BodyPart getMessageTextPart(String messageText) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageText);

        return messageBodyPart;
    }

    private BodyPart getFilePart(String filePath) throws MessagingException {
        BodyPart fileBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        fileBodyPart.setDataHandler(new DataHandler(source));
        fileBodyPart.setFileName(filePath);

        return fileBodyPart;
    }


}
