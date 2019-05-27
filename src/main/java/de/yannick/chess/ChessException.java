package de.yannick.chess;

/**
 * Indicates an error when creating a position.
 * 
 * @author Yannick Kirschen
 * @since Apr. 16, 2019
 *
 */
public class ChessException extends Exception {
	private static final long serialVersionUID = 1L;

	public ChessException(String message) {
		super(message);
	}

}
