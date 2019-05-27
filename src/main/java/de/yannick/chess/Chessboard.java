package de.yannick.chess;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.yannick.chess.geometry.Move;
import de.yannick.chess.geometry.Position;
import de.yannick.chess.pieces.Piece;

/**
 * A <code>Chessboard</code> represents a map in a chess game and holds all of its pieces.
 *
 * @author Yannick Kirschen
 * @since May 6, 2019
 *
 */
public class Chessboard implements Iterable<Position> {
	private Map<Position, Piece> board = new HashMap<>();

	/**
	 * Creates a new, empty, chessboard. Remember to add the pieces by using {@link #add(Coordinates, Piece)}.
	 */
	public Chessboard() {
	}

	/**
	 * Adds a piece to the board.
	 *
	 * @param position
	 *            Position to put the piece to.
	 * @param piece
	 *            Piece to add.
	 */
	public void add(Position position, Piece piece) {
		board.put(position, piece);
	}

	/**
	 * Removes a piece from the board.
	 *
	 * @param position
	 *            The position to remove the piece from.
	 */
	public void delete(Position position) {
		board.remove(position);
	}

	/**
	 * Looks for a piece and returns it.
	 *
	 * @param position
	 *            The position to look for a piece.
	 * @return The piece. {@code null}, if there is no piece.
	 */
	public Piece get(Position position) {
		return board.containsKey(position) ? board.get(position) : null;
	}

	/**
	 * Looks for a piece and returns it.
	 *
	 * @param x
	 *            The position on the x-axis.
	 * @param y
	 *            The position on the y-axis.
	 * @return The piece. {@code null}, if there is no piece.
	 * @throws ChessException
	 *             If the coordinates are out of bounds.
	 */
	public Piece get(int x, int y) throws ChessException {
		Position position = new Position(x, y);
		return board.containsKey(position) ? board.get(position) : null;
	}

	/**
	 * Moves a Piece from one position to another. Note, that it is not checked, if the move is allowed!
	 *
	 * @param oldPosition
	 *            Position of the piece to move.
	 * @param newPosition
	 *            Position to move. Consider, that it is not checked, if there is already a piece on the new position.
	 */
	public void move(Move move) {
		board.put(move.getEnd(), board.get(move.getStart()));
		board.remove(move.getStart());
	}

	/**
	 * Checks the existance of a piece at a particular position.
	 *
	 * @param position
	 *            The position to look for a piece.
	 * @return true, if there is a piece.
	 */
	public boolean exists(Position position) {
		return board.containsKey(position) && board.get(position) != null;
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}

	@Override
	public Iterator<Position> iterator() {
		return board.keySet().iterator();
	}

	@Override
	public String toString() {
		return String.format("<Chessboard with %s pieces>", board.size());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
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
		if (!(obj instanceof Chessboard)) {
			return false;
		}
		Chessboard other = (Chessboard) obj;
		if (board == null) {
			if (other.board != null) {
				return false;
			}
		} else if (!board.equals(other.board)) {
			return false;
		}
		return true;
	}

}
