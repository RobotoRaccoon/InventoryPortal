package me.RobotoRaccoon.InventoryPortal.Menu;

import me.RobotoRaccoon.InventoryPortal.Warp;
import me.RobotoRaccoon.InventoryPortal.WarpList;
import me.RobotoRaccoon.InventoryPortal.WarpList.SortType;

import java.util.List;

public class WarpSettings {

    private int page;
    private WarpList.SortType sort;
    private List<Warp> list;

    public WarpSettings() {
        this.page = 1;
        setSort(SortType.NAME);
        setList(WarpList.getSortedWarps(getSort()));
    }

    public void nextPage() {
        page++;
    }

    public void prevPage() {
        page--;
    }

    public int getPage() {
        return this.page;
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
