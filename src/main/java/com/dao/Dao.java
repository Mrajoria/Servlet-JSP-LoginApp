package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Item;
import com.model.UserItem;

public class Dao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/itemset";
	private String jdbcUsername = "root";
	private String jdbcPassword = "admin";

	private static final String INSERT_ITEMS_SQL = "INSERT INTO items" + "  (name, quantity, location, status) VALUES "
			+ " (?, ?, ?,?);";
	private static final String SELECT_ITEMS_BY_CLM_SQL = "select * from items WHERE name = ?;";
	private static final String SELECT_ALL_ITEMS = "select * from items;";
	private static final String DELETE_ITEMS_SQL = "delete from items where name = ?;";
	private static final String UPDATE_ITEMS_SQL = "update items set name = ?,quantity= ?, location =?, status =? where name = ?;";
	

	private static final String INSERT_ITEMSbyUser_SQL = "INSERT INTO useritems" + "  (name, quantity, location, status, actioncode) VALUES "
			+ " (?, ?, ?,?,?);";
	private static final String SELECT_ALL_ITEMSbyUser_SQL = "select * from useritems;";
	private static final String DELETE_ITEMSbyUser_SQL = "delete from useritems where name = ?;";
	private static final String UPDATE_ITEMSbyUser_SQL = "update useritems set name = ?,quantity= ?, location =?, status =? where name = ?;";
	private static final String SELECT_ITEMS_WHERE_byUser_SQL = "select * from useritems WHERE name = ?;";
	private static final String SELECT_ITEMS_BY_CLM_byUser_SQL = "select * from useritems WHERE actioncode = ?;";
	private static final String UPDATE_ITEMS_W_byUser_SQL ="update useritems set actioncode = ? WHERE name =?;";
	private static final String UPDATE_ITEMS_ALL_byUser_SQL ="update useritems set actioncode = ?;";

	
	public Dao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(Item item) throws SQLException {
		System.out.println(INSERT_ITEMS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL)) {
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getQuantity()+"");
			preparedStatement.setString(3, item.getLocation());
			preparedStatement.setString(4, item.getStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	

	public List<Item> selectAllItems() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Item> items = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name =  rs.getString("name");
			    int quantity = Integer.parseInt(rs.getString("quantity"));
				String location = rs.getString("location");
				String status = rs.getString("status");
				items.add(new Item(name, quantity, location,status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return items;
	}

	public boolean deleteItem(String name) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ITEMS_SQL);) {
			statement.setString(1, name);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateItem(Item item) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEMS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, item.getName());
			statement.setString(2, item.getQuantity()+"");
			statement.setString(3, item.getLocation());
			statement.setString(4, item.getStatus());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	
	public void DBINSERTrequestsbyUsers(UserItem uitem) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMSbyUser_SQL)) {
			preparedStatement.setString(1, uitem.getName());
			preparedStatement.setString(2, uitem.getQuantity()+"");
			preparedStatement.setString(3, uitem.getLocation());
			preparedStatement.setString(4, uitem.getStatus());
			preparedStatement.setString(5, uitem.getActioncode());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public List<UserItem> DBSELECTrequestsbyUsers(){
		// using try-with-resources to avoid closing resources (boiler plate code)
				List<UserItem> uitems = new ArrayList<>();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();

						// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMSbyUser_SQL);) {
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					// Step 4: Process the ResultSet object.
					while (rs.next()) {
						String name =  rs.getString("name");
					    int quantity = Integer.parseInt(rs.getString("quantity"));
						String location = rs.getString("location");
						String status = rs.getString("status");
						String actioncode = rs.getString("actioncode");
						uitems.add(new UserItem(name, quantity, location,status,actioncode));
					}
				} catch (SQLException e) {
					printSQLException(e);
				}
				return uitems;
	}
	
	public UserItem DBSELECT_WrequestsbyUsers(String primaryk){
		// using try-with-resources to avoid closing resources (boiler plate code)
				UserItem uitem = new UserItem();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();

						// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEMS_WHERE_byUser_SQL);) {
					
					preparedStatement.setString(1, primaryk);
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					
						// STep-4 get result
					    while(rs.next()) {
						String name =  rs.getString("name");
					    int quantity = Integer.parseInt(rs.getString("quantity"));
						String location = rs.getString("location");
						String status = rs.getString("status");
						String actioncode = rs.getString("actioncode");
						uitem =new UserItem(name, quantity, location,status,actioncode);
					    }
					
				} catch (SQLException e) {
					printSQLException(e);
				}
				return uitem;
	}
	
	public List<UserItem> DBSELECT_BY_CLM_requestsbyUsers(String colname){
		// using try-with-resources to avoid closing resources (boiler plate code)
				List<UserItem> uitems = new ArrayList<>();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();

						// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEMS_BY_CLM_byUser_SQL);) {
					
					preparedStatement.setString(1, colname);
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					
						// STep-4 get result
					    while(rs.next()) {
						String name =  rs.getString("name");
					    int quantity = Integer.parseInt(rs.getString("quantity"));
						String location = rs.getString("location");
						String status = rs.getString("status");
						String actioncode = rs.getString("actioncode");
						uitems.add(new UserItem(name, quantity, location,status,actioncode));
					    }
					
				} catch (SQLException e) {
					printSQLException(e);
				}
				return uitems;
	}
	
	
	
	public boolean DBupdate_WByUsers(String actioncode, String primaryk) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEMS_W_byUser_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, actioncode);
			statement.setString(2, primaryk);
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean DBupdate_AllByUsers(String actioncode) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEMS_ALL_byUser_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, actioncode);
		

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean DBdeleteItem_By_User(String name) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ITEMSbyUser_SQL);) {
			statement.setString(1, name);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	
	
	
	
}
