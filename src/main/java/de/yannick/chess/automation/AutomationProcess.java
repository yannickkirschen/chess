package de.yannick.chess.automation;

import de.yannick.chess.Chessboard;
import de.yannick.chess.Player;

/**
 * The {@code AutomationProcess} makes the decision which move to make next.
 *
 * @author Yannick Kirschen
 * @since May 27, 2019
 *
 */
public class AutomationProcess {
	private AutomationTree tree;
	private Chessboard board;

	public AutomationProcess(Chessboard board) {
		this.board = board;
		tree = new AutomationTree(this.board);
	}

	public GeneratedMove generateMove(Player player) {
		return generateMove(player, 20);
	}

	public GeneratedMove generateMove(Player player, int depth) {
		// MoveGenerator generator = new MoveGenerator(board);
		// for (int z = 0; z < depth; z++) {
		// for (Move move : generator.generate(player).keySet()) {
		// Chessboard newBoard = board.clone();
		// newBoard.move(move);
		// tree.addChild(new AutomationTree(newBoard));
		//
		// for (AutomationTree tree : tree.getChildrens()) {
		// Chessboard newoard = board.clone();
		// newBoard.move(move);
		// tree.addChild(new AutomationTree(newoard));
		// }
		//
		// player = player == Player.ONE ? Player.TWO : Player.ONE;
		// }
		// }
		//
		// System.out.println(tree);

		return new GeneratedMove(null, null);
	}

}
