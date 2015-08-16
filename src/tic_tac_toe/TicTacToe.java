package tic_tac_toe;
import java.util.Scanner;

public class TicTacToe {
	
	/**
	 * @author Bojan Aleksic
	 * Date: 11. July 2015.
	 * 
	 * (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take turns
	 * marking an available cell in a 3 * 3 grid with their respective tokens (either
	 * X or O). When one player has placed three tokens in a horizontal, vertical, or
	 * diagonal row on the grid, the game is over and that player has won. A draw (no
	 * winner) occurs when all the cells on the grid have been filled with tokens and
	 * neither player has achieved a win. Create a program for playing tic-tac-toe.
	 * The program prompts two players to enter an X token and O token alternately.
	 * Whenever a token is entered, the program redisplays the board on the
	 * console and determines the status of the game (win, draw, or continue). Here
	 * is a sample run:
	 * 
	 * |  |  |  |
	 * ——————-——————
	 * |  |  |  |
	 * ——————-——————
	 * |  |  |  |
	 * ——————-——————
	 * Enter a row (0, 1, or 2) for player X: 1
	 * Enter a column (0, 1, or 2) for player X: 1
	 * ——————-——————
	 * |  |  |  |
	 * ——————-——————
	 * |  | X |  |
	 * ——————-——————
	 * |  |  |  |
	 * ——————-——————
	 * Enter a row (0, 1, or 2) for player O: 1
	 * Enter a column (0, 1, or 2) for player O: 2
	 * ——————-——————
	 * |   |   |   |
	 * ——————-——————
	 * |  | X | O |
	 * ——————-——————
	 * |  |   |   |
	 * 
	 *  ....
	 */
	
	/** Counter for counting fields/inputs to check if game is tie */
	public static int counter = 0;
	
//////////////////////////////////////////////////////////////////////////	
	
	/** Display table */
	public static void displayTable(char[][] xoTable) {
		for(int i = 0; i < xoTable.length; i++) {
			System.out.println(" -------------");
			for(int j = 0; j < xoTable[i].length; j++) {
				System.out.print(" | ");
				System.out.print(xoTable[i][j]);
			}
			System.out.println(" | ");
		}
		System.out.println(" -------------");
	}
	
////////////////////////////////////////////////////////////////////////////	
	
	/** Player "X" */
	public static void playerX(int[][] table, char[][] xoTable) {
		Scanner input = new Scanner(System.in);
		/** Prompt the player X to enter a field */
		System.out.println("Enter a row (0, 1, or 2) for player X: ");
		int row = input.nextInt();
		
		System.out.println("Enter a column (0, 1, or 2) for player X: ");
		int column = input.nextInt();
		
		counter++; // count player's X move
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(table[row][column] == table[i][j]) {
					/** Check if field is already used */
					while(xoTable[row][column] == 'X' || xoTable[row][column] == 'O') {
						System.out.println("Field " + table[i][j] + " is already used. Try again.");
						System.out.println("Enter a row (0, 1, or 2) for player X: ");
						row = input.nextInt();
						System.out.println("Enter a column (0, 1, or 2) for player X: ");
						column = input.nextInt();
					} 
					/** If field is not used, insert 'X' into the field */
					if(xoTable[i][j] != 'X' && xoTable[i][j] != 'O') {
						xoTable[i][j] = 'X';
					}
				}
			}
		}
		displayTable(xoTable); // Display table
	}
	
///////////////////////////////////////////////////////////////////////////
	
	/** Player "O" */
	public static void playerO(int[][] table, char[][] xoTable) {
		Scanner input = new Scanner(System.in);
		/** Prompt the player O to enter a field */
		System.out.println("Enter a row (0, 1, or 2) for player O: ");
		int row = input.nextInt();
		
		System.out.println("Enter a column (0, 1, or 2) for player O: ");
		int column = input.nextInt();
		
		counter++; // count player's O move
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(table[row][column] == table[i][j]) {
					/** Check if field is already used */
					while(xoTable[row][column] == 'X' || xoTable[row][column] == 'O') {
						System.out.println("Field " + table[i][j] + " is already used. Try again.");
						System.out.println("Enter a row (0, 1, or 2) for player O: ");
						row = input.nextInt();
						System.out.println("Enter a column (0, 1, or 2) for player O: ");
						column = input.nextInt();
					} 
					/** If field is not used, insert 'O' into the field */
					if(xoTable[i][j] != 'O' && xoTable[i][j] != 'X') {
						xoTable[i][j] = 'O';
					}
				}
			}
		}
		displayTable(xoTable); // Display table
	}

///////////////////////////////////////////////////////////////////////////	
	
	/** Check winner */
	public static boolean isWin(char[][] xoTable) {
		boolean win = false;
		for(int i = 0; i < xoTable.length; i++) {
			for(int j = 0; j < xoTable.length; j++) {
				/** Check if 3 "X" characters are in a row horizontally */
				if(xoTable[i][0] == 'X' && xoTable[i][1] == 'X' && xoTable[i][2] == 'X' || 
					/** Check if 3 "X" characters are in a row vertically */
				   xoTable[0][j] == 'X' && xoTable[1][j] == 'X' && xoTable[2][j] == 'X' ||
				   /** Check if 3 "X" characters are in a row diagonally */
				   xoTable[0][0] == 'X' && xoTable[1][1] == 'X' && xoTable[2][2] == 'X' ||
				   xoTable[0][2] == 'X' && xoTable[1][1] == 'X' && xoTable[2][0] == 'X') {
				   win = true;
				/** Check same for the "O" characters */
				} else if(xoTable[i][0] == 'O' && xoTable[i][1] == 'O' && xoTable[i][2] == 'O' ||
						  xoTable[0][j] == 'O' && xoTable[1][j] == 'O' && xoTable[2][j] == 'O' || 
						  xoTable[0][0] == 'O' && xoTable[1][1] == 'O' && xoTable[2][2] == 'O' ||
						  xoTable[0][2] == 'O' && xoTable[1][1] == 'O' && xoTable[2][0] == 'O') {
					win = true;
				}
			}
		}
		return win;
	}
	
///////////////////////////////////////////////////////////////////////////
	
	/** Counter for checking if game is tied */
	public static int getCounter() {
		return counter;
	}
	
///////////////////////////////////////////////////////////////////////////	
	                         /** Main */
///////////////////////////////////////////////////////////////////////////		

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/** Create table with numbers */
		int[][] table = {
				{1, 2, 3}, 
			    {4, 5, 6}, 
			    {7, 8, 9}
				        };
		
		/** Create table for characters */
		char[][] xoTable = new char[3][3];
		
		boolean gameOver = false;
		
		while(gameOver == false) {
			
			/** Call player X */
			playerX(table, xoTable);
			
			/** Check if player X is a winner */
			if(isWin(xoTable)) {
				System.out.println("Player \"X\" has won!");
				System.exit(0);
			}
			
			/** Check if game is tie */
			if(getCounter() == 9) {
				System.out.println("Game is tied.");
				System.exit(1);
			}
			
			/** Call player O */
			playerO(table, xoTable);
			
			/** Check if player O is a winner */
			if(isWin(xoTable)) {
				System.out.println("Player \"O\" has won!");
				System.exit(2);
			}
		}
		
	}
}

