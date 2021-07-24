package xyz.tozymc.spigot.api.title.backend.actionbar;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface TitleActionbarApi {

  void sendActionbar(@NotNull Player player, String message);
}
