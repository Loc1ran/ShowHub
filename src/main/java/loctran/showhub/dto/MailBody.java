package loctran.showhub.dto;

public record MailBody(
        String to,
        String verificationToken,
        String subject
){}
