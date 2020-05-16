package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Warp;

import com.github.Fupery.InvMenu.API.Event.MenuCloseReason;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommands.Edit;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ClickableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class WarpButton extends ClickableButton {

    private Warp warp;

    public WarpButton(Warp warp) {
        super(null);
        this.warp = warp;
        loadButton();
    }

    public void click(Player player, ClickType clickType) {
        if (clickType.isRightClick() && player.hasPermission(new Edit().getPermission())) {
            InventoryPortal.getHandler().openMenu(player, new EditMenu(player, getWarp()));
        } else {
            InventoryPortal.getHandler().closeMenu(player, MenuCloseReason.DONE);
            getWarp().go(player);
        }
    }

    private void loadButton() {
        List<String> lore = warp.getCategory().getLoreFormat();
        addDescriptionLore(lore, warp.getCategory().getDescriptionFormat());

        setItem(warp.getItem().getType());

        setName(warp.getCategory().getNameFormat());
        formatName(warp.getDisplayName(), warp.getName());

        setLore(lore);
        formatLore(warp.getCategory().getDisplayName());
    }

    private void addDescriptionLore(List<String> lore, String format) {
        for (String s : warp.getDescription())
            lore.add(String.format(format, s));
    }

    public Warp getWarp() {
        return this.warp;
    }
}
