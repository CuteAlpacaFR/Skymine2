package test.test;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class CD implements Listener {
    private static Plugin plugin = null;
    private int id = -1;

    public CD(Plugin instance) {
        plugin = instance;
    }
    public CD(Runnable runnable) {
        this(runnable, 0);
    }

    public CD(Runnable runnable, long delay) {
        if (plugin.isEnabled()) {
            id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay);
        } else {
            runnable.run();
        }
    }
}