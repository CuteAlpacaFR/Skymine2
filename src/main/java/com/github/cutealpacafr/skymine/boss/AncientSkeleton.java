package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AncientSkeleton implements CommandExecutor, Listener {
    private final Plugin _plugin;
    private int _hitCounter = 0;

    public AncientSkeleton(Plugin plugin) {
        super();
        this._plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), WitherSkeleton.class, Sheep1 -> {
                Sheep1.setCustomName("§4☠ §c✦Ancient Skeleton✦ §4☠");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 400);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 20);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.4);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                ItemStack helmet = new ItemStack(Material.WITHER_SKELETON_SKULL);
                ItemMeta meta4 = helmet.getItemMeta();
                assert meta4 != null;
                meta4.setDisplayName(ChatColor.BLUE + "Titan's Helmet");
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
                assert meta != null;
                meta.setColor(Color.BLACK);
                meta.setUnbreakable(true);
                meta.setDisplayName(ChatColor.BLUE + "Titan's Chest-plate");
                ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
                assert meta2 != null;
                meta2.setColor(Color.WHITE);
                meta2.setUnbreakable(true);
                meta2.setDisplayName(ChatColor.BLUE + "Titan's Legging");
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
                assert meta3 != null;
                meta3.setColor(Color.BLACK);
                meta3.setUnbreakable(true);
                meta3.setDisplayName(ChatColor.BLUE + "Titan's Boots");
                chestplate.setItemMeta(meta);
                legging.setItemMeta(meta2);
                boots.setItemMeta(meta3);
                Objects.requireNonNull(Sheep1.getEquipment()).setChestplate(chestplate);
                Sheep1.getEquipment().setLeggings(legging);
                Sheep1.getEquipment().setBoots(boots);
                Sheep1.getEquipment().setHelmet(helmet);
                Sheep1.setHealth(400);
                Sheep1.addScoreboardTag("id:AS");
                Sheep1.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.3.AncientSkeleton");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.3.AncientSkeleton is uncontrolled, Please be careful while Executing or Spawning this bosses");
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 1);
            });
        }
    return true;}


    @EventHandler
    public void onDamagePlayer(EntityDamageByEntityEvent e) {
        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if attacker is not a Wither Skeleton (Boss)
        if (!(attacker instanceof WitherSkeleton boss)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§4☠ §c✦Ancient Skeleton✦ §4☠"))) return;

        // Return if hurt entity is not a player
        if (!(hurt instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;

        // Every hit, set player on fire and give them blindness for 100 ticks
        player.setFireTicks(100);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));

        World world = player.getWorld();
        Location playerLoc = player.getLocation();
        Location bossLoc = boss.getLocation();

        // Every 10 hits, smite player with lightning
        if (_hitCounter % 10 == 0) {
            for (int i = 0; i < 5; i++) {
                world.strikeLightning(playerLoc);
            }

            // Delay messages by 1 second (20 ticks)
            Bukkit.getScheduler().runTaskLater(_plugin, () -> {
                player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cENOUGH!.");
                player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cStrike " + player.getName() + "!");
                player.sendMessage("§7Ancient Skeleton has striked you!");
            }, 20);
        }

        // Every 20 hits
        if (_hitCounter % 20 == 0) {
            // Spawn more minions

            // ----- SHEEP MINION CAPTAIN -----
            world.spawn(bossLoc, WitherSkeleton.class, sheep -> {
                sheep.setCustomName("§4⚔ ▸Ancient Skeleton's Minion Captain◂ ⚔");
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(30);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 4);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.4);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                sheep.setCustomNameVisible(true);
                sheep.setHealth(30);

                // Delay messages by 1 second (20 ticks)
                Bukkit.getScheduler().runTaskLater(_plugin, () -> {
                    player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cDeath Isn't painful, enjoy the suffer of your last breathe.");
                    player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cGo, my minions.");
                    player.sendMessage("§7Ancient Skeleton has spawned minion!");
                }, 20);
            });

            // ----- 2 SHEEP MINIONS -----
            for (int i = 0; i < 2; i++) {
                world.spawn(bossLoc, WitherSkeleton.class, sheep -> {
                    sheep.setCustomName("§4⚔ ▸Ancient Skeleton's Minion◂ ⚔");
                    sheep.setCustomNameVisible(true);
                });
            }
        }

        // Every 30 hits
        if (_hitCounter % 30 == 0) {
            // Want timer delay?

            // Become unable to move for a few seconds (jump included)
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 255));

            player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 10, 29);
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 29);

            // Spawn 3 arrows above player
            for (int i = 0; i < 3; i++) {
                world.spawnEntity(playerLoc.add(0, 3, 0), EntityType.ARROW);
            }

            // Spawn primed TNT above player
            player.getWorld().createExplosion(1,1,2,2);
        }
    }

    @EventHandler
    public void onDamageBoss(EntityDamageByEntityEvent e) {
        // ---------- PHASES ----------

        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if hurt entity is not a boss
        if (!(hurt instanceof WitherSkeleton boss)) return;

        // Return if hurt entity name is not the boss name
        if (!(Objects.equals(hurt.getCustomName(), "§4☠ §c✦Ancient Skeleton✦ §4☠"))) return;

        // Return if attacker is not a Player
        if (!(attacker instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        double bossHealth = boss.getHealth();

        // When boss health is lower than 200, increase strength
        if (bossHealth <= 100) {
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(30);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
            assert meta != null;
            meta.setColor(Color.RED);
            meta.setUnbreakable(true);
            meta.setDisplayName(ChatColor.BLUE + "Titan's Chest-plate");
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
            assert meta2 != null;
            meta2.setColor(Color.BLACK);
            meta2.setUnbreakable(true);
            meta2.setDisplayName(ChatColor.BLUE + "Titan's Legging");
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
            assert meta3 != null;
            meta3.setColor(Color.BLACK);
            meta3.setUnbreakable(true);
            meta3.setDisplayName(ChatColor.BLUE + "Titan's Boots");
            chestplate.setItemMeta(meta);
            legging.setItemMeta(meta2);
            boots.setItemMeta(meta3);
            Objects.requireNonNull(boss.getEquipment()).setChestplate(chestplate);
            boss.getEquipment().setLeggings(legging);
            boss.getEquipment().setBoots(boots);
            boss.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_HOE));
            attacker.sendMessage("§r§7Oh no!, the boss seems enraged!");
            player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cURGGGGHHHHH ENOUGH! DIE LITTLE BART.");
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 1);
        }
        // like this? yes!
    }

    @EventHandler
    private void onBossDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:AS")) return;
        e.getDrops().clear();
        Entity attacker = e.getEntity().getKiller();
        attacker.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cFinally I've been released from this time prison, I am free... Why the way forward is so brightness? So.. many questions....");
        ItemStack item = new ItemStack(Material.CHARCOAL);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Ancient..", "§r§7Very old brain its already died not even seems working", "§r§7But it magically work somehow", "§r§7And its very smart you can even ask him question"));
        meta.setDisplayName(ChatColor.GOLD + "Ancient Skeleton's brain");
        item.setItemMeta(meta);
        attacker.getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
        }


    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}