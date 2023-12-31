package br.com.tcc.project.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Sending email");
        mailSender.send(message);
        LOGGER.info(message.toString());
        LOGGER.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Sending email");
        javaMailSender.send(message);
        LOGGER.info(message.toString());
        LOGGER.info("Email sent");
    }
}
