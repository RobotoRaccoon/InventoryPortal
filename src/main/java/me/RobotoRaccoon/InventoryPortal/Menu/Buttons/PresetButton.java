package me.RobotoRaccoon.InventoryPortal.Menu.Buttons;

import com.github.Fupery.InvMenu.API.Button.Button;
import com.github.Fupery.InvMenu.API.Button.StaticButton;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedList;
import java.util.List;

public class PresetButton {

    private Material item;
    private int durability;
    private String name;
    private List<String> lore;

    public PresetButton(String section) {
        loadButton(section);
    }

    public Button getButton() {
        return new StaticButton(getItem(), getDurability(), getName(), getLore().toArray(new String[0]));
    }

    private void loadButton(String section) {
        if (section == null || section.isEmpty())
            return;

        ConfigurationSection config = InventoryPortal.getConfiguration().getConfig().getConfigurationSection("buttons." + section);

        ButtonIcon icon = new ButtonIcon(config.getString("item"));
        String name = ChatColor.translateAlternateColorCodes('&', config.getString("name"));
        List<String> lore = config.getStringList("lore");

        setItem(icon.getType());
        setName(name);
        setLore(lore);
    }

    public PresetButton formatName(Object... args) {
        name = String.format(getName(), args);
        name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public PresetButton formatLore(Object... args) {
        for (int i = 0; i < getLore().size(); i++) {
            getLore().set(i, ChatColor.translateAlternateColorCodes('&', String.format(getLore().get(i), args)));
        }
        return this;
    }

    public Material getItem() {
        return this.item;
    }

    protected void setItem(Material item) {
        this.item = item;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return this.lore;
    }

    protected void setLore(List<String> lore) {
        this.lore = new LinkedList<>();
        for (String l : lore) {
            this.lore.add(ChatColor.translateAlternateColorCodes('&', l));
        }
    }
}
