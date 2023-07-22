package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
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


public class TheEnd implements CommandExecutor, Listener {
    private int _hitCounter = 0;

    //private final Plugin _plugin = SkyMine.getInstance();
    @SuppressWarnings("NullableProblems")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), Enderman.class, Sheep1 -> {
                Sheep1.setCustomName("§fT§dh§0e §fE§dn§0D");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 1100);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 65);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(1100);
                Sheep1.addScoreboardTag("id:TheD");
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
        if (!(attacker instanceof Enderman boss)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§fT§dh§0e §fE§dn§0D"))) return;

        // Return if hurt entity is not a player
        if (!(hurt instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;
        boss.setVelocity(new Vector(1,1,1));
        for (int i = 0; i < 2; i++) {
            player.getWorld().spawn(player.getLocation(), ShulkerBullet.class, bullet -> {

            });
        }

        if (_hitCounter % 10 == 0) {
            float y = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float x = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float z = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            boss.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 255));
            player.setVelocity(new Vector(x,y,z));
        }
        if (_hitCounter % 20 == 0)
            player.sendMessage("Boss wont take damage for 3 second!");
        boss.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 255));
        if (_hitCounter % 30 == 0) {
                    //Ender Madness
                    boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 255));
                    player.spawnParticle(Particle.SOUL, boss.getLocation(), 50);
                    boss.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 225));
                    boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 5));
                }
            }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:TheD")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Infinite" , "§r§The center of eye is so black inside almost unable to see", "§r§7Theres very strong void energy coming out like a beast every second", "§r§7Everything trying to damage it is gonna be teleported", "§r§7Seems the eye is The End's core"));
        meta.setDisplayName(ChatColor.GOLD + "Eye of endless void");
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);

    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}