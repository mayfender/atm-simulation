package com.exercise.atm.exception;

public class NoteNotEnoughException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 189019996310328969L;

	
	public NoteNotEnoughException(){}
	
	public NoteNotEnoughException(String str) {
		super(str);
	}

}
