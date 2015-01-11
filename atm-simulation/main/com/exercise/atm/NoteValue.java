package com.exercise.atm;

public enum NoteValue {
	NOTE_20(20), NOTE_50(50);
	
	private int value;
	
	private NoteValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
