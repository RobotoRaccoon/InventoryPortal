package me.RobotoRaccoon.InventoryPortal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class WarpList {
    private static HashMap<String, Warp> warps = new HashMap<>();

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
        warps.clear();
        ConfigurationSection config = InventoryPortal.getConfiguration().getWarp();
        for (String key : config.getKeys(false)) {
            Warp warp = new Warp(key, config.getConfigurationSection(key));
            warps.put(key, warp);
        }
    }

    public static Warp get(String name) {
        return warps.get(name.toLowerCase());
    }

    public static boolean contains(String name) {
        return warps.containsKey(name.toLowerCase());
    }

    public static void add(Warp warp) {
        warps.put(warp.getName(), warp);
    }

    public static void delete(Warp warp) {
        warps.remove(warp.getName());
        warp.delete();
    }

    public static List<Warp> getSortedWarps(SortType type) {
        List<Warp> warpList = new ArrayList<>(warps.values());
        sort(warpList, type);
        return warpList;
    }

    public static void sort(List<Warp> warpList, final SortType type) {
        Collections.sort(warpList, new Comparator<Warp>() {
            @Override
            public int compare(Warp o1, Warp o2) {
                switch (type) {
                    case NAME:
                    default:
                        return o1.getName().compareTo(o2.getName());
                    case CATEGORY:
                        return o1.getCategory().getName().compareTo(o2.getCategory().getName());
                    case POPULARITY:
                        return o2.getCount() - o1.getCount();
                }
            }
        });
    }
}
