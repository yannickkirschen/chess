package com.github.yannickkirschen.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.yannickkirschen.chess.geometry.Move;
import com.github.yannickkirschen.chess.geometry.Position;
import com.github.yannickkirschen.chess.pieces.Piece;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A <code>Chessboard</code> represents a map in a chess game and holds all of its pieces.
 *
 * @author Yannick Kirschen
 * @since May 6, 2019
 */
@EqualsAndHashCode
@ToString
public class Chessboard {
    private final Map<Position, Piece> board = new HashMap<>();

    /**
     * Adds a piece to the board.
     *
     * @param position Position to put the piece to.
     * @param piece    Piece to add.
     */
    public void add(Position position, Piece piece) {
        board.put(position, piece);
    }

    /**
     * Looks for a piece and returns it.
     *
     * @param position The position to look for a piece.
     * @return The piece. {@code null}, if there is no piece.
     */
    public Piece get(Position position) {
        return board.getOrDefault(position, null);
    }

    /**
     * Looks for a piece and returns it.
     *
     * @param x The position on the x-axis.
     * @param y The position on the y-axis.
     * @return The piece. {@code null}, if there is no piece.
     * @throws ChessException If the coordinates are out of bounds.
     */
    public Piece get(int x, int y) throws ChessException {
        Position position = new Position(x, y);
        return board.getOrDefault(position, null);
    }

    /**
     * Moves a Piece from one position to another. Note, that it is not checked, if the move is allowed!
     *
     * @param move The move to execute.
     */
    public void move(Move move) {
        board.put(move.getEnd(), board.get(move.getStart()));
        board.remove(move.getStart());
    }

    /**
     * Checks the existence of a piece at a particular position.
     *
     * @param position The position to look for a piece.
     * @return true, if there is a piece.
     */
    public boolean exists(Position position) {
        return board.containsKey(position) && board.get(position) != null;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Set<Position> iterator() {
        return board.keySet();
    }
}
