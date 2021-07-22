package xyz.tozymc.spigot.api.title;

import static xyz.tozymc.util.Preconditions.checkNotNull;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.minecraft.MinecraftVersion;
import xyz.tozymc.spigot.api.title.backend.actionbar.ActionbarApi;
import xyz.tozymc.spigot.api.title.backend.title.BackendTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.BukkitTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.LegacyTitleApi;
import xyz.tozymc.spigot.api.title.util.Ticks;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class TitleApi {

  private final BackendTitleApi titleApi;
  private final ActionbarApi actionbarApi;

  private TitleApi() {
    this.titleApi = MinecraftVersion.getVersion().isOlderThan(MinecraftVersion.v1_11_R1)
        ? new LegacyTitleApi()
        : new BukkitTitleApi();
    this.actionbarApi = new ActionbarApi();
  }

  public static TitleApi getInstance() {
    return TitleApiHelper.INSTANCE;
  }

  public static void sendTitle(@NotNull Player player, String title, String subtitle, int fadeIn,
      int stay, int fadeOut) {
    checkNotNull(player, "Player cannot be null");

    getInstance().sendTitle0(player, title, subtitle, fadeIn, stay, fadeOut, null);
  }

  public static void sendTitle(@NotNull Player player, String title, String subtitle, int fadeIn,
      int stay, int fadeOut, TimeUnit timeUnit) {
    checkNotNull(player, "Player cannot be null");

    getInstance().sendTitle0(player, title, subtitle, fadeIn, stay, fadeOut, timeUnit);
  }

  public static void sendTitle(@NotNull Player player, @NotNull Title title) {
    checkNotNull(player, "Player cannot be null");
    checkNotNull(title, "Title cannot be null");

    getInstance().sendTitle0(player, title.getTitle(), title.getSubtitle(), title.getFadeIn(),
        title.getStay(), title.getFadeOut(), title.getTimeUnit());
  }

  public static void sendActionbar(@NotNull Player player, String message) {
    checkNotNull(player, "Player cannot be null");

    getInstance().actionbarApi.sendActionbar(player, message);
  }

  private void sendTitle0(Player player, String title, String subtitle, int fadeIn, int stay,
      int fadeOut, TimeUnit timeUnit) {
    fadeIn = Ticks.convertToTick(fadeIn, timeUnit);
    stay = Ticks.convertToTick(stay, timeUnit);
    fadeOut = Ticks.convertToTick(fadeOut, timeUnit);

    titleApi.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
  }

  private static final class TitleApiHelper {

    private static final TitleApi INSTANCE = new TitleApi();
  }
}
