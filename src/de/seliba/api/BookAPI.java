package de.seliba.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookAPI {
	
	private ItemStack item;
	private BookMeta meta;
	
	public BookAPI() {
		item = new ItemStack(Material.BOOK);
		meta = (BookMeta) item.getItemMeta();
	}
	
	public BookAPI setAuthor(String author) {
		meta.setAuthor(author);
		return this;
	}
	
	public BookAPI addPage(String content) {
		meta.addPage(new String[] {content});
		return this;
	}
	
	public ItemStack build() {
		item.setItemMeta(meta);
		return item;
	}

}
