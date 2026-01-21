package com.wipro.bams.util;

public class InvalidCustomerException extends Exception{
	private static final long serialVersionUID = 1L; 
	public InvalidCustomerException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "InvalidCustomerException"+getMessage();
	}

}
