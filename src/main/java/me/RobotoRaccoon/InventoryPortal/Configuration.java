package me.RobotoRaccoon.InventoryPortal;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configuration {

    private File configFile;
    private FileConfiguration config;

    private File langFile;
    private FileConfiguration lang;

    private File warpFile;
    private FileConfiguration warp;

    private File countFile;
    private FileConfiguration count;

    public Configuration() {

        configFile = new File(InventoryPortal.getPlugin().getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        langFile = new File(InventoryPortal.getPlugin().getDataFolder(), "lang.yml");
        lang = YamlConfiguration.loadConfiguration(langFile);

        warpFile = new File(InventoryPortal.getPlugin().getDataFolder(), "warp.yml");
        warp = YamlConfiguration.loadConfiguration(warpFile);

        countFile = new File(InventoryPortal.getPlugin().getDataFolder(), "count.yml");
        count = YamlConfiguration.loadConfiguration(countFile);
    }

    public void createAllFiles() {
        if (!configFile.exists())
            InventoryPortal.getPlugin().saveResource("config.yml", true);

        if (!langFile.exists())
            InventoryPortal.getPlugin().saveResource("lang.yml", true);

        if (!warpFile.exists())
            InventoryPortal.getPlugin().saveResource("warp.yml", true);

        if (!countFile.exists())
            InventoryPortal.getPlugin().saveResource("count.yml", true);

        loadConfigs();
    }

    public boolean loadConfigs() {
        try {
            config = YamlConfiguration.loadConfiguration(new File(InventoryPortal.getPlugin().getDataFolder(), "config.yml"));
            lang   = YamlConfiguration.loadConfiguration(new File(InventoryPortal.getPlugin().getDataFolder(), "lang.yml"));
            warp   = YamlConfiguration.loadConfiguration(new File(InventoryPortal.getPlugin().getDataFolder(), "warp.yml"));
            count  = YamlConfiguration.loadConfiguration(new File(InventoryPortal.getPlugin().getDataFolder(), "count.yml"));
            InventoryPortal.getPlugin().reloadConfig();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveWarps() {
        try {
            warp.save(warpFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCounts() {
        try {
            count.save(countFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public FileConfiguration getLang() {
        return lang;
    }

    public FileConfiguration getWarp() {
        return warp;
    }

    public FileConfiguration getCount() {
        return count;
    }

}
