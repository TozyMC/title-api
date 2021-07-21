package xyz.tozymc.spigot.api.title;

import java.util.Objects;

public class Title {

  private String title;
  private String subtitle;
  private int fadeIn;
  private int stay;
  private int fadeOut;

  public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    this.title = title;
    this.subtitle = subtitle;
    this.fadeIn = fadeIn;
    this.stay = stay;
    this.fadeOut = fadeOut;
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
