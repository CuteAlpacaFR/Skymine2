package com.github.cutealpacafr.skymine;

import com.github.cutealpacafr.skymine.boss.AncientSkeleton;
import com.github.cutealpacafr.skymine.boss.Skilra;
import com.github.cutealpacafr.skymine.boss.Titan;
import com.github.cutealpacafr.skymine.boss.ZoneWarrior;
import com.github.cutealpacafr.skymine.command.OnCommand;
import com.github.cutealpacafr.skymine.equipment.weapon.Sword;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class SkyMine extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("kit")).setExecutor(new OnCommand());
        super.getServer().getPluginManager().registerEvents(new OnCommand(), this);
        Objects.requireNonNull(getCommand("ZW")).setExecutor(new ZoneWarrior());
        super.getServer().getPluginManager().registerEvents(new ZoneWarrior(), this);
        Objects.requireNonNull(getCommand("Titan")).setExecutor(new Titan());
        super.getServer().getPluginManager().registerEvents(new Titan(), this);
        Objects.requireNonNull(getCommand("fr")).setExecutor(new Sword());
        super.getServer().getPluginManager().registerEvents(new Sword(), this);
        Objects.requireNonNull(getCommand("ac")).setExecutor(new AncientSkeleton(this));
        super.getServer().getPluginManager().registerEvents(new AncientSkeleton(this), this);
        Objects.requireNonNull(getCommand("Skilra")).setExecutor(new Skilra(this));
        super.getServer().getPluginManager().registerEvents(new Skilra(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}