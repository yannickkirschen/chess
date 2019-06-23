package de.yannick.chess.automation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.yannick.chess.ChessException;
import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;
import de.yannick.chess.geometry.Move;
import de.yannick.chess.geometry.Position;
import de.yannick.chess.pieces.Piece;

/**
 * A {@code MoveGenerator} is part of the automation process of a chess game. It generates all possible moves for a piece.
 *
 * @author Yannick Kirschen
 * @since May 27, 2019
 *
 */
class MoveGenerator {
	private Chessboard board;

	/**
	 * Creates a new {@code MoveGenerator}.
	 *
	 * @param board
	 *            The board that should be used for generating the possible moves.
	 */
	public MoveGenerator(Chessboard board) {
		this.board = board;
	}

	/**
	 * Generates all possible moves a player can make.
	 *
	 * @param player
	 *            The active player.
	 * @return A map with the possible move as key and its piece as value.
	 */
	public Map<Move, Piece> generate(Player player) {
		Map<Move, Piece> moves = new HashMap<>();

		for (Position pos : board.iterator()) {
			Piece p = board.get(pos);
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					try {
						Move move = new Move(pos, new Position(x, y));
						p.register(move);
						if (player == p.getPlayer() && p.moveIsValid()) {
							moves.put(move, p);
						}
						p.deregister();
					} catch (ChessException e) {
					}
				}
			}
		}
		return moves;
	}

	public List<Chessboard> executeMoves(Player player) {
		List<Chessboard> newBoards = new LinkedList<>();
		Map<Move, Piece> moves = generate(player);

		for (Entry<Move, Piece> move : moves.entrySet()) {
			Chessboard newBoard = board.clone();
			newBoard.move(move.getKey());
			newBoards.add(newBoard);
		}
		return newBoards;
	}

}
