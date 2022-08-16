package io.github.usbharu.venriplugin2.display;

import static io.github.usbharu.venriplugin2.VenriPlugin2.CONFIGURATION;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateArg;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateLength;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateMaxLength;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validatePlayer;

import io.github.usbharu.venriplugin2.util.ActionBar;
import java.util.Arrays;
import java.util.List;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DisplayDamage implements CommandExecutor, TabCompleter, Listener {

  private static final String CONFIG_PATH = ".Functions.DisplayDamage.";
  private static final String CONFIG_SERVER = "Server" + CONFIG_PATH;
  private static final String CONFIG_PLAYER = "Player" + CONFIG_PATH;
  private final String enable = "enable";
  private final String disable = "disable";

  @EventHandler
  public void onTakeDamage(EntityDamageByEntityEvent event) {
    if (!CONFIGURATION().getConfiguration().getBoolean(CONFIG_SERVER + enable, true)) {
      return;
    }
    if (!CONFIGURATION().getConfiguration()
        .getBoolean(CONFIG_PLAYER + event.getDamager().getUniqueId() + ".enable", true)) {
      return;
    }

    if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
      Player player = (Player) event.getDamager();
      LivingEntity entity = (LivingEntity) event.getEntity();

      String message =
          "-" + (int) event.getDamage() + " " + ((int) entity.getHealth() - event.getDamage())
              + " / " + (int) entity.getAttribute(
              Attribute.GENERIC_MAX_HEALTH).getValue();

      ActionBar.send(player, message);

    }
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (!validateMaxLength(args, 2).wasSuccess()) {
      return false;
    }
    if (validateArg(args, 0, "server").wasSuccess() && validateArg(args, 1, enable,
        disable).wasSuccess()) {
      serverConfig(args[1].equals(enable));
      return true;
    }

    if (!validatePlayer(sender).wasSuccess()) {
      return false;
    }
    if (validateLength(args, 1).wasSuccess() && validateArg(args, 0, enable,
        disable).wasSuccess()) {
      playerConfig((Player) sender, args[0].equals(enable));
    }
    return true;
  }


  protected void serverConfig(boolean isSetEnable) {
    CONFIGURATION().serverSet("DisplayDamage", enable, isSetEnable);
    CONFIGURATION().save();
  }

  protected void playerConfig(@NotNull Player sender, boolean isSetEnable) {
    CONFIGURATION().set(CONFIG_PLAYER + sender.getUniqueId() + ".enable", isSetEnable);
    CONFIGURATION().save();
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
      @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
    if (args.length == 1) {
      return Arrays.asList(enable, disable, "server");
    } else if (args.length == 2 && args[0].equals("server")) {
      return Arrays.asList(enable, disable);
    }
    return null;
  }

  protected void onEnabled() {

  }

  protected void onDisabled() {

  }
}
