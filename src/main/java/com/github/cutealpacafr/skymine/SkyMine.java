package com.github.cutealpacafr.skymine;

import com.github.cutealpacafr.skymine.boss.*;
import com.github.cutealpacafr.skymine.command.Kit;
import com.github.cutealpacafr.skymine.equipment.weapon.Sword;
import com.github.cutealpacafr.skymine.util.RealTNT;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class SkyMine extends JavaPlugin {
    private static SkyMine _instance;

    public static SkyMine getInstance() {
        return _instance;
    }

    @Override
    public void onEnable() {
        _instance = this;

        // Plugin startup logic
        Objects.requireNonNull(getCommand("kit")).setExecutor(new Kit());
        getServer().getPluginManager().registerEvents(new Kit(), this);

        Objects.requireNonNull(getCommand("ZW")).setExecutor(new ZoneWarrior());
        getServer().getPluginManager().registerEvents(new ZoneWarrior(), this);

        Objects.requireNonNull(getCommand("Titan")).setExecutor(new Titan());
        getServer().getPluginManager().registerEvents(new Titan(), this);

        Objects.requireNonNull(getCommand("fr")).setExecutor(new Sword());
        getServer().getPluginManager().registerEvents(new Sword(), this);

        Objects.requireNonNull(getCommand("ac")).setExecutor(new AncientSkeleton());
        getServer().getPluginManager().registerEvents(new AncientSkeleton(), this);

        Objects.requireNonNull(getCommand("Skilra")).setExecutor(new Skilra());
        getServer().getPluginManager().registerEvents(new Skilra(), this);

        Objects.requireNonNull(getCommand("TD")).setExecutor(new TheDupe());
        getServer().getPluginManager().registerEvents(new TheDupe(), this);
        Objects.requireNonNull(getCommand("SE")).setExecutor(new SoulEater());
        getServer().getPluginManager().registerEvents(new SoulEater(), this);
        getServer().getPluginManager().registerEvents(new RealTNT(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}