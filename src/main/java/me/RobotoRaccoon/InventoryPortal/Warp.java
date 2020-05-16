package me.RobotoRaccoon.InventoryPortal;

import me.RobotoRaccoon.InventoryPortal.Helper.EssentialsHelper;
import me.RobotoRaccoon.InventoryPortal.Helper.SoundHelper;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ButtonIcon;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.List;

public class Warp {

    private String name;
    private String displayName;
    private List<String> description;
    private Category category;
    private ButtonIcon item;
    private Location location;

    private int count;

    private Warp() {
        description = new ArrayList<>();
    }

    public Warp(String name, Location location) {
        this();
        setName(name);
        setDisplayName(name);
        setCategory(InventoryPortal.getCategories().get("default"));
        setLocation(location);
        setCount(0);

        save();
    }

    public Warp (String name, Player player) {
        this(name, player.getLocation());
    }

    public Warp (String name, ConfigurationSection config) {
        this();
        setName(name);
        setDisplayName(config.getString("display-name"));

        if (config.isList("description")) {
            setDescription(config.getStringList("description"));
        } else {
            addDescription(config.getString("description"));
        }

        setCategory(InventoryPortal.getCategories().get(config.getString("category")));
        try {
            if (config.contains("item"))
                setItem(new ButtonIcon(config.getString("item")));
        } catch (IllegalArgumentException e) {}

        World world = InventoryPortal.getPlugin().getServer().getWorld(config.getString("world"));
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");
        float yaw = (float) config.getDouble("yaw");
        float pitch = (float) config.getDouble("pitch");
        setLocation(new Location(world, x, y, z, yaw, pitch));

        setCount(InventoryPortal.getConfiguration().getCount().getInt(getName()));
    }

    public void go(Player player) {
        if (getLocation().getWorld() == null) {
            new LangString("error.warp-not-loaded").send(player);
            return;
        }

        ConfigurationSection config = InventoryPortal.getConfiguration().getConfig().getConfigurationSection("command.warp");
        if (config.getBoolean("use-essentials-back")) {
            new EssentialsHelper().updateBackLocation(player);
        }

        player.teleport(getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        new LangString("command.goto.warping", getDisplayName()).send(player);
        new SoundHelper("warp").play(player);
        incrementCount();
    }

    public void save() {
        ConfigurationSection config = InventoryPortal.getConfiguration().getWarp();
        config = config.createSection(getName());
        config.set("display-name", getDisplayName());
        config.set("description", getDescription());
        config.set("category", getCategory().getName());
        config.set("item", isItemCustom() ? item.toString() : null);

        config.set("world", getLocation().getWorld().getName());
        config.set("x", getLocation().getX());
        config.set("y", getLocation().getY());
        config.set("z", getLocation().getZ());
        config.set("yaw", getLocation().getYaw());
        config.set("pitch", getLocation().getPitch());

        InventoryPortal.getConfiguration().getCount().set(getName(), getCount());

        InventoryPortal.getConfiguration().saveWarps();
        InventoryPortal.getConfiguration().saveCounts();
    }

    public void delete() {
        Configuration config = InventoryPortal.getConfiguration();
        config.getWarp().set(getName(), null);
        config.getCount().set(getName(), null);

        config.saveWarps();
        config.saveCounts();
    }

    public void incrementCount() {
        count++;
        InventoryPortal.getConfiguration().getCount().set(getName(), getCount());
        InventoryPortal.getConfiguration().saveCounts();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    private void addDescription(String item) {
        description.add(item);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isItemCustom() {
        return item != null;
    }

    public ButtonIcon getItem() {
        return isItemCustom() ? item : category.getItem();
    }

    public void setItem(ButtonIcon item) {
        this.item = (item == null || item.getType().isAir()) ? null : item;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
