package com.pmt.mvc.excpetion;

public class AccountAlreadyExistsException extends Exception {
	public AccountAlreadyExistsException(String message) {
		super(message);
	}

}
