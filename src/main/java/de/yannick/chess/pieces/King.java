package de.yannick.chess.pieces;

import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;

public class King extends Piece {

	public King(Chessboard board, Player player, int id) {
		super(board, player, id);
	}

	@Override
	public String getName() {
		return "King";
	}

	@Override
	public boolean moveIsValid() {
		return !collides() && ((relativeXMovement == 1 && relativeYMovement == 0) || (relativeXMovement == 1 && relativeYMovement == 1)
				|| (relativeXMovement == 0 && relativeYMovement == 1));
	}
}
