package connect_four;

import java.util.Scanner;

public class GameConnectFour {
	
	/**
	 * @author Bojan Aleksic
	 * Date: 17. Aug. 2015.
	 * 
	 * (Game: connect four) Connect four is a two-player board game in which the
	 * players alternately drop colored disks into a seven-column, six-row vertically
	 * suspended grid, as shown below.
	 * 
	 * The objective of the game is to connect four same-colored disks in a row, a column,
	 * or a diagonal before your opponent can do likewise. The program prompts
	 * two players to drop a red or yellow disk alternately. The red disk is shown as "R"
	 * and yellow as a "Y". Whenever a disk is dropped, the program redisplays the board 
	 * on the console and determines the status of the game (win, draw, or continue). 
	 * Here is a sample run:
	 * 
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * ———————————————
	 * Drop a red disk at column (0–6): 0
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * |R| | | | | | |
	 * ———————————————
	 * Drop a yellow disk at column (0–6): 3
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | | | | | |
	 * |R| | |Y| | | |
	 * . . .
	 * . . .
	 * . . .
	 * Drop a yellow disk at column (0–6): 6
	 * | | | | | | | |
	 * | | | | | | | |
	 * | | | |R| | | |
	 * | | | |Y|R|Y| |
	 * | | |R|Y|Y|Y|Y|
	 * |R|Y|R|Y|R|R|R|
	 * ———————————————
	 * The yellow player won
	 */
	
	public static int counter = 0;
	
///////////////////////////////////////////////////////////////	
	
	/** Method for printing a table */
	public static void displayTable(char[][] ryTable) {
		
		for(int i = 0; i < ryTable.length; i++) {
			for(int j = 0; j < ryTable[i].length; j++) {
				System.out.print(" | "); // print vertical line
				System.out.print(ryTable[i][j]); // print R's and Y's
			}
			System.out.println(" | "); // print vertical line
		}
		/** Printing a footer with dashed line and numbers of the columns */
		System.out.println(" -----------------------------");
		System.out.println("   0   1   2   3   4   5   6");
		System.out.println();
	}
	
///////////////////////////////////////////////////////////////
	
	/** Player's "R" disk input */
	public static void playerR(char[][] ryTable, Scanner input) {
		
		/** Display moves */
		if(counter != 0) {
			System.out.println("Move: " + counter);
			System.out.println();
		}
		
		/** Prompt player "R" to fill in a red disk */
		System.out.println("Player \"R\" is on the move");
		System.out.println("Drop a red disk at column (0–6): ");
		int column = input.nextInt();
		
		counter++; // Increment counter by +1 every time player "R" is on the move
		
		/** Iterate through the loop in reverse order to fill in
		 * disks from the bottom to the top */
		for(int i = 5; i >= 0; i--) {
			/** If at this row and this column, field is not taken */
			if(ryTable[i][column] != 'R' && ryTable[i][column] != 'Y') {
				/** ...then fill in a Yellow disk */
				ryTable[i][column] = 'R';
				break;
			} 
			/** Call ifColumnFull() method to check if specified column is full or not */
			while(ifColumnFull(ryTable, column)) {
				System.out.println("This column is full. Choose another column.");
				column = input.nextInt();
				i++;   // Increment "i" by +1, so it can turn back  
				break; // its starting position in iteration (i = 5; ...)
			}
		}
		
		/** Display table */
		displayTable(ryTable);
		
	}
	
///////////////////////////////////////////////////////////////	
	 
	/** Player's "Y" disk input */
	public static void playerY(char[][] ryTable, Scanner input) {
		
		if(counter != 0) {
			System.out.println("Move: " + counter);
			System.out.println();
		}
		
		/** Prompt player "Y" to fill in a yellow disk */
		System.out.println("Player \"Y\" is on the move");
		System.out.println("Drop a yellow disk at column (0–6): ");
		int column = input.nextInt();
		
		counter++; // Increment counter by +1 every time player "Y" is on the move
		
		/** Iterate through the loop in reverse order to fill in
		 * disks from the bottom to the top */
		for(int i = 5; i >= 0; i--) {
			/** If at this row and this column, field is not taken */
			if(ryTable[i][column] != 'Y' && ryTable[i][column] != 'R') {
				/** ...then fill in a Yellow disk */
				ryTable[i][column] = 'Y'; 
				break;
			} 
			/** Call ifColumnFull() method to check if specified column is full or not */
			while(ifColumnFull(ryTable, column)) {
				System.out.println("This column is full. Choose another column.");
				column = input.nextInt();
				i++;   // Increment "i" by +1, so it can turn back  
				break; // its starting position in iteration (i = 5; ...)
			}
		}
		
		/** Display table */
		displayTable(ryTable);
		
	}
	
///////////////////////////////////////////////////////////////////	
	
	/** Method that checks whether the specified column is full or not */
	public static boolean ifColumnFull(char[][] ryTable, int column) {
		for(int i = 0; i < ryTable.length; i++) {
			/** If the last column in the specified row is already filled with disk... */
			if(ryTable[0][column] == 'R' || ryTable[0][column] == 'Y') {
				return true; // ...then return true
			}
		}
		return false; // otherwise, the last row in the column is not used yet, thus return false
	}
	
///////////////////////////////////////////////////////////////////
	
	/** Method for checking is there a four same disks in a row, vertically, horizontally, or in diagonal */
	public static boolean checkWinner(char[][] ryTable) {
		
		/** Horizontal alignments of the "R" and "Y" disks 
		 * note: limit "j" to 4, to avoid OutOfBounds Exception */
		for(int i = 0; i < ryTable.length; i++) {
			for(int j = 0; j < 4; j++) {
				/** Check for horizontal alignments of the "R" and "Y" disks */
				 if((ryTable[i][j] == 'R') && (ryTable[i][j+1] == 'R') && (ryTable[i][j+2] == 'R') && (ryTable[i][j+3] == 'R') || 
					(ryTable[i][j] == 'Y') && (ryTable[i][j+1] == 'Y') && (ryTable[i][j+2] == 'Y') && (ryTable[i][j+3] == 'Y')) {
					 return true;
				}
			}
		}
		
		/** Vertical alignments of the "R" and "Y" disks 
		 * note: limit "i" to 3, to avoid OutOfBOunds Exception */
		for(int j = 0; j < ryTable[0].length; j++) {
			for(int i = 0; i < 3; i++) {
				/** Check for vertical alignments of the "R" and "Y" disks */
				if((ryTable[i][j] == 'R') && (ryTable[i+1][j] == 'R') && (ryTable[i+2][j] == 'R') && (ryTable[i+3][j] == 'R') || 
				   (ryTable[i][j] == 'Y') && (ryTable[i+1][j] == 'Y') && (ryTable[i+2][j] == 'Y') && (ryTable[i+3][j] == 'Y')) {
					return true;
				}
			}
		}
		
		/** Diagonal alignments of the "R" and "Y" disks in increasing order
		 * note: start "i" from 3, to avoid OutOfBounds Exception when reach top row,
		 * and limit "j" to 4, to prevent exception to occur at the last columns of the table */
		for(int i = 3; i < ryTable.length; i++) {
			for(int j = 0; j < 4; j++) {
				/** Check for diagonal alignments of the "R" and "Y" disks */
				if ((ryTable[i][j] == 'R') && (ryTable[i-1][j+1] == 'R') && (ryTable[i-2][j+2] == 'R') && (ryTable[i-3][j+3] == 'R') || 
					(ryTable[i][j] == 'Y') && (ryTable[i-1][j+1] == 'Y') && (ryTable[i-2][j+2] == 'Y') && (ryTable[i-3][j+3] == 'Y')) {
					return true;
				}
			}
		}
		
		/** Diagonal alignments of the "R" and "Y" disks in decreasing order
		 * note: limit "i" to 3, to avoid OutOfBounds Exception at the bottom row */
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				/** Check for diagonal alignments of the "R" and "Y" disks */
				if ((ryTable[i][j] == 'R') && (ryTable[i+1][j+1] == 'R') && (ryTable[i+2][j+2] == 'R') && (ryTable[i+3][j+3] == 'R') || 
					(ryTable[i][j] == 'Y') && (ryTable[i+1][j+1] == 'Y') && (ryTable[i+2][j+2] == 'Y') && (ryTable[i+3][j+3] == 'Y')) {
					return true;
				}
			}
		}
		/** If there is no winner, return false */
		return false;
	}
	
///////////////////////////////////////////////////////////////////
	
	/** Counter for checking if game is tied */
	public static int getCounter() {
		return counter;
	}
	
//////////////////////////////////////////////////////////////////////////
	                         /** Main */
//////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/** Create an array that represents a table of the game */
		char[][] ryTable = new char[6][7];
		
		/** Display table */
		displayTable(ryTable);
		
		/** Initially set boolean to true */
		boolean gameContinue = true;
		
		/** Loop through while gameContinue is true */
		while(gameContinue) {
			
			/** Call player "R" */
			playerR(ryTable, input);
			
			/** Check for a winner */
			if(checkWinner(ryTable)) {
				System.out.println("Game is over. Player R won!");
				System.exit(0); // If checkWinner is true, exit the program
			}
			
			/** Call player "Y" */
			playerY(ryTable, input);
			
			/** Check for a winner */
			if(checkWinner(ryTable)) {
				System.out.println("Game is over. Player Y won!");
				System.exit(1); // If checkWinner is true, exit the program
			}
			
			/** Check if game is tied */
			if(getCounter() == 42) {
				/** If counter reach all 42 fields and run out of empty fields without a winner, game is tie */
				System.out.println("Game is tied.");
				System.exit(2); // Exit the program
			}
		}
		
		input.close();
	}

}
