package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit;

import com.github.Fupery.InvMenu.API.Event.MenuCloseReason;
import me.RobotoRaccoon.InventoryPortal.Events.PlayerChatListener;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ClickableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.EditOption;
import me.RobotoRaccoon.InventoryPortal.Menu.EditOption.Setting;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class DescriptionButton extends ClickableButton {

    private Warp warp;

    public DescriptionButton(Warp warp) {
        super("edit.description");
        this.warp = warp;

        ConfigurationSection config = InventoryPortal.getConfiguration().getConfig();
        for (String s : warp.getDescription())
            getLore().add(String.format(config.getString("buttons.edit.description.description"), s));
        formatLore();
    }

    public void click(Player player, ClickType clickType) {
        EditOption settings = new EditOption(warp, Setting.DESCRIPTION);
        PlayerChatListener.EDIT_MAP.put(player, settings);
        String cancel = InventoryPortal.getConfiguration().getConfig().getString("command.edit.cancel");

        new LangString("command.edit.edit-description").send(player);
        new LangString("command.edit.cancel", cancel).send(player);
        InventoryPortal.getHandler().closeMenu(player, MenuCloseReason.SPECIAL);
    }
}
