package br.com.tcc.project.email;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage message);


    void sendHtmlEmail(MimeMessage message);

    void sendProfessorNotificationForAnaliseExpiration(NotificationDocument request);

    void sendNewPasswordEmail(UsuarioDocument customer, String newPassword);
    void sendOrderConfirmationHtmlEmail(EquivalenceDocument equivalenceDocument) throws MessagingException;
    void sendNewPasswordEmailHtml(UsuarioDocument usuarioDocument, String newPassword) throws MessagingException;
    void sendProfessorNotificationForAnaliseExpirationHtml(NotificationDocument request) throws MessagingException;
}
