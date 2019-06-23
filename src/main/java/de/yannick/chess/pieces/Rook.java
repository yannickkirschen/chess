package de.yannick.chess.pieces;

import de.yannick.chess.ChessException;
import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;
import de.yannick.chess.interfaces.Jumps;

public class Rook extends Piece implements Jumps {

	public Rook(Chessboard board, Player player, int id) {
		super(board, player, id);
	}

	@Override
	public String getName() {
		return "Rook";
	}

	@Override
	public boolean moveIsValid() throws ChessException {
		return !collides() && !leaps() && RookBishopQueen.moveIsValidRook(relativeXMovement, relativeYMovement);
	}

	@Override
	public boolean leaps() throws ChessException {
		return RookBishopQueen.leapsRook(move, board);
	}

}
