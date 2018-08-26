package pl.pawel.service.email;

public interface EmailSender {

	public void sendEmail(String to, String subject, String message);
	
	public void sendEmail(Email email);
	
	public void sendEmailWithAttachment(Email email, String pathToAttachment);
}
