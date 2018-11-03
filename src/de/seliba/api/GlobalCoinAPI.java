package de.seliba.api;

import java.util.Locale;
import java.util.UUID;

import org.bukkit.entity.Player;

public class GlobalCoinAPI {
	
	private static API instance = API.getAPI();
	
	public GlobalCoinAPI() {
		
	}
	
	  private static boolean hasMoney(Player p)
	  {
	    String u = p.getUniqueId().toString().trim();
	    return instance.databaseHandler.exists(String.format(Locale.ENGLISH, "SELECT * FROM `money` WHERE `uuid` = '%s'", new Object[] { u }));
	  }
	  
	  public static long getMoney(Player p)
	  {
	    String u = p.getUniqueId().toString().trim();
	    if (hasMoney(p)) {
	    	return ((Long)instance.databaseHandler.getResult(String.format(Locale.ENGLISH, "SELECT * FROM `money` WHERE `uuid` = '%s'", new Object[] { u }), "money")).longValue();	    }
	    return 0L;
	  }
	  
	  public static void setMoney(Player p, long money)
	  {
	    String u = p.getUniqueId().toString().trim();
	    if (hasMoney(p))
	    {
	      instance.databaseHandler.update(String.format(Locale.ENGLISH, "UPDATE `money` SET `money` = %d WHERE `uuid` = '%s'", new Object[] { Long.valueOf(money), u }));
	      return;
	    }
	    instance.databaseHandler.update(String.format(Locale.ENGLISH, "INSERT INTO `money` (`uuid`, `money`) VALUES ('%s', %d)", new Object[] { u, Long.valueOf(money) }));
	  }
	  
	  public static void addMoney(Player p, long money)
	  {
	    setMoney(p, getMoney(p) + money);
	  }
	  
	  public static void removeMoney(Player p, long money)
	  {
	    setMoney(p, getMoney(p) - money);
	  }
	  
	  private static boolean hasMoney(UUID u)
	  {
	    return instance.databaseHandler.exists(String.format(Locale.ENGLISH, "SELECT * FROM `money` WHERE `uuid` = '%s'", new Object[] { u.toString().trim() }));
	  }
	  
	  public static long getMoney(UUID u)
	  {
	    if (hasMoney(u)) {
	      return ((Long)instance.databaseHandler.getResult(String.format(Locale.ENGLISH, "SELECT * FROM `money` WHERE `uuid` = '%s'", new Object[] { u.toString().trim() }), "money")).longValue();
	    }
	    return 0L;
	  }
	  
	public static void setMoney(UUID u, long money)
	  {
	    if (hasMoney(u))
	    {
	      instance.databaseHandler.update(String.format(Locale.ENGLISH, "UPDATE `money` SET `money` = %d WHERE `uuid` = '%s'", new Object[] { Long.valueOf(money), u.toString().trim() }));
	      return;
	    }
	    instance.databaseHandler.update(String.format(Locale.ENGLISH, "INSERT INTO `money` (`uuid`, `money`) VALUES ('%s', %d)", new Object[] { u.toString().trim(), Long.valueOf(money) }));
	  }
	  
	  public static void addMoney(UUID u, long money)
	  {
	    setMoney(u, getMoney(u) + money);
	  }
	  
	  public static void removeMoney(UUID u, long money)
	  {
	    setMoney(u, getMoney(u) - money);
	  }

}
