package com.github.yannickkirschen.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.yannickkirschen.chess.geometry.Move;
import com.github.yannickkirschen.chess.geometry.Position;
import com.github.yannickkirschen.chess.pieces.Bishop;
import com.github.yannickkirschen.chess.pieces.King;
import com.github.yannickkirschen.chess.pieces.Knight;
import com.github.yannickkirschen.chess.pieces.Pawn;
import com.github.yannickkirschen.chess.pieces.Piece;
import com.github.yannickkirschen.chess.pieces.Queen;
import com.github.yannickkirschen.chess.pieces.Rook;

public class Chess {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chess.class);
    private final Chessboard board = new Chessboard();
    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    protected Player activePlayer = Player.ONE;
    private String message = "";
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

    public static void main(String[] args) throws ChessException, IOException {
        new Chess().run();
    }

    /**
     * Prints the chessboard on the screen.
     *
     * @throws ChessException if the coordinates are out of range.
     */
    private void out() throws ChessException {
        LOGGER.info("                Player Two\n\n");
        LOGGER.info("  |  A |  B |  C |  D |  E |  F |  G |  H\n");
        LOGGER.info("-------------------------------------------\n");

        for (int y = 0; y < 8; y++) {
            LOGGER.info("{} |", (y + 1));

            for (int x = 0; x < 8; x++) {
                Piece p = board.get(new Position(x, y));
                LOGGER.info("{}", p != null ? p.format() : "    ");
                LOGGER.info("|");

            }
            LOGGER.info("\n-------------------------------------------\n");
        }

        LOGGER.info("\n                Player One\n");
        LOGGER.info(message);
        LOGGER.info("Player: {}\n", activePlayer == Player.ONE ? "One" : "Two");
        message = "";
    }

    /**
     * Checks if the move has beaten a piece from the enemy and if that piece is the king.
     *
     * @param beaten The piece where the move is supposed to end. May be null, if there is no piece.
     * @return {@code true}, if the king is beaten.
     */
    private boolean checkmate(Piece beaten) {
        if (beaten != null && beaten.getName().equals("King")) {
            LOGGER.info("Checkmate!");
            LOGGER.info("Congratulations, player {}!", activePlayer == Player.ONE ? "one" : "two");
            return true;
        }
        return false;
    }

    /**
     * Performs a basic check of the input.
     *
     * @param in The user input.
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
     * @param move The move to be executed.
     * @throws ChessException If the move is not possible.
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
                    message = "Move not allowed\n";
                }
                piece.deregister();
            } else {
                message = "Please move only your own pieces\n";
            }
        }
    }

    /**
     * Runs the game.
     *
     * @throws ChessException If an error occurs while creating new positions.
     * @throws IOException    If there occurs an error while reading the users input.
     */
    public void run() throws ChessException, IOException {
        while (run) {
            out();

            LOGGER.info("> ");
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
}
