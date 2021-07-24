package xyz.tozymc.spigot.api.title;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.Contract;

/**
 * The title object to use when sending this to the player.
 *
 * @since 1.0
 */
public class Title {

  public static final int DEFAULT_FADE_IN = 20;
  public static final int DEFAULT_STAY = 60;
  public static final int DEFAULT_FADE_OUT = 20;

  private String title;
  private String subtitle;
  private int fadeIn;
  private int stay;
  private int fadeOut;
  private TimeUnit timeUnit;

  /**
   * Creates new title object.
   *
   * <p>If {@code timeUnit} is null, default time unit is Minecraft ticks.
   *
   * @param title    The title message.
   * @param subtitle The subtitle message.
   * @param fadeIn   The title fade in time.
   * @param stay     The title stay time.
   * @param fadeOut  The title fade out time.
   * @param unit     The time unit for fadeIn, stay, and fadeOut.
   */
  @Contract(pure = true)
  public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut, TimeUnit unit) {
    this.title = title;
    this.subtitle = subtitle;
    this.fadeIn = fadeIn;
    this.stay = stay;
    this.fadeOut = fadeOut;
    this.timeUnit = unit;
  }

  /**
   * Creates new title object with time unit is Minecraft ticks.
   *
   * @param title    The title message.
   * @param subtitle The subtitle message.
   * @param fadeIn   The title fade in time.
   * @param stay     The title stay time.
   * @param fadeOut  The title fade out time.
   */
  @Contract(pure = true)
  public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    this(title, subtitle, fadeIn, stay, fadeOut, null);
  }

  /**
   * Creates new title object with default fade and stay time.
   *
   * @param title    The title message.
   * @param subtitle The subtitle message.
   * @see #DEFAULT_FADE_IN
   * @see #DEFAULT_STAY
   * @see #DEFAULT_FADE_OUT
   */
  @Contract(pure = true)
  public Title(String title, String subtitle) {
    this(title, subtitle, DEFAULT_FADE_IN, DEFAULT_STAY, DEFAULT_FADE_OUT);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public int getFadeIn() {
    return fadeIn;
  }

  public void setFadeIn(int fadeIn) {
    this.fadeIn = fadeIn;
  }

  public int getStay() {
    return stay;
  }

  public void setStay(int stay) {
    this.stay = stay;
  }

  public int getFadeOut() {
    return fadeOut;
  }

  public void setFadeOut(int fadeOut) {
    this.fadeOut = fadeOut;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Title)) {
      return false;
    }
    Title title1 = (Title) o;
    return getFadeIn() == title1.getFadeIn() && getStay() == title1.getStay()
        && getFadeOut() == title1.getFadeOut() && Objects.equals(getTitle(),
        title1.getTitle()) && Objects.equals(getSubtitle(), title1.getSubtitle());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTitle(), getSubtitle(), getFadeIn(), getStay(), getFadeOut());
  }

  @Override
  public String toString() {
    return "Title{" +
        "title='" + title + '\'' +
        ", subtitle='" + subtitle + '\'' +
        ", fadeIn=" + fadeIn +
        ", stay=" + stay +
        ", fadeOut=" + fadeOut +
        '}';
  }
}
