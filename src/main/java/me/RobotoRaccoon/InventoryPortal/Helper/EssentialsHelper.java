package me.RobotoRaccoon.InventoryPortal.Helper;

import com.earth2me.essentials.Essentials;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Warp;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class EssentialsHelper {
    private Essentials ess;

    public EssentialsHelper() {
        this.ess = getEssentials();
    }

    private static Essentials getEssentials() {
        Plugin ess = InventoryPortal.getPlugin().getServer().getPluginManager().getPlugin("Essentials");
        if (ess instanceof Essentials) {
            return (Essentials) ess;
        } else {
            return null;
        }
    }

    public void updateBackLocation(Player player) {
        if (ess != null) {
            ess.getUser(player).setLastLocation();
        }
    }

    public Integer importWarps(CommandSender sender) {
        if (ess == null) {
            return null;
        }

        Integer added = 0;
        for (String name : ess.getWarps().getList()) {
            if (!WarpList.contains(name)) {
                try {
                    Location loc = ess.getWarps().getWarp(name);
                    WarpList.add(new Warp(name, loc));
                    added++;
                    new LangString("command.import.import-warp", name).send(sender);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return added;
    }
}
