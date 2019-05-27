package de.yannick.chess.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.yannick.chess.ChessException;

/**
 * A <code>Move</code> describes an imutable move of a piece in a chess game. It has a starting position and an end position. Two moves are equal to
 * each other, when their starting position and their ending position are equal to each other.
 *
 * @author Yannick Kirschen
 * @since May 6, 2019
 *
 */
public final class Move {
	private final Position start;
	private final Position end;

	private static final List<String> chars = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

	/**
	 * Crerates a new move for a chess game.
	 *
	 * @param start
	 *            The position to start the move from.
	 * @param end
	 *            The position to end the move in.
	 */
	public Move(Position start, Position end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Creates a new move for a chess game from two positions in chess style.
	 *
	 * @param firstPosition
	 *            Start in chess style (e.g. "A2")
	 * @param secondPosition
	 *            End in chess style (e.g. "A4")
	 * @throws ChessException
	 *             If the values are out of range.
	 */
	public Move(String firstPosition, String secondPosition) throws ChessException {
		start = new Position(chars.indexOf(firstPosition.substring(0, 1)), Integer.parseInt(firstPosition.substring(1, 2)) - 1);
		end = new Position(chars.indexOf(secondPosition.substring(0, 1)), Integer.parseInt(secondPosition.substring(1, 2)) - 1);
	}

	public Position getStart() {
		return start;
	}

	public Position getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return String.format("<Move: %s -> %s>", start, end);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	/**
	 * Two moves are equal to each other, when their starting position and their ending position are equal to each other.
	 *
	 * @param obj
	 *            The move to compare to.
	 * @return true, if the two moves are equal to each other.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Move)) {
			return false;
		}
		Move other = (Move) obj;
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		return true;
	}

}
