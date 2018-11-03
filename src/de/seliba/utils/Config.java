package de.seliba.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
	
	  private File file;
	  private String name;
	  private YamlConfiguration yamlConfiguration;
	  private Plugin plugin;
	  
	  public Config(String name, Plugin plugin) {
	    this.name = name;
	    this.plugin = plugin;
	  }
	  
	  public YamlConfiguration getYml() {
	    return this.yamlConfiguration;
	  }
	  
	  public void load() {
	    this.file = new File(this.plugin.getDataFolder(), this.name + ".yml");
	    this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
	  }
	  
	  public void reload() {
	    this.file = new File(this.plugin.getDataFolder(), this.name + ".yml");
	    this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
	  }
	  
	  public void save() {
	    try {
	      this.yamlConfiguration.save(this.file);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public boolean exists() {
	    return this.file.exists();
	  }
	  
}
