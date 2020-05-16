package me.RobotoRaccoon.InventoryPortal.Events;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.RobotoRaccoon.InventoryPortal.Helper.SoundHelper;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.Menu.EditOption;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    public static HashMap<Player, EditOption> editMap = new HashMap<>();

    private static final String CANCEL = "cancel";
    private static final int SPLIT_COLUMN = 45;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!editMap.containsKey(player))
            return;

        event.setCancelled(true);
        EditOption s = editMap.get(player);
        editMap.remove(player);

        String message = event.getMessage();
        String cancel = InventoryPortal.getConfiguration().getConfig().getString("command.edit.cancel");
        // If message is CANCEL or the one defined in the config, do not edit.
        if (message.equalsIgnoreCase(CANCEL) || message.equalsIgnoreCase(cancel)) {
            new SoundHelper("edit-cancel").play(player);
            openMenu(player, s.getWarp());
            return;
        }

        // Update appropriate setting
        switch (s.getSetting()) {
            case DISPLAYNAME:
                s.getWarp().setDisplayName(message);
                break;
            case DESCRIPTION:
                s.getWarp().setDescription(warpString(message));
                break;
        }

        new SoundHelper("edit-success").play(player);
        openMenu(player, s.getWarp());
    }

    private void openMenu(Player player, Warp warp) {
        Bukkit.getScheduler().runTask(InventoryPortal.getPlugin(), () -> {
            InventoryPortal.getHandler().openMenu(player, new EditMenu(player, warp));
        });
    }

    private List<String> warpString(String message) {
        message = WordUtils.wrap(message, SPLIT_COLUMN, null, true);
        return Arrays.asList(message.split("\\n"));
    }
}
