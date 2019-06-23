package de.yannick.chess.automation;

import java.util.LinkedList;
import java.util.List;

import de.yannick.chess.Chessboard;

/**
 * An {@code AutomationTree} is the representation of all possible states of the chessboard with a certain depth.
 *
 * @author Yannick Kirschen
 * @since May 27, 2019
 *
 */
class AutomationTree {
	private List<AutomationTree> childrens = new LinkedList<>();

	private final Chessboard board;

	public AutomationTree(Chessboard root) {
		this.board = root;
	}

	public void addChild(AutomationTree tree) {
		childrens.add(tree);
	}

	public List<AutomationTree> getChildrens() {
		return childrens;
	}

	public Chessboard getBoard() {
		return board;
	}

	@Override
	public String toString() {
		return "AutomationTree [childrens=" + childrens + ", board=" + board + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + ((childrens == null) ? 0 : childrens.hashCode());
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
		if (!(obj instanceof AutomationTree)) {
			return false;
		}
		AutomationTree other = (AutomationTree) obj;
		if (board == null) {
			if (other.board != null) {
				return false;
			}
		} else if (!board.equals(other.board)) {
			return false;
		}
		if (childrens == null) {
			if (other.childrens != null) {
				return false;
			}
		} else if (!childrens.equals(other.childrens)) {
			return false;
		}
		return true;
	}

}
