package de.seliba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

	public static Connection connection;
	private static String host, database, username, password;
	private static int port;
	private Statement statement = null;
	
	public DatabaseHandler() {
		
	}
	
	public void setConnection(String host2, int port2, String database2, String username2, String password2) {
		host = host2;
		port = port2;
		database = database2;
		username = username2;
		password = password2;
	}
	
	public void connect() {
		if(!isConnected()) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if(isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		return connection != null;
	}
	
	public void createTable(String statement) {
		if(isConnected()) {
			try {
				connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS UltimateBans (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), Anmerkung VARCHAR(100), Banner VARCHAR(100))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(String query) {
		if(isConnected()) {
			try {
				connection.createStatement().executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getResult(String query) {
		if(isConnected()) {
			try {
				return connection.createStatement().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	  @SuppressWarnings("static-access")
	public Object getResult(String query, String get)
	  {
	    if (!isConnected()) {
	      return null;
	    }
	    Object object = null;
	    try
	    {
	      this.statement = this.connection.createStatement();
	      ResultSet resultSet = this.statement.executeQuery(query);
	      resultSet.next();
	      object = resultSet.getObject(get);
	      resultSet.close();
	      this.statement.close();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	    return object;
	  }

	
	  @SuppressWarnings("static-access")
	public boolean exists(String query)
	  {
	    boolean exists = false;
	    if (!isConnected()) {
	      return exists;
	    }
	    try
	    {
	      this.statement = this.connection.createStatement();
	      ResultSet resultSet = this.statement.executeQuery(query);
	      exists = resultSet.next();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	    return exists;
	  }
	
}
