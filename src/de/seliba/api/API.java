package de.seliba.api;

import de.seliba.utils.Config;
import de.seliba.utils.DatabaseHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class API {
	
	private static JavaPlugin plugin;
	public DatabaseHandler databaseHandler;
	private Config mysql;
	private Config money;
	private static API api;

	public API(JavaPlugin javaPlugin) {
		plugin = javaPlugin;
		api = this;
		config();
		databaseHandler.connect();
	}
	
	private void config() {
		mysql = new Config("mysql", plugin);
		money = new Config("money", plugin);
		mysql.load();
		money.load();
		if(!mysql.exists()) {
			mysql.getYml().set("enabled", false);
			mysql.getYml().set("host", "localhost");
			mysql.getYml().set("port", 3306);
			mysql.getYml().set("database", "mysql");
			mysql.getYml().set("username", "root");
			mysql.getYml().set("password", "passwort123");
			mysql.save();
			mysql.reload();
		}
		if(!money.exists()) {
			money.save();
		}
		boolean enabled = mysql.getYml().getBoolean("enabled");
		if(enabled) {
			databaseHandler = new DatabaseHandler();
			databaseHandler.setConnection(mysql.getYml().getString("host"), mysql.getYml().getInt("port"), mysql.getYml().getString("database"), mysql.getYml().getString("username"), mysql.getYml().getString("password"));
			databaseHandler.connect();
			databaseHandler.createTable("CREATE TABLE IF NOT EXISTS `money` (`uuid` VARCHAR(36) NOT NULL PRIMARY KEY, `money` BIGINT)");
		} else {
			System.out.println("Bitte aktiviere MySQL in der mysql.yml!");
		}
	}
	
	public static JavaPlugin getPlugin() {
		return plugin;
	}

	public static API getAPI() {
		return api;
	}
	
	public void setMoney(Player p, long money) {
		this.money.getYml().set(p.getUniqueId().toString().trim(), money);
		this.money.save();
	}
	
	public void addMoney(Player p, long money) {
		if(hasMoney(p)) {
			setMoney(p, getMoney(p) + money);
		} else {
			setMoney(p, money);
		}
	}
	
	public long getMoney(Player p) {
		if(hasMoney(p)) {
			return money.getYml().getLong(p.getUniqueId().toString().trim());
		}
		return 0L;
	}
	
	private boolean hasMoney(Player p) {
		String uuid = p.getUniqueId().toString().trim();
		if(money.getYml().getString(uuid) != null) {
			return true;
		}
		return false;
	}

}
