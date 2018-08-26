package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.Helper.EssentialsHelper;
import me.RobotoRaccoon.InventoryPortal.LangString;

public class Import extends SubCommand {

    public Import() {
        setConsoleAllowed(true);
        setName("import");
    }

    public void handle(CommandInfo info) {
        Integer count = new EssentialsHelper().importWarps(info.getSender());
        new LangString("command.import.import-count", count).send(info.getSender());
    }
}
