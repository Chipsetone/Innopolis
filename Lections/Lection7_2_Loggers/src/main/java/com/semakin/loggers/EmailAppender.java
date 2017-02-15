package com.semakin.loggers;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Семакин Виктор
 */
public class EmailAppender extends AppenderSkeleton{
    private String fromAddress;
    private String login;
    private String password;
    private String smtpHost;
    private String smtpPort;

    private String toAddress;
    private String messageTheme;
    private String defaultMessageText = "";

    @Override
    protected void append(LoggingEvent loggingEvent) {
        String preparedMessage = getDefaultMessageText() + layout.format(loggingEvent);

        send(preparedMessage);


    }

    public void send(String message){
        EmailSender sender = new EmailSender(fromAddress, login, password, smtpHost, smtpPort);

        sender.send(message, getMessageTheme(), getToAddress());
    }

    @Override
    public void close() {
        System.out.println("custom appender closed");
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getDefaultMessageText() {
        if(defaultMessageText == null){
            return "";
        }
        return defaultMessageText;
    }

    public void setDefaultMessageText(String defaultMessageText) {
        this.defaultMessageText = defaultMessageText;
    }

    public String getMessageTheme() {
        return messageTheme;
    }

    public void setMessageTheme(String messageTheme) {
        this.messageTheme = messageTheme;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
}
