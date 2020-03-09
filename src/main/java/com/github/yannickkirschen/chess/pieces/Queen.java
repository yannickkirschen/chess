package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;

public final class Queen extends Piece implements Jumps {
    public Queen(Chessboard board, Player player, int id) {
        super(board, player, id);
    }

    @Override
    public String getName() {
        return "Queen";
    }

    @Override
    public boolean moveIsValid() throws ChessException {
        return collides() && leaps() && RookBishopQueen.moveIsValidBishop(relativeXMovement, relativeYMovement) && RookBishopQueen.moveIsValidRook(
            relativeXMovement, relativeYMovement);
    }

    @Override
    public boolean leaps() throws ChessException {
        return RookBishopQueen.leapsRook(move, board) || RookBishopQueen.leapsBishop(move, board);
    }
}
