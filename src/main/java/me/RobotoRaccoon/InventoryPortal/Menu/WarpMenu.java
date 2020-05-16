package me.RobotoRaccoon.InventoryPortal.Menu;

import com.github.Fupery.InvMenu.API.Button.Button;
import com.github.Fupery.InvMenu.API.Event.MenuCloseReason;
import me.RobotoRaccoon.InventoryPortal.Commands.CoreCommand;
import me.RobotoRaccoon.InventoryPortal.Commands.SubCommands.Random;
import me.RobotoRaccoon.InventoryPortal.InventoryPortal;
import me.RobotoRaccoon.InventoryPortal.LangString;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.ClickableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.PresetButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.UpdatableButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Warp.SortButton;
import me.RobotoRaccoon.InventoryPortal.Menu.Buttons.Warp.WarpButton;
import me.RobotoRaccoon.InventoryPortal.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class WarpMenu extends GenericMenu {

    private static final int PER_PAGE = 45;

    private WarpMenu menu;
    private WarpSettings settings;

    public WarpMenu(Player player) {
        this(null, player, new WarpSettings());
    }

    public WarpMenu(GenericMenu menu, Player player, WarpSettings settings) {
        super(player, new LangString("menu.warp"), 54);
        this.menu = menu == null ? this : (WarpMenu) menu;
        this.settings = settings;
    }

    protected void generateButtons() {
        buttons = new Button[PER_PAGE + 9];
        int page = settings.getPage();

        List<Warp> warps = settings.getList();
        int maxPage = Math.max(0, (warps.size() - 1) / PER_PAGE) + 1;

        buttons[0] = new SortButton(menu, settings).getButton();
        buttons[1] = new PresetButton("warp.info").getButton();

        if (page > 1) {
            buttons[3] = previousButton(menu, settings);
        }

        if (maxPage > 1) {
            buttons[4] = new PresetButton("warp.page").formatName(page, maxPage).getButton();
            if (page <= 64) {
                buttons[4].setAmount(page);
            }
        }

        if (page < maxPage) {
            buttons[5] = nextButton(menu, settings);
        }

        if (getPlayer().hasPermission(CoreCommand.subCommands.get("random").getPermission())) {
            buttons[8] = randomButton();
        }

        addWarpButtons(warps, page);
    }

    private void addWarpButtons(List<Warp> warps, final int page) {
        Warp[] warpArray = warps.toArray(new Warp[0]);
        for (int i = 0; i < PER_PAGE; i++) {
            int index = (page - 1) * PER_PAGE + i;
            if (index >= warps.size())
                break;

            buttons[i + 9] = new WarpButton(warpArray[index]).getButton();
        }
    }

    private Button previousButton(final WarpMenu menu, final WarpSettings settings) {
        return new UpdatableButton("warp.previous", menu) {
            public void click(Player player, ClickType clickType) {
                WarpSettings next = new WarpSettings(settings);
                next.setPage(next.getPage() - 1);
                update(new WarpMenu(getMenu(), player, next));
            }
        }.getButton();
    }

    private Button nextButton(final WarpMenu menu, final WarpSettings settings) {
        return new UpdatableButton("warp.next", menu) {
            public void click(Player player, ClickType clickType) {
                WarpSettings next = new WarpSettings(settings);
                next.setPage(next.getPage() + 1);
                update(new WarpMenu(getMenu(), player, next));
            }
        }.getButton();
    }

    private Button randomButton() {
        return new ClickableButton("warp.random") {
            public void click(Player player, ClickType clickType) {
                new Random().warp(player);
                InventoryPortal.getHandler().closeMenu(player, MenuCloseReason.DONE);
            }
        }.getButton();
    }
}
