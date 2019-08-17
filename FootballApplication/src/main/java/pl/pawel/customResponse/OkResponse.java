package pl.pawel.customResponse;

import java.time.LocalDate;

public class OkResponse {

	private String message;
	private int num;
	private LocalDate date;
	
	public OkResponse(String message, int num, LocalDate date) {
		this.message = message;
		this.num = num;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
