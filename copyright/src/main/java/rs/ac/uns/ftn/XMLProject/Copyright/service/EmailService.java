package rs.ac.uns.ftn.XMLProject.Copyright.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Async
    public void send(String recipient, String id) throws IOException {
        {
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("zavodzaintelektualnusvojinu@gmail.com");
            mailSender.setPassword("q._vA2F/S&yLPZ.i(]4&.?j7}A(D24y;Y\\+-)'t-kR-R");
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.ssl.trust", "*");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.connectiontimeout", "5000");
            props.put("mail.smtp.timeout", "3000");
            props.put("mail.smtp.writetimeout", "5000");
        }
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    "utf-8"
            );
            helper.setTo(recipient);
            helper.setFrom("zavodzaintelektualnusvojinu@gmail.com");
            helper.setText("""
                    Поштовани,

                    у прилогу вам се налази решење поднетог захтева.

                    Срдачно,
                    Завод за интелектуалну својину""");
            FileSystemResource pdf = new FileSystemResource(new File("gen/pdf/" + id + "-solution.pdf"));
            helper.addAttachment("rešenje.pdf", pdf, "application/pdf");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
    }
}
