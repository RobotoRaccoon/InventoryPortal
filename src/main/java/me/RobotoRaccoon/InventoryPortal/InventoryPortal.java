package me.RobotoRaccoon.InventoryPortal;

import com.github.Fupery.InvMenu.API.Handler.MenuHandler;
import com.github.Fupery.InvMenu.Menu;
import me.RobotoRaccoon.InventoryPortal.Commands.CoreCommand;
import me.RobotoRaccoon.InventoryPortal.Commands.WarpCommand;
import me.RobotoRaccoon.InventoryPortal.Events.PlayerChatListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class InventoryPortal extends JavaPlugin {

    private static Plugin plugin;
    private static Configuration configuration;

    private static MenuHandler handler;

    private static HashMap<String, Category> categories = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        // Load configuration
        configuration = new Configuration();
        loadConfigData();

        // Menu handler
        handler = Menu.getMenuHandler(plugin);

        // Register commands
        this.getCommand("invportal").setExecutor(new CoreCommand());
        this.getCommand("warp").setExecutor(new WarpCommand());

        // Event listeners
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static void loadConfigData() {
        getConfiguration().createAllFiles();

        ConfigurationSection config = getConfiguration().getConfig().getConfigurationSection("category");
        getCategories().clear();
        for (String key : config.getKeys(false)) {
            Category category = new Category(key, config.getConfigurationSection(key));
            getCategories().put(key, category);
        }
        WarpList.loadWarps();

        CoreCommand.addAllCommands();
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static MenuHandler getHandler() {
        return handler;
    }

    public static HashMap<String, Category> getCategories() {
        return categories;
    }
}
