package de.seliba.api;

import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class HologramAPI {
	
	private List<EntityArmorStand> entitys = new ArrayList<EntityArmorStand>();
	private String[] text;
	private Location location;
	private final Double DISTANCE = 0.25D;
	@SuppressWarnings("unused")
	private int count;
	
	public HologramAPI(String[] text, Location location) {
		this.text = text;
		this.location = location;
		create();
	}
	
	private void create() {
		for(String text : text) {
			EntityArmorStand entity = new EntityArmorStand((WorldServer) location.getWorld(), location.getX(), location.getY(), location.getZ());
			entity.setCustomName(IChatBaseComponent.ChatSerializer.a(text));
			entity.setCustomNameVisible(true);
			entity.setInvisible(true);
			entity.setNoGravity(true);
			entitys.add(entity);
			location.subtract(0, DISTANCE, 0);
			count++;
		}
	}
	
	public void show(Player p) {
		for(EntityArmorStand armor : entitys) {
			PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void show(Player p, long time) {
		show(p);
		
		Bukkit.getScheduler().runTaskLater(API.getPlugin(), new BukkitRunnable() {
			
			@Override
			public void run() {
				remove(p);
			}
			
		}, time);
	}
	
	public void showAll() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			show(all);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void showAll(long time) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			show(all, time);
		}
		Bukkit.getScheduler().runTaskLater(API.getPlugin(), new BukkitRunnable() {
			
			@Override
			public void run() {
				removeAll();
			}
			
		}, time);
	}
	
	public void remove(Player p) {
		for(EntityArmorStand entitys : entitys) {
			PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entitys.getId());
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	public void removeAll() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			remove(all);
		}
	}

}
