package io.github.usbharu.venriplugin2;

import io.github.usbharu.venriplugin2.display.DisplayDamage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class VenriPlugin2 extends JavaPlugin {

  private static FileConfiguration CONFIGURATION;

  public static FileConfiguration CONFIGURATION() {
    return CONFIGURATION;
  }

  @Override
  public void onEnable() {
    getLogger().info("Enable Venri-Plugin2 !!");
    saveDefaultConfig();
    CONFIGURATION = getConfig();
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
