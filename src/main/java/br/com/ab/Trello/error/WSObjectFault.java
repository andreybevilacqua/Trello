package br.com.ab.Trello.error;

public class WSObjectFault {

	private String faultCode;
	private String faultMessage;

	// Constructors
	public WSObjectFault(){
		
	}
	
	public WSObjectFault(ErrorMessage errorMessage){
		faultCode = errorMessage.getCode();
		faultMessage = errorMessage.getMessage();
	}
	
	public WSObjectFault(String faultCode, String faultString){
		this.faultCode = faultCode;
		this.faultMessage = faultString;
	}

	// Gets
	public String getFaultCode() {
		return faultCode;
	}

	public String getFaultString() {
		return faultMessage;
	}

	// Sets
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	
	public void setFaultString(String faultString) {
		this.faultMessage = faultString;
	}

}
