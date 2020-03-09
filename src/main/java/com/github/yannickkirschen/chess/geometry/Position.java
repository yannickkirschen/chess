package com.github.yannickkirschen.chess.geometry;

import com.github.yannickkirschen.chess.ChessException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A position is the mathematical position a piece has on a chessboard.
 * <p>
 * Counting begins at 0.
 *
 * @author Yannick Kirschen
 * @since Apr. 16, 2019
 */
@EqualsAndHashCode
@ToString
public final class Position {
    @Getter
    private int x;

    @Getter
    private int y;

    /**
     * Creates a new position. Positions must be between 0 and 7.
     *
     * @param x x-axis
     * @param y y-axis
     * @throws ChessException if the coordinates are out of range.
     */
    public Position(int x, int y) throws ChessException {
        if (y < 0 || y > 8 || x < 0 || x > 8) {
            throw new ChessException("Position has to be between 0 and 7.");
        }

        this.x = x;
        this.y = y;
    }
}
