package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Titan implements CommandExecutor, Listener {
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
        player.getWorld().spawn(player.getLocation(), Skeleton.class, Sheep1 -> {
            Sheep1.setCustomName(ChatColor.RED + "§4☠ §c✦Titan✦ §4☠");
            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 200);
            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 15);
            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/0.4);
            ItemStack helmet = new ItemStack(Material.SPAWNER);
            ItemMeta meta4 = helmet.getItemMeta();
            assert meta4 != null;
            meta4.setDisplayName(ChatColor.BLUE + "Titan's Helmet");
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
            assert meta != null;
            meta.setColor(Color.GRAY);
            meta.setUnbreakable(true);
            meta.setDisplayName(ChatColor.BLUE + "Titan's Chestplate");
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
            assert meta2 != null;
            meta2.setColor(Color.SILVER);
            meta2.setUnbreakable(true);
            meta2.setDisplayName(ChatColor.BLUE + "Titan's Legging");
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
            assert meta3 != null;
            meta3.setColor(Color.WHITE);
            meta3.setUnbreakable(true);
            meta3.setDisplayName(ChatColor.BLUE + "Titan's Boots");
            chestplate.setItemMeta(meta);
            legging.setItemMeta(meta2);
            boots.setItemMeta(meta3);
            Objects.requireNonNull(Sheep1.getEquipment()).setChestplate(chestplate);
            Sheep1.getEquipment().setLeggings(legging);
            Sheep1.getEquipment().setBoots(boots);
            Sheep1.getEquipment().setHelmet(helmet);
            Sheep1.setHealth(200);
            Sheep1.addScoreboardTag("id:Titan");
            Sheep1.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
            Sheep1.setCustomNameVisible(true);
            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
            Sheep1.setTarget(player);
        });
        return true;
    }
        return true;
}

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:Titan")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Titanic", "§r§7The bone that moves so smooth and very hard", "§r§7Maybe this is why some people says teeth are harder then diamond"));
        meta.setDisplayName(ChatColor.GOLD + "Titan's Bone");
        item.setItemMeta(meta);
        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}
