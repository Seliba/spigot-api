package de.seliba.api;

import java.sql.ResultSet;

public class DatabaseAPI {
	
	public DatabaseAPI() {
		
	}
	
	public void update(String query) {
		API.getAPI().databaseHandler.update(query);
	}
	
	public ResultSet getResult(String query) {
		return API.getAPI().databaseHandler.getResult(query);
	}
	
	public Object getResult(String query, String get) {
	    return API.getAPI().databaseHandler.getResult(query, get);
	}

	public boolean exists(String query) {
		return API.getAPI().databaseHandler.exists(query);
	}

}
