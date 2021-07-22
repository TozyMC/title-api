package xyz.tozymc.spigot.api.title;

import static xyz.tozymc.util.Preconditions.checkNotNull;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.minecraft.MinecraftVersion;
import xyz.tozymc.spigot.api.title.backend.actionbar.ActionbarApi;
import xyz.tozymc.spigot.api.title.backend.title.BackendTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.BukkitTitleApi;
import xyz.tozymc.spigot.api.title.backend.title.LegacyTitleApi;

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

    getInstance().titleApi.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
  }

  public static void sendTitle(@NotNull Player player, @NotNull Title title) {
    checkNotNull(player, "Player cannot be null");
    checkNotNull(title, "Title cannot be null");

    sendTitle(player, title.getTitle(), title.getSubtitle(), title.getFadeIn(), title.getStay(),
        title.getFadeOut());
  }

  public static void sendActionbar(@NotNull Player player, String message) {
    checkNotNull(player, "Player cannot be null");

    getInstance().actionbarApi.sendActionbar(player, message);
  }

  private static final class TitleApiHelper {

    private static final TitleApi INSTANCE = new TitleApi();
  }
}
