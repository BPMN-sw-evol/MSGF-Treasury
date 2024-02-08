package com.msgfoundation.MSGFTreasury.services;

import com.msgfoundation.MSGFTreasury.model.Payment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    public void updatePayment(Long codRequest) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/credit_request", "postgres", "admin");

        String updateQuery = "UPDATE credit_request SET payment = true WHERE cod_request = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setLong(1, codRequest);
            updateStatement.executeUpdate();
            System.out.println("CONTROLLER: The payment was made");
        }

    }

    public void sendPaymentNotification(Payment payment){
        String subject = "Informe de Desembolso Realizado";
        String templateName = "InformeDesembolso";
        Context context = new Context(Locale.getDefault());
        context.setVariable("processId",payment.getProcessId());
        context.setVariable("coupleName1", payment.getCoupleName1());
        context.setVariable("coupleName2", payment.getCoupleName2());
        context.setVariable("coupleEmail1", payment.getCoupleEmail1());
        context.setVariable("coupleEmail2", payment.getCoupleEmail2());
        context.setVariable("housePrices", payment.getHousePrices());
        context.setVariable("quotaValue", payment.getQuotaValue());
        context.setVariable("paymentDate", payment.getDate());

        String message = templateEngine.process(templateName, context);

        sendEmail(new String[]{payment.getCoupleEmail1(), payment.getCoupleEmail2()}, subject, message);
    }

    private void sendEmail(String[] to, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
