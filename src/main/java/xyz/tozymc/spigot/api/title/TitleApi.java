package xyz.tozymc.spigot.api.title;

import static xyz.tozymc.util.Preconditions.checkNotNull;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.minecraft.MinecraftVersion;
import xyz.tozymc.spigot.api.title.backend.actionbar.NmsTitleActionbarApi;
import xyz.tozymc.spigot.api.title.backend.actionbar.SpigotTitleActionbarApi;
import xyz.tozymc.spigot.api.title.backend.actionbar.TitleActionbarApi;
import xyz.tozymc.spigot.api.title.backend.title.BackendTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.NmsTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.SpigotTitleApi;
import xyz.tozymc.spigot.api.title.util.Ticks;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class TitleApi {

  private final BackendTitleApi titleApi;
  private final TitleActionbarApi titleActionbarApi;

  private TitleApi() {
    MinecraftVersion version = MinecraftVersion.getVersion();
    this.titleApi = version.isOlderThan(MinecraftVersion.v1_11_R1)
        ? new NmsTitleApi()
        : new SpigotTitleApi();
    this.titleActionbarApi = version.isOlderThan(MinecraftVersion.v1_9_R1)
        ? new NmsTitleActionbarApi()
        : new SpigotTitleActionbarApi();
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

    getInstance().titleActionbarApi.sendActionbar(player, message);
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
