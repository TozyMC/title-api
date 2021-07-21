package xyz.tozymc.spigot.api.title.backend.impl;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.title.backend.BackendTitleApi;

public class BukkitTitleApi implements BackendTitleApi {

  @Override
  public void sendTitle(@NotNull Player player,
      String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
  }
}
