package xyz.tozymc.spigot.api.title.util;

import org.jetbrains.annotations.Contract;

import java.util.concurrent.TimeUnit;

public final class Ticks {

  private Ticks() {}

  @Contract("_, null -> param1")
  public static int convertToTick(int duration, TimeUnit timeUnit) {
    if (timeUnit == null) {
      return duration;
    }
    long seconds = timeUnit.toSeconds(duration);
    return Math.toIntExact(seconds * 20);
  }
}
