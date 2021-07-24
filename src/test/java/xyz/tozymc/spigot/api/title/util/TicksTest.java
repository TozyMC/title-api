package xyz.tozymc.spigot.api.title.util;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.tozymc.spigot.api.title.util.Ticks.convertToTick;

import org.junit.jupiter.api.Test;

class TicksTest {

  @Test
  void convertToTick_fromSecond() {
    assertEquals(40, convertToTick(2, SECONDS));
  }

  @Test
  void convertToTick_fromMinutes() {
    assertEquals(6000, convertToTick(5, MINUTES));
  }
}