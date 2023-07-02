package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Skilra implements CommandExecutor, Listener {
    private final Plugin _plugin;
    private int _hitCounter = 0;
    private BlockExplodeEvent event;

    public Skilra(Plugin plugin) {
        super();
        this._plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), PigZombie.class, Sheep1 -> {
                Sheep1.setCustomName("§4}☠{ §c✪S k i l r a✪ §4}☠{");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 600);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 35);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.25);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                ItemStack helmet = new ItemStack(Material.LODESTONE);
                ItemMeta meta4 = helmet.getItemMeta();
                assert meta4 != null;
                meta4.setDisplayName(ChatColor.BLUE + "Titan's Helmet");
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
                assert meta != null;
                meta.setColor(Color.RED);
                meta.setUnbreakable(true);
                meta.setDisplayName(ChatColor.BLUE + "Titan's Chestplate");
                ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
                assert meta2 != null;
                meta2.setColor(Color.AQUA);
                meta2.setUnbreakable(true);
                meta2.setDisplayName(ChatColor.BLUE + "Titan's Legging");
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
                assert meta3 != null;
                meta3.setColor(Color.BLACK);
                meta3.setUnbreakable(true);
                meta3.setDisplayName(ChatColor.DARK_AQUA + "Titan's Boots");
                chestplate.setItemMeta(meta);
                legging.setItemMeta(meta2);
                boots.setItemMeta(meta3);
                Objects.requireNonNull(Sheep1.getEquipment()).setChestplate(chestplate);
                Sheep1.getEquipment().setLeggings(legging);
                Sheep1.getEquipment().setBoots(boots);
                Sheep1.getEquipment().setHelmet(helmet);
                Sheep1.setHealth(600);
                Sheep1.addScoreboardTag("id:Skilra");
                Sheep1.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.4.Skilra");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.3.Skilra is uncontrolled, Please be careful while Executing or Spawning this bosses");
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 1);
                Sheep1.setTarget(player);
                int health = (int) Sheep1.getHealth();
                if (health <= 200) ;
                player.sendMessage("aaa");
            });
        }
        return true;
    }


    @EventHandler
    public void onDamagePlayer(EntityDamageByEntityEvent e) {
        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();
        Vector v = new Vector();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if attacker is not a Wither Skeleton (Boss)
        if (!(attacker instanceof WitherSkeleton boss)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§4}☠{ §c✪S k i l r a✪ §4}☠{"))) return;

        // Return if hurt entity is not a player
        if (!(hurt instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;

        // Every hit, set player on fire and give them blindness for 100 ticks
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));

        World world = player.getWorld();
        Location playerLoc = player.getLocation();
        Location bossLoc = boss.getLocation();

        // Every 10 hits, smite player with lightning
        if (_hitCounter % 10 == 0) {
            for (Block b : event.blockList()) {
                if (b.getType().equals(Material.AIR)) ;
                BlockState saved = b.getState();
                v.setY(2);
                b.setType(Material.AIR);
                attacker.getWorld().spawn(player.getLocation(), TNTPrimed.class, Sheep1 -> {
                    FallingBlock fallingBlock = Bukkit.getWorld("world").spawnFallingBlock(saved.getLocation(), saved.getType(), saved.getData().getData());
                    fallingBlock.setVelocity(v);
                    Sheep1.setFuseTicks(0);
                    attacker.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 2);
                    attacker.setFreezeTicks(60);
                });
            }
        }

        // Delay messages by 1 second (20 ticks)
        Bukkit.getScheduler().runTaskLater(_plugin, () -> {
            player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cENOUGH!.");
            player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cStrike " + player.getName() + "!");
            player.sendMessage("§7Ancient Skeleton has striked you!");
        }, 20);

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
        if (bossHealth <= 200) {
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
            player.sendMessage("§4☠ §c✦Ancient Skeleton✦ §4☠: §cYou earn my respect, Lets have a fair fight.");
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
        }
        // like this? yes!
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:Skilra")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
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