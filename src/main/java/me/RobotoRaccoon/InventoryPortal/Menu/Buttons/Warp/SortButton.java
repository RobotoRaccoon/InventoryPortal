package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Warp;

import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.UpdatableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.GenericMenu;
import me.RobotoRaccoon.InventoryPortal.Menu.WarpMenu;
import me.RobotoRaccoon.InventoryPortal.Menu.WarpSettings;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SortButton extends UpdatableButton {

    private WarpSettings settings;

    public SortButton(GenericMenu menu, WarpSettings settings) {
        super("warp.sort", menu);
        this.settings = settings;

        WarpList.SortType type = settings.getSort();
        formatLore(type.prev().name(), type
                .name(), type
                .next().name());
    }

    public void click(Player player, ClickType clickType) {
        WarpSettings next = new WarpSettings(settings);
        WarpList.SortType sort = next.getSort();
        if (clickType.isLeftClick()) {
            sort = sort.next();
        } else {
            sort = sort.prev();
        }
        next.setSort(sort);
        WarpList.sort(next.getList(), next.getSort());

        update(new WarpMenu(getMenu(), player, next));
    }
}
