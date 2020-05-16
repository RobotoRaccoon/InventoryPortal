package me.RobotoRaccoon.InventoryPortal.Commands;

import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    private boolean consoleAllowed = false;
    private int minArgs = 0;
    private String name = "";
    private String permission = "";

    public abstract void handle(CommandInfo commandInfo);

    public void execute(CommandInfo info) {
        CommandSender sender = info.getSender();

        // Sender is console and command does not allow console access.
        if (!(sender instanceof Player) && !isConsoleAllowed()) {
            new LangString("error.no-console").send(sender);
            return;
        }

        // Player does not have permission to use the command.
        if (!sender.hasPermission(getPermission())) {
            new LangString("error.no-permission").send(sender);
            return;
        }

        // Not enough arguments have been supplied.
        if (info.getArgs().length < getMinArgs()) {
            new LangString("error.arguments").send(sender);
            new LangString().append("/" + info.getLabel() + " " + getUsage()).send(sender);
            return;
        }

        // Run the command.
        handle(info);
    }

    public ConfigurationSection getConfig() {
        return InventoryPortal.getConfiguration().getConfig().getConfigurationSection("command." + getName());
    }

    public String getDescription() {
        return new LangString("command." + getName() + ".description").getMessage();
    }

    public String getUsage() {
        return new LangString("command." + getName() + ".usage").getMessage();
    }

    public boolean isConsoleAllowed() {
        return consoleAllowed;
    }

    protected void setConsoleAllowed(boolean consoleAllowed) {
        this.consoleAllowed = consoleAllowed;
    }

    public int getMinArgs() {
        return minArgs;
    }

    protected void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
        setPermission("inventoryportal." + name);
    }

    public String getPermission() {
        return permission;
    }

    protected void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getAliases() {
        ConfigurationSection config = InventoryPortal.getConfiguration().getConfig().getConfigurationSection("aliases");
        if (config != null && config.isList(getName())) {
            return config.getStringList(getName());
        } else {
            return new ArrayList<>();
        }
    }
}
