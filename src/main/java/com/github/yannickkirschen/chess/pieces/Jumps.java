package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;

/**
 * Indicates, that a piece is able to make a move that requires checking if it would jump over another piece.
 *
 * @author Yannick Kirschen
 * @since May 20, 2019
 */
interface Jumps {
    /**
     * Checks, if the piece would leap another character, if the move is executed.
     *
     * @return {@code true}, if the piece would leap another piece.
     * @throws ChessException If something went wrong while creating a new position.
     */
    boolean leaps() throws ChessException;
}
