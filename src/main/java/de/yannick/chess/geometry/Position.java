package de.yannick.chess.geometry;

import de.yannick.chess.ChessException;

/**
 * A position is the mathematical position a piece has on a chessboard.
 *
 * Counting begins at 0.
 *
 * @author Yannick Kirschen
 * @since Apr. 16, 2019
 *
 */
public final class Position {
	private int x;
	private int y;

	/**
	 * Creates a new position. Positions must be between 0 and 7.
	 *
	 * @param x
	 *            x-axis
	 * @param y
	 *            y-axis
	 * @throws ChessException
	 *             if the coordinates are out of range.
	 */
	public Position(int x, int y) throws ChessException {
		if (y < 0 || y > 8 || x < 0 || x > 8) {
			throw new ChessException("Position has to be between 0 and 7.");
		}

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return String.format("<Position: (%s|%s)>", this.x, this.y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		return x == other.x && y == other.y;
	}
}
