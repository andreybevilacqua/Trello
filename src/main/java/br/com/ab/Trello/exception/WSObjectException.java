package br.com.ab.Trello.exception;

import javax.xml.ws.WebFault;

import br.com.ab.Trello.error.WSObjectFault;

@WebFault(name = "WSObjectFault")
public class WSObjectException extends Exception {

	private static final long serialVersionUID = 1L;
	private WSObjectFault fault;
	
	public WSObjectException(){
		
	}
	
	public WSObjectException(WSObjectFault fault){
		super(fault.getFaultString());
		this.fault = fault;
	}
	
	public WSObjectException(String message){
		super(message);
	}
	
	public WSObjectException(String message, WSObjectFault fault){
		super(message);
		this.fault = fault;
	}
	
	public WSObjectException(String message, WSObjectFault fault, Throwable cause){
		super(message, cause);
		this.fault = fault;
	}
	
	public WSObjectFault getWSObjectFault(){
		return fault;
	}
	
	public WSObjectException(String code, String message) {
		super(message);
		this.fault = new WSObjectFault();
	    this.fault.setFaultString(message);
	    this.fault.setFaultCode(code);
	}
	
}
