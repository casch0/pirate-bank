package com.blued.views;

import com.blued.dao.UserDao;
import com.blued.models.Pirate;
import com.blued.util.ScannerUtil;
import java.math.BigDecimal;

public class CreateChestMenu implements View {

	Pirate pirate;

	public CreateChestMenu(Pirate pirate) {
		this.pirate = pirate;
	}

	@Override
	public View process() {

		System.out.println("Choose what kind of chest ye want to make!");
		printMenu();
		int input = ScannerUtil.getInput(4);
		if(input == 0) return new MainMenu();
		System.out.println("How much would ye like to deposit?");
		double deposit = ScannerUtil.getInput(10_000);
		int pirate_number = pirate.getPirate_number();
		BigDecimal amount = java.math.BigDecimal.valueOf(deposit);

		switch (input) {
		case 0:
			return new MainMenu();
		case 1: {
			UserDao.createChest(pirate_number, amount, "Checking");
			System.out.println("Chest has been created!");
			return new UserMenu(pirate);
		}
		case 2: {
			UserDao.createChest(pirate_number, amount, "Savings");
			System.out.println("Chest has been created!");
			return new UserMenu(pirate);
		}
		case 3: {
			UserDao.createSharedChest(pirate_number, amount, "Joint Checkings");
			System.out.println("Chest has been created!");
			addUsersToSharedChest();
			return new UserMenu(pirate);
		}
		case 4: {
			UserDao.createSharedChest(pirate_number, amount, "Joint Savings");
			System.out.println("Chest has been created!");
			addUsersToSharedChest();
			return new UserMenu(pirate);
		}
		}
		return new UserMenu(pirate);
	}

	public void printMenu() {
		System.out.println("1. Personal Checking Chest");
		System.out.println("2. Personal Savings Chest");
		System.out.println("3. Joint Checking Chest");
		System.out.println("4. Joint Savings Chest");
		System.out.println("0. Quit!");
	}

	public void addUsersToSharedChest() {
		boolean isDone = false;
		while (!isDone) {
			System.out.println("Do ye want to share with another pirate? (Please type 'yes' or 'no')");
			String input = ScannerUtil.getStringInput();
			if (input.toLowerCase().equals("no")) {
				isDone = true;
			} else if (input.toLowerCase().equals("yes")) {
				System.out.println(
						"Which pirate do ye want to have access to this shared chest? (Type the name of the pirate)");
				String name = ScannerUtil.getStringInput();
				Pirate pirate = UserDao.getPiratebyName(name);
				if (pirate == null) {
					System.out.println("That pirate doesn't exist!");
					continue;
				}
				UserDao.addPirateToSharedChest(pirate.getPirate_number(), UserDao.getMaxSharedNumber());
				System.out.println(pirate.getPirate_name() + " has been added to your shared chest!");
			}
		}
	}

}
