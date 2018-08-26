package me.RobotoRaccoon.InventoryPortal.Menu;

import java.util.List;

import me.RobotoRaccoon.InventoryPortal.Warp;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import me.RobotoRaccoon.InventoryPortal.WarpList.SortType;

public class WarpSettings {

    private int page;
    private WarpList.SortType sort;
    private List<Warp> list;

    public WarpSettings() {
        setPage(1);
        setSort(SortType.NAME);
        setList(WarpList.getSortedWarps(getSort()));
    }

    public WarpSettings(WarpSettings settings) {
        setPage(settings.getPage());
        setSort(settings.getSort());
        setList(settings.getList());
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public WarpList.SortType getSort() {
        return this.sort;
    }

    public void setSort(WarpList.SortType sort) {
        this.sort = sort;
    }

    public List<Warp> getList() {
        return this.list;
    }

    public void setList(List<Warp> list) {
        this.list = list;
    }
}
