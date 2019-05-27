package de.yannick.chess.pieces;

import de.yannick.chess.ChessException;
import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;
import de.yannick.chess.interfaces.Jumpcapable;

public class Bishop extends Piece implements Jumpcapable {

	public Bishop(Chessboard board, Player player, int id) {
		super(board, player, id);
	}

	@Override
	public String getName() {
		return "Bishop";
	}

	@Override
	public boolean moveIsValid() throws ChessException {
		return !collides() && !leaps() && RookBishopQueen.moveIsValidBishop(relativeXMovement, relativeYMovement);
	}

	@Override
	public boolean leaps() throws ChessException {
		return RookBishopQueen.leapsBishop(move, board);
	}

}
