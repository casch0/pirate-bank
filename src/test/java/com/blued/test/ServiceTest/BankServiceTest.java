package com.blued.test.ServiceTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;

import com.blued.dao.UserDao;
import com.blued.exceptions.InvalidAmountException;
import com.blued.models.PirateChest;
import com.blued.models.SharedChest;
import com.blued.services.BankService;

public class BankServiceTest {

	UserDao userDao = Mockito.mock(UserDao.class);
	BankService bankService = new BankService(userDao);

	@Test(expected = InvalidAmountException.class)
	public void withdrawTooMuchTest() throws InvalidAmountException {
		PirateChest retrievedChest = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number = 500;

		Mockito.when(userDao.getChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.updateChestBooty(userDao.getChest(chest_number), new BigDecimal(-1500)))
				.thenReturn(retrievedChest);

		BankService.withdraw(retrievedChest, new BigDecimal(1500));

		Mockito.verify(userDao).getChest(chest_number);
	}

	@Test
	public void depositTest() {
		PirateChest retrievedChest = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number = 500;

		Mockito.when(userDao.getChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.updateChestBooty(userDao.getChest(chest_number), new BigDecimal(500)))
				.thenReturn(retrievedChest);

		BankService.deposit(retrievedChest, new BigDecimal(300));

		Mockito.verify(userDao).getChest(chest_number);

		assertEquals("1000 + 300 = 1300", new BigDecimal(1300), retrievedChest.getBooty());
	}

	@Test
	public void depositToJointTest() {
		SharedChest retrievedChest = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number = 500;

		Mockito.when(userDao.getSharedChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.updateSharedBooty(userDao.getSharedChest(chest_number), new BigDecimal(500)))
				.thenReturn(retrievedChest);

		BankService.deposit(retrievedChest, new BigDecimal(300));

		Mockito.verify(userDao).getSharedChest(chest_number);

		assertEquals("1000 + 300 = 1300", new BigDecimal(1300), retrievedChest.getBooty());
	}

	@Test
	public void withdrawTest() {
		PirateChest retrievedChest = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number = 500;

		Mockito.when(userDao.getChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.updateChestBooty(userDao.getChest(chest_number), new BigDecimal(-300)))
				.thenReturn(retrievedChest);

		try {
			BankService.withdraw(retrievedChest, new BigDecimal(300));
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Mockito.verify(userDao).getChest(chest_number);

		assertEquals("1000 - 300 = 700", new BigDecimal(700), retrievedChest.getBooty());
	}

	@Test
	public void withdrawFromJointTest() {
		SharedChest retrievedChest = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number = 500;

		Mockito.when(userDao.getSharedChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.updateSharedBooty(userDao.getSharedChest(chest_number), new BigDecimal(-300)))
				.thenReturn(retrievedChest);

		try {
			BankService.withdraw(retrievedChest, new BigDecimal(300));
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Mockito.verify(userDao).getSharedChest(chest_number);

		assertEquals("1000 - 300 = 700", new BigDecimal(700), retrievedChest.getBooty());
	}

	@Test
	public void transferPersonalToPersonalTest() {
		PirateChest retrievedChest = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number = 500;
		PirateChest retrievedChest2 = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number2 = 501;

		Mockito.when(userDao.getChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.getChest(chest_number2)).thenReturn(retrievedChest2);

		Mockito.when(userDao.updateChestBooty(userDao.getChest(chest_number), new BigDecimal(500)))
				.thenReturn(retrievedChest);
		Mockito.when(userDao.updateChestBooty(userDao.getChest(chest_number), new BigDecimal(500)))
				.thenReturn(retrievedChest);

		BankService.transfer(retrievedChest, retrievedChest2, new BigDecimal(500));

		assertEquals("1000 - 500 = 500", new BigDecimal(500), retrievedChest.getBooty());
		assertEquals("1000 + 500 = 1500", new BigDecimal(1500), retrievedChest2.getBooty());
	}

	@Test
	public void transferPersonalToSharedTest() {
		PirateChest retrievedChest = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number = 500;
		SharedChest retrievedChest2 = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number2 = 501;

		BankService.transfer(retrievedChest, retrievedChest2, new BigDecimal(500));

		Mockito.when(userDao.getChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.getSharedChest(chest_number2)).thenReturn(retrievedChest2);

		assertEquals("1000 - 500 = 500", new BigDecimal(500), retrievedChest.getBooty());
		assertEquals("1000 + 500 = 1500", new BigDecimal(1500), retrievedChest2.getBooty());
	}

	@Test
	public void transferSharedToPersonalTest() {
		PirateChest retrievedChest2 = new PirateChest(1, 500, new BigDecimal(1000.00), "Checking");
		int chest_number2 = 500;
		SharedChest retrievedChest = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number = 501;

		BankService.transfer(retrievedChest, retrievedChest2, new BigDecimal(500));

		Mockito.when(userDao.getSharedChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.getChest(chest_number2)).thenReturn(retrievedChest2);

		assertEquals("1000 - 500 = 500", new BigDecimal(500), retrievedChest.getBooty());
		assertEquals("1000 + 500 = 1500", new BigDecimal(1500), retrievedChest2.getBooty());
	}

	@Test
	public void transferSharedToSharedTest() {
		SharedChest retrievedChest2 = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number = 500;
		SharedChest retrievedChest = new SharedChest(1, 500, new BigDecimal(1000.00), "Joint Checking");
		int chest_number2 = 501;

		BankService.transfer(retrievedChest, retrievedChest2, new BigDecimal(500));

		Mockito.when(userDao.getSharedChest(chest_number)).thenReturn(retrievedChest);
		Mockito.when(userDao.getSharedChest(chest_number2)).thenReturn(retrievedChest2);

		assertEquals("1000 - 500 = 500", new BigDecimal(500), retrievedChest.getBooty());
		assertEquals("1000 + 500 = 1500", new BigDecimal(1500), retrievedChest2.getBooty());
	}
}
