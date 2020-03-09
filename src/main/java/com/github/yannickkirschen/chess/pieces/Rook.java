package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.ChessException;
import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;

public final class Rook extends Piece implements Jumps {
    public Rook(Chessboard board, Player player, int id) {
        super(board, player, id);
    }

    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    public boolean moveIsValid() throws ChessException {
        return collides() && leaps() && RookBishopQueen.moveIsValidRook(relativeXMovement, relativeYMovement);
    }

    @Override
    public boolean leaps() throws ChessException {
        return RookBishopQueen.leapsRook(move, board);
    }
}
