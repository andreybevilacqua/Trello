package br.com.ab.Trello.error;

public enum ErrorMessage {

	EMPTY_NULL_PARAMETERS("0001", "Error: Empty or null parameter"),
	ID_NULL_MINOR_ZERO("0002", "Error: Id null or minor then 0");
	
	private final String code;
	private final String message;

	// Constructors
	private ErrorMessage(String code, String message){
		this.code = code;
		this.message = message;
	}

	// Gets
	
	public String getCode(){return code;}
	public String getMessage(){return message;}

	// Sets

	// Methods
	@Override
	public String toString(){
		return code + ": " + message;
	}
	
}
