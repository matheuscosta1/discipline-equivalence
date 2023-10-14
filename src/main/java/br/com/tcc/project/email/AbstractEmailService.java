package br.com.tcc.project.email;

import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendProfessorNotificationForAnaliseExpiration(NotificationDocument request){
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromRequest(request);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromRequest(NotificationDocument notificationDocument){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationDocument.getEmail());
        simpleMailMessage.setFrom("Análise de equivalência em atraso" + "<" + sender + ">");
        simpleMailMessage.setSubject("Olá, " + notificationDocument.getAnalisesDocument().getProfessor().getNome());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("O senhor possui uma análise de equivalência de disciplinas pendente, faça login no portal de Equivalência de Disciplinas e confira. A data máxima para análise é: " +  convertData(notificationDocument.getAnalisesDocument().getDataMaxima()));
        return simpleMailMessage;
    }

    protected SimpleMailMessage prepareNewPasswordEmail(NotificationDocument notificationDocument, String newPassword){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationDocument.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("New Password");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("New password: "+ newPassword);
        return simpleMailMessage;
    }

    protected String htmlFromTemplateRequest(NotificationDocument request){
        Context context = new Context();
        context.setVariable("request", request);
        return templateEngine.process("email/requestConfirmation", context);
    }

    private String convertData(Date maximumDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(maximumDate);
        } catch (Exception e) {
            return "Date format error";
        }
    }
}
