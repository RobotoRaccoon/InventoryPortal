package me.RobotoRaccoon.InventoryPortal.Menu;

import com.github.Fupery.InvMenu.API.Button.Button;
import com.github.Fupery.InvMenu.API.Event.MenuCloseReason;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ClickableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit.CategoryButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit.DescriptionButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit.DisplayNameButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Edit.ItemButton;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;

public class EditMenu extends GenericMenu {

    private static final InventoryType TYPE = InventoryType.DISPENSER;

    private EditMenu menu;
    private Warp warp;

    public EditMenu(Player player, Warp warp) {
        this(null, player, warp);
    }

    public EditMenu(GenericMenu menu, Player player, Warp warp) {
        super(player, new LangString("menu.edit", warp.getName()), TYPE);
        this.warp = warp;
        this.menu = menu == null ? this : (EditMenu) menu;
    }

    protected void generateButtons() {
        buttons = new Button[9];

        buttons[0] = backButton();

        buttons[3] = new DisplayNameButton(warp).getButton();
        buttons[4] = new DescriptionButton(warp).getButton();

        buttons[6] = new ItemButton(menu, warp).getButton();
        buttons[7] = new CategoryButton(menu, warp).getButton();
        buttons[8] = locationButton();
    }

    @Override
    public void onMenuCloseEvent(Player player, MenuCloseReason reason) {
        if (reason != MenuCloseReason.SPECIAL) {
            warp.save();
        }
    }

    private Button backButton() {
        return new ClickableButton("edit.warp-menu") {
            public void click(Player player, ClickType clickType) {
                InventoryPortal.getHandler().openMenu(player, new WarpMenu(player));
            }
        }.getButton();
    }

    private Button locationButton() {
        return new ClickableButton("edit.location") {
            public void click(Player player, ClickType clickType) {
                warp.setLocation(player.getLocation());
                new LangString("command.edit.location").send(player);
                InventoryPortal.getHandler().closeMenu(player, MenuCloseReason.DONE);
            }
        }.getButton();
    }
}
