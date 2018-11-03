package de.seliba.api;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryAPI {
	
	/*
	 * By pixelino / fnogen
	 */

    public static final int TOP = 0,
            RIGHT = 1,
            BOTTOM = 2,
            LEFT = 3,
            FULL = 4;

    private Inventory inventory;

    public InventoryAPI(final Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryAPI(final int rows, final String title) {
        this(Bukkit.createInventory(null, ((rows >= 1 && rows <= 8) ? rows : 3) * 9, title));
    }

    public InventoryAPI setItem(final int slot, final ItemStack itemStack) {
        inventory.setItem(slot, itemStack);
        return this;
    }

    public InventoryAPI fillRow(final int row, final ItemStack itemStack) {
        for (int i = (9 * row); i < (9 * row) + 9; i++)
            inventory.setItem(i, itemStack);
        return this;
    }

    public InventoryAPI fillColumn(final int column, final ItemStack itemStack) {
        for (int i = column; i < inventory.getSize(); i += 9)
            inventory.setItem(i, itemStack);
        return this;
    }

    public InventoryAPI fill(final ItemStack itemStack) {
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, itemStack);
        return this;
    }

    public InventoryAPI border(final ItemStack itemStack, int... positions) {
        for (int position : positions) {
            switch (position) {
            }

            switch (position) {
                case TOP:
                    fillRow(0, itemStack);
                    break;
                case RIGHT:
                    fillColumn(8, itemStack);
                    break;
                case BOTTOM:
                    fillRow((inventory.getSize() / 9) - 1, itemStack);
                    break;
                case LEFT:
                    fillColumn(0, itemStack);
                    break;
                case FULL:
                    border(itemStack, TOP, RIGHT, BOTTOM, LEFT);
                    break;
            }
        }
        return this;
    }

    public Inventory build() {
        return inventory;
    }
}
