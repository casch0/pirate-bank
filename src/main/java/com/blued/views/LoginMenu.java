package com.blued.views;

import com.blued.util.ScannerUtil;
import com.blued.dao.UserDao;
import com.blued.models.Pirate;

import java.util.List;

public class LoginMenu implements View{

	@Override
	public View process() {
		boolean logIn = false;
		System.out.println("First, ye need to tell me yer pirate name! (Enter username or type 'back' to go to the main menu)");
		String name = ScannerUtil.getStringInput();
		if(name.equals("back")) return new MainMenu();
		Pirate pirate = UserDao.getPiratebyName(name);
		if(pirate == null) {
			System.out.println("Ye arrr not on the list ye scalleywag! (Username not found)");
			return new LoginMenu();
		}
		System.out.println("Good, now whisper in me ear yer secret pirate code... (Enter password or type 'back' to select a new user)");
		String pw = "";
		while(pw.isEmpty()) {
			pw = ScannerUtil.getStringInput();
			if(pw.equals("back")) return new LoginMenu();
			else if(!pw.equals(pirate.getSecret_code())) {
				System.out.println("Have ye forgotten ye secret code?! Try again ye whale fart! (Wrong password)");
				pw = "";
			}
			else logIn = true;
		}
		if(!logIn) {
			return new MainMenu();
		}
		else {
			return new UserMenu(pirate);
		}
	}
	
	

}