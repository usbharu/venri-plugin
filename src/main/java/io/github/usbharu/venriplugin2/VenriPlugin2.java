package io.github.usbharu.venriplugin2;

import io.github.usbharu.venriplugin2.display.DisplayDamage;
import io.github.usbharu.venriplugin2.display.DisplayPlayerStatus;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class VenriPlugin2 extends JavaPlugin {

  private static FileConfiguration CONFIGURATION;
  private static Config config;
  private @NotNull Server server;

  public static Config CONFIGURATION() {
    return config;
  }

  @Override
  public void onEnable() {
    getLogger().info("Enable Venri-Plugin2 !!");
    server = getServer();
    config = new Config(this);
    init();
  }

  @Override
  public void onDisable() {
    saveConfig();
    // Plugin shutdown logic
  }

  protected void init() {

    DisplayDamage displayDamage = new DisplayDamage();

    getCommand("displayDamage").setExecutor(displayDamage);
    getCommand("displayDamage").setTabCompleter(displayDamage);
    server.getPluginManager().registerEvents(displayDamage, this);

    DisplayPlayerStatus displayPlayerStatus = new DisplayPlayerStatus();

    server.getPluginManager().registerEvents(displayPlayerStatus, this);
    getCommand("displayPlayerHealth").setExecutor(displayPlayerStatus);
    getCommand("displayPlayerLevel").setExecutor(displayPlayerStatus);
  }
}
