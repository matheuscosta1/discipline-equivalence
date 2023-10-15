package br.com.tcc.project.email;

import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage message);


    void sendHtmlEmail(MimeMessage message);

    void sendProfessorNotificationForAnaliseExpiration(NotificationDocument request);

    void sendNewPasswordEmail(UserDocument customer, String newPassword);

}