package cl.govegan.msauthuser.auth.services.mailservice;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String template, Context context) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String html = templateEngine.process(template, context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);
        helper.setFrom("hello.govegan@zohomail.com");  // Asegúrate de que esto sea lo mismo que tu spring.mail.username

        javaMailSender.send(message);
    }
}