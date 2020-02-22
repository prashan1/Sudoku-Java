import java.util.*;
class Sudoku{
	public static boolean bool = true;
	public static  class EmptyCoordinates{
		int i = 0 ;
		int j = 0 ;
		EmptyCoordinates( int i , int j ){
			this.i = i ;
			this.j = j ;
		}
	}
	public static void main(String[] ag)throws InterruptedException{
		int[][] sudoBoard = {{5,3,0,0,7,0,0,0,0},
							 {6,0,0,1,9,5,0,0,0},
							 {0,9,8,0,0,0,0,6,0},
							 {8,0,0,0,6,0,0,0,3},
							 {4,0,0,8,0,3,0,0,1},
							 {7,0,0,0,2,0,0,0,6},
							 {0,6,0,0,0,0,2,8,0},
							 {0,0,0,4,1,9,0,0,5},
							 {0,0,0,0,8,0,0,7,0}} ;
	    List< EmptyCoordinates > emptyPlaces = new ArrayList<>();			//using List for tracing back to previous position while
																			//  Backtracking
		for(int i = 0 ; i < sudoBoard.length ; i++ )
			for(int j = 0 ; j < sudoBoard[ i ].length ; j++)
				if( sudoBoard[ i ][ j ] == 0 )
					emptyPlaces.add( new EmptyCoordinates( i , j ) );		//All the empty 0 places
				
		fillSudoBoard( sudoBoard , emptyPlaces , 0 ) ;
	}
	public static void fillSudoBoard(int[][] sudoBoard , List< EmptyCoordinates > emptyPlaces , int indexOFList )throws InterruptedException{
		
	if( indexOFList == emptyPlaces.size() ){                               //If all the 0 places are filled
		printOurBoard(sudoBoard);
			return ;
		}
		for(int element = 1 ; element < 10 ; element++ ){
			
			 int i = emptyPlaces.get( indexOFList ).i;
			 int j = emptyPlaces.get( indexOFList ).j;
			 
			if( !isValidPosition(sudoBoard , i , j , element) ) continue;
			
			sudoBoard[ i ][ j ] = element;
			fillSudoBoard(sudoBoard , emptyPlaces , indexOFList + 1 );
			sudoBoard[ i ][ j ] = 0 ;
		}
		
	}
	public static boolean isValidPosition(int[][] sudoBoard , int i , int j ,int element ){
		int topX = 0 ;
		int topY = 0 ; 
		if( i >= 0 && i <= 2 ){											//For limiting our search with a given square
			topX = 0 ;
		}
		else if(i >= 3 && i <= 5){
			topX = 3;
		}
		else
			topX = 6;
		
		if( j >= 0 && j <= 2 ){
			topY = 0 ;
		}
		else if( j >= 3 && j <= 5){
			topY = 3;
		}
		else
			topY = 6;
		
		return checkDiagonally(sudoBoard , i , j , element) && checkInSquare( sudoBoard ,topX , topY , element ) ;
	}
	public static boolean checkDiagonally(int[][] sudoBoard , int i , int j ,int element ){
		//check Vertically
		for(int vertical = 0 ; vertical < sudoBoard.length ; vertical++ )
			if( sudoBoard[ vertical ][ j ] == element )
				return false;
		
		//check Horizontally 
		for(int horizan = 0 ; horizan < sudoBoard[ i ].length ; horizan++ )
			if( sudoBoard[ i ][ horizan ] == element )
				return false;
		
		return true;
	}
		
		public static boolean checkInSquare(int[][] sudoBoard , int i , int j , int element ){
		for(int x = i ; x < 3 + i ; x++ )
			for(int y = j ; y < 3 + j ;  y++ )
				if( sudoBoard[ x ][ y ] == element )
					return false;
		return true;
		
	}
	public static void printOurBoard(int[][] sudoBoard ){
		for(int[] l : sudoBoard ){
			for(int c : l )
				System.out.print( c +" " );
		    System.out.println();
		}
		System.out.println();
	}
		
}