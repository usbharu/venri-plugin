package io.github.usbharu.venriplugin2;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config {

  private final Plugin plugin;

  private final Configuration configuration;

  public Config(Plugin plugin) {
    this.plugin = plugin;
    this.configuration = load();
  }

  public void save() {
    plugin.saveConfig();
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void set(String path, Object value) {
    configuration.set(path, value);
    save();
  }

  public @NotNull FileConfiguration load() {
    plugin.saveDefaultConfig();
    return plugin.getConfig();
  }

  public void serverSet(String path, String name, Object value) {
    set("Server.Functions." + path + "." + name, value);
  }

  public void playerSet(String path, String name, Player player, Object value) {
    set("Player.Functions." + path + "." + player.getUniqueId() + "." + name, value);
  }
}
