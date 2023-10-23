package br.com.tcc.project.email;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
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
    public void sendProfessorNotificationForAnaliseExpiration(NotificationDocument notificationDocument){
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromRequest(notificationDocument);
        sendEmail(simpleMailMessage);
    }

    @Override
    public void sendProfessorNotificationForAnaliseExpirationHtml(NotificationDocument notificationDocument) throws MessagingException {
        MimeMessage mimeMessage = prepareMimeMessageForDelayedEquivalenceAnalysis(notificationDocument);
        sendHtmlEmail(mimeMessage);
    }
    @Override
    public void sendNewPasswordEmail(UsuarioDocument usuarioDocument, String newPassword){
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(usuarioDocument, newPassword);
        sendEmail(simpleMailMessage);
    }

    @Override
    public void sendNewPasswordEmailHtml(UsuarioDocument usuarioDocument, String newPassword) throws MessagingException {
        MimeMessage mimeMessage = prepareMimeMessageNewPasswordEmail(usuarioDocument, newPassword);
        sendHtmlEmail(mimeMessage);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(EquivalenceDocument equivalenceDocument) throws MessagingException {
        MimeMessage mimeMessageProfessor = prepareMimeMessageFromRequestToProfessor(equivalenceDocument);
        sendHtmlEmail(mimeMessageProfessor);

        MimeMessage mimeMessageAdmin = prepareMimeMessageFromRequestToAdmin(equivalenceDocument);
        sendHtmlEmail(mimeMessageAdmin);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromRequest(NotificationDocument notificationDocument){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationDocument.getEmail());
        simpleMailMessage.setFrom("Análise de equivalência em atraso" + "<" + sender + ">");
        simpleMailMessage.setSubject("Prezado(a) Professor(a) " + notificationDocument.getAnalisesDocument().getProfessor().getNome());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        String dataMaxima = convertData(notificationDocument.getAnalisesDocument().getDataMaxima());
        String mensagem = "Espero que esteja bem. Gostaríamos de informar que você possui uma análise de equivalência de disciplinas pendente no nosso sistema. A data limite para conclusão é " + dataMaxima + ". Por favor, faça login no Portal de Equivalência de Disciplinas para realizar a análise. Se precisar de assistência ou tiver alguma dúvida, não hesite em nos contatar. Obrigado pela atenção!";
        simpleMailMessage.setText(mensagem);
        return simpleMailMessage;
    }
    protected SimpleMailMessage prepareNewPasswordEmail(UsuarioDocument usuarioDocument, String newPassword){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(usuarioDocument.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("New Password");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("New password: "+ newPassword);
        return simpleMailMessage;
    }

    protected MimeMessage prepareMimeMessageNewPasswordEmail(UsuarioDocument usuarioDocument, String newPassword) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(usuarioDocument.getEmail());
        mimeMessageHelper.setFrom("Nova senha de acesso ao Portal de Equivalência" + "<" + sender + ">");
        mimeMessageHelper.setSubject("Nova senha de acesso ao Portal de Equivalência.");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateForNewPassword(newPassword), true);
        return mimeMessage;
    }

    protected MimeMessage prepareMimeMessageForDelayedEquivalenceAnalysis(NotificationDocument notificationDocument) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(notificationDocument.getEmail());
        mimeMessageHelper.setFrom("Análise de equivalência em atraso" + "<" + sender + ">");
        mimeMessageHelper.setSubject("Análise de equivalência em atraso.");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateForDelayedEquivalence(notificationDocument), true);
        return mimeMessage;
    }
    
    protected MimeMessage prepareMimeMessageFromRequestToProfessor(EquivalenceDocument analisesDocument) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(analisesDocument.getAnalisesDocument().getProfessor().getUsuario().getEmail());
        mimeMessageHelper.setFrom("Recibo de Registro de Equivalência" + "<" + sender + ">");
        mimeMessageHelper.setSubject("Recibo de Registro de Equivalência");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTempleForEquivalenceRegisterNotification(analisesDocument), true);
        return mimeMessage;
    }

    protected MimeMessage prepareMimeMessageFromRequestToAdmin(EquivalenceDocument analisesDocument) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(analisesDocument.getAnalisesDocument().getUsuarioAdmin().getEmail());
        mimeMessageHelper.setFrom("Recibo de Registro de Equivalência" + "<" + sender + ">");
        mimeMessageHelper.setSubject("Recibo de Registro de Equivalência");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTempleForEquivalenceRegisterNotification(analisesDocument), true);
        return mimeMessage;
    }

    protected String htmlFromTempleForEquivalenceRegisterNotification(EquivalenceDocument equivalenceDocument){
        Context context = new Context();
        context.setVariable("equivalenceDocument", equivalenceDocument);
        return templateEngine.process("email/registroEquivalencia", context);
    }

    protected String htmlFromTemplateForNewPassword(String newPassword){
        Context context = new Context();
        context.setVariable("newPassword", newPassword);
        return templateEngine.process("email/novaSenha", context);
    }

    protected String htmlFromTemplateForDelayedEquivalence(NotificationDocument notificationDocument){
        Context context = new Context();
        context.setVariable("notificationDocument", notificationDocument);
        return templateEngine.process("email/analiseEquivalenciaEmAtraso", context);
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
