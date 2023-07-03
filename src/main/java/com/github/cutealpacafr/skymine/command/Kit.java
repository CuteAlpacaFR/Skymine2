package com.github.cutealpacafr.skymine.command;


import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class Kit implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        gapple.setAmount(2);
        player.getInventory().addItem(sword, helmet, chestplate, leggings, boots, gapple);
        return true;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("OP"))
            event.setCancelled(true);
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("OP"))
            event.setCancelled(true);
    }
}