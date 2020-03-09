# Chess

![GitHub Workflow build status badge](https://github.com/yannickkirschen/chess/workflows/Maven%20clean%20install/badge.svg)
[![](https://api.dependabot.com/badges/status?host=github&repo=yannickkirschen/chess)](https://dependabot.com)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/yannickkirschen/chess/graphs/commit-activity)
[![GitHub license](https://img.shields.io/github/license/yannickkirschen/chess.svg)](https://github.com/yannickkirschen/chess/blob/master/LICENSE)
[![GitHub release](https://img.shields.io/github/release/yannickkirschen/chess.svg)](https://github.com/yannickkirschen/chess/releases/)

`Chess` is a two-player chess game written in Java.

The Java version is set to 1.8. You can edit the `pom.xml` and set it to >=8 if you want to.

## Running

Start the game by typing

```
git clone https://github.com/yannickkirschen/Chess
cd Chess
mvn clean install
java -jar target/Chess.jar
```

in the terminal of your choice.

## How it works

Once you've started the game, you see a chessboard on the screen. Player one is at the bottom, player two at the top. The pieces name's are four characters
wide, so some of them are cut; I'm sure you know, what pieces they are .

To make a move, just type `START_FIELD STOP_FIELD`, e.g. `A2 A4`.

## License

Licensed under the [MIT License](https://github.com/yannickkirschen/chess****/blob/master/LICENSE).
Happy forking :)
