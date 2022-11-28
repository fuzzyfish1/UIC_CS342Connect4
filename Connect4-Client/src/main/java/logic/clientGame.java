package logic;

import commonCode.CFourInfo;
import commonCode.status;
import coms.clientComThread;

import java.io.IOException;

public class clientGame {

	// TODO: put these in constants and verify

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

		int col = info.getCol();

		if (info.getStatus() == status.START) {
			errorMsg = "First Move, counter Primed";
			System.out.println("First Move, counter Primed");
			turnNum++; // priming counter
			return;
		} else if (turnNum % 2 == 1) {
			errorMsg = "Not their Move";
			System.out.println("Not their Move");
			return;
		}

		turnNum++;

		if (info.getStatus() == status.WIN) {
			System.out.println("enemy Wins");
		} else if (info.getStatus() == status.TIE) {
			System.out.println("We tie");
		}

		errorMsg = "No Err";

		for (int y = 0; y < Globals.constants.height; y++) {
			if (board[col][y] == 0) {
				board[col][y] = 2;
				break;
			}
		}

		printBoard();

	}

	public void makeMove(int col) {

		System.out.println(col);

		if (turnNum % 2 == 0) {
			errorMsg = "Not Our Move";
			System.out.println("Not Our Move");
			return;
		}
		System.out.println("making Move: ");

		int player = 0;

		// drop piece into slot
		for (int i = 0;; i++) {
			if (i == Globals.constants.height) {
				errorMsg = "INVALID MOVE";
				System.out.println("INVALID MOVE");
				return;
			} else if (board[col][i] == 0) {
				board[col][i] = 1;
				break;
			}
		}

		errorMsg = "No Err";
		turnNum++;

		status status = this.checkWin();

		clientComThread.getInstance().send(
				new CFourInfo(col, player, status)
		);

		printBoard();
	}

	public boolean checkDirection(int x, int y, int spotX, int spotY) {

		int xEnd = spotX + 3 * x;
		int yEnd = spotY + 3 * y;

		if (xEnd >= Globals.constants.width || xEnd < 0 || yEnd >= Globals.constants.height || yEnd < 0) {
			return false;
		}

		try {
			for (int i = 0; i < 4; i++) {

				if (board[spotX][spotY] != 1) {
					return false;
				}

				spotX += x;
				spotY += y;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(spotX);
			System.out.println(spotY);
		}

		return true;
	}

	public status checkWin() {

		int count = 0;
		boolean win = false;

		for (int x = 0; x < Globals.constants.width; x++) {
			for (int y = 0; y < Globals.constants.height; y++) {

				if (board[x][y] == 1) {

					win |= checkDirection(0, 1, x, y);
					win |= checkDirection(1, 0, x, y);
					win |= checkDirection(1, 1, x, y);
					win |= checkDirection(1, -1, x, y);

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
			return status.TIE;
		} else {
			return status.RUNNING;
		}
	}
}
