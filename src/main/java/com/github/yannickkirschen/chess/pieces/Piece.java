package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;
import com.github.yannickkirschen.chess.geometry.Move;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A {@code Piece} represents a Piece in a chess game. There are the following pieces:
 *
 * <ul>
 * <li>King</li>
 * <li>Queen</li>
 * <li>Rook</li>
 * <li>Knight</li>
 * <li>Bishop</li>
 * <li>Pawn</li>
 * </ul>
 * <p>
 * Each piece is represented by its own class that derives from {@code Piece}. A Piece has a reference to the {@link Chessboard} we're playing on, as
 * well as the {@link Player} the piece belongs to. Furthermore, a piece must be registered with a move before checking if it is allowed.<br>
 * Each subclass must implement the following methods:
 *
 * <ul>
 * <li>{@link #getName()} returns a proper name of the piece</li>
 * <li>{@link #moveIsValid()} checks, if a requested move is allowed or not</li>
 * </ul>
 *
 * @author Yannick Kirschen
 * @since Apr. 16, 2019
 */
@EqualsAndHashCode
@ToString
public abstract class Piece {
    protected final Chessboard board;
    @Getter
    protected final Player player;
    private final int id;
    protected Move move;
    protected int relativeXMovement;
    protected int relativeYMovement;

    /**
     * Creates a new instance of a piece in chess game.
     *
     * @param board  The board we're playing on.
     * @param player The player this piece belongs to.
     * @param id     A unique id.
     */
    public Piece(Chessboard board, Player player, int id) {
        this.board = board;
        this.player = player;
        this.id = id;
    }

    /**
     * Each {@link Piece} should define a name. That name is displayed e.g. on the chessboard. A name could be "King" or "Pawn".
     *
     * @return The name of the piece.
     */
    public abstract String getName();

    /**
     * Checks if a move is valid.
     *
     * @return {@code true}, if the move is allowed.
     * @throws ChessException If something went wrong while creating a new position.
     */
    public abstract boolean moveIsValid() throws ChessException;

    /**
     * Registers a move and calculates the {@link #relativeXMovement} as well as the {@link #relativeYMovement} for using them internally.
     *
     * @param move The move that is executed.
     */
    public void register(Move move) {
        this.move = move;
        relativeXMovement = relativeXMovement();
        relativeYMovement = relativeYMovement();
    }

    /**
     * Deregister a move.
     */
    public void deregister() {
        move = null;
        relativeXMovement = 0;
        relativeYMovement = 0;
    }

    /**
     * Formats the name of the piece for the map. The name gets cut after four characters and made uppercase for black pieces and lowercase for white
     * pieces.
     *
     * @return The formatted name of the piece.
     */
    public String format() {
        return player == Player.ONE ? getName().substring(0, 4).toLowerCase() : getName().substring(0, 4).toUpperCase();
    }

    /**
     * Checks, whether the destination field already contains a piece and if that piece is the enemy.
     *
     * @return {@code true}, when the destinations field holds an own piece.
     */
    protected boolean collides() {
        Piece piece = board.get(move.getEnd());
        return piece == null || piece.player != player;
    }

    /**
     * Calculates the relative movement on the x-axis.
     *
     * @return The relative move on the x-axis.
     */
    private int relativeXMovement() {
        return Math.abs(move.getStart().getX() - move.getEnd().getX());
    }

    /**
     * Calculates the relative movement on the y-axis.
     *
     * @return The relative move on the y-axis.
     */
    private int relativeYMovement() {
        return Math.abs(move.getStart().getY() - move.getEnd().getY());
    }
}
