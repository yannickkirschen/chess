package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;

public final class Bishop extends Piece implements Jumps {
    public Bishop(Chessboard board, Player player, int id) {
        super(board, player, id);
    }

    @Override
    public String getName() {
        return "Bishop";
    }

    @Override
    public boolean moveIsValid() throws ChessException {
        return collides() && leaps() && RookBishopQueen.moveIsValidBishop(relativeXMovement, relativeYMovement);
    }

    @Override
    public boolean leaps() throws ChessException {
        return RookBishopQueen.leapsBishop(move, board);
    }
}
