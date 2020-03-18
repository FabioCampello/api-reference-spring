package com.spring.tutorial.commons.exception;

public class ServicosOnlineApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServicosOnlineApiException(String message) {
		super(message);
	}
	
	public ServicosOnlineApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
