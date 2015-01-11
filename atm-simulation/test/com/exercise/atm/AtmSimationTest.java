package com.exercise.atm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.exercise.atm.exception.NoteNotEnoughException;

/**
 * 
 * @author Mayfender
 * 
 */

public class AtmSimationTest {
	private NoteManager noteManager;
	
	@Before
	public void startUp() {
		
		NoteInfo noteInfo = new NoteInfo();
		noteInfo.setNote20(10);
		noteInfo.setNote50(10);

		noteManager = NoteManager.getInstance(noteInfo);
	}

	@After
	public void tearDown() {
		// TODO:
	}
	
	@Test(expected=NoteNotEnoughException.class)
	public void requestOverAtmThrowNoteNotEnoughException() throws Exception {
		try {
			
			// Have to throw NoteNotEnoughException because in the ATM have only $700
			noteManager.requestMoney(720);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expected=Exception.class)
	public void notAccordingNoteThrowException() throws Exception {
		try {			
			
			// Have to throw Exception because there are only $20 note and $50
			noteManager.requestMoney(10);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
