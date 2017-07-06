
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Utils {
	
	private Board board;
	private int[] boxValues1 = {0,1,2};
	private int[] boxValues2 = {3,4,5};
	private int[] boxValues3 = {6,7,8};
	private int currentUnsolved = 81,afterUnsolved=81;
	private int counter = 0;
	
	//hashmap storing all the possible values that can be solutions for each empty position in sudoku
	private Map<String, ArrayList<Integer>> possibleSolutionsMap = new HashMap<>();
	
	
	public Utils(Board board) {
		this.board = board;
	}
	
	public Map<String, ArrayList<Integer>> getPossibleSolutionsMap(){
		return possibleSolutionsMap;
	}
	
	public void setPossibleSolutionsMap(Map<String, ArrayList<Integer>> possibleSolutionsMap) {
		this.possibleSolutionsMap = possibleSolutionsMap;
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
				//System.out.print(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]+" ");
				tempList.remove(new Integer(board.getBoard()[Integer.parseInt(tempPositions[i].substring(0, 1))][Integer.parseInt(tempPositions[i].substring(1))]));
			}
				
		}
		//System.out.println(tempList.toString());
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

		return tempList;
		
	}
	
	public void createTreeOfSolutions() {
		System.out.println("EMPTY pos before: "+board.getEmptyPositions());
		possibleSolutionsMap = new HashMap<>();
		
		ArrayList<Integer> tempArray = new ArrayList<>();
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				if(board.getPositionValue(i, j)==(-1)) {
					tempArray = this.getValuesAfterElimination(i, j);
					System.out.println("["+i+"]["+j+"]"+tempArray.toString());
					if(tempArray.size()==1) {
						board.setBoardValue(i, j, tempArray.get(0));
						board.reduceEmptyPositionCounter();
					}else {
						possibleSolutionsMap.put(i+""+j+"",tempArray);
						//System.out.println("["+i+"]["+j+"]"+possibleSolutionsMap.get(i+""+j));
					}
					
				}
					
			}
		}
		System.out.println("POSSIBLE SOL SIZE: "+possibleSolutionsMap.size());
		System.out.println("EMPTY pos after: "+board.getEmptyPositions());
		
	}
	
	public void onlyChoiceSolutionBox(String position) {
		
		//System.out.println(possibleSolutionsMap.size());
		if(possibleSolutionsMap.size()>0) {
			String[] tempBox = getBox(position);
			
			ArrayList<ArrayList<Integer>> tempPossibleValues = new ArrayList<>();
			ArrayList<int[]> tempValPosIndex = new ArrayList<>();
			
			int[] index2D = board.getPositionIndex2D(position);
			if(board.getPositionValue(index2D[0],index2D[1])==(-1)) {
				ArrayList<Integer> tempPositionSolutions = possibleSolutionsMap.get(index2D[0]+""+index2D[1]);
				tempPossibleValues.add(tempPositionSolutions);
				tempValPosIndex.add(index2D);
			}
			for(int i =0; i<tempBox.length; i++) {
				index2D = new int[]{Integer.parseInt(tempBox[i].substring(0, 1)),Integer.parseInt(tempBox[i].substring(1))};
				
				if(board.getPositionValue(index2D[0],index2D[1])==(-1)) {
					ArrayList<Integer> tempPositionSolutions = possibleSolutionsMap.get(index2D[0]+""+index2D[1]);
					
					tempPossibleValues.add(tempPositionSolutions);
					tempValPosIndex.add(index2D);
				}
			}
			
			
			int[] repeateValuesCountList = new int[10];
			
			for(int i=0; i<tempPossibleValues.size(); i++) {
				for(int val : tempPossibleValues.get(i)) {
					repeateValuesCountList[val]++;
				}
				
			}
			
			//System.out.println("");
			for(int i=0; i<repeateValuesCountList.length; i++) {
				if(repeateValuesCountList[i]==1) {
					for(int j=0; j<tempPossibleValues.size(); j++) {
						for(int val : tempPossibleValues.get(j)) {
							//System.out.println("VAL: "+val+" repe "+i);
							if(val==i) {
								board.setBoardValue(tempValPosIndex.get(j)[0], tempValPosIndex.get(j)[1], i);
								repeateValuesCountList[i]=0;
							}
						}
						
					}
				}
			}
			//createTreeOfSolutions();
			
		}
	}
	
	public void onlyChoiceSolutionBox(int row, int col) {
			
			if(possibleSolutionsMap.size()>0) {
				String[] tempBox = getBox(row, col);
				
				Map<int[],ArrayList<Integer>> tempValMapPos = new HashMap<>();
				ArrayList<ArrayList<Integer>> tempPossibleValues = new ArrayList<>();
				ArrayList<int[]> tempValPosIndex = new ArrayList<>();
				int[] reservedValues = new int[9]; 
				int indexReservedValues = 0;
				
				int[] index2D = new int[] {row, col};
				if(board.getPositionValue(index2D[0],index2D[1])==(-1)) {
					ArrayList<Integer> tempPositionSolutions = possibleSolutionsMap.get(index2D[0]+""+index2D[1]);
					//tempValMapPos.put(index2D,tempPositionSolutions);
					tempPossibleValues.add(tempPositionSolutions);
					tempValPosIndex.add(index2D);
				}else {
					reservedValues[indexReservedValues]=board.getPositionValue(index2D[0],index2D[1]);
					indexReservedValues++;
				}
				
				for(int i =0; i<tempBox.length; i++) {
					index2D = new int[]{Integer.parseInt(tempBox[i].substring(0, 1)),Integer.parseInt(tempBox[i].substring(1))};
					
					if(board.getPositionValue(index2D[0],index2D[1])==(-1)) {
						ArrayList<Integer> tempPositionSolutions = possibleSolutionsMap.get(index2D[0]+""+index2D[1]);
						
						tempPossibleValues.add(tempPositionSolutions);
						tempValPosIndex.add(index2D);
					}else {
						reservedValues[indexReservedValues]=board.getPositionValue(index2D[0],index2D[1]);
						indexReservedValues++;
					}
				}
				
				
				
				
				int[] repeateValuesCountList = new int[10];
				
				for(int i=0; i<repeateValuesCountList.length; i++) {
					for(int h : reservedValues) {
						if(i==h) {
							repeateValuesCountList[i]=0;
						}
					}
				}
				
				for(int i=0; i<repeateValuesCountList.length; i++) {
					if(repeateValuesCountList[i]==1) {
						for(int j=0; j<tempPossibleValues.size(); j++) {
							for(int val : tempPossibleValues.get(j)) {
								//System.out.println("VAL: "+val+" repe "+i);
								if(val==i) {
									boolean isPossible = true;
									for(int reservedVal : reservedValues) {
										if(reservedVal==val) {
											isPossible=false;
										}
									}
									if(isPossible)
										board.setBoardValue(tempValPosIndex.get(j)[0], tempValPosIndex.get(j)[1], i);
								}
							}
							
						}
					}
				}
				//createTreeOfSolutions();
				
			}
		}

	public boolean ultimateSolver() {
		
		System.out.println("SOLVING...");
		currentUnsolved = board.getEmptyPositions();
		boolean result = false;
		createTreeOfSolutions();
		for(int i =0; i<7;i+=3) {
			for(int j = 0; j<7; j+=3) {
				onlyChoiceSolutionBox(i, j);
				//System.out.println(i+" "+j);
			}
		}
		afterUnsolved = board.getEmptyPositions();
		System.out.println("AFTER: " + afterUnsolved+" BEFORE: "+currentUnsolved);
		if(currentUnsolved>afterUnsolved && currentUnsolved!=0) {
			//System.out.println("AFTER: " + afterUnsolved+" BEFORE: "+currentUnsolved);
			System.out.println("AFTER: " + afterUnsolved+" BEFORE: "+currentUnsolved);
			counter++;
			ultimateSolver();
		}
		if(afterUnsolved==0) {
			result = true;
		}
		board.printBoard();
		return result;
		//System.out.println(board.returnEmptyPositionCounter());
		//board.printBoard();
		
	}
	
	public ArrayList<String> getEasierToSolvePosition() {
		int length = 9;
		createTreeOfSolutions();
		ArrayList<String> result = new ArrayList<>();
		for(String key : possibleSolutionsMap.keySet()) {
			if(possibleSolutionsMap.get(key).size()<length) {
				length=possibleSolutionsMap.get(key).size();
				result = new ArrayList<>();
				result.add(key);
			}else if (possibleSolutionsMap.get(key).size()==length) {
				result.add(key);
			}
				
		}
		return result;
	}
	
	public boolean search() {
		Board boardCopy = new Board();
		Utils utilsCopy = new Utils(boardCopy);
		ArrayList<String> possibleKeys = getEasierToSolvePosition();
		System.out.println(possibleKeys);
		for(String key : possibleKeys) {
			System.out.println("KEY: "+key);
			for(int val : possibleSolutionsMap.get(key)) {
				System.out.println("VAL: "+val);
				
				boardCopy.setBoard(board.getBoard());
				boardCopy.printBoard();
				boardCopy.setEmptyPositions(board.getEmptyPositions());
				boardCopy.setBoardValue(Integer.parseInt(key.substring(0,1)), Integer.parseInt(key.substring(1)), val);
				
				utilsCopy.setPossibleSolutionsMap(possibleSolutionsMap);
				boolean result = utilsCopy.ultimateSolver();
				System.out.println("RESULT: "+result);
				if(utilsCopy.ultimateSolver()) {
					boardCopy.printBoard();
					board = boardCopy;
					return true;
				}
				System.out.println("COUNTER "+counter);
			}
		}
		boardCopy.printBoard();
		return false;
	}
}
