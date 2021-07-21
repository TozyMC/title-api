package xyz.tozymc.spigot.api.title.backend;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface BackendTitleApi {

  void sendTitle(@NotNull Player player, String title, String subtitle, int fadeIn, int stay,
      int fadeOut);
}
