package logic.game;
import coms.*;
import coms.game.clientComThread;
import logic.Globals;

import java.io.IOException;

public class clientGame {

	// TODO: put these in constants and verify

	// 0, 0 is bottom left of board
	// use 1 for our pieces, 0 for empty, 2 for enemy
	// x y
	public int[][] board = new int[Globals.constants.width][Globals.constants.height];

	int turnNum = 0; // make move on odd nums
	private static clientGame instance = null;

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

		if (col == -1) {
			turnNum ++; // priming counter
			return;
		} else if (turnNum % 2 == 1) {
			System.out.println("Not their Move");
			return;
		}

		turnNum ++;

		if (info.getStatus() == 1) {
			System.out.println("enemy Wins");
		} else if (info.getStatus() == 2) {
			System.out.println("We tie");
		}

		for (int y = 0; y < Globals.constants.height; y++) {
			if (board[col][y] == 0) {
				board[col][y] = 2;
				break;
			}
		}

		printBoard();

	}

	public void makeMove(int col) throws IOException {

		System.out.println("jklsdfjklsdfjklsdfjklsdfjksdfjklsdfjklfa;slkdfjasld fkjakl;sdk fja;sd flkjasd; fklja sd ;lfkjas;dlfkajsdf;lkjasd f;kjCOL NUM: ");
		System.out.println(col);

		if (turnNum % 2 == 0) {
			System.out.println("Not Our Move");
			return;
		}

		turnNum ++;

		System.out.println("making Move: ");

		// read the value of user input for col
		//int col = Integer.parseInt(reader.readLine());
		int player = 0;

		// drop piece into slot
		for (int i = 0; i < Globals.constants.height; i++) {
			if (board[col][i] == 0) {
				board[col][i] = 1;
				break;
			}
		}

		int status = this.checkWin();

		clientComThread.getInstance().send(
				new CFourInfo(col, player, status)
		);

		printBoard();
	}

	public boolean checkDirection(int x, int y, int spotX, int spotY) {

		int xEnd = spotX + 3 * x;
		int yEnd = spotY + 3 * y;

		if (xEnd > Globals.constants.width || xEnd < 0 || yEnd > Globals.constants.height || yEnd < 0) {
			return false;
		}

		for (int i = 0; i < 4; i++) {

			if (board[spotX][spotY] != 1) {
				return false;
			}

			spotX += x;
			spotY += y;
		}

		return true;
	}

	/*
	* 1 = client wins
	* 2 = tie
	* 3 = no win/loss yet
	* */
	public int checkWin() {

		int count = 0;
		boolean win = false;

		for (int x = 0; x < Globals.constants.width; x ++) {
			for (int y = 0; y < Globals.constants.height; y++) {

				if (board[x][y] == 1) {

					win |= checkDirection(0, 1, x, y);
					win |= checkDirection(1, 0, x, y);
					win |= checkDirection(1,1, x, y);
					win |= checkDirection(1, -1, x,y);

					if (win == true) {
						System.out.println("we Wins");
						return 1;
					}
					count++;
				} else if ( board[x][y] == 2) {
					count++;
				}
			}
		}

		if (count == Globals.constants.height * Globals.constants.width) {
			System.out.println("we tied");
			return 2;
		} else {
			return 3;
		}
	}
}
