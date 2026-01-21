package com.wipro.bams.util;

public class TransactionException extends Exception{
	private static final long serialVersionUID = 1L; 
	public TransactionException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "TransactionException"+getMessage();
	}
	

}
