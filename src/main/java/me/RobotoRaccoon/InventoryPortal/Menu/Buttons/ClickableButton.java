package me.RobotoRaccoon.InventoryPortal.Menu.Buttons;

import com.github.Fupery.InvMenu.API.Button.Button;
import me.RobotoRaccoon.InventoryPortal.Helper.SoundHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public abstract class ClickableButton extends PresetButton {

    public ClickableButton(String section) {
        super(section);
    }

    @Override
    public Button getButton() {
        return new Button(getItem(), 0, getName(), getLore().toArray(new String[0])) {
            public void onClick(Player player, ClickType clickType) {
                click(player, clickType);
                new SoundHelper("click").play(player);
            }
        };
    }

    protected abstract void click(Player paramPlayer, ClickType paramClickType);
}
