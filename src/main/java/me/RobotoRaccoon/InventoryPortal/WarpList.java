package me.RobotoRaccoon.InventoryPortal;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpList {
    private static final HashMap<String, Warp> WARPS = new HashMap<>();

    public enum SortType {
        NAME, CATEGORY, POPULARITY;

        private static SortType[] types = values();

        public SortType prev() {
            return types[((ordinal() - 1 + types.length) % types.length)];
        }

        public SortType next() {
            return types[((ordinal() + 1) % types.length)];
        }
    }

    public static void loadWarps() {
        WARPS.clear();
        ConfigurationSection config = InventoryPortal.getConfiguration().getWarp();
        for (String key : config.getKeys(false)) {
            Warp warp = new Warp(key, config.getConfigurationSection(key));
            WARPS.put(key, warp);
        }
    }

    public static Warp get(String name) {
        return WARPS.get(name.toLowerCase());
    }

    public static boolean contains(String name) {
        return WARPS.containsKey(name.toLowerCase());
    }

    public static void add(Warp warp) {
        WARPS.put(warp.getName(), warp);
    }

    public static void delete(Warp warp) {
        WARPS.remove(warp.getName());
        warp.delete();
    }

    public static List<Warp> getSortedWarps(SortType type) {
        List<Warp> warpList = new ArrayList<>(WARPS.values());
        sort(warpList, type);
        return warpList;
    }

    public static void sort(List<Warp> warpList, final SortType type) {
        warpList.sort((o1, o2) -> {
            switch (type) {
            case CATEGORY:
                return o1.getCategory().getName().compareTo(o2.getCategory().getName());
            case POPULARITY:
                return o2.getCount() - o1.getCount();
            case NAME:
            default:
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
