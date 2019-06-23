package de.yannick.chess.automation;

import de.yannick.chess.geometry.Move;
import de.yannick.chess.pieces.Piece;

/**
 * A {@code GeneratedMove} is a value object that stores a piece and the move that should be executed to be successful.
 *
 * @author Yannick Kirschen
 * @since May 27, 2019
 *
 */
public final class GeneratedMove {
	private final Piece piece;
	private final Move move;

	/**
	 * Creates a new instance of {@code GeneratedMove}.
	 *
	 * @param piece
	 *            The piece that should execute the move.
	 * @param move
	 *            The move that should be executed.
	 */
	public GeneratedMove(Piece piece, Move move) {
		this.piece = piece;
		this.move = move;
	}

	public Piece getPiece() {
		return piece;
	}

	public Move getMove() {
		return move;
	}

	@Override
	public String toString() {
		return "GeneratedMove [piece=" + piece + ", move=" + move + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((move == null) ? 0 : move.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GeneratedMove)) {
			return false;
		}
		GeneratedMove other = (GeneratedMove) obj;
		if (move == null) {
			if (other.move != null) {
				return false;
			}
		} else if (!move.equals(other.move)) {
			return false;
		}
		if (piece == null) {
			if (other.piece != null) {
				return false;
			}
		} else if (!piece.equals(other.piece)) {
			return false;
		}
		return true;
	}

}
