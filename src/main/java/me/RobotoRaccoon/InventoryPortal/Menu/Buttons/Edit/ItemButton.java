package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit;

import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ButtonIcon;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.UpdatableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ItemButton extends UpdatableButton {

    private Warp warp;

    public ItemButton(EditMenu menu, Warp warp) {
        super("edit.item", menu);
        this.warp = warp;
        setItem(warp.getItem().getType());
        formatLore(warp.isItemCustom() ? warp.getItem().getType().name() : "N/A");
    }

    public void click(Player player, ClickType clickType) {
        if (clickType.isLeftClick())
            warp.setItem(new ButtonIcon(player.getInventory().getItemInMainHand()));
        else
            warp.setItem(null);

        update(new EditMenu(getMenu(), player, warp));
    }
}
