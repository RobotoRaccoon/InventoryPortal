package me.RobotoRaccoon.InventoryPortal.Menu.Buttons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ButtonIcon {

    private ItemStack item;

    public ButtonIcon(String parse) {
        String[] split = parse.split(":", 2);
        Material material = Material.valueOf(split[0]);
        item = new ItemStack(material);
    }

    public ButtonIcon(ItemStack item) {
        this.item = item;
    }

    public Material getType() {
        return item.getType();
    }

    @Override
    public String toString() {
        return getType().name();
    }
}
