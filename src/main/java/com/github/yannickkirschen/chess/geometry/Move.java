package com.github.yannickkirschen.chess.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.yannickkirschen.chess.ChessException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A <code>Move</code> describes an imutable move of a piece in a chess game. It has a starting position and an end position. Two moves are equal to
 * each other, when their starting position and their ending position are equal to each other.
 *
 * @author Yannick Kirschen
 * @since May 6, 2019
 */
@EqualsAndHashCode
@ToString
public final class Move {
    private static final List<String> chars = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

    @Getter
    private final Position start;

    @Getter
    private final Position end;

    /**
     * Crerates a new move for a chess game.
     *
     * @param start The position to start the move from.
     * @param end   The position to end the move in.
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new move for a chess game from two positions in chess style.
     *
     * @param firstPosition  Start in chess style (e.g. "A2")
     * @param secondPosition End in chess style (e.g. "A4")
     * @throws ChessException If the values are out of range.
     */
    public Move(String firstPosition, String secondPosition) throws ChessException {
        start = new Position(chars.indexOf(firstPosition.substring(0, 1)), Integer.parseInt(firstPosition.substring(1, 2)) - 1);
        end = new Position(chars.indexOf(secondPosition.substring(0, 1)), Integer.parseInt(secondPosition.substring(1, 2)) - 1);
    }
}
