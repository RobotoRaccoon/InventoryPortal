package me.RobotoRaccoon.InventoryPortal.Menu;

import me.RobotoRaccoon.InventoryPortal.Warp;

public class EditOption {

    private final Warp warp;
    private final Setting setting;

    public enum Setting {
        DISPLAYNAME, DESCRIPTION
    }

    public EditOption(Warp warp, Setting setting) {
        this.warp = warp;
        this.setting = setting;
    }

    public Warp getWarp() {
        return warp;
    }

    public Setting getSetting() {
        return setting;
    }
}
