package tests.com.semain.loggers;

import com.semakin.loggers.EmailSender;
import org.junit.jupiter.api.Test;

/**
 * @author Семакин Виктор
 */
class EmailSenderTest {
    @Test
    void send() {
        String fromAddress = "v.semakin.stc@innopolis.ru";

        String login = "v.semakin.stc@innopolis.ru";
        String password = "xUku8ePhaG";
        String smtpHost = "mail.innopolis.ru";
        String smtpPort = "587";

        EmailSender sender = new EmailSender(fromAddress, login, password, smtpHost, smtpPort);

        sender.send("some message", "some Theme", "chipsetone@gmail.com");
    }

}