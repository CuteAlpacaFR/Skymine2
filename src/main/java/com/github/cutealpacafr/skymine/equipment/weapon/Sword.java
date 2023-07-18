package com.github.cutealpacafr.skymine.equipment.weapon;

import com.github.cutealpacafr.skymine.util.NotNull;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Sword implements CommandExecutor, Listener {
    public static ItemStack Sword;

    public Sword() {
        ItemStack item = new ItemStack((Material.IRON_SWORD));
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "The Sword.Charge'< DR.ALPACA's SOUL >'.HATE.SOUL.DIE.OUT.GET.OFF.FROM.MY.PLACE(HELL)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "For real");
        lore.add(ChatColor.GRAY + "Make everything returns Null");
        lore.add(ChatColor.GRAY + "To reaility unreachable");
        lore.add(ChatColor.GRAY + "Muda Muda");
        lore.add(ChatColor.GRAY + "No Usage.");
        meta.setLore(lore);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 200, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        Sword = item;
    }
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player player = (Player) commandSender;
        if (player.isOp() && s.equalsIgnoreCase("fr")) {
            player.getWorld().dropItemNaturally((player.getLocation()), Sword);
            player.sendMessage("fr");
            player.spawnParticle(Particle.REDSTONE, player.getLocation(), 50, 0, 1, 0);
            Location startLoc = player.getEyeLocation();

            Location particleLoc = startLoc.clone();

            World world = startLoc.getWorld();
            Vector dir = startLoc.getDirection();
            Vector vecOffset = dir.clone().multiply(0.5);

            new BukkitRunnable() {
                final int maxBeamLength = 30;
                int beamLength = 0;
                public void run() {
                    for (Entity entity : Objects.requireNonNull(world).getNearbyEntities(particleLoc, 5, 5, 5)) {
                        if (entity instanceof LivingEntity) {
                            if (entity == player) {
                                continue;
                            }
                            Vector particleMinVector = new Vector(
                                    particleLoc.getX() - 0.25,
                                    particleLoc.getY() - 0.25,
                                    particleLoc.getZ() - 0.25);
                            Vector particleMaxVector = new Vector(
                                    particleLoc.getX() + 0.25,
                                    particleLoc.getY() + 0.25,
                                    particleLoc.getZ() + 0.25);
                            if (entity.getBoundingBox().overlaps(particleMinVector, particleMaxVector)) {
                                world.spawnParticle(Particle.SMALL_FLAME, particleLoc, 0);
                                world.playSound(particleLoc, Sound.BLOCK_FIRE_AMBIENT, 2, 1);
                                entity.setVelocity(entity.getVelocity().add(particleLoc.getDirection().normalize().multiply(1.5)));
                                ((Damageable) entity).damage(5, player);
                                this.cancel();
                                return;
                            }
                        }
                    }
                    beamLength++;
                    if (beamLength >= maxBeamLength) {
                        world.spawnParticle(Particle.FLASH, particleLoc, 0);
                        this.cancel();
                        return;
                    }
                    particleLoc.add(vecOffset);
                    world.spawnParticle(Particle.FIREWORKS_SPARK, particleLoc, 0);
                }
            };
        }
    return true;}
        }