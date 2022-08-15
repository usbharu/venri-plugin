package io.github.usbharu.venriplugin2;

import io.github.usbharu.venriplugin2.display.DisplayDamage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class VenriPlugin2 extends JavaPlugin {

  private static FileConfiguration CONFIGURATION;
  private static Config config;

  public static Config CONFIGURATION() {
    return config;
  }

  @Override
  public void onEnable() {
    getLogger().info("Enable Venri-Plugin2 !!");
    config = new Config(this);
    init();
  }

  @Override
  public void onDisable() {
    saveConfig();
    // Plugin shutdown logic
  }

  protected void init() {
    DisplayDamage listener = new DisplayDamage();

    getCommand("displayDamage").setExecutor(listener);
    getCommand("displayDamage").setTabCompleter(listener);
    getServer().getPluginManager().registerEvents(listener, this);
  }
}
