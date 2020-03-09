package com.github.yannickkirschen.chess;

/**
 * Indicates an error when creating a position.
 *
 * @author Yannick Kirschen
 * @since Apr. 16, 2019
 */
public class ChessException extends Exception {
    public ChessException(String message) {
        super(message);
    }
}
