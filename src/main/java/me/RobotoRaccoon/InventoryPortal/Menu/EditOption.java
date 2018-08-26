package me.RobotoRaccoon.InventoryPortal.Menu;

import me.RobotoRaccoon.InventoryPortal.Warp;

public class EditOption {

    private Warp warp;
    private Setting setting;

    public enum Setting {
        DISPLAYNAME, DESCRIPTION
    }

    public EditOption(Warp warp, Setting setting) {
        setWarp(warp);
        setSetting(setting);
    }

    public Warp getWarp() {
        return warp;
    }

    public void setWarp(Warp warp) {
        this.warp = warp;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}
