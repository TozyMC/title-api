package xyz.tozymc.spigot.api.title.backend.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ActionbarApi {

  public void sendActionbar(@NotNull Player player, String message) {
    BaseComponent[] components = TextComponent.fromLegacyText(message);
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, components);
  }
}
