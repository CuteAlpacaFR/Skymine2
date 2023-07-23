package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.SkyMine;
import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
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
import java.util.Random;

public class Skilra implements CommandExecutor, Listener {
    private final Plugin _plugin = SkyMine.getInstance();
    private int _hitCounter = 0;

    @SuppressWarnings("NullableProblems")
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
                meta.setColor(Color.AQUA);
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
                meta3.setColor(Color.AQUA);
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
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 50);
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
        if (!(attacker instanceof PigZombie boss)) return;

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

        // Skilra explosion
        if (_hitCounter % 10 == 0) {
        Objects.requireNonNull(player.getLocation().getWorld()).spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 50, 0, 0,0);
        attacker.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 2);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255));
        player.setVelocity(new Vector(0, 6, 0));
        player.damage(10);
                        // Delay messages by 1 second (20 ticks)
        Bukkit.getScheduler().runTaskLater(_plugin, () -> {
            player.sendMessage("§4}☠{ §c✪S k i l r a✪ §4}☠{: §cOnce I smash the ground the world trembles.");
            player.sendMessage("§7Ancient Skeleton smashes the ground!!");
        }, 20);
    }

        // Every 20 hits
        if (_hitCounter % 20 == 0)
            // Spawn more minions

            // ----- SKILRA JUMP ------
            boss.setVelocity(new Vector(0, 10, 0));
        boss.setFreezeTicks(60);
        Location blockloc0 = player.getLocation().add(0, 3, 0);
        Location blockloc = player.getLocation().add(0, 2, 0);
        Location blockloc2 = player.getLocation().add(0, 1, 0);
        Location blockloc3 = player.getLocation().add(0, 0, 0);
        Location wallloc0 = player.getLocation().add(1, 0, 0);
        Location wallloc = player.getLocation().add(0, 0, 1);
        Location wallloc2 = player.getLocation().add(-1, 0, 0);
        Location wallloc3 = player.getLocation().add(0, 0, -1);
        if (blockloc.getBlock().getType().equals(Material.AIR))
            blockloc.getBlock().setType(Material.NETHER_QUARTZ_ORE);
        if (blockloc0.getBlock().getType().equals(Material.AIR))
            blockloc0.getBlock().setType(Material.LODESTONE);
        if (blockloc2.getBlock().getType().equals(Material.AIR))
            blockloc2.getBlock().setType(Material.NETHERRACK);
        if (blockloc3.getBlock().getType().equals(Material.AIR))
            blockloc3.getBlock().setType(Material.NETHER_WART_BLOCK);
        if (wallloc2.getBlock().getType().equals(Material.AIR))
            wallloc2.getBlock().setType(Material.RED_NETHER_BRICK_WALL);
        if (wallloc3.getBlock().getType().equals(Material.AIR))
            wallloc3.getBlock().setType(Material.RED_NETHER_BRICK_WALL);
        if (wallloc.getBlock().getType().equals(Material.AIR))
            wallloc.getBlock().setType(Material.RED_NETHER_BRICK_WALL);
        if (wallloc0.getBlock().getType().equals(Material.AIR))
            wallloc0.getBlock().setType(Material.RED_NETHER_BRICK_WALL);
        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 10));
        Bukkit.getScheduler().runTaskLater(_plugin, () -> {
            if (blockloc.getBlock().getType().equals(Material.NETHER_QUARTZ_ORE))
                blockloc.getBlock().setType(Material.AIR);
            if (blockloc0.getBlock().getType().equals(Material.LODESTONE))
                blockloc0.getBlock().setType(Material.AIR);
            if (blockloc2.getBlock().getType().equals(Material.NETHERRACK))
                blockloc2.getBlock().setType(Material.AIR);
            if (blockloc3.getBlock().getType().equals(Material.NETHER_WART_BLOCK))
                blockloc3.getBlock().setType(Material.AIR);
            if (wallloc2.getBlock().getType().equals(Material.RED_NETHER_BRICK_WALL))
                wallloc2.getBlock().setType(Material.AIR);
            if (wallloc3.getBlock().getType().equals(Material.RED_NETHER_BRICK_WALL))
                wallloc3.getBlock().setType(Material.AIR);
            if (wallloc.getBlock().getType().equals(Material.RED_NETHER_BRICK_WALL))
                wallloc.getBlock().setType(Material.AIR);
            if (wallloc0.getBlock().getType().equals(Material.RED_NETHER_BRICK_WALL))
                wallloc0.getBlock().setType(Material.AIR);
            boss.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 10));
            player.sendMessage("§4}☠{ §c✪S k i l r a✪ §4}☠{: §cThe Pillars! Awaken from sleep.");
            player.sendMessage("§7Skilra summoned pillars!!");

        }, 60);

        // Every 30 hits
        if (_hitCounter % 30 == 0) {
            // Want timer delay?
            World world = player.getWorld();
            boss.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 254));
            world.spawn(boss.getLocation(), WitherSkeleton.class, sheep -> {
                sheep.setCustomName("§dSkilra's Core");
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 4);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.4);
                Objects.requireNonNull(sheep.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                sheep.setCustomNameVisible(true);
                sheep.setHealth(100);
                sheep.setAI(false);
                sheep.setInvisible(true);
                ItemStack helmet2 = new ItemStack(Material.REDSTONE_BLOCK);
                ItemStack air = new ItemStack(Material.AIR);
                ItemMeta meta4 = helmet2.getItemMeta();
                assert meta4 != null;
                meta4.setDisplayName(ChatColor.BLUE + "Titan's Helmet");
                Objects.requireNonNull(sheep.getEquipment()).setHelmet(helmet2);
                sheep.getEquipment().setItemInMainHand(air);
                // Become unable to move for a few seconds (jump included)
                player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 29);
                Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.5);
                // When boss health is lower than 200, increase strength
                if (sheep.isDead()) {
                    boss.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 255));
                    Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.25);
                    boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));

            }
        });
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
        if (!(hurt instanceof PigZombie boss)) return;

        // Return if hurt entity name is not the boss name
        if (!(Objects.equals(hurt.getCustomName(), "§4}☠{ §c✪S k i l r a✪ §4}☠{"))) return;

        // Return if attacker is not a Player
        if (!(attacker instanceof Player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        double bossHealth = boss.getHealth();

        // When boss health is lower than 200, increase strength
        if (bossHealth <= 200) {
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(50);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
            assert meta != null;
            meta.setColor(Color.GRAY);
            meta.setUnbreakable(true);
            meta.setDisplayName(ChatColor.BLUE + "Titan's Chest-plate");
            ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta2 = (LeatherArmorMeta) legging.getItemMeta();
            assert meta2 != null;
            meta2.setColor(Color.GRAY);
            meta2.setUnbreakable(true);
            meta2.setDisplayName(ChatColor.BLUE + "Titan's Legging");
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta meta3 = (LeatherArmorMeta) boots.getItemMeta();
            assert meta3 != null;
            meta3.setColor(Color.GRAY);
            meta3.setUnbreakable(true);
            meta3.setDisplayName(ChatColor.BLUE + "Titan's Boots");
            chestplate.setItemMeta(meta);
            legging.setItemMeta(meta2);
            boots.setItemMeta(meta3);
            ItemStack helmet = new ItemStack(Material.AIR);
            Objects.requireNonNull(boss.getEquipment()).setChestplate(chestplate);
            boss.getEquipment().setLeggings(legging);
            boss.getEquipment().setBoots(boots);
            boss.getEquipment().setHelmet(helmet);
            boss.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_HOE));
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
        }
        // like this? yes!
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:Skilra")) return;
        e.getDrops().clear();
        Random ran = new Random();
        int chance = ran.nextInt(10);
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Very Hard", "§r§7Any bloody part of skilra is very rare and hard", "§r§7And the meat is the hardest", "§r§7Because Skilra's skin and meat is filled with netherite and nether energy and", "§r§7Because of Skilra is actually a SI-Based life so their skin is very very hard! and that also make skilra slower", "§r§6Fun fact! Skilra and all the nether mob is SI-based life!"));
        meta.setDisplayName(ChatColor.GOLD + "Skilra's Meat");
        item.setItemMeta(meta);
        ItemStack item2 = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta2 = item.getItemMeta();
        assert meta2 != null;
        meta2.setLore(toArrayList("§r§8Lightly", "§r§7It's soft and wet", "§r§7The powerful energy", "§r§7from the deepest hell", "§r§7Because of Skilra is actually a SI-Based life so their skin is very very hard! and that also make skilra slower", "§r§7Fun fact! Skilra and any nether mob is SI-based life!"));
        meta2.setDisplayName(ChatColor.GOLD + "Skilra's Core");
        item2.setItemMeta(meta2);
        ItemStack NOTE = new ItemStack(Material.PAPER);
        ItemMeta meta3 = item.getItemMeta();
        assert meta3 != null;
        meta3.setLore(toArrayList("§r§8Doctor.Alpaca -", "§r§7Hello adventurers", "§r§7You must know that every single mob in HELL is SI-based", "§r§7This special note is for YOU yes, YOU.", "§r§7... before he come i have to tell you", "§r§7THIS LOOT ONLY HAS 1% TO DROP IS BECAUSE HE WILL FIND US HE WILL FIND US", "§r§7THIS WORLD IS FAKE THE REAL WORLD IS NOT HERE", "§r§7but... the real world is destroyed its a ruin.", "§r§7Heheheheh HAHAHHAHAHAHAHHAHAHAHAHHAHAHAHA... hes lying..", "§r§7NO TRUST ME-", "§r§7hehehe... adventurer you better §l§4RUN :)"));
        meta3.setDisplayName(ChatColor.RED + "Dr.Alpaca's Note");
        NOTE.setItemMeta(meta3);
        if (chance < 9)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
        if (chance < 2)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item2);
        if (chance < 1)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), NOTE);
    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (event instanceof TNTPrimed)
            event.setCancelled(true);
    }
}