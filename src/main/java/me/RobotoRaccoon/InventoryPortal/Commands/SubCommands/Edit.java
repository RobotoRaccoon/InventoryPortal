package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.entity.Player;

public class Edit extends SubCommand {

    public Edit() {
        setName("edit");
        setMinArgs(1);
    }

    public void handle(CommandInfo commandInfo) {
        String warpName = commandInfo.getArg(0);
        Player player = (Player) commandInfo.getSender();
        if (!WarpList.contains(warpName)) {
            new LangString("error.warp-not-found", warpName).send(player);
            return;
        }

        InventoryPortal.getHandler().openMenu(player, new EditMenu(player, WarpList.get(warpName)));
    }
}
