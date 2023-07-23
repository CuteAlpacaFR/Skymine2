package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static java.util.Objects.requireNonNull;

public class SoulEater extends JavaPlugin implements CommandExecutor, Listener {
    public int _hitCounter = 0;

    @SuppressWarnings("NullableProblems")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), Wither.class, Sheep1 -> {
                Sheep1.setCustomName("§b⚛ Soul ♡ Eater ⚛");
                requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 1000);
                requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 50);
                requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.25);
                requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(1000);
                Sheep1.addScoreboardTag("id:SE");
                requireNonNull(Sheep1.getEquipment()).setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.6.SoulEater");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.6.SoulEater is uncontrolled, Please be careful while Executing or Spawning this bosses");
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
        if (!(attacker instanceof Player player)) return;
        if (!(hurt instanceof Wither boss)) return;
        // Return if attacker name is not the boss name
        if (!(Objects.equals(hurt.getCustomName(), "§b⚛ Soul ♡ Eater ⚛")))
            return;
        // Return if hurt entity is not a player
        _hitCounter++;
        boss.setLastDamage(boss.getLastDamage() + 1);
        Random random = new Random();
        int chance = random.nextInt(30);
        if (chance == 5) {
            Location playerLocation = player.getLocation();
            playerLocation.add(0.0, 3.0, 0.0).getBlock().setType(Material.POINTED_DRIPSTONE);
            player.getWorld().spawn(playerLocation, EvokerFangs.class, Sheep1 -> {
            });
        }
        if (chance == 15) {
            player.getWorld().spawn(player.getLocation(), Guardian.class, Sheep1 -> {
                Sheep1.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 225));
                Sheep1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 225));
                Sheep1.setVelocity(new Vector(3, 0, 0));
                player.getWorld().spawn(player.getLocation(), Guardian.class, Sheep2 -> {
                    Sheep2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 225));
                    Sheep2.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 225));
                    Sheep2.setVelocity(new Vector(-3, 0, 0));
                    player.getWorld().spawn(player.getLocation(), Guardian.class, Sheep3 -> {
                        Sheep3.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 225));
                        Sheep3.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 225));
                        Sheep3.setVelocity(new Vector(0, 0, -3));
                        player.getWorld().spawn(player.getLocation(), Guardian.class, Sheep4 -> {
                            Sheep4.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 225));
                            Sheep4.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 225));
                            Sheep4.setVelocity(new Vector(0, 0, 3));
                        });
                    });
                });
            });
        }
        if (chance == 25) {
            boss.teleport(player.getLocation().add(10, 0, 0));
            boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 225));
            boss.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 225));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 225));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 225));
            Location blockloc0 = player.getLocation().add(10, 0, 0);
            player.teleport(blockloc0);
        }
    }

    @EventHandler
    public void Phases(EntityDamageByEntityEvent e) {
        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting
        // Return if attacker is not a Wither Skeleton (Boss)
        if (!(attacker instanceof Player player)) return;
        if (!(hurt instanceof Wither boss)) return;
        if (!(Objects.equals(hurt.getCustomName(), "§b⚛ Soul ♡ Eater ⚛"))) return;
        if (player.getLocation().distance(boss.getLocation()) >= 20.0) {
            double damage = player.getMaxHealth() * 0.1;
            player.damage(damage);
            player.sendMessage(ChatColor.RED + "I AM WATCHING YOU");
        }
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:SE")) return;
        e.getDrops().clear();
        Random ran = new Random();
        int chance = ran.nextInt(10);
        if (chance == 9) {
            ItemStack item = new ItemStack(Material.WITHER_SKELETON_SKULL);
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setLore(toArrayList("§r§8Horrible and terrifyingly", "§r§7The Lord of Nether Kingdom.", "§r§7The Ruler and Controller of entire Nether", "§r§7Great Lord", "§r§7Soul Eater", "§r§6Fun fact! Soul Eater and any nether mob is SI-based life!"));
            meta.setDisplayName(ChatColor.GOLD + "Soul Eater's Skull");
            item.setItemMeta(meta);
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
        }
        if (chance == 5) {
            ItemStack item = new ItemStack(Material.NETHER_STAR);
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setLore(toArrayList("§r§8It's powerful", "§r§7Where Soul Eater store their Nether Energy.", "§r§7The battery of Soul Eater", "§r§7Soul Eater can change soul to Nether Energy", "§r§7Very strong nether energy batteries", "§r§6Fun fact! Soul Eater and any nether mob is SI-based life!"));
            meta.setDisplayName(ChatColor.GOLD + "Soul Eater's Core");
            item.setItemMeta(meta);
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
        }
    }
    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}