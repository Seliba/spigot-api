package de.seliba.api;

import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class TitleAPI {
	
	/*
	 * Created by pixelino / fnogen
	 */
	
	public TitleAPI() {
		
	}
	
	public void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String title) {
		sendFullTitle(p, fadeIn, stay, fadeOut, title, null);
	}
	  
	public void sendSubTitle(Player p, int fadeIn, int stay, int fadeOut, String subTitle) {
		sendFullTitle(p, fadeIn, stay, fadeOut, null, subTitle);
	}
	  
	public void sendFullTitle(Player p, int fadeIn, int stay, int fadeOut, String title, String subTitle) {
		PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
	    
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		if (title != null) {
			PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]);
			connection.sendPacket(packetTitle);
		}
		if (subTitle != null) {
			PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subTitle)[0]);
			connection.sendPacket(packetSubtitle);
	   }
	}

}
