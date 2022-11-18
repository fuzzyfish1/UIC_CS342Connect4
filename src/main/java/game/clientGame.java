package game;
import coms.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class clientGame {

	// TODO: put these in constants and verify
	final int width = 7;
	final int longth = 6;

	// 0, 0 is bottom left of board
	// use 1 for our pieces, 0 for empty, 2 for enemy
	// x y
	public int[][] board = new int[width][longth];

	private static clientGame instance = null;

	private clientGame() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < longth; y++) {
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
		for (int y = longth - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {

				System.out.print(board[x][y]);

			}
			System.out.println("\n");
		}
	}

	public void enemyMove(CFourInfo info) {
		if (info.getStatus() == 1) {
			System.out.println("enemy Wins");
		} else if (info.getStatus() == 2) {
			System.out.println("We tie");
		}

		int col = info.getCol();

		if (col != -1) {
			for (int y = 0; y < longth; y++) {
				if (board[col][y] == 0) {
					board[col][y] = 2;
					break;
				}
			}
		}

		printBoard();

	}

	public void makeMove() throws IOException {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println(this);
		System.out.println("making Move: ");

		// read the value of user input for col
		int col = Integer.parseInt(reader.readLine());
		int player = 0;

		// drop piece into slot
		for (int i = 0; i < longth; i++) {
			if (board[col][i] == 0) {
				board[col][i] = 1;
				break;
			}
		}

		int status = this.checkWin();

		// send
		clientComThread.getInstance().send(
				new CFourInfo( col, player, status)
		);

		// print out new board
		printBoard();
	}

	public boolean checkDirection(int x, int y, int spotX, int spotY) {

		int xEnd = spotX + 3 * x;
		int yEnd = spotY + 3 * y;

		if (xEnd > width || xEnd < 0 || yEnd > longth || yEnd < 0) {
			return false;
		}

		for (int i = 0; i < 4; i++) {

			if (board[spotX][spotY] != 1) {
				return false;
			}

			spotX += x;
			spotY += y;
		}

		System.out.println(" 4 in row at");
		System.out.println(spotX);
		System.out.println(spotY);

		System.out.println(x);
		System.out.println(y);

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

		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < longth; y++) {

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

		if (count == longth * width) {
			System.out.println("we tied");
			return 2;
		} else {
			return 3;
		}
	}

}
