package com.github.yannickkirschen.chess.pieces;

import com.github.yannickkirschen.chess.Chessboard;
import com.github.yannickkirschen.chess.Player;

public final class King extends Piece {
    public King(Chessboard board, Player player, int id) {
        super(board, player, id);
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    public boolean moveIsValid() {
        return collides() && ((relativeXMovement == 1 && relativeYMovement == 0) || (relativeXMovement == 1 && relativeYMovement == 1) || (
            relativeXMovement == 0 && relativeYMovement == 1));
    }
}
