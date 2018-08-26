package me.RobotoRaccoon.InventoryPortal.Menu.Buttons;

import me.RobotoRaccoon.InventoryPortal.Menu.GenericMenu;

public abstract class UpdatableButton extends ClickableButton {

    private GenericMenu menu;

    public UpdatableButton(String section, GenericMenu menu) {
        super(section);
        setMenu(menu);
    }

    protected void update(GenericMenu menu) {
        getMenu().update(menu);
    }

    protected GenericMenu getMenu() {
        return menu;
    }

    private void setMenu(GenericMenu menu) {
        this.menu = menu;
    }

}
