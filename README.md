[originalLicense]: https://opensource.org/licenses/MIT "MIT License"

[license]: https://github.com/TozyMC/title-api/blob/main/LICENSE "MIT License"

[github]: https://github.com/TozyMC/title-api "GitHub project"

[release]: https://github.com/TozyMC/title-api/releases "GitHub Release"

[javadoc]: https://www.javadoc.io/doc/xyz.tozymc.spigot/title-api/ "title-api Javadoc"

[ossIndex]: https://ossindex.sonatype.org/component/pkg:maven/xyz.tozymc.spigot/title-api "OSS Index"

[spigot]: / "Spigot Resources"

<div align="center">
  <h1>title-api</h1>
  <p><i>Library to use titles in Spigot server.</i></p>
  <a href="https://search.maven.org/artifact/xyz.tozymc.spigot/title-api/1.0/jar"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/xyz.tozymc.spigot/title-api?label=Maven%20Central&logo=apache-maven&style=flat-square"></a>
  <a href="https://github.com/TozyMC/title-api/releases"><img alt="GitHub release" src="https://img.shields.io/github/v/release/TozyMC/title-api?logo=github&style=flat-square"></a>
  <a href="https://www.javadoc.io/doc/xyz.tozymc.spigot/title-api/"><img alt="Javadoc" src="https://javadoc.io/badge2/xyz.tozymc.spigot/title-api/javadoc.svg?style=flat-square&label=Javadoc&color=brightgreen&logo=java"></a>
  <a href="https://github.com/TozyMC/title-api/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/TozyMC/title-api?style=flat-square"></a>
  <a href="https://github.com/TozyMC/title-api/blob/main/LICENSE"><img alt="MIT License" src="https://img.shields.io/github/license/TozyMC/title-api?style=flat-square"></a>
</div>
<br>

This library will help you to send titles in your server. You can send a title to the player by
sending it to the player's title or the player's actionbar. The title message supports color codes
and hex colors. So it will become easier to send titles to players.

***Notes:*** *This is a library,* ***not*** *a complete spigot plugin.*

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
  - [Manual](#manual)

- [How to use](#how-to-use)
- [License](#license)
- [External Links](#external-links)

## Requirements

The source code used is *java 8* and built under `jdk1.8.0_291`. Any java version lower than *java
8* will cause an error.

- **Java 8** or later
- **Spigot 1.8** or later

## Installation

There are many ways to install libraries to your plugin dependencies. You can follow this
instruction.

### Maven

Add this section inside `<dependencies>` tag in your `pom.xml`.

``` xml
<dependency>
  <groupId>xyz.tozymc.spigot</groupId>
  <artifactId>title-api</artifactId>
  <version>1.0</version>
</dependency>
```

### Gradle

Follow this instruction if your build tool is Gradle.

```gradle
dependencies {
    implementation 'xyz.tozymc.spigot:title-api:1.0'
}
```

### Manual

If your project doesn't have any build tools, you can install it manually.

1. [Download][release] the library in github release.
2. Add `jar` file to your project.

## How to use

### Title

#### Directly

```java
TitleApi.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
```

#### Title object

```java
Title title = new Title(title, subtitle, fadeIn, stay, fadeOut);
TitleApi.sendTitle(player, title);
```

### Actionbar

```java
TitleApi.sendActionbar(player, message);
```

## License

Distributed under the [MIT License][originalLicense]. See [`LICENSE`][license] for more information.

## External Links

- Javadoc: [javadoc.io][javadoc]
- GitHub: [github.com][github]
- Bintray: [ossindex.sonatype.org][ossIndex]
- Spigot Resources: [spigotmc.org][spigot]
