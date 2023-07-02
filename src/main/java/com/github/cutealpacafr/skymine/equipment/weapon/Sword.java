package com.github.cutealpacafr.skymine.equipment.weapon;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sword implements CommandExecutor, Listener {
    public static ItemStack Sword;

    public Sword(){
        ItemStack item = new ItemStack((Material.IRON_SWORD));
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "The Sword.Charge'< DR.ALPACA's SOUL >'.HATE.SOUL.DIE.OUT.GET.OFF.FROM.MY.PLACE(HELL)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "For real");
        lore.add(ChatColor.GRAY + "Make everything returns Null");
        lore.add(ChatColor.GRAY + "To reaility unreachable");
        lore.add(ChatColor.GRAY + "Muda Muda");
        lore.add(ChatColor.GRAY + "No Usage.");
        meta.setLore(lore);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 200, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        Sword = item;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player player = (Player) commandSender;
        if (player.isOp() && s.equalsIgnoreCase("fr")) {
            player.getWorld().dropItemNaturally((player.getLocation()), Sword);
                player.sendMessage("fr");
        }return true;
    }
}
