package com.blued;

import com.blued.dao.UserDao;
import com.blued.views.MainMenu;
import com.blued.views.View;

public class Application {
	public static void main(String[] args) {
		View currentView = new MainMenu();
		while (currentView != null) {
			currentView = currentView.process();
		}
		System.out.println("Good riddance ye scalleywag!");
	}
}