import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Utils {
	
	private Board board;
	private int[] boxValues1 = {0,1,2};
	private int[] boxValues2 = {3,4,5};
	private int[] boxValues3 = {6,7,8};
	
	//hashmap storing all the possible values that can be solutions for each empty position in sudoku
	private HashMap<int[], ArrayList<Integer>> possibleSolutionsMap = new HashMap<>();
	
	
	public Utils(Board board) {
		this.board = board;
	}
	
	public String[] getBox(String position){
		int[] index2D = board.getPositionIndex2D(position);
		int[] rowBoxValues = {-1,-1,-1}; 
		int[] colBoxValues = {-1,-1,-1};
		String[] result = new String[8];
		if(index2D[0]==-1){
			return null;
		}else{
			for (int i : boxValues1) {
				if(index2D[0]==i)
					rowBoxValues=boxValues1;
				if(index2D[1]==i)
					colBoxValues=boxValues1;
			}
			for (int i : boxValues2) {
				if(index2D[0]==i)
					rowBoxValues=boxValues2;
				if(index2D[1]==i)
					colBoxValues=boxValues2;
			}
			for (int i : boxValues3) {
				if(index2D[0]==i)
					rowBoxValues=boxValues3;
				if(index2D[1]==i)
					colBoxValues=boxValues3;
			}
			int q = 0;
			for(int k =0; k<3; k++){
				for(int i=0; i<3; i++){
						if(rowBoxValues[k]==index2D[0]) {
							if(colBoxValues[i]==index2D[1]) {
							}else {
								result[q]=rowBoxValues[k]+""+(colBoxValues[i]);
								q++;
							}
							
								
						}else {
							result[q]=rowBoxValues[k]+""+(colBoxValues[i]);
							q++;	
						}
						
					
					
				}
			}
			return result;
		}
	}
	
	public String[] getBox(int row, int col){
		int[] index2D = {row, col};
		int[] rowBoxValues = {-1,-1,-1}; 
		int[] colBoxValues = {-1,-1,-1};
		String[] result = new String[8];
		if(index2D[0]==-1){
			return null;
		}else{
			for (int i : boxValues1) {
				if(index2D[0]==i)
					rowBoxValues=boxValues1;
				if(index2D[1]==i)
					colBoxValues=boxValues1;
			}
			for (int i : boxValues2) {
				if(index2D[0]==i)
					rowBoxValues=boxValues2;
				if(index2D[1]==i)
					colBoxValues=boxValues2;
			}
			for (int i : boxValues3) {
				if(index2D[0]==i)
					rowBoxValues=boxValues3;
				if(index2D[1]==i)
					colBoxValues=boxValues3;
			}
			int q = 0;
			for(int k =0; k<3; k++){
				for(int i=0; i<3; i++){
						if(rowBoxValues[k]==index2D[0]) {
							if(colBoxValues[i]==index2D[1]) {
							}else {
								result[q]=rowBoxValues[k]+""+(colBoxValues[i]);
								q++;
							}
							
								
						}else {
							result[q]=rowBoxValues[k]+""+(colBoxValues[i]);
							q++;	
						}
						
					
					
				}
			}
			return result;
		}
	}
	
	public String[] getLines(String position) {
		int[] index2D = board.getPositionIndex2D(position);
		String[] result = new String[16];
		if(index2D[0]==-1){
			return null;
		}else{
			int q = 0;
			for(int i = 0; i<9; i++) {
				if(i!=index2D[0]) {
					result[q]=i+""+index2D[1];
					q++;
				}
			}
			for(int i = 0; i<9; i++) {
				if(i!=index2D[1]) {
					result[q]=index2D[0]+""+i;
					q++;
				}
			}
			return result;
		}
		
	}
	
	public String[] getLines(int row, int col) {
		int[] index2D = {row, col};
		String[] result = new String[16];
		if(index2D[0]==-1){
			return null;
		}else{
			int q = 0;
			for(int i = 0; i<9; i++) {
				if(i!=index2D[0]) {
					result[q]=i+""+index2D[1];
					q++;
				}
			}
			for(int i = 0; i<9; i++) {
				if(i!=index2D[1]) {
					result[q]=index2D[0]+""+i;
					q++;
				}
			}
			return result;
		}
		
	}
	
	public ArrayList<Integer> getValuesAfterElimination(String position) { //List<Integer>
		ArrayList<Integer> tempList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		String[] tempPositions = Stream.concat(Arrays.stream(getBox(position)), Arrays.stream(getLines(position)))
                .toArray(String[]::new);
		for (int i = 0; i < tempPositions.length; i++) {
			if(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]!=(-1)) {
				System.out.print(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]+" ");
				tempList.remove(new Integer(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]));
			}
				
		}
		System.out.println(tempList.toString());
		return tempList;
		
	}
	
	public ArrayList<Integer> getValuesAfterElimination(int row, int col) { //List<Integer>
		ArrayList<Integer> tempList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		String[] tempPositions = Stream.concat(Arrays.stream(getBox(row, col)), Arrays.stream(getLines(row, col)))
                .toArray(String[]::new);
		for (int i = 0; i < tempPositions.length; i++) {
			if(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]!=(-1)) {
				//System.out.print(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]+" ");
				tempList.remove(new Integer(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]));
			}
				
		}
		System.out.println("["+row+"]["+col+"]"+tempList.toString());
		return tempList;
		
	}
	
	public void createTreeOfSolutions() {
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				if(board.getPositionValue(i, j)==(-1)) {
					ArrayList<Integer> tempArray = this.getValuesAfterElimination(i, j);
					if(tempArray.size()==1) {
						board.setBoardValue(i, j, tempArray.get(0));
					}else {
						possibleSolutionsMap.put(new int[]{i,j},tempArray);	
					}
					
				}
					
			}
		}
		
		board.printBoard();
	}
	
	public void onlyChoiceSolutionBox() {
		
	}
}
