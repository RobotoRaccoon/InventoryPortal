package me.RobotoRaccoon.InventoryPortal.Commands.SubCommands;

import me.RobotoRaccoon.InventoryPortal.Commands.CommandInfo;
import me.RobotoRaccoon.InventoryPortal.Commands.CoreCommand;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommand;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class Help extends SubCommand {

    public Help() {
        setConsoleAllowed(true);
        setName("help");
    }

    public void handle(CommandInfo commandInfo) {
        CommandSender sender = commandInfo.getSender();

        new LangString("command.help.header").send(sender);
        new LangString("command.help.about").send(sender);

        for (Map.Entry<String, SubCommand> entry : CoreCommand.subCommands.entrySet()) {
            String name = entry.getKey();
            SubCommand cmd = entry.getValue();

            // Ignore help command and aliases of any command
            if (cmd == this || !name.equalsIgnoreCase(cmd.getName())) {
                continue;
            }

            if (sender.hasPermission(cmd.getPermission())) {
                new LangString("command.help.format", cmd.getUsage(), cmd.getDescription()).send(sender);
            }
        }

        new LangString("command.help.footer").send(sender);
    }
}
