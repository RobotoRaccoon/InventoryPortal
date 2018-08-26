package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;

public class Reload extends SubCommand {

    public Reload() {
        setConsoleAllowed(true);
        setName("reload");
    }

    public void handle(CommandInfo commandInfo) {
        InventoryPortal.loadConfigData();
        new LangString("command.reload.reloaded").send(commandInfo.getSender());
    }
}
