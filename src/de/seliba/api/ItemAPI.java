package de.seliba.api;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagList;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Map;

public class ItemAPI {
	
	ItemStack itemStack;
	ItemMeta itemMeta;
	SkullMeta skullMeta;
	LeatherArmorMeta leatherArmorMeta;
	boolean glowing;
	
	public ItemAPI(Material material, int amout, short subid, String name, List<String> lore, Map<Enchantment, Integer> enchantments, List<ItemFlag> itemflags, Boolean glowing, String owner, Color color) {
		itemStack = new ItemStack(material, amout, subid);
		itemStack.addEnchantments(enchantments);
		this.glowing = glowing;
		if(material == Material.LEGACY_SKULL_ITEM) {
			skullMeta = (SkullMeta) itemStack.getItemMeta();
			skullMeta.setDisplayName(name);
			skullMeta.setLore(lore);
			skullMeta.setOwner(owner);
			for(ItemFlag flags : itemflags) {
				skullMeta.addItemFlags(flags);
			}
			itemStack.setItemMeta(skullMeta);
		} else if(material == Material.LEATHER_BOOTS || material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_HELMET || material == Material.LEATHER_LEGGINGS) {
			leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
			leatherArmorMeta.setDisplayName(name);
			leatherArmorMeta.setLore(lore);
			leatherArmorMeta.setColor(color);
			for(ItemFlag flags : itemflags) {
				leatherArmorMeta.addItemFlags(flags);
			}
			itemStack.setItemMeta(leatherArmorMeta);
		} else {
			itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(name);
			itemMeta.setLore(lore);
			for(ItemFlag flags : itemflags) {
				itemMeta.addItemFlags(flags);
			}
			itemStack.setItemMeta(itemMeta);
		}
	}
	
	private ItemStack getGlowingItem() {
        if (itemMeta.hasEnchants() || leatherArmorMeta.hasEnchants() || skullMeta.hasEnchants())
            return itemStack;
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
        tag.set("ench", new NBTTagList());
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
	}

	public ItemStack build() {
		if(glowing) {
			return getGlowingItem();
		}
		return itemStack;
	}

}
