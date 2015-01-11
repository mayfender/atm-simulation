package com.exercise.atm;

import com.exercise.atm.exception.NoteNotEnoughException;

/**
 * 
 * @author Mayfender
 *
 */
public class NoteManager {
	private static NoteManager instance;
	private NoteInfo noteInfo;
	
	private NoteManager(NoteInfo noteInfo) {
		this.noteInfo = noteInfo;
	}
	
	public static synchronized NoteManager getInstance(NoteInfo noteInfo) {
		if(instance == null) 
			instance = new NoteManager(noteInfo);
		
		return instance;
	}
	
	/**
	 * 
	 * @param require
	 * @return
	 * @throws Exception
	 */
	public NoteInfo requestMoney(int require) throws Exception {
		
		try {
			NoteInfo result = new NoteInfo();
			int note50Sub;
			int note50Mul;
			int note20Mod;
			int note50Result;
			
			// 1. Find amount of bank note $50.
			note50Result = require / NoteValue.NOTE_50.getValue();
			
			// 2. Check if amount of bank note $50 less than required.
			if(noteInfo.getNote50() < note50Result){
				note50Result = noteInfo.getNote50();
			}
			
			// 3. Process of finding the properly result bank note.
			while(true) {
				// 3.1 Find bank note $50 value
				note50Mul = note50Result * NoteValue.NOTE_50.getValue();			
				
				// 3.2 Find value for using with bank note $20.
				note50Sub = require - note50Mul;
				
				// 3.3 Check that does value according with bank note $20 ?
				note20Mod = note50Sub % NoteValue.NOTE_20.getValue();
				if(note20Mod == 0 || note50Result == 0) {
					break;
				}
				
				// 3.4 Decrease amount of bank note $50 by one.
				note50Result--;
			}
			
			// 4. Check the error.
			if(note20Mod != 0) {
				throw new Exception("In an ATM with only $20 and $50 notes, it is not possible to dispense $"+require+". ");
			}
			
			// 5. Get amount of bank note $20.
			int note20Result = note50Sub / NoteValue.NOTE_20.getValue();
			
			// 6. Check bank note enough ?
			if((noteInfo.getNote20() < note20Result) || 
					noteInfo.getNote50() < note50Result) {
				
				throw new NoteNotEnoughException("ATM have money not enough.");
			}
			
			// 7. Set amount of bank note $50.
			decreaseNote50(note50Result);
			result.setNote50(note50Result);
			
			// 8. Set amount of bank note $20.
			decreaseNote20(note20Result);
			result.setNote20(note20Result);
			
			// 9. Return result.
			return result;
		} catch (NoteNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param amount
	 * @throws Exception
	 */
	public void decreaseNote20(int amount) throws Exception {
		noteInfo.setNote20(noteInfo.getNote20() - amount);
	}
	
	/**
	 * 
	 * @param amount
	 * @throws Exception
	 */
	public void decreaseNote50(int amount) throws Exception {
		noteInfo.setNote50(noteInfo.getNote50() - amount);
	}

	public NoteInfo getNoteInfo() {
		return noteInfo;
	}
	
}