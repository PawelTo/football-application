package pl.pawel.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImplementation implements EmailSender{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String to, String subject, String message) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message);
			//some emails like gmail doesn't need to setFrom explicitly
			helper.setFrom("spring.tutorial@o2.pl");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSender.send(mail);
	}

	@Override
	public void sendEmail(Email email) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(email.getAddress());
			helper.setSubject(email.getSubject());
			helper.setText(email.getMessage());
			//some emails like gmail doesn't need to setFrom explicitly
			helper.setFrom("spring.tutorial@o2.pl");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSender.send(mail);
	}
	
	@Override
	public void sendEmailWithAttachment(Email email, String pathToAttachment) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(email.getAddress());
			helper.setSubject(email.getSubject());
			helper.setText(email.getMessage());
			FileSystemResource file = new FileSystemResource(pathToAttachment);
			String fileFormat = file.getFilename().substring(file.getFilename().lastIndexOf("."));
			helper.addAttachment("Nazwa_Zalacznika"+fileFormat, file);
			//some emails like gmail doesn't need to setFrom explicitly
			helper.setFrom("spring.tutorial@o2.pl");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaMailSender.send(mail);
	}

}
