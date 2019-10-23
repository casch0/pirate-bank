package com.blued.test.dao;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.postgresql.core.BaseStatement;

import com.blued.util.ConnectionUtil;
import com.blued.dao.UserDao;
import com.blued.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTests {
	
	private UserDao userDao = new UserDao();
	
	@Mock
	private Connection conn;
	
	@Spy
	private BaseStatement stmt = (BaseStatement) ConnectionUtil.getConnection();

	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		ConnectionUtil.testMode = true;
		ConnectionUtil.getConnection().createStatement().executeUpdate("CREATE TABLE pirates as table pirate_bank.pirates with no data");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionUtil.getConnection().createStatement().executeUpdate("drop table employees");
		ConnectionUtil.testMode = false;
	}
	
	@Test
	public void getAllUserTest() {
		
	}
}
