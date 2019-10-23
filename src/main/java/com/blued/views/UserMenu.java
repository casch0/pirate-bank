package com.blued.views;

import java.math.BigDecimal;
import java.util.List;

import com.blued.dao.UserDao;
import com.blued.exceptions.InvalidAmountException;
import com.blued.models.Pirate;
import com.blued.models.PirateChest;
import com.blued.models.SharedChest;
import com.blued.services.*;
import com.blued.util.ScannerUtil;

public class UserMenu implements View {

	UserDao userDao = new UserDao();

	Pirate pirate;

	public UserMenu(Pirate pirate) {
		this.pirate = pirate;
	}

	public void printMenu() {
		System.out.println("1. Add some Debloons to a chest! (Deposit)");
		System.out.println("2. Take some Debloons from a chest! (Withdraw)");
		System.out.println("3. Move some Debloons from one chest to another! (Transfer)");
		System.out.println("4. Take yer Debloons and close this chest! (Close Account)");
		System.out.println("0. Quit");
	}

	@Override
	public View process() {

		List<PirateChest> chests = UserDao.getPersonalChests(pirate.getPirate_number());
		List<SharedChest> shared_chests = UserDao.getSharedChests(pirate.getPirate_number());
		if (chests.isEmpty() && shared_chests.isEmpty()) {
			System.out.println("It looks like you haven't made a chest with us yet!");
			return new CreateChestMenu(pirate);
		}
		System.out.println("| Chest # |  Number of Debloons  |   Chest Type   |");
		System.out.println("___________________________________________________");
		for (PirateChest chest : chests) {
			System.out.println("|    " + chest.getChestID() + "    |    " + chest.getBooty().doubleValue()
					+ " Debloons    |    " + chest.getType() + "    |");
		}
		for (SharedChest chest : shared_chests) {
			System.out.println("|    " + chest.getShared_number() + "    |    " + chest.getBooty().doubleValue()
					+ " Debloons    |    " + chest.getChest_type() + "    |");
		}

		System.out.println("1. Choose a chest to make changes to!");
		System.out.println("2. Make a new chest!");
		System.out.println("0. Quit!");

		int input = ScannerUtil.getInput(2);

		if (input == 0)
			return new MainMenu();
		else if (input == 1) {
			System.out.println("Which chest would ye like to gander at? (Choose chest number)");
			int chest_input = ScannerUtil.getInput(10_000);
			int chest_number = BankService.validateChest(pirate.getPirate_number(), chest_input);
			if (chest_number == 0) {
				System.out.println("Pick your own chest ye thief!");
				return new UserMenu(pirate);
			} else {
				System.out.println("What would ye like to do with chest number " + chest_number + "?");
				printMenu();
			}

			PirateChest chest = null;
			SharedChest sharedChest = null;

			for (PirateChest c : chests)
				if (chest_number == c.getChestID())
					chest = c;
			for (SharedChest s : shared_chests)
				if (chest_number == s.getShared_number())
					sharedChest = s;

			int selection = ScannerUtil.getInput(4);
			switch (selection) {
			case 0:
				return new MainMenu();
			case 1: {
				System.out.println("How many debloons would ye like to add to yer booty?");
				double deposit = ScannerUtil.getInput(10_000.00);
				BigDecimal add = new BigDecimal(deposit);
				if (chest != null) {
					BankService.deposit(chest, add);
				} else {
					BankService.deposit(sharedChest, add);
				}
				return new UserMenu(pirate);
			}
			case 2: {
				System.out.println("How many debloons would ye like to take from yer booty?");
				if (chest != null) {
					double withdrawal = ScannerUtil.getWithdrawInput(chest.getBooty().doubleValue());
					BigDecimal subtract = new BigDecimal(withdrawal);
					try {
						BankService.withdraw(chest, subtract);
					} catch (InvalidAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					double withdrawal = ScannerUtil.getWithdrawInput(sharedChest.getBooty().doubleValue());
					BigDecimal subtract = new BigDecimal(withdrawal);
					try {
						BankService.withdraw(sharedChest, subtract);
					} catch (InvalidAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return new UserMenu(pirate);
			}
			case 3: {
				System.out.println("| Chest # |  Number of Debloons  |   Chest Type   |");
				System.out.println("___________________________________________________");
				for (PirateChest chestP : chests) {
					System.out.println("|    " + chestP.getChestID() + "    |    " + chestP.getBooty().doubleValue()
							+ " Debloons    |    " + chestP.getType() + "    |");
				}
				for (SharedChest chestP : shared_chests) {
					System.out.println("|    " + chestP.getShared_number() + "    |    " + chestP.getBooty().doubleValue()
							+ " Debloons    |    " + chestP.getChest_type() + "    |");
				}
				System.out.println("You'll need to give me the chest number that you'd like to transfer to first!");
				int transfer = ScannerUtil.getInput(100);
				PirateChest transferChest = userDao.getChest(transfer);
				SharedChest transferSChest = userDao.getSharedChest(transfer);

				if (transferChest != null) {
					System.out.println("Now how many Debloons would ye like to transfer?");
					if (chest != null) {
						double transfer_amount = ScannerUtil.getInput(chest.getBooty().doubleValue());
						BigDecimal transferDecimal = new BigDecimal(transfer_amount);
						BankService.transfer(chest, transferChest, transferDecimal);
						return new UserMenu(pirate);
					} else {
						double transfer_amount = ScannerUtil.getInput(sharedChest.getBooty().doubleValue());
						BigDecimal transferDecimal = new BigDecimal(transfer_amount);
						BankService.transfer(sharedChest, transferChest, transferDecimal);
						return new UserMenu(pirate);
					}
				}
				else {
					System.out.println("Now how many Debloons would ye like to transfer?");
					if (chest != null) {
						double transfer_amount = ScannerUtil.getInput(chest.getBooty().doubleValue());
						BigDecimal transferDecimal = new BigDecimal(transfer_amount);
						BankService.transfer(chest, transferSChest, transferDecimal);
						return new UserMenu(pirate);
					} else {
						double transfer_amount = ScannerUtil.getInput(sharedChest.getBooty().doubleValue());
						BigDecimal transferDecimal = new BigDecimal(transfer_amount);
						BankService.transfer(sharedChest, transferSChest, transferDecimal);
						return new UserMenu(pirate);
					}
				}


			}
			case 4: {
				System.out.println("Are ye REALLY sure ye want to close this chest?('yes' or 'no')");
				String answer = "";
				while(answer.isEmpty()) {
					String str = ScannerUtil.getStringInput();
					if(str.equals("yes") || str.equals("no")) answer = str;
					else System.out.println("This is a yes or no question ye dunce!");
				}
				if(answer.equals("yes")) {
					userDao.closeAccount(chest_number);
					return new UserMenu(pirate);
				}
				if(answer.equals("no")) return new UserMenu(pirate);
				
			}
			default:
				return null;
			}
		} else if (input == 2)
			return new CreateChestMenu(pirate);
		return null;
	}
}
