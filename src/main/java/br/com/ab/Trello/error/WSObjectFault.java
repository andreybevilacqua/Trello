package br.com.ab.Trello.error;

public class WSObjectFault {

	private String faultCode;
	private String faultMessage;
	
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
	
	public String getFaultCode() {
		return faultCode;
	}
	
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	
	public String getFaultString() {
		return faultMessage;
	}
	
	public void setFaultString(String faultString) {
		this.faultMessage = faultString;
	}
	
}
