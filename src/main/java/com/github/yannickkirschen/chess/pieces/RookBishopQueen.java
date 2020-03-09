package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.geometry.Move;

/**
 * This class contains functions for validating the move of a rook and a bishop, since they are also used by the queen.
 *
 * @author Yannick Kirschen
 * @since May 9, 2019
 */
public final class RookBishopQueen {
    private RookBishopQueen() {
    }

    /**
     * Checks if a move of the bishop is valid.
     *
     * @param relativeXMovement Relative movement on the x-axis.
     * @param relativeYMovement Relative movement on the y-axis.
     * @return {@code true}, if the move is allowed.
     */
    protected static boolean moveIsValidBishop(int relativeXMovement, int relativeYMovement) {
        return relativeXMovement == relativeYMovement;
    }

    /**
     * Checks if a move of the rook is valid.
     *
     * @param relativeXMovement Relative movement on the x-axis.
     * @param relativeYMovement Relative movement on the y-axis.
     * @return {@code true}, if the move is allowed.
     */
    protected static boolean moveIsValidRook(int relativeXMovement, int relativeYMovement) {
        return (relativeXMovement == 0 && relativeYMovement != 0) || (relativeXMovement != 0 && relativeYMovement == 0);
    }

    /**
     * Checks, if a bishop would leap another character, if the move is executed.
     *
     * @return {@code true}, if a bishop would leap another piece.
     * @throws ChessException If the move is not possible.
     */
    protected static boolean leapsBishop(Move move, Chessboard board) throws ChessException {
        int subX = move.getStart().getX() < move.getEnd().getX() ? 1 : -1;
        int subY = move.getStart().getY() < move.getEnd().getY() ? 1 : -1;
        int x = move.getStart().getX() + subX;
        int y = move.getStart().getY() + subY;

        while (x != move.getEnd().getX() && y != move.getEnd().getY() && y >= 0 && y < 8 && x >= 0 && x < 8) {
            Piece piece = board.get(x, y);
            if (piece != null) {
                return false;
            }
            x += subX;
            y += subY;
        }

        return true;
    }

    /**
     * Checks, if a rook would leap another character, if the move is executed.
     *
     * @return {@code true}, if a rook would leap another piece.
     * @throws ChessException If the move is not possible.
     */
    protected static boolean leapsRook(Move move, Chessboard board) throws ChessException {
        int start;
        int end;

        if (move.getStart().getX() < move.getEnd().getX()) {
            start = move.getStart().getX() + 1;
            end = move.getEnd().getX();
        } else {
            start = move.getEnd().getX() + 1;
            end = move.getStart().getX();
        }

        for (int x = start; x < end; x++) {
            Piece piece = board.get(x, move.getStart().getY());
            if (piece != null) {
                return false;
            }
        }

        if (move.getStart().getY() < move.getEnd().getY()) {
            start = move.getStart().getY() + 1;
            end = move.getEnd().getY();
        } else {
            start = move.getEnd().getY() + 1;
            end = move.getStart().getY();
        }

        for (int y = start; y < end; y++) {
            Piece piece = board.get(move.getStart().getX(), y);
            if (piece != null) {
                return false;
            }
        }

        return true;
    }
}
