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

//where tf
public class OnCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        System.out.println("debug1");
        if (sender instanceof Player) {
            System.out.println("debug2");
            Player player = (Player) sender;
            ItemStack IronSword = new ItemStack(Material.IRON_SWORD);
            ItemStack IronHelm = new ItemStack(Material.IRON_HELMET);
            ItemStack IronChest = new ItemStack(Material.IRON_CHESTPLATE);
            ItemStack IronLeg = new ItemStack(Material.IRON_LEGGINGS);
            ItemStack IronBoots = new ItemStack(Material.IRON_BOOTS);
            ItemStack GAP = new ItemStack(Material.GOLDEN_APPLE);
            GAP.setAmount(2);
            player.getInventory().addItem(IronSword, IronHelm, IronChest, IronLeg, IronBoots, GAP);
            return true;
        }
        return false;
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