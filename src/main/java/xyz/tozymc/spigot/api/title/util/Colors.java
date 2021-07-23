package xyz.tozymc.spigot.api.title.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.md_5.bungee.api.ChatColor;

public final class Colors {

  private static final char ALT_COLOR_CODE;
  private static final Pattern HEX_COLOR_PATTERN;

  static {
    ALT_COLOR_CODE = '&';
    HEX_COLOR_PATTERN = Pattern.compile("(#[a-fA-F0-9]{6})");
  }

  private Colors() {}

  public static String color(String text) {
    text = ChatColor.translateAlternateColorCodes(ALT_COLOR_CODE, text);
    return colorRgb(text);
  }

  private static String colorRgb(String text) {
    Matcher matcher = HEX_COLOR_PATTERN.matcher(text);
    StringBuffer buffer = new StringBuffer();
    if (!matcher.matches()) {
      return text;
    }
    while (matcher.find()) {
      matcher.appendReplacement(buffer, translateHexColor(matcher.group(1)));
    }
    return buffer.toString();
  }

  private static String translateHexColor(String hex) {
    char[] chars = hex.toCharArray();
    return IntStream.range(1, chars.length)
        .mapToObj(i -> String.valueOf(ChatColor.COLOR_CHAR) + chars[i])
        .collect(Collectors.joining("", ChatColor.COLOR_CHAR + "x", ""));
  }
}