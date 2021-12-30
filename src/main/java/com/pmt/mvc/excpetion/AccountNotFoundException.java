package com.pmt.mvc.excpetion;

public class AccountNotFoundException extends Exception{
	public AccountNotFoundException(String message)
	{
		super(message);
	}
}
