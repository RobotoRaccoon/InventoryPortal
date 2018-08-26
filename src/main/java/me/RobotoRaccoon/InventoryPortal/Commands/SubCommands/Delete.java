package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.entity.Player;

public class Delete extends SubCommand {

    public Delete() {
        setConsoleAllowed(true);
        setName("delete");
        setMinArgs(1);
    }

    public void handle(CommandInfo commandInfo) {
        String warpName = commandInfo.getArg(0);
        Player player = (Player) commandInfo.getSender();
        if (!WarpList.contains(warpName)) {
            new LangString("error.warp-not-found", warpName).send(player);
        } else {
            WarpList.delete(WarpList.get(warpName));
            new LangString("command.delete.warp-deleted").send(player);
        }
    }
}
