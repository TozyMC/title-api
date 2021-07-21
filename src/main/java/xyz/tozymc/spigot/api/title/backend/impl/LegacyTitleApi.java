package xyz.tozymc.spigot.api.title.backend.impl;

import static xyz.tozymc.reflect.accessor.Accessors.accessConstructor;
import static xyz.tozymc.reflect.accessor.Accessors.accessField;
import static xyz.tozymc.spigot.api.title.util.Reflections.newChatComponentText;
import static xyz.tozymc.spigot.api.title.util.Reflections.sendPacket;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.reflect.accessor.ConstructorAccessor;
import xyz.tozymc.reflect.resolver.minecraft.NmsClassResolver;
import xyz.tozymc.spigot.api.title.backend.BackendTitleApi;

public class LegacyTitleApi implements BackendTitleApi {

  private static final Class<?> PacketPlayOutTitle;
  private static final Class<?> EnumTitleAction;
  private static final Class<?> IChatBaseComponent;

  private static final ConstructorAccessor<?> titleConstructor;

  private static final Object TITLE;
  private static final Object SUBTITLE;

  static {
    PacketPlayOutTitle = NmsClassResolver.resolver().resolve("PacketPlayOutTitle");
    EnumTitleAction = PacketPlayOutTitle.getDeclaredClasses()[0];
    IChatBaseComponent = NmsClassResolver.resolver().resolve("IChatBaseComponent");

    titleConstructor = accessConstructor(PacketPlayOutTitle, EnumTitleAction, IChatBaseComponent);

    TITLE = accessField(EnumTitleAction, "TITLE").get(null);
    SUBTITLE = accessField(EnumTitleAction, "SUBTITLE").get(null);

  }

  @Override
  public void sendTitle(@NotNull Player player, String title, String subtitle, int fadeInt,
      int stay, int fadeOut) {
    if (title != null) {
      Object titleComponent = newChatComponentText(title);
      Object packetTitle = titleConstructor.newInstance(TITLE, titleComponent);
      sendPacket(player, packetTitle);
    }
    if (subtitle != null) {
      Object subtitleComponent = newChatComponentText(subtitle);
      Object packetSubtitle = titleConstructor.newInstance(SUBTITLE, subtitleComponent);
      sendPacket(player, packetSubtitle);
    }

    Object packetTimes = accessConstructor(PacketPlayOutTitle, int.class, int.class,
        int.class).newInstance(fadeInt, stay, fadeOut);
    sendPacket(player, packetTimes);
  }
}
