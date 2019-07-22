# Chess

`Chess` is a two-player chess game written in Java.
The Java version is set to 11. You can edit the `pom.xml` and set it to >=8 if you want to.

## Running

Once you've downloaded the JAR, you can start the game by typing

```
git clone https://github.com/yannickkirschen/Chess
cd Chess
mvn clean install
java -jar target/Chess.jar
```

in the terminal of your choice.

## How it works

Once you've started the game, you see a chessboard on the screen. Player one is at the bottom, player two at the top. The pieces name's are four characters wide, so some of them are cut; I'm sure you know, what pieces they are :smile:.

To make a move, just type `START_FIELD STOP_FIELD`, e.g. `A2 A4`.

Have fun!