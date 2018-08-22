package pl.pawel.service.email;

public interface EmailSender {

	public void sendEmail(String to, String subject, String text);
}
