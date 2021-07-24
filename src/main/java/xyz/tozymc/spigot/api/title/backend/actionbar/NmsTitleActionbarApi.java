package xyz.tozymc.spigot.api.title.backend.actionbar;

import static xyz.tozymc.spigot.api.title.util.Reflections.IChatBaseComponent;
import static xyz.tozymc.spigot.api.title.util.Reflections.newChatComponentText;
import static xyz.tozymc.spigot.api.title.util.Reflections.sendPacket;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.reflect.accessor.Accessors;
import xyz.tozymc.reflect.accessor.ConstructorAccessor;
import xyz.tozymc.reflect.resolver.minecraft.NmsClassResolver;

public class NmsTitleActionbarApi implements TitleActionbarApi {

  private static final Class<?> PacketPlayOutChat;

  static {
    PacketPlayOutChat = NmsClassResolver.resolver().resolve("PacketPlayOutChat");
  }

  @Override
  public void sendActionbar(@NotNull Player player, String message) {
    ConstructorAccessor<?> constructor = Accessors.accessConstructor(PacketPlayOutChat,
        IChatBaseComponent, byte.class);
    Object component = newChatComponentText(message);
    Object packet = constructor.newInstance(component, (byte) 2);
    sendPacket(player, packet);
  }
}
