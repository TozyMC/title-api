package xyz.tozymc.spigot.api.title.util;

import static xyz.tozymc.reflect.accessor.Accessors.accessConstructor;
import static xyz.tozymc.reflect.accessor.Accessors.accessField;
import static xyz.tozymc.reflect.accessor.Accessors.accessMethod;
import static xyz.tozymc.reflect.resolver.minecraft.NmsClassResolver.resolver;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.reflect.accessor.FieldAccessor;
import xyz.tozymc.reflect.accessor.MethodAccessor;

import java.util.Arrays;

public final class Reflections {

  private Reflections() {}

  public static Object getHandle(@NotNull Player player) {
    Class<?> playerClass = player.getClass();
    MethodAccessor getHandleMethod = accessMethod(playerClass, "getHandle");
    return getHandleMethod.invoke(player);
  }

  public static Object getPlayerConnection(Player player) {
    Object handle = getHandle(player);
    Class<?> entityPlayerClass = handle.getClass();
    FieldAccessor playerConnectionField = accessField(entityPlayerClass, "playerConnection");
    return playerConnectionField.get(handle);
  }

  public static void sendPacket(Player player, Object @NotNull ... packets) {
    Object playerConnection = getPlayerConnection(player);
    Class<?> playerConnectionClass = playerConnection.getClass();
    Class<?> packetClass = resolver().resolve("Packet");
    MethodAccessor sendPacketMethod = accessMethod(playerConnectionClass, "sendPacket",
        packetClass);
    if (packets.length == 1) {
      sendPacketMethod.invoke(playerConnection, packets[0]);
      return;
    }
    Arrays.stream(packets).forEach(packet -> sendPacketMethod.invoke(playerConnection, packet));
  }

  public static Object newChatComponentText(String text) {
    // TODO Add color
    Class<?> chatComponentClass = resolver().resolve("ChatComponentText");
    return accessConstructor(chatComponentClass, String.class).newInstance(text);
  }
}
