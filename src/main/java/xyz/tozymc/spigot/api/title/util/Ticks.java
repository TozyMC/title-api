package xyz.tozymc.spigot.api.title.util;

import org.jetbrains.annotations.Contract;

import java.util.concurrent.TimeUnit;

/**
 * Utility class for Minecraft tick.
 *
 * @author TozyMC
 * @since 1.0
 */
public final class Ticks {

  private Ticks() {}

  /**
   * Converts duration to Minecraft ticks, with 1 tick equal to 1/20 seconds.
   *
   * <p>If {@code timeUnit} is null, it will always return duration.
   *
   * @param duration The duration.
   * @param timeUnit The time unit of duration parameter.
   * @return Minecraft ticks converted from duration.
   */
  @Contract("_, null -> param1")
  public static int convertToTick(int duration, TimeUnit timeUnit) {
    if (timeUnit == null) {
      return duration;
    }
    long seconds = timeUnit.toSeconds(duration);
    return Math.toIntExact(seconds * 20);
  }
}
