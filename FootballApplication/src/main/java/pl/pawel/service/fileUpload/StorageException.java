package pl.pawel.service.fileUpload;

public class StorageException extends RuntimeException{

	public StorageException(String message) {
		super(message);
	}
	
	public StorageException(String message, Throwable cause) {
		super(message,cause);
	}
}
