package com.blued;

import com.blued.dao.UserDao;
import com.blued.views.MainMenu;
import com.blued.views.View;

public class Application {
	public static void main(String[] args) {
		MainMenu.printBoat();
		System.out.println("WELCOME TO GOLD ROGER'S DINGHY OF DEBLOON DEPOSITS AND WITHDRAWALS AND TRANSFERS!");
		System.out.println("____________________________________________________________________________________");
		View currentView = new MainMenu();
		while (currentView != null) {
			currentView = currentView.process();
		}
		System.out.println("Good riddance ye scalleywag!");
	}
}