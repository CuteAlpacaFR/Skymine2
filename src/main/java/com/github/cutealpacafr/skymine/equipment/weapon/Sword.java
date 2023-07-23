package com.github.cutealpacafr.skymine.equipment.weapon;

import com.github.cutealpacafr.skymine.util.NotNull;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getWorld;

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
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 2147267562, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
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
            Location npcLocation = player.getLocation();
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) Objects.requireNonNull(getWorld("world"))).getHandle();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Alpaca Is Bald");
            String signature = "ewogICJ0aW1lc3RhbXAiIDogMTY5MDAwNjA0NzMxMCwKICAicHJvZmlsZUlkIiA6ICI5ZWVjMjE1N2MwY2I0ODQ2OThjMmZkMWE3Y2QyYjg4ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDbG93blBpZXJjZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80YWYwNmY3NjBlZGQzNzAzMTliZWZmNWIzNGYyMWE2Y2QzYzdjY2JmZDY2ZTdkOTZmNjhiMTlmOGE4NDg1MTgyIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
            String texture = "eZn5ks2NbbcC/TpC+1jEyx4HFC9lWtIrN1n7idHn3qHRouKu0hlCAmDZBAsbSzsrdGpivn/63MymHFfOmsedPyhsEU0DKUzgFaHfEUdrbpwHvs5I8Wh3ZhDOswdnfQe/hPn4WYRPj8+BZV3Xz4IS+3wXGIi261wcsQUocXIWFUCdkdBCT4WxPeLRVbMFaU+hjYMq7sMtKJdEHY9a2ozWoFpP6pO7M4Zx7PwHvH9/Np3rv2glLTxTJKfPYfdEqTTGLzcW/Q/SzmFpBtxU+zNzT5RMqbMdBIfVYHTDp/OHmA4ycUSmN3+emtF4K6iX5JwmaryWEh9kyN5E6pAt56006RRR60luyF44+G9YyGSpRH6Tq7qRDbrJ36P74c1E10PvKehPKgZH+Fug/Pvrbam+sPvT8QuaVegV5zHVWGpDQPxN/bQ2WUOO64fT0CD78itX+sdkTwd8mZjig7ZORZWp7lZIH50zcVur4hPMZE5iuocSYGecAQ4TbQdlAD5dv8qmVt7uNrWhA45tLCat8tg6a8AMqAni9wRmT60IyZ5tfvSyKRmddzS3e6XmEiRuUOl0M8w62xuTO74VRt3TNaAwuN5Hp/R0JCH0mpUd1bRYEyg6EAt9F3nKKpxp/BM/lrLqmthP4xjQfCdG8XNZ/2V7Y/A3jkj6vNsAVSf4we74iS4=";
            EntityPlayer npcPlayer = new EntityPlayer(server, world, gameProfile);
            gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
            npcPlayer.b(npcLocation.getX(), npcLocation.getY(), npcLocation.getZ(), npcLocation.getYaw(), npcLocation.getPitch());
            PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
            connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npcPlayer));
            connection.a(new PacketPlayOutNamedEntitySpawn(npcPlayer));
            connection.a(new PacketPlayOutEntityHeadRotation(npcPlayer, (byte) (npcLocation.getYaw() * 256 / 360)));
            connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npcPlayer));
            float y = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float x = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            float z = (float) -2 + (float) (Math.random() * (2 + 2) + (1));
            player.setVelocity(new Vector(x, y, z));
                }
        return true;
    }
}