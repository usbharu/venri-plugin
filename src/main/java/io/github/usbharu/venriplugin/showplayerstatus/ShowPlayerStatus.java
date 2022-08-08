package io.github.usbharu.venriplugin.showplayerstatus;

import io.github.usbharu.venriplugin.pin.PinAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ShowPlayerStatus implements Listener {

//  Objective playerLocationX = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("playerLocationX");
//  Objective playerLocationY = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("playerLocationY");
//  Objective playerLocationZ = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("playerLocationZ");

  public void setupShowPlayerStatus() {
    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    Scoreboard mainScoreboard = scoreboardManager.getMainScoreboard();

    Objective playerHealth = mainScoreboard.getObjective("playerHealth");
    if (playerHealth == null) {
      playerHealth =
          mainScoreboard.registerNewObjective("playerHealth", "health", "HP", RenderType.HEARTS);
      playerHealth.setDisplaySlot(DisplaySlot.PLAYER_LIST);
    }
    for (Player player : Bukkit.getOnlinePlayers()) {
      playerHealth.getScore(player).setScore((int) player.getHealth());
    }

    Objective playerLevel = mainScoreboard.getObjective("playerLevel");
    if (playerLevel == null) {
      playerLevel =
          mainScoreboard.registerNewObjective("playerLevel", "level", "Level", RenderType.INTEGER);
      playerLevel.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    for (Player player : Bukkit.getOnlinePlayers()) {
      playerLevel.getScore(player).setScore(player.getLevel());
    }
//
//    if (playerLocationX == null) {
//      playerLocationX =
//          mainScoreboard.registerNewObjective("playerLocationX", "dummy", "X", RenderType.INTEGER);
//      playerLocationX.setDisplaySlot(DisplaySlot.SIDEBAR);
//    }
//
//    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//      playerLocationX.getScore(onlinePlayer).setScore((int) onlinePlayer.getLocation().getX());
//    }
//
//    if (playerLocationY == null) {
//      playerLocationY =
//          mainScoreboard.registerNewObjective("playerLocationY", "dummy", "Y", RenderType.INTEGER);
//      playerLocationY.setDisplaySlot(DisplaySlot.SIDEBAR);
//    }
//
//    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//      playerLocationY.getScore(onlinePlayer).setScore((int) onlinePlayer.getLocation().getX());
//    }
//
//    if (playerLocationZ == null) {
//      playerLocationZ =
//          mainScoreboard.registerNewObjective("playerLocationZ", "dummy", "Z", RenderType.INTEGER);
//      playerLocationZ.setDisplaySlot(DisplaySlot.SIDEBAR);
//    }
//
//    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//      playerLocationZ.getScore(onlinePlayer).setScore((int) onlinePlayer.getLocation().getX());
//    }

  }

//  @EventHandler
//  public void onPlayerMove(PlayerMoveEvent event) {
//    playerLocationX.getScore(event.getPlayer()).setScore((int) event.getTo().getX());
//    playerLocationY.getScore(event.getPlayer()).setScore((int) event.getTo().getY());
//    playerLocationZ.getScore(event.getPlayer()).setScore((int) event.getTo().getZ());
//  }

  @EventHandler
  public void onDamaged(EntityDamageEvent event){
    if (event.getEntity() instanceof Player player) {
      if (player.getHealth() ==1) {
        player.getServer().broadcastMessage(player.getName()+"が"+event.getCause()+"でピンチです! X:"+ ((int) player.getLocation().getX()) +" Y:"+ ((int) player.getLocation().getY())+" Z:"+ ((int) player.getLocation().getZ())+"にいますよ!");
      }
    }
  }

  @EventHandler
  public void onDied(PlayerDeathEvent event){
    Player player = event.getEntity();
    player.getServer().broadcastMessage(player.getName()+"が X:"+ ((int) player.getLocation().getX()) +" Y:"+ ((int) player.getLocation().getY())+" Z:"+ ((int) player.getLocation().getZ())+"で死亡しました!");
    PinAction.pin("death", player,player.getLocation());
  }

  @EventHandler
  public void onLevelChanged(PlayerLevelChangeEvent event){
    if (event.getOldLevel() <event.getNewLevel()&&(event.getNewLevel()&30)==0){
      Player player = event.getPlayer();
      player.getServer().broadcastMessage(player.getName()+"のレベルが"+event.getNewLevel()+"になりました!");
    }
  }
}
