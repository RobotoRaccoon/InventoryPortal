package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Warp;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.entity.Player;

public class Goto extends SubCommand {

    public Goto() {
        setName("goto");
        setPermission("inventoryportal.use");
        setMinArgs(1);
    }

    public void handle(CommandInfo commandInfo) {
        Player player = (Player) commandInfo.getSender();

        String warpName = commandInfo.getArg(0);
        if (!WarpList.contains(warpName)) {
            new LangString("error.warp-not-found", warpName).send(player);
            return;
        }

        Warp warp = WarpList.get(warpName);
        warp.go(player);
    }
}
