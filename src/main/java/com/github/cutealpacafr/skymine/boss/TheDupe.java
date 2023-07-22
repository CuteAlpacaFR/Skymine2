package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class TheDupe implements CommandExecutor, Listener {
    private int _hitCounter = 0;

    @SuppressWarnings("NullableProblems")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            player.getWorld().spawn(player.getLocation(), Blaze.class, Sheep1 -> {
                Sheep1.setCustomName("§4♠ The Dupe ♠");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 300);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 30);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(300);
                Sheep1.addScoreboardTag("id:TD");
                Objects.requireNonNull(Sheep1.getEquipment()).setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.4.Skilra");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.3.Skilra is uncontrolled, Please be careful while Executing or Spawning this bosses");
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 1);
                Sheep1.setTarget(player);
            });
        }
        return true;
    }


    @EventHandler
    public void onDamagePlayer(EntityDamageByEntityEvent e) {
        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if attacker is not a Wither Skeleton (Boss)
        if (!(attacker instanceof Blaze boss)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§4♠ The Dupe ♠"))) return;

        // Return if hurt entity is not a player
        if (!(hurt instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;
        Random ran = new Random();
        int chance = ran.nextInt(10);
        if (chance < 5)
            for (int i = 0; i < 10; i++)
                player.getWorld().spawn(player.getLocation(), Bat.class, SheepBAT -> {
                });
        if (chance < 9)
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 255));
        if (chance < 2) {
            player.setVelocity(new Vector(0, 10, 0));
        }
        // Skilra explosion
        if (_hitCounter % 10 == 0) {
            player.getWorld().spawn(player.getLocation(), Blaze.class, Sheep1 -> {
                Sheep1.setCustomName("§4♠ The Dupe. ♠");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/300);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 10);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.5);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(300);
                Sheep1.addScoreboardTag("id:SMILE");
                Objects.requireNonNull(Sheep1.getEquipment()).setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.4.Skilra");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.3.Skilra is uncontrolled, Please be careful while Executing or Spawning this bosses");
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 1);
                Sheep1.setTarget(player);
            });
            // Every 20 hits
            if (_hitCounter % 20 == 0)
                boss.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 40, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255));
            player.setVelocity(new Vector(0, 100, 0));
            player.sendMessage("§4♠ The Dupe ♠: §cENJOY SOME SKY DIVING HAHAHAHAH");
        }
        // Every 30 hits
        if (_hitCounter % 30 == 0) {
            // Want timer delay?
            boss.teleport(player);
            player.spawnParticle(Particle.REDSTONE, player.getLocation(), 50, 0, 1, 0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 5));
            player.sendMessage("§4♠ The Dupe ♠: §cIS THAT HURT??? HAHAHAHAHAHA");
        }
        if (_hitCounter % 50 == 0) {
            Random ran1 = new Random();
            int chance1 = ran1.nextInt(10);
            if (chance1 < 9)
                player.setHealth(10);
            if (chance1 < 8)
                player.setHealth(20);
            if (chance1 < 5)
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
        }
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:TD")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.SPIDER_EYE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Very Hard", "§r§7Any bloody part of skilra is very rare and hard", "§r§7And the meat is the hardest", "§r§7Because Skilra's skin and meat is filled with netherite and nether energy and", "§r§7Because of Skilra is actually a SI-Based life so their skin is very very hard! and that also make skilra slower", "§r§6Fun fact! Skilra and any nether mob is SI-based life!"));
        meta.setDisplayName(ChatColor.GOLD + "Skilra's Meat");
        item.setItemMeta(meta);
        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}