package com.blued.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.blued.models.Pirate;
import com.blued.models.PirateChest;
import com.blued.models.SharedChest;
import com.blued.util.ConnectionUtil;

public class UserDao implements Savepoint{
	
	public PirateChest transfer(PirateChest chestFrom, PirateChest chestTo, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(4);
			Savepoint savepoint1 = connection.setSavepoint("Savepoint1");
			
			String sql = "update chests set booty_amount = booty_amount + ? where chest_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chestFrom.getChestID());
			statement.setBigDecimal(1, amount.negate());
			statement.executeUpdate();
			
			Savepoint savepoint2 = connection.setSavepoint("Savepoint2");
			
			String sql2 = "update chests set booty_amount = booty_amount + ? where chest_number = ?;";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(2, chestTo.getChestID());
			statement2.setBigDecimal(1, amount);
			statement2.executeUpdate();
			
			connection.commit();
			
			return chestFrom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PirateChest transfer(PirateChest chestFrom, SharedChest chestTo, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			connection.setAutoCommit(false);
			
			Savepoint savepoint1 = connection.setSavepoint("Savepoint1");
			
			String sql = "update chests set booty_amount = booty_amount + ? where chest_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chestFrom.getChestID());
			statement.setBigDecimal(1, amount.negate());
			statement.executeUpdate();
			
			Savepoint savepoint2 = connection.setSavepoint("Savepoint2");
			
			String sql2 = "update shared_chests set booty_amount = booty_amount + ? where shared_number = ?;";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(2, chestTo.getShared_number());
			statement2.setBigDecimal(1, amount);
			statement2.executeUpdate();
			
			connection.commit();
			
			return chestFrom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SharedChest transfer(SharedChest chestFrom, SharedChest chestTo, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			connection.setAutoCommit(false);
			
			Savepoint savepoint1 = connection.setSavepoint("Savepoint1");
			
			String sql = "update shared_chests set booty_amount = booty_amount + ? where shared_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chestFrom.getShared_number());
			statement.setBigDecimal(1, amount.negate());
			statement.executeUpdate();
			
			Savepoint savepoint2 = connection.setSavepoint("Savepoint2");
			
			String sql2 = "update shared_chests set booty_amount = booty_amount + ? where shared_number = ?;";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(2, chestTo.getShared_number());
			statement2.setBigDecimal(1, amount);
			statement2.executeUpdate();
			
			connection.commit();
			
			return chestFrom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SharedChest transfer(SharedChest chestFrom, PirateChest chestTo, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			connection.setAutoCommit(false);
			
			Savepoint savepoint1 = connection.setSavepoint("Savepoint1");
			
			String sql = "update shared_chests set booty_amount = booty_amount + ? where shared_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chestFrom.getShared_number());
			statement.setBigDecimal(1, amount.negate());
			statement.executeUpdate();
			
			Savepoint savepoint2 = connection.setSavepoint("Savepoint2");
			
			String sql2 = "update chests set booty_amount = booty_amount + ? where chest_number = ?;";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(2, chestTo.getChestID());
			statement2.setBigDecimal(1, amount);
			statement2.executeUpdate();
			
			connection.commit();
			
			return chestFrom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static int getMaxSharedNumber() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "select max(shared_number) from shared_chests;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int max = resultSet.getInt("max");
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getMaxChestNumber() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "select max(chest_number) from chests;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int max = resultSet.getInt("max");
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public PirateChest updateChestBooty(PirateChest chest, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "update chests set booty_amount = booty_amount + ? where chest_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chest.getChestID());
			statement.setBigDecimal(1, amount);
			statement.executeUpdate();
			return chest;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SharedChest updateSharedBooty(SharedChest chest, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "update shared_chests set booty_amount = booty_amount + ? where shared_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(2, chest.getShared_number());
			statement.setBigDecimal(1, amount);
			statement.executeUpdate();
			return chest;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void createChest(int pirate_number, BigDecimal deposit, String type) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			if (getMaxChestNumber() > getMaxSharedNumber()) {
				String sql = "insert into chests (chest_number, pirate_number, chest_type, booty_amount)\r\n" + "values (?, ?, ?, ?);";
				PreparedStatement statement = connection.prepareStatement(sql);
				int newNumber = getMaxChestNumber()+1;
				statement.setInt(1, newNumber);
				statement.setInt(2, pirate_number);
				statement.setString(3, type);
				statement.setBigDecimal(4, deposit);
				statement.executeUpdate();
			} else {
				String sql = "insert into chests (chest_number, pirate_number, chest_type, booty_amount)\r\n"
						+ "values (?, ?, ?, ?);";
				PreparedStatement statement = connection.prepareStatement(sql);
				int newNumber = getMaxSharedNumber() + 1;
				statement.setInt(1, newNumber);
				statement.setInt(2, pirate_number);
				statement.setString(3, type);
				statement.setBigDecimal(4, deposit);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createSharedChest(int pirate_number, BigDecimal deposit, String type) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			if (getMaxSharedNumber() > getMaxChestNumber()) {
				String sql = "insert into shared_chests (shared_number, chest_type, booty_amount)\r\n" + "values (?, ?, ?);";
				PreparedStatement statement = connection.prepareStatement(sql);
				int newNumber = getMaxSharedNumber()+1;
				statement.setInt(1, newNumber);
				statement.setString(2, type);
				statement.setBigDecimal(3, deposit);
				statement.executeUpdate();
			}
			else {
				String sql = "insert into shared_chests (shared_number, chest_type, booty_amount)\r\n" + "values (?, ?, ?);";
				PreparedStatement statement = connection.prepareStatement(sql);
				int newNumber = getMaxChestNumber()+1;
				statement.setInt(1, newNumber);
				statement.setString(2, type);
				statement.setBigDecimal(3, deposit);
				statement.executeUpdate();
			}

			String sql2 = "insert into pirates_sharing_chests (pirate_number, shared_number)\r\n" + "values (?, ?);";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(1, pirate_number);
			statement2.setInt(2, getMaxSharedNumber());
			statement2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addPirateToSharedChest(int pirate_number, int shared_number) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "insert into pirates_sharing_chests (pirate_number, shared_number)\r\n" + "values (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pirate_number);
			statement.setInt(2, shared_number);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Pirate getPiratebyName(String pirate_name) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM pirates WHERE LOWER(pirate_name) = LOWER(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pirate_name);
			ResultSet resultSet = statement.executeQuery();
			Pirate pirate = null;
			while (resultSet.next()) {
				pirate = extractPirate(resultSet);
			}
			return pirate;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PirateChest getChest(int id) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM chests WHERE chest_number=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			PirateChest chest = null;
			while (resultSet.next()) {
				chest = extractChest(resultSet);
			}
			return chest;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SharedChest getSharedChest(int id) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "select shared_chests.shared_number, pirate_number, booty_amount, chest_type "
					+ "from shared_chests left join  pirates_sharing_chests \r\n"
					+ "on pirates_sharing_chests.shared_number = shared_chests.shared_number where shared_chests.shared_number = ? limit(1);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			SharedChest chest = null;
			while (resultSet.next()) {
				chest = extractSharedChest(resultSet);
			}
			return chest;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<PirateChest> getPersonalChests(int pirate_number) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM chests WHERE pirate_number = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pirate_number);
			ResultSet resultSet = statement.executeQuery();
			List<PirateChest> chests = new ArrayList<>();
			while (resultSet.next()) {
				PirateChest chest = extractChest(resultSet);
				chests.add(chest);
			}
			return chests;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SharedChest> getSharedChests(int pirate_number) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "SELECT sc.shared_number, psc.pirate_number, booty_amount, chest_type "
					+ "FROM shared_chests as sc left join pirates_sharing_chests as psc\r\n"
					+ "on sc.shared_number = psc.shared_number WHERE pirate_number=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pirate_number);
			ResultSet resultSet = statement.executeQuery();
			List<SharedChest> chests = new ArrayList<>();
			while (resultSet.next()) {
				SharedChest chest = extractSharedChest(resultSet);
				chests.add(chest);
			}
			return chests;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Pirate> getPiratesByName(String pirate_name) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM pirates WHERE LOWER(pirate_name) = LOWER(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pirate_name);
			ResultSet resultSet = statement.executeQuery();
			List<Pirate> pirates = new ArrayList<>();
			while (resultSet.next()) {
				Pirate pirate = extractPirate(resultSet);
				pirates.add(pirate);
			}
			return pirates;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void createPirate(String pirate_name, String secret_code) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "insert into pirates (pirate_name, secret_code)\r\n" + "values (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pirate_name);
			statement.setString(2, secret_code);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Pirate extractPirate(ResultSet resultSet) throws SQLException {
		int pirate_number = resultSet.getInt("pirate_number");
		String pirate_name = resultSet.getString("pirate_name");
		String secret_code = resultSet.getString("secret_code");
		Pirate pirate = new Pirate(pirate_number, pirate_name, secret_code);
		return pirate;
	}

	private static PirateChest extractChest(ResultSet resultSet) throws SQLException {
		int chest_id = resultSet.getInt("chest_number");
		int pirate_number = resultSet.getInt("pirate_number");
		String type = resultSet.getString("chest_type");
		BigDecimal booty = resultSet.getBigDecimal("booty_amount");
		PirateChest chest = new PirateChest(chest_id, pirate_number, booty, type);
		return chest;
	}

	private static SharedChest extractSharedChest(ResultSet resultSet) throws SQLException {
		int shared_id = resultSet.getInt("shared_number");
		int pirate_number = resultSet.getInt("pirate_number");
		String type = resultSet.getString("chest_type");
		BigDecimal booty = resultSet.getBigDecimal("booty_amount");
		SharedChest chest = new SharedChest(shared_id, pirate_number, booty, type);
		return chest;
	}

	@Override
	public int getSavepointId() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSavepointName() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeAccount(int chest_number) {
		PirateChest chest = getChest(chest_number);
		String table_name = "";
		
		if(chest!=null) {
			try (Connection connection = ConnectionUtil.getConnection()) {
				String sql = "delete from chests where chest_number = ?;";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, chest_number);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try (Connection connection = ConnectionUtil.getConnection()) {
				String sql = "delete from pirates_sharing_chests where shared_number = ?;";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, chest_number);
				statement.executeUpdate();
				String sql2 = "delete from shared_chests where shared_number = ?;";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setInt(1, chest_number);
				statement2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
