package logic;

import commonCode.CFourInfo;
import commonCode.status;
import coms.clientComThread;

public class clientGame {

	private static clientGame instance = null;
	// 0, 0 is bottom left of board
	// use 1 for our pieces, 0 for empty, 2 for enemy
	// x y
	public int[][] board = new int[Globals.constants.width][Globals.constants.height];
	public int turnNum = 0; // make move on odd nums

	private clientGame() {
		for (int x = 0; x < Globals.constants.width; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = 0;
			}
		}
	}

	public status currStatus = status.START;

	public static clientGame getInstance() {
		if (instance == null) {
			instance = new clientGame();
		}

		return instance;
	}

	public String errorMsg = "";

	public void printBoard() {
		// print out new board
		for (int y = Globals.constants.height - 1; y >= 0; y--) {
			for (int x = 0; x < Globals.constants.width; x++) {

				System.out.print(board[x][y]);

			}
			System.out.println("\n");
		}
	}

	public void enemyMove(CFourInfo info) {

		System.out.println("TUIRN NUM");
		System.out.println(turnNum);

		int col = info.getCol();

		if (info.getStatus() == status.START) {
			errorMsg = "";
			//System.out.println("First Move, counter Primed");
			turnNum++; // priming counter
			return;
		} else if (turnNum % 2 == 1) {
			errorMsg = "Not their Move";
			//System.out.println("Not their Move");
			return;
		}

		turnNum++;

		for (int y = 0; y < Globals.constants.height; y++) {
			if (board[col][y] == 0) {
				board[col][y] = 2;
				break;
			}
		}

		if (info.getStatus() == status.WIN) {
			this.currStatus = status.LOSE;
			errorMsg = "ENEMY WIN";

			for (int x = 0; x < Globals.constants.width; x++) {
				for (int y = 0; y < Globals.constants.height; y++) {
					checkDirection(0, 1, x, y, 2);
					checkDirection(1, 0, x, y, 2);
					checkDirection(1, 1, x, y, 2);
					checkDirection(1, -1, x, y, 2);
				}
			}

		} else if (info.getStatus() == status.TIE) {
			this.currStatus = status.TIE;
			errorMsg = "TIE";
		} else {
			errorMsg = "";
		}

	}

	public void makeMove(int col) {

		//System.out.println(col);

		if (turnNum % 2 == 0) {
			errorMsg = "Not your Move";
			return;
		}

		int player = 0;

		// drop piece into slot
		for (int i = 0;; i++) {
			if (i == Globals.constants.height) {
				errorMsg = "you moved to column: "+ col + ". This is NOT a valid move. You pick again.";
				return;
			} else if (board[col][i] == 0) {
				board[col][i] = 1;
				break;
			}
		}

		errorMsg = "";
		turnNum++;

		this.currStatus = this.checkWin();

		if (this.currStatus == commonCode.status.WIN) {
			errorMsg = "YOU WINNER WINNER CHIGGEN DINNER";
		} else if (this.currStatus == commonCode.status.TIE) {
			errorMsg = "you tied with the enemy";
		}

		clientComThread.getInstance().send(
				new CFourInfo(col, player, this.currStatus)
		);

		//printBoard();
	}

	public boolean checkDirection(int x, int y, int spotX, int spotY, int value) {

		int xEnd = spotX + 3 * x;
		int yEnd = spotY + 3 * y;

		int xCurr = spotX, yCurr = spotY;

		if (xEnd >= Globals.constants.width || xEnd < 0 || yEnd >= Globals.constants.height || yEnd < 0) {
			return false;
		}

		try {
			for (int i = 0; i < 4; i++) {

				if (board[xCurr][yCurr] != value) {
					return false;
				}

				xCurr += x;
				yCurr += y;
			}

			xCurr = spotX;
			yCurr = spotY;

			for (int i = 0; i < 4; i++) {

				board[xCurr][yCurr] += 2;

				xCurr += x;
				yCurr += y;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public status checkWin() {

		int count = 0;
		boolean win = false;

		for (int x = 0; x < Globals.constants.width; x++) {
			for (int y = 0; y < Globals.constants.height; y++) {

				if (board[x][y] == 1) {

					win |= checkDirection(0, 1, x, y, 1);
					win |= checkDirection(1, 0, x, y, 1);
					win |= checkDirection(1, 1, x, y, 1);
					win |= checkDirection(1, -1, x, y, 1);

					if (win) {
						System.out.println("we Wins");
						return status.WIN;
					}
					count++;
				} else if (board[x][y] == 2) {
					count++;
				}
			}
		}

		if (count == Globals.constants.height * Globals.constants.width) {
			System.out.println("we tied");
			this.currStatus = status.TIE;
			return status.TIE;
		} else {
			this.currStatus = status.RUNNING;
			return status.RUNNING;
		}
	}
}
