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
		tree = new AutomationTree(board);
		for (int i = 0; i < depth; i++) {
			System.out.println(i);
			MoveGenerator generator = new MoveGenerator(board);
			for (Chessboard b : generator.executeMoves(player)) {
				tree.addChild(new AutomationTree(b));
			}
		}
		return new GeneratedMove(null, null);
	}
}
