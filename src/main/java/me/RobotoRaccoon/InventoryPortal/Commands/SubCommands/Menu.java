package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.Menu.WarpMenu;
import org.bukkit.entity.Player;

public class Menu extends SubCommand {

    public Menu() {
        setName("menu");
        setPermission("inventoryportal.use");
    }

    public void handle(CommandInfo commandInfo) {
        Player player = (Player) commandInfo.getSender();
        InventoryPortal.getHandler().openMenu(player, new WarpMenu(player));
    }
}
