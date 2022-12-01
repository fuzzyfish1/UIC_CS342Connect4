import lib.CFourInfo;
import lib.status;
import logic.clientGame;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

	@org.junit.jupiter.api.Test
	public void t1() {
		clientGame.getInstance().turnNum = 1;

		clientGame.getInstance().enemyMove(new CFourInfo(0, 0, status.RUNNING));

		assertEquals(clientGame.getInstance().board[0][0], 0);
		assertEquals(clientGame.getInstance().errorMsg, "Not their Move");
		assertEquals(clientGame.getInstance().turnNum, 1);
	}

	@org.junit.jupiter.api.Test
	public void t2() {

		int[][] board = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0}
		};

		clientGame.getInstance().board = board;
		assertEquals(clientGame.getInstance().checkWin(), status.RUNNING);

	}

	@org.junit.jupiter.api.Test
	public void t3() {
		int[][] board = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0}
		};
		clientGame.getInstance().board = board;

		assertEquals(clientGame.getInstance().checkWin(), status.WIN);

	}

	@org.junit.jupiter.api.Test
	public void t4() {
		int[][] board = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 0}
		};
		clientGame.getInstance().board = board;

		assertEquals(clientGame.getInstance().checkWin(), status.WIN);

	}

	@org.junit.jupiter.api.Test
	public void t5() {
		int[][] board = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0},
				{0, 0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0, 0},
				{0, 1, 1, 0, 1, 0}
		};
		clientGame.getInstance().board = board;

		assertEquals(clientGame.getInstance().checkWin(), status.WIN);

	}

	@org.junit.jupiter.api.Test
	public void t6() {
		int[][] board = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 1, 0},
				{0, 1, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 0}
		};
		clientGame.getInstance().board = board;

		assertEquals(clientGame.getInstance().checkWin(), status.WIN);

	}

	@org.junit.jupiter.api.Test
	public void t7() {
		int[][] board = {
				{1, 1, 1, 2, 1, 1},
				{1, 2, 1, 2, 1, 1},
				{1, 1, 1, 2, 2, 1},
				{2, 2, 2, 1, 2, 2},
				{1, 1, 1, 2, 2, 1},
				{1, 2, 1, 2, 1, 1},
				{1, 1, 1, 2, 1, 1}
		};
		clientGame.getInstance().board = board;

		assertEquals(clientGame.getInstance().checkWin(), status.TIE);

	}

}









