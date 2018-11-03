package de.seliba.api;

import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MessageAPI {
	
	/*
	 * Created by DomeDD
	 */
	
	public MessageAPI() {
		
	}
	
	  public void sendClickableHoverableMessage(Player p, String textpart, String clickabletext, String hovertext, String runcommand)
	  {
	    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" +
	      "[{\"text\":\"" + clickabletext + "\",\"hoverEvent\":{\"action\":\"show_text\", " + 
	      "\"value\":\"" + hovertext + "\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":" + 
	      "\"/" + runcommand + "\"}}]}");
	    PacketPlayOutChat packet = new PacketPlayOutChat(chat);
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	  }
	  
	  public void sendClickableHoverableMessage(String textpart, String clickabletext, String hovertext, String runcommand)
	  {
		  for(Player all : Bukkit.getOnlinePlayers()) {
			    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" + 
			  	      "[{\"text\":\"" + clickabletext + "\",\"hoverEvent\":{\"action\":\"show_text\", " + 
			  	      "\"value\":\"" + hovertext + "\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":" + 
			  	      "\"/" + runcommand + "\"}}]}");
			  	 PacketPlayOutChat packet = new PacketPlayOutChat(chat);
			  	 ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
		  }
	  }
	  
	  public void sendHoverableMessage(Player p, String textpart, String hoverabletext, String hovertext)
	  {
	    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" + 
	      "[{\"text\":\"" + hoverabletext + "\",\"hoverEvent\":{\"action\":\"show_text\", " + 
	      "\"value\":\"" + hovertext + "\"}}]}");
	    PacketPlayOutChat packet = new PacketPlayOutChat(chat);
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	  }
	  
	  public void sendHoverableMessage(String textpart, String hoverabletext, String hovertext)
	  {
		  for(Player all : Bukkit.getOnlinePlayers()) {
			    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" + 
			  	      "[{\"text\":\"" + hoverabletext + "\",\"hoverEvent\":{\"action\":\"show_text\", " + 
			  	      "\"value\":\"" + hovertext + "\"}}]}");
			  	PacketPlayOutChat packet = new PacketPlayOutChat(chat);
			  	((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);  
		  }
	  }
	  
	  public void sendClickableMessage(Player p, String textpart, String clickabletext, String runcommand)
	  {
		  IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" + 
				  "[{\"text\":\"" + clickabletext + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":" + 
				  "\"/" + runcommand + "\"}}]}");
		  PacketPlayOutChat packet = new PacketPlayOutChat(chat);
		  ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	  }
	  
	  public void sendClickableMessage(String textpart, String clickabletext, String runcommand)
	  {
		  for(Player all : Bukkit.getOnlinePlayers()) {
			    IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + textpart + "\",\"extra\":" + 
			  	      "[{\"text\":\"" + clickabletext + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":" + 
			  	      "\"/" + runcommand + "\"}}]}");
			  	PacketPlayOutChat packet = new PacketPlayOutChat(chat);
			  	((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
		  }
	  }

}
