package de.seliba.api;

import org.bukkit.entity.Player;

public class LocalCoinAPI {
	
	public LocalCoinAPI() {
		
	}
	
	public void setMoney(Player p, long money) {
		API.getAPI().setMoney(p, money);
	}
	
	public long getMoney(Player p) {
		return API.getAPI().getMoney(p);
	}
	
	public void addMoney(Player p, long money) {
		API.getAPI().addMoney(p, money);
	}
	
	public boolean removeMoney(Player p, long money) {
		long newMoney = getMoney(p) - money;
		if(newMoney >= 0) {
			setMoney(p, newMoney);
			return true;
		}
		return false;
	}

}
