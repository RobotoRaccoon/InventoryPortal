package me.RobotoRaccoon.InventoryPortal.Helper;

import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundHelper {

    private Sound sound;

    public SoundHelper(String key) {
        load(key);
    }

    private void load(String key) {
        String name = InventoryPortal.getConfiguration().getConfig().getString("sound." + key);
        try {
            if (name.length() > 0)
                sound = Sound.valueOf(name);
        } catch (IllegalArgumentException e) {
            InventoryPortal.getPlugin().getLogger().warning("The specified sound `" + name + "` does not exist...");
        }
    }

    public void play(Player player) {
        if (sound != null)
            player.playSound(player.getLocation(), sound, 1000, 1);
    }
}
