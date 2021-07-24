package xyz.tozymc.spigot.api.title.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.tozymc.spigot.api.title.util.Colors.color;

import org.junit.jupiter.api.Test;

class ColorsTest {

  @Test
  void color_withOutHexColor() {
    String text = "&aHello world&c!";
    assertEquals("\u00A7aHello world\u00A7c!", color(text)); // expected: §aHello world§c!
  }

  @Test
  void color_withoutCharColor() {
    String text = "#ABCDEFHello world#123456!";
    assertEquals(
        "\u00A7x\u00A7a\u00A7b\u00A7c\u00A7d\u00A7e\u00A7fHello world\u00A7x\u00A71\u00A72\u00A73\u00A74\u00A75\u00A76!",
        color(text)); // expected: §x§a§b§c§d§e§fHello world§x§1§2§3§4§5§6!
  }

  @Test
  void color_combined() {
    String text = "#FABE12Hello world&f!";
    assertEquals("\u00A7x\u00A7f\u00A7a\u00A7b\u00A7e\u00A71\u00A72Hello world\u00A7f!",
        color(text)); // expected: §x§f§a§b§e§1§2Hello world§f!
  }
}