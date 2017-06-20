import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Board {
	
	private int[][] board = new int[9][9]; 
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public Board(String values) {
		placeValues(values);
		
	}
	
	public boolean placeValues(String values){
		if(values.length()==81){
			for(int k =0; k<9; k++){
				for(int i=0; i<9; i++){
					int tempVal = (int)values.charAt(i+9*k);
					if(tempVal==46 || (tempVal>47 && tempVal<58)){
						if(tempVal!=46){
							board[k][i]=Integer.parseInt(values.substring(i+9*k, i+9*k+1));
						}else{
							board[k][i]=-1;
						}
					}else{
						return false;
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	public int[][] getBoard() {
		return board;
	}
	public void printBoard(){
		for(int k=0; k<9; k++){
			for(int i=0; i<9; i++){
				if(board[k][i]==-1){
					System.out.print(". ");
				}else{
					System.out.print(board[k][i]+" ");
				}
				if(i==2||i==5)
					System.out.print("| ");
				if((k==2||k==5)&&i==8){
					System.out.println("");
					System.out.print("------+-------+------");
				}
					
			}
			System.out.println("");
			
		}
		System.out.println("");
	}
	
	public int getPositionValue(String position){
		if(position.length()==2){
			int tempValPos = (int)position.charAt(0);
			if(tempValPos>64 && tempValPos<74 && Integer.parseInt(position.substring(1))>0 && Integer.parseInt(position.substring(1))<10){
				return board[(int)position.charAt(0)-65][Integer.parseInt(position.substring(1))-1];
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
	
	public int getPositionValue(int row, int col){
		if(row>=0 && row < 9 && col >= 0 && col <9){
			return board[row][col];
		}else{
			return -1;
		}
	}
	
	public int[] getPositionIndex2D(String position){
		if(position.length()==2){
			int tempValPos = (int)position.charAt(0);
			if(tempValPos>64 && tempValPos<74 && Integer.parseInt(position.substring(1))>0 && Integer.parseInt(position.substring(1))<10){
				return new int[] {((int)position.charAt(0)-65),(Integer.parseInt(position.substring(1))-1)};
			}else{
				return new int[] {-1,-1};
			}
		}else{
			return new int[] {-1,-1};
		}
	}
	
	public void setBoardValue(int row, int col, int value) {
		board[row][col] = value;
	}
}
