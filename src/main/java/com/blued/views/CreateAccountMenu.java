package com.blued.views;

import com.blued.services.BankService;

public class CreateAccountMenu implements View {
	
	public View process() {
		BankService.createAccount();
		return new MainMenu();
	}
}
