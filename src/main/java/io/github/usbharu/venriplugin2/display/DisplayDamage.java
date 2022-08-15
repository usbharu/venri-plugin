package io.github.usbharu.venriplugin2.display;

import static io.github.usbharu.venriplugin2.VenriPlugin2.CONFIGURATION;

import io.github.usbharu.venriplugin2.command.validate.CommandValidation;
import io.github.usbharu.venriplugin2.command.validate.Validate;
import io.github.usbharu.venriplugin2.util.ActionBar;
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

  @EventHandler
  public void onTakeDamage(EntityDamageByEntityEvent event) {
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
    if (!CommandValidation.verifyLength(args, 1).wasSuccess()) {
      return false;
    }
    Validate validate = CommandValidation.verifyArg(args, 0, "enable", "disable");
    if (!validate.wasSuccess()) {
      return false;
    }
    if (args[0].equals("enable")) {
      CONFIGURATION().set("enable", true);
      onEnabled();
    } else {
      CONFIGURATION().set("enable", false);
      onDisabled();
    }
    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
      @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
    return null;
  }

  protected void onEnabled() {

  }

  protected void onDisabled() {

  }
}
