package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;
import com.github.yannickkirschen.chess.geometry.Position;

public final class Pawn extends Piece implements Jumps {
    public Pawn(Chessboard board, Player player, int id) {
        super(board, player, id);
    }

    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public boolean moveIsValid() throws ChessException {
        if (relativeYMovement > 2) {
            return false;
        }

        if (relativeXMovement != 0) {
            if (relativeXMovement == 1) {
                if (board.exists(move.getEnd()) && board.get(move.getEnd()).getPlayer() == player) {
                    return false;
                }
            } else {
                return false;
            }
            return false;
        }

        if ((player == Player.ONE && move.getStart().getY() < move.getEnd().getY()) || (player == Player.TWO && move.getStart().getY() > move.getEnd()
            .getY())) {
            return false;
        }

        return collides() && leaps();
    }

    @Override
    public boolean leaps() throws ChessException {
        return relativeYMovement != 2 || !board.exists(
            new Position(move.getEnd().getX(), player == Player.ONE ? move.getEnd().getY() + 1 : move.getEnd().getY() - 1));
    }
}
