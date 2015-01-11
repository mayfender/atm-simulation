package com.exercise.atm;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.exercise.atm.exception.NoteNotEnoughException;

/**
 * 
 * @author Mayfender
 *
 */
public class AtmSimulationMain {
	private static NoteManager noteManager;
	
	public static void main(String[] args) {
		Scanner sc = null;
		int menu;
		
		try {
			
			if(args == null || args.length != 2) {
				throw new Exception("Parameter are wrong please put 2 parameter as integer by 1st is amount of $20 note and 2nd is amount of $50 note");
			}
			
			// Initial amount of bank note $20.
			int note$20 = Integer.parseInt(args[0]);
			// Initial amount of bank note $50.
			int note$50 = Integer.parseInt(args[1]);
			
			NoteInfo noteInfo = new NoteInfo();
			noteInfo.setNote20(note$20);
			noteInfo.setNote50(note$50);
			
			noteManager = NoteManager.getInstance(noteInfo);
			
			System.out.println("################################");
			System.out.println("#### ATM Simalation program ####");
			System.out.println("####     Created by May     ####");
			System.out.println("################################");
			System.out.println();
			
			do {
				
				System.out.println("#############: Menus :#############");
				System.out.println("-> Enter 1 for Get Money.");
				System.out.println("-> Enter 2 for Get Report Bank Note Info.");
				System.out.println("-> Enter 3 Exit.");
				
				try {
					sc = new Scanner(System.in);
					menu = sc.nextInt();					
				} catch (InputMismatchException e) {
					System.out.println("**** You have to enter Number only. ****");
					System.out.println();
					continue;
				}
				
				if(menu != 1 && menu != 2 && menu != 3) {
					System.out.println("**** Don't have menu "+menu+" please enter again. ****");
					System.out.println();
					continue;					
				}
				
				if (menu == 1) {
					getMoney();
				} else if(menu == 2) {
					System.out.println();
					System.out.println(":----: Report Bank Note Info menu :----:");
					
					System.out.println("   -> Have $20 = " + noteManager.getNoteInfo().getNote20()+ " amt");
					System.out.println("   -> Have $50 = " + noteManager.getNoteInfo().getNote50()+ " amt");
					System.out.println(":======================================:");
					System.out.println("\n");
				} else {
					System.out.println("-------: Good Bye :------");
					break;
				}
				
			} while (true);
			
		} catch (Exception e) {
			System.err.println(e.toString());
		} finally {
			if(sc != null) sc.close();
		}
	}
	
	private static void getMoney() throws Exception {
		Scanner sc = null;
		int amount;
		
		System.out.println();
		System.out.println(":----: Get Money Menu :----:");
		
		try {
			do {
				System.out.println("Please enter your money : ");
				
				try {
					sc = new Scanner(System.in);
					amount = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("**** You have to enter Number only. ****");
					System.out.println();
					continue;
				}
				
				NoteInfo result = noteManager.requestMoney(amount);
				
				System.out.println();
				System.out.println(":------------: Result :-----------:");
				System.out.println("Paid to you amount : $" + ((result.getNote20() * 20) +  (result.getNote50() * 50)));
				
				StringBuilder msg = new StringBuilder();
				
				if(result.getNote20() != 0) {
					msg.append("   -> Bank note $20 amt("+result.getNote20() +") \n");
				}
				if(result.getNote50() != 0) {
					msg.append("   -> Bank note $50 amt(" +result.getNote50() + ") ");
				}
				
				System.out.println(msg);
				System.out.println(":======================================:");
				System.out.println("\n");
				
				break;
			} while (true);
		
		} catch (NoteNotEnoughException e) {			
			System.out.println("****** "+e.getMessage() + " ******\n");
		} catch (Exception e) {
			System.out.println("****** "+e.getMessage() + " ******\n");
		}
	}

}
