import java.awt.*;
import java.util.Scanner;

public class TicTac {

	// [y][x]
	static char[][] gameBoard = new char[3][3];
	static int turn = 0;
	final static int MAX_TURNS = 9;

	private static void drawBoard(Graphics g) {

		g.drawLine(100, 50, 100, 200);
		g.drawLine(150, 50, 150, 200);
		g.drawLine(50, 100, 200, 100);
		g.drawLine(50, 150, 200, 150);
		// g.drawLine(x1, y1, x2, y2);

		g.drawLine(50, 200, 50, 50); // left line
		g.drawLine(50, 50, 200, 50); // top line
		g.drawLine(200, 200, 200, 50); // right
		g.drawLine(50, 200, 200, 200); // bottom

		/*
		 * Useful for clearing board g.setColor(Color.WHITE); 
		 * g.fillRect(45, 45, 155,
		 * 155);
		 */

		// title
		g.drawString("Tic Tac Toe", 90, 20);

		// numbered rows
		g.drawString("0", 40, 80);
		g.drawString("1", 40, 130);
		g.drawString("2", 40, 180);

		// numbered columns
		g.drawString("0", 70, 45);
		g.drawString("1", 120, 45);
		g.drawString("2", 170, 45);
	}

	private static void takeTurn(Graphics g, char currPlayer) {
		Scanner scnr = new Scanner(System.in);

		drawBoard(g);

		if (currPlayer == 'X') {
			System.out.print("Player One,");
		} else {
			System.out.print("Player Two, ");
		}

		try {
			System.out.print(" enter desired row and column: ");
			int row = scnr.nextInt();
			int column = scnr.nextInt();

			if (isEmpty(row, column)) {
				gameBoard[row][column] = currPlayer;
				drawTurn(g, currPlayer, row, column);
				/*
				if (checkWin(currPlayer, row, column)) {
					announceGame(currPlayer);
				}
				*/
			} else {
				System.out.println("\nYou suck! That slot is taken. Try again.\n");
				takeTurn(g, currPlayer);
			}
		} catch (Exception ArrayIndexOutOfBoundsException) {
			System.out.println("\nThis doesn't exist! Try 0, 1, or 2, nimrod. \n");
			takeTurn(g, currPlayer);
		}
		
		

	}

	// just putting x's or o's in the right spot
	private static void drawTurn(Graphics g, char currPlayer, int row, int column) {

		int x1 = 60;
		int y1 = 60;
		int width = 30;
		int height = 30;
		int x2 = x1 + width;
		int y2 = y1 + height;

		if (row == 1) {
			y1 += 50;
			y2 += 50;
		}

		if (row == 2) {
			y1 += 100;
			y2 += 100;
		}

		if (column == 1) {
			x1 += 50;
			x2 += 50;

		}

		if (column == 2) {
			x1 += 100;
			x2 += 100;
		}

		if (currPlayer == 'X') {
			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x1 + width, y1, x2 - width, y2);
		} else {
			g.drawOval(x1, y1, width, height);
		}

	}

	// check to see if spot is empty
	private static boolean isEmpty(int row, int column) {

		if (gameBoard[row][column] == 0) {
			return true;
		} else {
			return false;
		}

	}


	//method that returns whether or not someone won  before turn hits 9
	//it will check every row each time :(
	private static boolean checkWin() {
		
		//rows and columns
		for (int i = 0; i<2; i++) {
			if (gameBoard[i][0] + gameBoard[i][1]+ gameBoard[i][2]==264 || gameBoard[i][0] + gameBoard[i][1]+ gameBoard[i][2]==237) {
				return true;
			}
			if (gameBoard[0][i] + gameBoard[1][i]+ gameBoard[2][i]==264 || gameBoard[0][i] + gameBoard[1][i]+ gameBoard[2][i]==237) {
				return true;
			} 
		}
		
		//diagonal
		if (gameBoard[0][0] + gameBoard[1][1]+ gameBoard[2][2]==264 || gameBoard[0][0] + gameBoard[1][1]+ gameBoard[2][2]==237) {
			return true;
		}
		//antidiagonal
		if (gameBoard[0][2] + gameBoard[1][1]+ gameBoard[2][0]==264 || gameBoard[0][2] + gameBoard[1][1]+ gameBoard[2][0]==237) {
			return true;
		}
		
		return false;
		
	}
	
	private static void welcomeMsg() {
		System.out.println("Welcome to Tic-Tac-Toe!"
				+ "\n\nWhen you run this program, a drawing panel box will appear. \n"
				+ "Rows and columns will be labeled from 0 - 2. \n"
				+ "Please enter your choice as whole integers with a space in between.\n\n"
				+ "Player One is always 'X', and Player Two is always 'O'.\n"
				+ "Winner is determined by getting three in a row. Have fun!\n\n  ");
	}
	
	private static void announceGame(char Player) {
		
		if (turn == MAX_TURNS) {
			System.out.println("Game was a draw. Press play to start over.");
		}
		
		else if (Player == 'X') {
			System.out.println("\nPlayer One won!");
		}else {
			System.out.println("\nPlayer Two won!");
		}
		
	}

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(250, 250);
		Graphics g = panel.getGraphics();



		char playerOne = 'X';
		char playerTwo = 'O';
		char currPlayer = ' ';
		//int turn = 0;
		
		welcomeMsg();

		while (checkWin()==false && (turn < MAX_TURNS)) {
			if (turn % 2 == 0) {
				currPlayer = playerOne;
			} else {
				currPlayer = playerTwo;
			}
			takeTurn(g, currPlayer);
			turn++;
			
		}
		
		announceGame(currPlayer);

	}

}
