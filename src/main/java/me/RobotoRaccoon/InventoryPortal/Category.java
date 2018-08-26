package me.RobotoRaccoon.InventoryPortal;

import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ButtonIcon;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private String displayName;
    private ButtonIcon item;

    private String descriptionFormat;
    private List<String> loreFormat;
    private String nameFormat;

    public Category(String name, ConfigurationSection config) {
        ConfigurationSection defaultConfig = config.getParent().getConfigurationSection("default");

        // Required
        this.name = name;
        this.displayName = config.getString("display-name");
        this.item = new ButtonIcon(config.getString("item"));

        // Optional parameters
        descriptionFormat = config.getString("description", defaultConfig.getString("description"));
        nameFormat = config.getString("name", defaultConfig.getString("name"));

        if (config.contains("lore"))
            loreFormat = config.getStringList("lore");
        else
            loreFormat = defaultConfig.getStringList("lore");
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ButtonIcon getItem() {
        return item;
    }

    public String getDescriptionFormat() {
        return descriptionFormat;
    }

    public List<String> getLoreFormat() {
        return new ArrayList<>(loreFormat);
    }

    public String getNameFormat() {
        return nameFormat;
    }
}
