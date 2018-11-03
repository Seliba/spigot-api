package de.seliba.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityAPI {
	
	/*
	 * Created by DomeDD
	 */

	  private Entity en;
	  
	  public EntityAPI(EntityType entity, Location loc)
	  {
	    this.en = loc.getWorld().spawnEntity(loc, entity);
	  }
	  
	  public EntityAPI(EntityType entity, World world, double x, double y, double z)
	  {
	    Location loc = new Location(world, x, y, z);
	    this.en = loc.getWorld().spawnEntity(loc, entity);
	  }
	  
	  public EntityAPI(EntityType entity, String world, double x, double y, double z)
	  {
	    Location loc = new Location(Bukkit.getWorld(world), x, y, z);
	    this.en = loc.getWorld().spawnEntity(loc, entity);
	  }
	  
	  public EntityAPI setCustomName(String name)
	  {
	    this.en.setCustomName(name);
	    return this;
	  }
	  
	  public EntityAPI setCustomNameVisible(boolean visible)
	  {
	    this.en.setCustomNameVisible(visible);
	    return this;
	  }
	  
	  public EntityAPI setPassenger(Entity entity)
	  {
	    this.en.setPassenger(entity);
	    return this;
	  }
	  
	  public EntityAPI setAge(EntityAge age)
	  {
	    if ((this.en instanceof Ageable)) {
	      switch (age)
	      {
	      case ADULT: 
	        ((Ageable)this.en).setBaby();
	        break;
	      case BABY: 
	        ((Ageable)this.en).setAdult();
	      }
	    } else {
	      throw new IllegalArgumentException("Entity's age cannot be modified!");
	    }
	    return this;
	  }
	  
	  public static enum EntityAge
	  {
	    BABY,  ADULT;
	  }
	  
	  public Entity spawn()
	  {
	    return this.en;
	  }
	
}
