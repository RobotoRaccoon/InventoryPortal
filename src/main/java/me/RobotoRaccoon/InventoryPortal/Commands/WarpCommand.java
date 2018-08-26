package me.RobotoRaccoon.InventoryPortal.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        String subCommandName;
        if (args.length > 0) {
            subCommandName = "goto";
        } else {
            // No warp given - use menu
            subCommandName = "menu";
        }

        // Run the command.
        final SubCommand subCommand = CoreCommand.subCommands.get(subCommandName);
        subCommand.execute(new CommandInfo(label, sender, args));
        return true;
    }
}
