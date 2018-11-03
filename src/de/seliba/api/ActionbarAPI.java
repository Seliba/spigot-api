package de.seliba.api;

import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionbarAPI {
	
	public ActionbarAPI() {
		
	}
	
	public void sendActionbar(Player p, String text) {
	    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\"}");
	    PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.a((byte) 2));
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public void sendActionbar(String text) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			sendActionbar(all, text);
		}
	}
	
}