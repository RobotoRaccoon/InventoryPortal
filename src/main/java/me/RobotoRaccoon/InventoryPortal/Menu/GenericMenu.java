package me.RobotoRaccoon.InventoryPortal.Menu;

import com.github.Fupery.InvMenu.API.Button.Button;
import com.github.Fupery.InvMenu.API.Templates.BasicMenu;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public abstract class GenericMenu extends BasicMenu {

    protected Button[] buttons;
    private Player player;

    public GenericMenu(Player player, LangString title, InventoryType type) {
        super(InventoryPortal.getHandler(), title.toString(), type);
        this.player = player;
    }

    public GenericMenu(Player player, LangString title, int size) {
        super(InventoryPortal.getHandler(), title.toString(), size);
        this.player = player;
    }

    protected abstract void generateButtons();

    public Button[] getButtons() {
        if (buttons == null)
            generateButtons();
        return buttons;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(GenericMenu menu) {
        buttons = menu.getButtons();
        InventoryPortal.getHandler().refreshMenu(player);
    }

    //public void refresh() {
    //    InventoryPortal.getHandler().refreshMenu(player);
    //}
}
