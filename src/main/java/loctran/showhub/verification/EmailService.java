package loctran.showhub.verification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import loctran.showhub.dto.MailBody;
import loctran.showhub.exceptions.EmailSendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${app.base-url}")
    private String baseUrl;

    @Async
    public void sendVerificationEmail(MailBody mailBody) {
        String verificationUrl = baseUrl + "/verify-email?token=" + mailBody.verificationToken();

        String htmlBody = """
            <html>
            <body style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;">
                <h2>Verify Your Email Address</h2>
                <p>Thank you for registering! Please click the button below to verify your email.</p>
                <a href="%s" style="
                    display: inline-block;
                    padding: 12px 24px;
                    background-color: #4CAF50;
                    color: white;
                    text-decoration: none;
                    border-radius: 4px;
                    margin: 16px 0;
                ">Verify Email</a>
                <p>Or copy this link: <a href="%s">%s</a></p>
                <p>This link expires in 24 hours.</p>
                <p>If you didn't create an account, please ignore this email.</p>
            </body>
            </html>
            """.formatted(verificationUrl, verificationUrl, verificationUrl);

        sendHtmlEmail(mailBody, htmlBody);
    }

    @Async
    public void sendPasswordResetEmail(MailBody mailBody) {
        String resetUrl = baseUrl + "/api/auth/reset-password?token=" + mailBody.verificationToken();

        String htmlBody = """
            <html>
            <body style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;">
                <h2>Reset Your Password</h2>
                <p>You requested a password reset. Click the button below to set a new password.</p>
                <a href="%s" style="
                    display: inline-block;
                    padding: 12px 24px;
                    background-color: #2196F3;
                    color: white;
                    text-decoration: none;
                    border-radius: 4px;
                    margin: 16px 0;
                ">Reset Password</a>
                <p>Or copy this link: <a href="%s">%s</a></p>
                <p><strong>This link expires in 15 minutes.</strong></p>
                <p>If you didn't request this, please ignore this email. Your password will not change.</p>
            </body>
            </html>
            """.formatted(resetUrl, resetUrl, resetUrl);

        sendHtmlEmail(mailBody, htmlBody);
    }

    private void sendHtmlEmail(MailBody mailBody, String htmlBody) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(mailBody.to());
            helper.setSubject(mailBody.subject());
            helper.setText(htmlBody, true);
            mailSender.send(message);
            log.info("Email sent to {}", mailBody.to());
        } catch (MessagingException e) {
            throw new EmailSendException("Failed to send email");
        }

    }
}
