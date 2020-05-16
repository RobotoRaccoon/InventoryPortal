package me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit;

import me.RobotoRaccoon.InventoryPortal.Category;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.UpdatableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.EditMenu;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.LinkedList;
import java.util.List;

public class CategoryButton extends UpdatableButton {

    private Warp warp;
    private Category prev;
    private Category next;

    public CategoryButton(EditMenu menu, Warp warp) {
        super("edit.category", menu);
        this.warp = warp;
        setIcon();
    }

    public void click(Player player, ClickType clickType) {
        if (clickType.isLeftClick())
            warp.setCategory(next);
        else
            warp.setCategory(prev);

        update(new EditMenu(getMenu(), player, warp));
    }

    private void setIcon() {
        List<String> names = new LinkedList<>();
        names.addAll(InventoryPortal.getCategories().keySet());

        int index = names.indexOf(this.warp.getCategory().getName());
        int p = (index - 1 + names.size()) % names.size();
        int n = (index + 1) % names.size();

        prev = InventoryPortal.getCategories().get(names.get(p));
        next = InventoryPortal.getCategories().get(names.get(n));

        setItem(warp.getCategory().getItem().getType());
        formatLore(prev.getDisplayName(),
                warp.getCategory().getDisplayName(),
                next.getDisplayName());
    }
}
