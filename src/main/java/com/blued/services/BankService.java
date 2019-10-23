package com.blued.services;

import java.math.BigDecimal;
import java.util.List;

import com.blued.dao.UserDao;
import com.blued.exceptions.InvalidAmountException;
import com.blued.models.Pirate;
import com.blued.models.PirateChest;
import com.blued.models.SharedChest;
import com.blued.util.ScannerUtil;
import com.blued.views.CreateAccountMenu;
import com.blued.views.MainMenu;
import com.blued.views.UserMenu;
import com.blued.views.View;

public class BankService {

	UserDao userDao;

	static UserDao userRepo = new UserDao();

	public BankService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public static void deposit(PirateChest chest, BigDecimal amount) {

		chest.setBooty(chest.getBooty().add(amount));
		userRepo.updateChestBooty(chest, amount);

	}

	public static void deposit(SharedChest chest, BigDecimal amount) {
		
		chest.setBooty(chest.getBooty().add(amount));
		userRepo.updateSharedBooty(chest, amount);

	}

	public static void withdraw(PirateChest chest, BigDecimal amount) throws InvalidAmountException {
		
		if(amount.compareTo(chest.getBooty()) == 1) throw new InvalidAmountException("You can't take more than you have!");
		
		chest.setBooty(chest.getBooty().subtract(amount));
		amount = amount.negate();
		userRepo.updateChestBooty(chest, amount);
		
	}

	public static void withdraw(SharedChest chest, BigDecimal amount) throws InvalidAmountException {
		
		if(amount.compareTo(chest.getBooty()) == 1) throw new InvalidAmountException("You can't take more than you have!");
		
		chest.setBooty(chest.getBooty().subtract(amount));
		amount = amount.negate();
		userRepo.updateSharedBooty(chest, amount);
		
	}

	public static void transfer(PirateChest fromChest, PirateChest toChest, BigDecimal amount) {
		
		fromChest.setBooty(fromChest.getBooty().subtract(amount));
		toChest.setBooty(toChest.getBooty().add(amount));
		userRepo.transfer(fromChest, toChest, amount);

	}

	public static void transfer(PirateChest fromChest, SharedChest toChest, BigDecimal amount) {
		
		fromChest.setBooty(fromChest.getBooty().subtract(amount));
		toChest.setBooty(toChest.getBooty().add(amount));
		userRepo.transfer(fromChest, toChest, amount);

	}

	public static void transfer(SharedChest fromChest, PirateChest toChest, BigDecimal amount) {
		
		fromChest.setBooty(fromChest.getBooty().subtract(amount));
		toChest.setBooty(toChest.getBooty().add(amount));
		userRepo.transfer(fromChest, toChest, amount);

	}

	public static void transfer(SharedChest fromChest, SharedChest toChest, BigDecimal amount) {
		
		fromChest.setBooty(fromChest.getBooty().subtract(amount));
		toChest.setBooty(toChest.getBooty().add(amount));
		userRepo.transfer(fromChest, toChest, amount);

	}

	public static int validateChest(int p_id, int id) {
		List<PirateChest> chests = UserDao.getPersonalChests(p_id);
		List<SharedChest> shared_chests = UserDao.getSharedChests(p_id);
		int chest_number = 0;
		for (PirateChest chest : chests) {
			if (chest.getChestID() == id)
				chest_number = id;
		}
		for (SharedChest chest : shared_chests) {
			if (chest.getShared_number() == id)
				chest_number = id;
		}
		if (chest_number == 0) {
			System.out.println("Choose an existing chest ye barnacle sucker!");
			return 0;
		}
		return id;
	}

	public static View createAccount() {
		System.out.println(
				"What is your new pirate name? (Type your new username or type 'back' to go to the main menu)");
		String username = ScannerUtil.getStringInput();
		if (username.isEmpty()) {
			System.out.println("You can't have no name ye landlubber!");
			return new CreateAccountMenu();
		}

		if (username.equals("back")) {
			return new MainMenu();
		}

		List<Pirate> resultSet = UserDao.getPiratesByName(username);

		if (!resultSet.isEmpty()) {
			System.out.println("That pirate name has been taken ye scurvy dog!");
			return new CreateAccountMenu();
		}

		System.out.println("Whisper in me ear, a memorable secret code... (Type your password)");
		String password = "";
		while (password.isEmpty()) {
			password = ScannerUtil.getStringInput();
			if (password.length() < 6) {
				System.out.println("Ye secret code needs to be longer than 5 letters ye swab!");
				password = "";
			}
		}
		UserDao.createPirate(username, password);
		System.out.println(
				"Aye Cap'n, I've remembered ye name and secret code, now ye arrrrr allowed to set up ye own chests!");
		return new MainMenu();
	}

}
