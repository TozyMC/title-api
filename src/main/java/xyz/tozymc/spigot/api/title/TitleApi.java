package xyz.tozymc.spigot.api.title;

import static xyz.tozymc.util.Preconditions.checkNotNull;

import java.util.concurrent.TimeUnit;

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

/**
 * {@code TitleApi} provides methods for sending titles to player.
 *
 * <p>To send title, use:
 * {@link TitleApi#sendTitle(Player, String, String, int, int, int)}
 * <p>To send action bar, use:
 * {@link TitleApi#sendActionbar(Player, String)}
 *
 * @author TozyMC
 * @since 1.0
 */
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

  private static TitleApi getInstance() {
    return TitleApiHelper.INSTANCE;
  }

  /**
   * Sends the message to player title. Time unit is Minecraft ticks.
   *
   * @param player   The player to receive title.
   * @param title    The title send to player.
   * @param subtitle The subtitle send to player.
   * @param fadeIn   The fade in time of title.
   * @param stay     The stay time of title.
   * @param fadeOut  The fade out time of tile.
   */
  public static void sendTitle(@NotNull Player player, String title, String subtitle, int fadeIn,
      int stay, int fadeOut) {
    checkNotNull(player, "Player cannot be null");

    getInstance().sendTitle0(player, title, subtitle, fadeIn, stay, fadeOut, null);
  }

  /**
   * Sends the message to player title, with custom time unit.
   *
   * @param player   The player to receive title.
   * @param title    The title send to player.
   * @param subtitle The subtitle send to player.
   * @param fadeIn   The fade in time of title.
   * @param stay     The stay time of title.
   * @param fadeOut  The fade out time of tile.
   * @param timeUnit The time unit of fade and stay time.
   */
  public static void sendTitle(@NotNull Player player, String title, String subtitle, int fadeIn,
      int stay, int fadeOut, TimeUnit timeUnit) {
    checkNotNull(player, "Player cannot be null");

    getInstance().sendTitle0(player, title, subtitle, fadeIn, stay, fadeOut, timeUnit);
  }

  /**
   * Sends the message to player title by using {@link Title} object.
   *
   * @param player The player to receive title.
   * @param title  The title object to send player.
   */
  public static void sendTitle(@NotNull Player player, @NotNull Title title) {
    checkNotNull(player, "Player cannot be null");
    checkNotNull(title, "Title cannot be null");

    getInstance().sendTitle0(player, title.getTitle(), title.getSubtitle(), title.getFadeIn(),
        title.getStay(), title.getFadeOut(), title.getTimeUnit());
  }

  /**
   * Sends the message to player actionbar.
   *
   * @param player  The player to receive action bar message.
   * @param message The message send to player.
   */
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
