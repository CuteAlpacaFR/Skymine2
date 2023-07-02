package test.test;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ZoneWarrior implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender Sender,@NotNull Command command,@NotNull String s, String[] strings) {
        if (Sender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), Zombie.class, Sheep -> {
                Sheep.setCustomName("§4☠ §c✦Zone Warrior✦ §4☠");
                Objects.requireNonNull(Sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 100);
                Objects.requireNonNull(Sheep.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 7);
                Objects.requireNonNull(Sheep.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.5);
                ItemStack helmet = new ItemStack(Material.SKELETON_SKULL);
                SkullMeta meta4 = (SkullMeta) helmet.getItemMeta();
                assert meta4 != null;
                meta4.setDisplayName(ChatColor.BLUE + "Zone Warrior's Helmet");
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
                assert meta != null;
                meta.setColor(Color.GRAY);
                meta.setUnbreakable(true);
                meta.setDisplayName(ChatColor.BLUE + "Zone Warrior's Chestplate");
                ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
                assert meta2 != null;
                meta2.setColor(Color.SILVER);
                meta2.setUnbreakable(true);
                meta2.setDisplayName(ChatColor.BLUE + "Zone Warrior's Legging");
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
                assert meta3 != null;
                meta3.setColor(Color.WHITE);
                meta3.setUnbreakable(true);
                meta3.setDisplayName(ChatColor.BLUE + "Zone Warrior's Boots");
                chestplate.setItemMeta(meta);
                legging.setItemMeta(meta2);
                boots.setItemMeta(meta3);
                Objects.requireNonNull(Sheep.getEquipment()).setChestplate(chestplate);
                Sheep.getEquipment().setLeggings(legging);
                Sheep.getEquipment().setBoots(boots);
                Sheep.getEquipment().setHelmet(helmet);
                Sheep.setHealth(100);
                Sheep.addScoreboardTag("id:ZW");
                Sheep.setCustomNameVisible(true);
                Objects.requireNonNull(Sheep.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep.setTarget(player);
            });
            return true;
        }
        return true;
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:ZW")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.BEETROOT);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§7A heart that even still beating without any other boody piece","§r§7Somehow this heart is very hard that used to craft armor","§r§7And its beating so fast that gains energy when you're holding it" ));
        meta.setDisplayName(ChatColor.GOLD + "Zone Warrior's Heart");
        item.setItemMeta(meta);
        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
    }