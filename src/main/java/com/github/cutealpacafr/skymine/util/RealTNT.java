package com.github.cutealpacafr.skymine.util;

import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class RealTNT extends JavaPlugin implements Listener {
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        for (Block b : event.blockList()) {
            float y = (float) -2 + (float)  (Math.random() * (2 + 2) +(1));
            float x = (float) -0 + (float)  (Math.random() * (0) + (0));
            float z = (float) -0 + (float)  (Math.random() * (0) + (0));
            FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(
                    b.getLocation(), b.getType(), b.getData());
            fallingBlock.setDropItem(false);
            fallingBlock.setVelocity(new Vector(x, y, z));
        }
    }

}