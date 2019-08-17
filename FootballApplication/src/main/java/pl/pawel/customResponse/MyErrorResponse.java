package pl.pawel.customResponse;

public class MyErrorResponse {

	private String message;
	private int num;

	
	public MyErrorResponse() {}
	
	public MyErrorResponse(String message, int num) {
		this.message = message;
		this.num = num;
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
}
