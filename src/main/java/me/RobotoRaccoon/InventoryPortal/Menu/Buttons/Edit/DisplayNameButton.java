package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit;

import com.github.Fupery.InvMenu.API.Event.MenuCloseReason;
import me.RobotoRaccoon.InventoryPortal.Events.PlayerChatListener;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ClickableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.EditOption;
import me.RobotoRaccoon.InventoryPortal.Menu.EditOption.Setting;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class DisplayNameButton extends ClickableButton {

    private Warp warp;

    public DisplayNameButton(Warp warp) {
        super("edit.display-name");
        this.warp = warp;
        formatLore(warp.getDisplayName());
    }

    public void click(Player player, ClickType clickType) {
        EditOption settings = new EditOption(warp, Setting.DISPLAYNAME);
        PlayerChatListener.editMap.put(player, settings);
        String cancel = InventoryPortal.getConfiguration().getConfig().getString("command.edit.cancel");

        new LangString("command.edit.edit-display-name").send(player);
        new LangString("command.edit.cancel", cancel).send(player);
        InventoryPortal.getHandler().closeMenu(player, MenuCloseReason.SPECIAL);
    }
}
