package me.RobotoRaccoon.InventoryPortal.Helper;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldGuardHelper {

    private static final WorldGuardPlugin wg;

    static {
        wg = getWorldGuard();
    }

    private static WorldGuardPlugin getWorldGuard() {
        Plugin wg = InventoryPortal.getPlugin().getServer().getPluginManager().getPlugin("WorldGuard");
        return (wg instanceof WorldGuardPlugin) ? (WorldGuardPlugin) wg : null;
    }

    public static boolean canBuild(Player player, Location location) {
        if (wg == null) {
            return true;
        }

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        return query.testBuild(BukkitAdapter.adapt(location), wg.wrapPlayer(player), Flags.BUILD);
    }
}
