package io.github.usbharu.venriplugin;

import io.github.usbharu.venriplugin.navigate.NavigateCommand;
import io.github.usbharu.venriplugin.pin.PinCommand;
import io.github.usbharu.venriplugin.postplayerlocation.PostPlayerLocation;
import io.github.usbharu.venriplugin.pin.PinAction;
import io.github.usbharu.venriplugin.showdamage.ShowDamage;
import io.github.usbharu.venriplugin.showplayerstatus.ShowPlayerStatus;
import org.bukkit.plugin.java.JavaPlugin;

public final class VenriPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Hello, world!");

    getCommand("pin").setExecutor(new PinCommand());
    getCommand("where").setExecutor(new PostPlayerLocation());
    getCommand("navigate").setExecutor(new NavigateCommand());

    ShowPlayerStatus showPlayerStatus = new ShowPlayerStatus();
    getServer().getPluginManager().registerEvents(showPlayerStatus, this );
    showPlayerStatus.setupShowPlayerStatus();

    getServer().getPluginManager().registerEvents(new PinAction(), this);
    getServer().getPluginManager().registerEvents(new ShowDamage(), this);
    getServer().getPluginManager().registerEvents(new DoorOpener(),this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

}
