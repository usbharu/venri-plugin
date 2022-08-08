package io.github.usbharu.venriplugin.showdamage;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ShowDamage implements Listener {
  @EventHandler
  public void onTakeDamage(EntityDamageByEntityEvent event){
    if (event.getDamager() instanceof Player player) {
      if (event.getEntity() instanceof LivingEntity entity) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("-"+(int)event.getDamage()+" "+((int)(entity.getHealth()-event.getDamage()))+" / "+ entity.getMaxHealth()));
      }
    }
  }
}
