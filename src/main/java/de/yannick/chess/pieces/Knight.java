package de.yannick.chess.pieces;

import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;

public class Knight extends Piece {

	public Knight(Chessboard board, Player player, int id) {
		super(board, player, id);
	}

	@Override
	public String getName() {
		return "Knight";
	}

	@Override
	public boolean moveIsValid() {
		return !collides() && ((relativeXMovement == 1 && relativeYMovement == 2) || (relativeXMovement == 2 && relativeYMovement == 1));
	}
}
