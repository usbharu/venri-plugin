package io.github.usbharu.venriplugin2.display;

import static io.github.usbharu.venriplugin2.VenriPlugin2.CONFIGURATION;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateArg;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateLabel;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validateMaxLength;
import static io.github.usbharu.venriplugin2.command.validate.CommandValidation.validatePlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

public class DisplayPlayerStatus implements Listener, CommandExecutor {

  private final String enable = "enable";
  private final String disable = "disable";

  public DisplayPlayerStatus() {
    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    Scoreboard mainScoreboard = scoreboardManager.getMainScoreboard();

    Objective playerHealth = mainScoreboard.getObjective("playerHealth");
    if (playerHealth == null) {
      playerHealth =
          mainScoreboard.registerNewObjective("playerHealth", "health", "HP", RenderType.HEARTS);
      playerHealth.setDisplaySlot(DisplaySlot.PLAYER_LIST);
    }
    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      playerHealth.getScore(onlinePlayer).setScore(((int) onlinePlayer.getHealth()));
    }

    Objective playerLevel = mainScoreboard.getObjective("playerLevel");
    if (playerLevel == null) {
      playerLevel =
          mainScoreboard.registerNewObjective("playerLevel", "level", "Level", RenderType.INTEGER);
      playerLevel.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      playerLevel.getScore(onlinePlayer).setScore(onlinePlayer.getLevel());
    }
  }

  @EventHandler
  public void onDied(PlayerDeathEvent event) {
    Player player = event.getEntity();
    player.getServer().broadcastMessage(player.getDisplayName() + "が");
  }

  @EventHandler
  public void onLevelChanged(PlayerLevelChangeEvent event) {
    if ((event.getOldLevel() < event.getNewLevel()) && ((event.getNewLevel() % 30) == 0)) {
      Player player = event.getPlayer();
      player.getServer()
          .broadcastMessage(player.getName() + "のレベルが" + event.getNewLevel() + "になりました!");
    }
  }


  // TODO: 2022/08/18 クラス分けたほうがいいと思う
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    if (!validateMaxLength(args, 2).wasSuccess()) {
      return false;
    }

    if (validateLabel(label, "displayPlayerHealth", "displayplayerhealth", "dph").wasSuccess()) {
      return displayPlayerHealthCommand(args, sender);
    } else if (validateLabel(label, "displayPlayerLevel", "diplayplayerlevel",
        "dpl").wasSuccess()) {
      return displayPlayerLevelCommand(args, sender);
    }

    return false;
  }

  private boolean displayPlayerHealthCommand(@NotNull String[] args, CommandSender sender) {

    if (validateArg(args, 0, "server").wasSuccess() && validateArg(args, 1, enable,
        disable).wasSuccess()) {
      CONFIGURATION().serverSet("DisplayPlayerHealth", enable, args[1].equals(enable));
      Bukkit.getScoreboardManager().getMainScoreboard().clearSlot(DisplaySlot.BELOW_NAME);
      return true;
    }

    if (!validatePlayer(sender).wasSuccess()) {
      sender.sendMessage(
          "プレイヤーの設定はプレイヤーのみ設定できます。サーバーの設定をする場合は \"/displayPlayerHealth server enable\" コマンドを使ってください ");
      return false;
    }

    if (validateArg(args, 0, enable, disable).wasSuccess()) {

      CONFIGURATION().playerSet("DisplayPlayerHealth", enable, ((Player) sender),
          args[0].equals(enable));
      if (args[0].equals(disable)) {
        Bukkit.getScoreboardManager().getMainScoreboard().clearSlot(DisplaySlot.PLAYER_LIST);
      }
    }
    return true;
  }

  private boolean displayPlayerLevelCommand(String[] args, CommandSender sender) {
    if (validateArg(args, 0, "server").wasSuccess() && validateArg(args, 1, enable,
        disable).wasSuccess()) {
      CONFIGURATION().serverSet("DisplayPlayerLevel", enable, args[1].equals(enable));
      return true;
    }
    if (!validatePlayer(sender).wasSuccess()) {
      sender.sendMessage(
          "プレイヤーの設定はプレイヤーのみ設定できます。サーバーの設定をする場合は \"/displayPlayerLEVEL server enable\" コマンドを使ってください ");
      return false;
    }
    if (validateArg(args, 0, enable, disable).wasSuccess()) {
      CONFIGURATION().playerSet("DisplayPlayerLevel", enable, ((Player) sender),
          args[0].equals(enable));
    }
    return true;
  }
}
