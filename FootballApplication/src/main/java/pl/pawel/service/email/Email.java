package pl.pawel.service.email;

public class Email {

	private String address;
	private String subject;
	private String message;
	
	public Email() {}

	
	@Override
	public String toString() {
		return "Email [address=" + address + ", subject=" + subject + ", message=" + message + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
