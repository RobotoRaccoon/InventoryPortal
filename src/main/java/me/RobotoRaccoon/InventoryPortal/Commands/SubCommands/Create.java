package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.Warp;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.entity.Player;

public class Create extends SubCommand {

    public Create() {
        setName("create");
        setMinArgs(1);
    }

    public void handle(CommandInfo commandInfo) {
        String warpName = commandInfo.getArg(0);
        Player player = (Player) commandInfo.getSender();
        if (WarpList.contains(warpName)) {
            new LangString("command.create.already-exists").send(player);
            return;
        }

        Warp warp = new Warp(warpName, player);
        WarpList.add(warp);
        new LangString("command.create.warp-created").send(player);

        InventoryPortal.getHandler().openMenu(player, new EditMenu(player, warp));
    }
}
