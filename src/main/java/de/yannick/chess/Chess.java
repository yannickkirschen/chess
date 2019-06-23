package de.yannick.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.yannick.chess.geometry.Move;
import de.yannick.chess.geometry.Position;
import de.yannick.chess.pieces.Bishop;
import de.yannick.chess.pieces.King;
import de.yannick.chess.pieces.Knight;
import de.yannick.chess.pieces.Pawn;
import de.yannick.chess.pieces.Piece;
import de.yannick.chess.pieces.Queen;
import de.yannick.chess.pieces.Rook;

public class Chess {
	private Chessboard board = new Chessboard();
	protected Player activePlayer = Player.ONE;
	private String message = "";

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private boolean run = true;

	public Chess() throws ChessException {
		board.add(new Position(0, 7), new Rook(board, Player.ONE, 1));
		board.add(new Position(1, 7), new Knight(board, Player.ONE, 2));
		board.add(new Position(2, 7), new Bishop(board, Player.ONE, 3));
		board.add(new Position(3, 7), new Queen(board, Player.ONE, 4));
		board.add(new Position(4, 7), new King(board, Player.ONE, 5));
		board.add(new Position(5, 7), new Bishop(board, Player.ONE, 6));
		board.add(new Position(6, 7), new Knight(board, Player.ONE, 7));
		board.add(new Position(7, 7), new Rook(board, Player.ONE, 8));

		board.add(new Position(0, 6), new Pawn(board, Player.ONE, 9));
		board.add(new Position(1, 6), new Pawn(board, Player.ONE, 10));
		board.add(new Position(2, 6), new Pawn(board, Player.ONE, 11));
		board.add(new Position(3, 6), new Pawn(board, Player.ONE, 12));
		board.add(new Position(4, 6), new Pawn(board, Player.ONE, 13));
		board.add(new Position(5, 6), new Pawn(board, Player.ONE, 14));
		board.add(new Position(6, 6), new Pawn(board, Player.ONE, 15));
		board.add(new Position(7, 6), new Pawn(board, Player.ONE, 16));

		board.add(new Position(0, 1), new Pawn(board, Player.TWO, 17));
		board.add(new Position(1, 1), new Pawn(board, Player.TWO, 18));
		board.add(new Position(2, 1), new Pawn(board, Player.TWO, 19));
		board.add(new Position(3, 1), new Pawn(board, Player.TWO, 20));
		board.add(new Position(4, 1), new Pawn(board, Player.TWO, 21));
		board.add(new Position(5, 1), new Pawn(board, Player.TWO, 22));
		board.add(new Position(6, 1), new Pawn(board, Player.TWO, 23));
		board.add(new Position(7, 1), new Pawn(board, Player.TWO, 24));

		board.add(new Position(0, 0), new Rook(board, Player.TWO, 25));
		board.add(new Position(1, 0), new Knight(board, Player.TWO, 26));
		board.add(new Position(2, 0), new Bishop(board, Player.TWO, 27));
		board.add(new Position(3, 0), new Queen(board, Player.TWO, 28));
		board.add(new Position(4, 0), new King(board, Player.TWO, 29));
		board.add(new Position(5, 0), new Bishop(board, Player.TWO, 30));
		board.add(new Position(6, 0), new Knight(board, Player.TWO, 31));
		board.add(new Position(7, 0), new Rook(board, Player.TWO, 32));
	}

	/**
	 * Prints the chessboard on the screen.
	 *
	 * @throws ChessException
	 */
	private void out() throws ChessException {
		System.out.print("                Player Two\n\n");
		System.out.print("  |  A |  B |  C |  D |  E |  F |  G |  H\n");
		System.out.print("-------------------------------------------\n");

		for (int y = 0; y < 8; y++) {
			System.out.print((y + 1) + " |");

			for (int x = 0; x < 8; x++) {
				Piece p = board.get(new Position(x, y));
				System.out.print(p != null ? p.format() : "    ");
				System.out.print("|");

			}
			System.out.print("\n-------------------------------------------\n");
		}

		System.out.println("\n                Player One\n");
		System.out.println(message);
		System.out.println(String.format("Player: %s%n", activePlayer == Player.ONE ? "One" : "Two"));
		message = "";
	}

	/**
	 * Checks if the move has beaten a piece from the enemy and if that piece is the king.
	 *
	 * @param beaten
	 *            The piece where the move is supposed to end. May be null, if there is no piece.
	 * @return {@code true}, if the king is beaten.
	 */
	private boolean checkmate(Piece beaten) {
		if (beaten != null && beaten.getName().equals("King")) {
			System.out.println("Checkmate!");
			System.out.println(String.format("Congratulations, player %s!", activePlayer == Player.ONE ? "one" : "two"));
			return true;
		}
		return false;
	}

	/**
	 * Performs a bsic qeck of the input.
	 *
	 * @param in
	 *            The user input.
	 */
	private void basicCheck(String in) {
		if (in.equals("")) {
			message = "Enter a command";
		} else if (in.equals("q")) {
			run = false;
		}
	}

	/**
	 * Performs the actual move.
	 *
	 * @param move
	 *            The move to be executed.
	 * @throws ChessException
	 *             If the move is not possible.
	 */
	private void move(Move move) throws ChessException {
		Piece piece = board.get(move.getStart());
		if (piece != null) {
			if (piece.getPlayer() == activePlayer) {
				piece.register(move);
				if (piece.moveIsValid()) {
					Piece beaten = board.get(move.getEnd());
					activePlayer = activePlayer == Player.TWO ? Player.ONE : Player.TWO;
					board.move(move);
					run = !checkmate(beaten);
				} else {
					message = "Move not allowed";
				}
				piece.deregister();
			} else {
				message = "Please move only your own pieces";
			}
		}
	}

	/**
	 * Runs the game.
	 *
	 * @throws ChessException
	 *             If an error occures while creating new positions.
	 * @throws IOException
	 *             If there occures an error while reading the users input.
	 */
	public void run() throws ChessException, IOException {
		// AutomationProcess auto = new AutomationProcess(board);

		while (run) {
			System.out.print("\033[H\033[2J");
			out();

			// GeneratedMove genMove = auto.generateMove(activePlayer);
			// System.out.println("Recomendation: " + genMove.toString());

			System.out.print("> ");
			String in = input.readLine();
			basicCheck(in);

			String[] coords = in.split(" ");
			if (coords.length == 2) {
				try {
					move(new Move(coords[0], coords[1]));

				} catch (ChessException e) {
					message = "The position does not exist.";
				}
			} else {
				message = "Enter the piece you want to move and its destination";
			}

		}
	}

	public static void main(String[] args) throws ChessException, IOException {
		new Chess().run();
	}
}
