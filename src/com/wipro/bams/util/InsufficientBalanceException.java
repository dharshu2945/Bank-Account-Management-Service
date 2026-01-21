package com.wipro.bams.util;

public class InsufficientBalanceException extends Exception{
	private static final long serialVersionUID = 1L; 

	public InsufficientBalanceException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "InsufficientBalanceException"+getMessage();
	}
	

}
