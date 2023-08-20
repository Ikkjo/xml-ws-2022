package rs.ac.uns.ftn.XMLProject.Copyright.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    private final String USERNAME = "zavodzaintelektualnusvojinu@gmail.com";

    @Async
    public void send(String recipient, String id) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    "utf-8"
            );
            helper.setTo(recipient);
            helper.setFrom(USERNAME);
            helper.setText("\nПоштовани,\n\nу прилогу вам се налази решење поднетог захтева.\n\nСрдачно,\nЗавод за интелектуалну својину");
            File pdf = new File("gen/pdf/" + id + "-solution.pdf");
            helper.addAttachment("rešenje.pdf", pdf);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
    }
}
