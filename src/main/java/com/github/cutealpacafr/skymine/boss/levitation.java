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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class levitation implements CommandExecutor, Listener {
    private int _hitCounter = 0;

    private final Plugin _plugin = SkyMine.getInstance();
    @SuppressWarnings("NullableProblems")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), Shulker.class, Sheep1 -> {
                Sheep1.setCustomName("§d☁ §flevitation §d☁");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 800);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 45);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.25);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(800);
                Sheep1.addScoreboardTag("id:l");
                Objects.requireNonNull(Sheep1.getEquipment()).setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.6.SoulEater");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.6.SoulEater is uncontrolled, Please be careful while Executing or Spawning this bosses");
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
        if (!(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)) return;
        if (!(attacker instanceof Player player)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§d☁ §flevitation §d☁"))) return;

        // Return if hurt entity is not a player
        if (!(hurt instanceof Shulker boss)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;
        boss.setVelocity(new Vector(1,1,1));
        for (int i = 0; i < 2; i++) {
            player.getWorld().spawn(player.getLocation(), ShulkerBullet.class, bullet -> {

            });
        }

        if (_hitCounter % 10 == 0)
            for (int a1 = 0; a1 < 5; a1++)
                player.setVelocity(new Vector(0, -10, 0));
        Bukkit.getScheduler().runTaskLater(_plugin, () -> {
            player.setVelocity(new Vector(0, 20, 0));
            Bukkit.getScheduler().runTaskLater(_plugin, () -> player.setVelocity(new Vector(10, 0, 0)), 20);
        }, 60);
        if (_hitCounter % 20 == 0)
            boss.setVelocity(new Vector(0, 20, 0));
        float y = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
        float x = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
        float z = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
        for (int a1 = 0; a1 < 10; a1++)
            player.getWorld().spawn(player.getLocation(), Shulker.class, Sheep2 -> {
                Sheep2.setVelocity(new Vector(x,y,z));
            if (_hitCounter % 30 == 0) {
                //player's foot location
                Location blockloc = player.getLocation().add(0, -1, 0);
                //another location
                Location blockloc2 = player.getLocation().add(1, -1, 0);
                Location blockloc3 = player.getLocation().add(-1, -1, 0);
                Location blockloc4 = player.getLocation().add(0, -1, 1);
                Location blockloc5 = player.getLocation().add(0, -1, -1);
                Location blockloc6 = player.getLocation().add(-1, -1, -1);
                Location blockloc7 = player.getLocation().add(-1, -1, 1);
                Location blockloc8 = player.getLocation().add(1, -1, -1);
                Location blockloc9 = player.getLocation().add(1, -1, 1);
                if (blockloc.getBlock().getType().equals(Material.END_STONE))
                    blockloc.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc2.getBlock().getType().equals(Material.END_STONE))
                    blockloc2.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc3.getBlock().getType().equals(Material.END_STONE))
                    blockloc3.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc4.getBlock().getType().equals(Material.END_STONE))
                    blockloc4.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc5.getBlock().getType().equals(Material.END_STONE))
                    blockloc5.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc6.getBlock().getType().equals(Material.END_STONE))
                    blockloc6.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc7.getBlock().getType().equals(Material.END_STONE))
                    blockloc7.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc8.getBlock().getType().equals(Material.END_STONE))
                    blockloc8.getBlock().setType(Material.RED_CONCRETE);
                if (blockloc9.getBlock().getType().equals(Material.END_STONE))
                    blockloc9.getBlock().setType(Material.RED_CONCRETE);
                Bukkit.getScheduler().runTaskLater(_plugin, () -> {
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    if (blockloc.getBlock().getType().equals(Material.RED_CONCRETE))
                        blockloc.getBlock().setType(Material.AIR);
                    Bukkit.getScheduler().runTaskLater(_plugin, () -> {
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                        if (blockloc.getBlock().getType().equals(Material.AIR))
                            blockloc.getBlock().setType(Material.END_STONE);
                },20);
            }, 20);
        }
    });
    }
    @EventHandler
    public void onDamageBoss(EntityDamageByEntityEvent e) {
        // ---------- PHASES ----------

        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if hurt entity is not a boss
        if (!(hurt instanceof Shulker boss)) return;

        // Return if hurt entity name is not the boss name
        if (!(Objects.equals(hurt.getCustomName(), "§d☁ §flevitation §d☁"))) return;

        // Return if attacker is not a Player
        if (!(attacker instanceof Player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        double bossHealth = boss.getHealth();

        // When boss health is lower than 200, increase strength
        if (bossHealth <= 200) {
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(50);
        }
        // like this? yes!
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:l")) return;
        e.getDrops().clear();
        Random ran = new Random();
        int chance = ran.nextInt(10);
        ItemStack item = new ItemStack(Material.CONDUIT);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Wow it's floating", "§r§7This levitation heart has almost no gravity", "§r§7and theres six interface to charge item to become non-gravity state", "§r§8Fun fact! Levitation and all the end mob is VE-based (void energy) life!"));
        meta.setDisplayName(ChatColor.GOLD + "Levitation's Heart");
        item.setItemMeta(meta);
        ItemStack NOTE = new ItemStack(Material.PAPER);
        ItemMeta meta3 = item.getItemMeta();
        assert meta3 != null;
        meta3.setLore(toArrayList("§r§8Experimental report:", "§r§7we have founded 2 new element", "§r§7and they have almost no relationship or related to all other element", "§r§7These 2 element doesn't and never exist on the white room or ADW (After Death World)", "§r§7After we open the portal to red room theres a lot of nether energy spreading out of the portal", "§r§7Same to the end but the only different is the portal spreading out void energy instead of nether energy ", "§r§7nether energy is weaken version of void energy and was made by void energy", "§r§7the things is starting to get worse and worse...", "§r§the experimental report from TEST.7.Levitation is out it shows a lot of void energy is in their heart to make non-gravity and more weirder thing", "§r§7and levitation's shell is complete useless and its just for defensing"));
        meta3.setDisplayName(ChatColor.RED + "Dr.Alpaca's Second Note");
        NOTE.setItemMeta(meta3);
        if(chance < 9)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
        if(chance <1)
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), NOTE);
    }
    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}