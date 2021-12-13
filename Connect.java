import java.util.Scanner;

public class Connect{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n,m;

		System.out.println("Enter Number of columns:"); //Default 7 columns
		n=sc.nextInt();

		System.out.println("Enter Number of rows:"); //Default 6 rows
		m=sc.nextInt();

		char[][] matrix = new char[n][m];
		
		//Array Intialization 
		
		for (int row = 0; row < matrix.length; row++){
			for (int col = 0; col < matrix[0].length; col++){
				matrix[row][col] = ' ';
			}
		}
		

		int turn = 1;
		char player = 'r';
		boolean winner = false;		
		int limit = n*m;

		//start playing the game

		while (winner == false && turn <= limit){  

			boolean correctplay;
			int play;
			do {
				display(matrix);
				
				System.out.print("Player " + player + ", choose a column for your move: ");
				play = sc.nextInt();

				//if play is negative game stops executing
				if(play < 0)
					System.out.println("Error !! Enter positive number");
				
				//Check if the play is valid

				correctplay = validate(play,matrix);
				
			}while (correctplay == false);
			
			//Input the player name in the the column entered by the player

			for (int row = matrix.length-1; row >= 0; row--){
				if(matrix[row][play] == ' '){
					matrix[row][play] = player;
					break;
				}
			}
			
			//Checking who is the winner is four consecutive plays are made of the same player.

			winner = isWinner(player,matrix);
			
			//switch players
			if (player == 'r'){
				player = 'y';
			}else{
				player = 'r';
			}
			
			turn++;			
		}
		display(matrix);
		
		if (winner){
			if (player=='r'){
				System.out.println("Yellow won");
			}else{
				System.out.println("Red won");
			}
		}else{
			System.out.println("Tie game");
		}
		
	}
	
	public static void display(char[][] matrix){
	
		for(int i=0;i<=matrix.length;i++){
			System.out.print(i+" ");
		}

		System.out.println();
		System.out.println("---------------");

		for (int row = 0; row < matrix.length; row++){
			System.out.print("|");
			for (int col = 0; col < matrix[0].length; col++){
				System.out.print(matrix[row][col]);
				System.out.print("|");
			}
			System.out.println();
			System.out.println("---------------");
		}
		for(int i=0;i<=matrix.length;i++){
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static boolean validate(int column, char[][] matrix){
		//validate column?
		if (column < 0 || column > matrix[0].length){
			return false;
		}
		
		//full column?
		if (matrix[0][column] != ' '){
			return false;
		}
		
		return true;
	}
	
	public static boolean isWinner(char player, char[][] matrix){
		//checking  across
		for(int row = 0; row<matrix.length; row++){
			for (int col = 0;col < matrix[0].length - 3;col++){
				if (matrix[row][col] == player   && 
					matrix[row][col+1] == player &&
					matrix[row][col+2] == player &&
					matrix[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < matrix.length - 3; row++){
			for(int col = 0; col < matrix[0].length; col++){
				if (matrix[row][col] == player   && 
					matrix[row+1][col] == player &&
					matrix[row+2][col] == player &&
					matrix[row+3][col] == player){
					return true;
				}
			}
		}
		//checking upward diagonal
		for(int row = 3; row < matrix.length; row++){
			for(int col = 0; col < matrix[0].length - 3; col++){
				if (matrix[row][col] == player   && 
					matrix[row-1][col+1] == player &&
					matrix[row-2][col+2] == player &&
					matrix[row-3][col+3] == player){
					return true;
				}
			}
		}
		//checking downward diagonal
		for(int row = 0; row < matrix.length - 3; row++){
			for(int col = 0; col < matrix[0].length - 3; col++){
				if (matrix[row][col] == player   && 
					matrix[row+1][col+1] == player &&
					matrix[row+2][col+2] == player &&
					matrix[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
}