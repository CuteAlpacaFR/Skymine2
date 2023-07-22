package com.github.cutealpacafr.skymine.boss;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class VoidDragon implements CommandExecutor, Listener {
    private int _hitCounter = 0;

    //private final Plugin _plugin = SkyMine.getInstance();
    @SuppressWarnings("NullableProblems")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getWorld().spawn(player.getLocation(), EnderDragon.class, Sheep1 -> {
                Sheep1.setCustomName("§fT§dh§0e §fE§dn§0D");
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 1500);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 70);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.25);
                Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                Sheep1.setHealth(1500);
                Sheep1.addScoreboardTag("id:VD");
                Objects.requireNonNull(Sheep1.getEquipment()).setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
                Sheep1.setCustomNameVisible(true);
                player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.6.SoulEater");
                player.sendMessage("§4§l▲!WARNING!▲: §cTEST.6.SoulEater is uncontrolled, Please be careful while Executing or Spawning this bosses");
                Sheep1.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Sheep1.getLocation(), 50);
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
        if (!(hurt instanceof EnderDragon boss)) return;

        // Return if attacker name is not the boss name
        if (!(Objects.equals(attacker.getCustomName(), "§b⚛ Soul ☄ Eater ⚛"))) return;

        // Return if hurt entity is not a player
        if (!(attacker instanceof Player player)) return;

        // ---------- MAIN LOGIC ----------
        // Only run main logic after passing guard clauses above

        // Increment hit counter
        _hitCounter++;
        boss.setLastDamage(boss.getLastDamage() + 1);

        if (_hitCounter % 10 == 0) {
            float y = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float x = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float z = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 255));
            player.setVelocity(new Vector(x, y, z));
        }
        if (_hitCounter % 20 == 0)
            //Ender Guardians
            player.getWorld().spawn(player.getLocation(), Enderman.class, egg -> {
                egg.setCustomName("§4⚔ ▸Ender Guardian◂ ⚔");
                Objects.requireNonNull(egg.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
                Objects.requireNonNull(egg.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 10);
                Objects.requireNonNull(egg.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.4);
                Objects.requireNonNull(egg.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                egg.setCustomNameVisible(true);
                egg.setHealth(100);
            });
        if (_hitCounter % 30 == 0) {
            boss.getWorld().spawnParticle(Particle.SMALL_FLAME, boss.getLocation(), 100);
            player.getWorld().spawnParticle(Particle.SMALL_FLAME, boss.getLocation(), 100);
            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, boss.getLocation(), 50);
            player.setFireTicks(100);
            boss.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2000, 255));
                if (_hitCounter % 40 == 0) {
                    Random ran = new Random();
                    int chance = ran.nextInt(4);
                    if (chance == 1) {
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
                    }
                    if (chance == 2)
                        player.getWorld().spawn(player.getLocation(), WitherSkeleton.class, boss1 -> {
                                    boss1.setCustomName("§4☠ §c✦Ancient Skeleton✦ §4☠");
                                    Objects.requireNonNull(boss1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 400);
                                    Objects.requireNonNull(boss1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 20);
                                    Objects.requireNonNull(boss1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.4);
                                    Objects.requireNonNull(boss1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);

                                    ItemStack helmet2 = new ItemStack(Material.WITHER_SKELETON_SKULL);
                                    ItemMeta meta41 = helmet2.getItemMeta();
                                    assert meta41 != null;
                                    meta41.setDisplayName(ChatColor.BLUE + "Titan's Helmet");

                                    ItemStack chestplate2 = new ItemStack(Material.LEATHER_CHESTPLATE);
                                    LeatherArmorMeta meta1 = (LeatherArmorMeta) chestplate2.getItemMeta();
                                    assert meta1 != null;
                                    meta1.setColor(Color.BLACK);
                                    meta1.setUnbreakable(true);
                                    meta1.setDisplayName(ChatColor.BLUE + "Titan's Chest-plate");

                                    ItemStack legging2 = new ItemStack(Material.LEATHER_LEGGINGS);
                                    LeatherArmorMeta meta21 = (LeatherArmorMeta) legging2.getItemMeta();
                                    assert meta21 != null;
                                    meta21.setColor(Color.WHITE);
                                    meta21.setUnbreakable(true);
                                    meta21.setDisplayName(ChatColor.BLUE + "Titan's Legging");

                                    ItemStack boots2 = new ItemStack(Material.LEATHER_BOOTS);
                                    LeatherArmorMeta meta31 = (LeatherArmorMeta) boots2.getItemMeta();
                                    assert meta31 != null;
                                    meta31.setColor(Color.BLACK);
                                    meta31.setUnbreakable(true);
                                    meta31.setDisplayName(ChatColor.BLUE + "Titan's Boots");

                                    chestplate2.setItemMeta(meta1);
                                    legging2.setItemMeta(meta21);
                                    boots2.setItemMeta(meta31);
                                    Objects.requireNonNull(boss1.getEquipment()).setChestplate(chestplate2);
                                    boss1.getEquipment().setLeggings(legging2);
                                    boss1.getEquipment().setBoots(boots2);
                                    boss1.getEquipment().setHelmet(helmet2);
                                    boss1.setHealth(200);
                                    boss1.addScoreboardTag("id:AS");
                                    boss1.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
                                    boss1.setCustomNameVisible(true);
                                    player.sendMessage("§4§l▲!WARNING!▲: §cThis bosses is still in test");
                                    player.sendMessage("§4§l▲!WARNING!▲: §cThe boss is now still in test folder - TEST.3.AncientSkeleton");
                                    player.sendMessage("§4§l▲!WARNING!▲: §cTEST.3.AncientSkeleton is uncontrolled, Please be careful while Executing or Spawning this bosses");
                                    player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, boss1.getLocation(), 50);
                                }
                        );
                    if (chance == 3) {
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
                            Sheep1.setHealth(200);
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
                    if (chance == 0) {
                        player.getWorld().spawn(player.getLocation(), Enderman.class, Sheep1 -> {
                            Sheep1.setCustomName("§fT§dh§0e §fE§dn§0D");
                            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(/*Value Here*/ 1100);
                            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(/*Value Here*/ 65);
                            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
                            Objects.requireNonNull(Sheep1.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(/*Value Here*/ 2101221122);
                            Sheep1.setHealth(330);
                        });
                    }
                }
            }
        }



    @EventHandler
    public void onDamageBoss(EntityDamageByEntityEvent e) {
        // ---------- PHASES ----------

        Entity hurt = e.getEntity();
        Entity attacker = e.getDamager();

        // ---------- GUARD CLAUSES ----------
        // Logic that returns early, so you don't have deep nesting

        // Return if hurt entity is not a boss.
        if (!(hurt instanceof PigZombie boss)) return;

        // Return if hurt entity name is not the boss name
        if (!(Objects.equals(hurt.getCustomName(), "§4}☠{ §c✪S k i l r a✪ §4}☠{"))) return;

        // Return if attacker is not a Player
        if (!(attacker instanceof Player player)) return;

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
            attacker.sendMessage("§r§7Oh no!, the boss seems enraged!");
            player.sendMessage("§4}☠{ §c✪S k i l r a✪ §4}☠{: §cYou earn my respect, Lets have a fair fight.");
            Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(/*Value Here*/ 0.3);
        }
        // like this? yes!
    }

    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getScoreboardTags().contains("id:TD")) return;
        e.getDrops().clear();
        ItemStack item = new ItemStack(Material.CONDUIT);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(toArrayList("§r§8Infinite", "§r§The center of eye is so black inside almost unable to see", "§r§7Theres very strong void energy coming out like a beast every second", "§r§7Everything trying to damage it is gonna be teleported", "§r§7Seems the eye is The End's core"));
        meta.setDisplayName(ChatColor.GOLD + "Eye of endless void");
        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);

    }

    @SafeVarargs
    private <T> ArrayList<T> toArrayList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }
}