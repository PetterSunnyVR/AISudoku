import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.javafx.scene.control.behavior.TwoLevelFocusBehavior;

public class SudokuUtils {
	public SudokuGrid grid;
	private int indexRecursion = 0; 
	ArrayList<String> keysChecked = new ArrayList<>();
	Map<String, ArrayList<Integer>> CopyGridValues = new HashMap<>();
	Map<String, ArrayList<Integer>> attempt = new HashMap<>();
	private boolean stopSearch = false; 
	public SudokuUtils(SudokuGrid grid) {
		this.grid=grid;
	}
	
	
	//ELIMINATION STRATEGY - STRATEGY 1
	//find all solved boxes and remove their value from possible values of all the peers (row,col,box) that are affected by this box
	public void eliminateStrategy(Map<String, ArrayList<Integer>> gridValues) {
		String[] tempBoxes = grid.getBoxes();
		for (int i = 0; i < tempBoxes.length; i++) { // tempBoxes.length
			if(gridValues.get(tempBoxes[i]).size()==1) {
				int temp = gridValues.get(tempBoxes[i]).get(0);
				for(String[] lists : grid.getPeersMap().get(tempBoxes[i])) {
					
					//prints each value from row_unit, col_unit and box_unit
					for (int j = 0; j < lists.length; j++) {
						//if(tempBoxes[i].equals("A3")) {
						//System.out.println("E: "+tempBoxes[i]+" "+CopyGridValues.get(lists[j]) +" "+lists[j]);
						//if(gridValues.get(lists[j]).size()>1) { //FOR SOME REASON THIS ALGORITHM WANTS TO DELETE FROM EMPTY FIELDS SO THIS IS NEEDED
							gridValues.get(lists[j]).remove(new Integer(temp));
						//}
						
						//}
					}
				}
			}
			
		}
	}
	
	
	//ONLY CHOICE STRATEGY - STRATEGY 2
	public void onlyChoiceStrategy(Map<String, ArrayList<Integer>> gridValues) {
		//digits to check
		int[] digits = new int[] {1,2,3,4,5,6,7,8,9};
		//array to store found boxes
		ArrayList<String> tempBoxes;
		//go through all the BoxUnits
		for (int i = 0; i < grid.getBoxUnits().length; i++) {
			//clear the array
			tempBoxes = new ArrayList<>();
			//go through all digits
			for (int j = 0; j < digits.length; j++) {
				//go through all the boxes in BoxUnit[i]
				for(String box : grid.getBoxUnits()[i]) {
					//if the box isn't already solved and values of that box contains this digit add that box to the tempBoxes arraylist
					if(gridValues.get(box).contains(new Integer(digits[j]))) {
						tempBoxes.add(box);
					}
				}
				//if(i==7) {
				//	System.out.println("DIGIT: "+digits[j]+" "+tempBoxes.toString());
				//}
				//if there is only 1 box that have this digit as a solution
				if(tempBoxes.size()==1 && gridValues.get(tempBoxes.get(0)).size()>1) {
					gridValues.put(tempBoxes.get(0),new ArrayList<Integer>());
					gridValues.get(tempBoxes.get(0)).add(digits[j]);
					
				}
				tempBoxes = new ArrayList<>();
			}
		}
	}
	
	////////////////////////////////////////////////////NOT WORKING RIGHT NOW//////////////////////////////
	//NAKED TWIN STRATEGY - STRATEGY 3
	public void nakedTwinsStrategy(Map<String, ArrayList<Integer>> gridValues) {
		int[] digits = new int[] {1,2,3,4,5,6,7,8,9};
		ArrayList<String> tempBoxKeys;
		ArrayList<String[]> foundBoxKeys = new ArrayList<>();
		//for (int i = 0; i < grid.getBoxes().length; i++) { 
			//iterate through every rowUnit
			for(String[] row : grid.getRowUnits()) {
				//check if any of the positions has only 2 values as a solution
				tempBoxKeys = new ArrayList<>();
				for(String rowKey : row) {			
					if(gridValues.get(rowKey).size()==2) {
						//if yes put it on the list
						tempBoxKeys.add(rowKey);
					}
				}
				if(tempBoxKeys.size()>1) {
					for(String key1 : tempBoxKeys) {
						for(String key2 : tempBoxKeys) {
							//comparing values of key1 key2
							if(!key1.equals(key2) && gridValues.get(key1).get(0)==gridValues.get(key2).get(0) && gridValues.get(key1).get(1)==gridValues.get(key2).get(1)) {
								//if they are ok add both of them to new arraylist
								
								foundBoxKeys.add(new String[] {key1,key2});
							}
						}
					}
				}
			}
			
			//same for colum_units
			for(String[] col : grid.getColUnits()) {
				tempBoxKeys = new ArrayList<>();
				for(String colKey : col) {
					if(gridValues.get(colKey).size()==2) {
						//if yes put it on the list
						tempBoxKeys.add(colKey);
					}
				}
				if(tempBoxKeys.size()>1) {
					for(String key1 : tempBoxKeys) {
						for(String key2 : tempBoxKeys) {
							//comparing values of key1 key2
							if(!key1.equals(key2) && gridValues.get(key1).get(0)==gridValues.get(key2).get(0) && gridValues.get(key1).get(1)==gridValues.get(key2).get(1)) {
								//if they are ok add both of them to new arraylist
								foundBoxKeys.add(new String[] {key1,key2});
							}
						}
					}
				}
			}
		//}
		
		//having the list foundBoxKey filled we now delete values from the boxes
		for (int i = 0; i < 1; i++) {
			if(foundBoxKeys.size()>1) {
				//System.out.println("FOUND KEYS: "+foundBoxKeys.get(i)[0]+ " "+foundBoxKeys.get(i)[1]);
				
				//save the values of 1st key - we know that it has only 2 values and key2 has the same values
				int tempValue1 = gridValues.get(foundBoxKeys.get(i)[0]).get(0);
				int tempValue2 = gridValues.get(foundBoxKeys.get(i)[0]).get(1);
				//remove those values from every element in the boxPeer of key 1
				for (String key : grid.getPeersMap().get(foundBoxKeys.get(i)[0]).get(2)) {
					//if specific boxes[key] has value tempValue1/2 remove it 
					//for safety never remove the value if it is the solution - should never happen
					if(gridValues.get(key).size()>1) {
						gridValues.get(key).remove(new Integer(tempValue1));
						if(gridValues.get(key).size()>1) {
							gridValues.get(key).remove(new Integer(tempValue2));
						}
					}
					
				}
				
				//remove those values from every element in the boxPeer of key 2
				for (String key : grid.getPeersMap().get(foundBoxKeys.get(i)[1]).get(2)) {
					//if specific boxes[key] has value tempValue1/2 remove it 
					//for safety never remove the value if it is the solution - should never happen
					if(gridValues.get(key).size()>1) {
						//System.out.println(key+" SIZE before 1 removal: "+gridValues.get(key).size());
						gridValues.get(key).remove(new Integer(tempValue1));
						//System.out.println(key+" SIZE after 1 removal: "+gridValues.get(key).size());
						gridValues.get(key).remove(new Integer(tempValue2));
					}
					
				}
			}
			
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Map<String, ArrayList<Integer>> search(Map<String, ArrayList<Integer>> gridValues) {
		
				gridValues = solveSudoku(gridValues);
				if(gridValues==null) {
					return null;
				}
				int afterUnsolvedCount = getUnsolvedBoxesNumber(gridValues);
				if(afterUnsolvedCount==0) {
					System.out.println("SOLUTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					//printGrid(gridValues);
					stopSearch = true;
					return gridValues;
				}
				
		/*		System.out.println("");
				printGrid(gridValues);
				System.out.println("");*/
		
				
				//for now - delete later
		/*		if(indexRecursion>3) {
					System.out.println("BREAKING");
					return null;
				}*/
				
				//get box with least possible solutions
				String keyToCheck = "";
				
				int smallestindex = 9;
				for (int i = 0; i < grid.getBoxes().length; i++) {
					if(gridValues.get(grid.getBoxes()[i]).size()>1 && gridValues.get(grid.getBoxes()[i]).size()<smallestindex) {
						//if(!keysChecked.contains(grid.getBoxes()[i])) {
							//System.out.println(keysToCheck.contains(grid.getBoxes()[i])+" "+grid.getBoxes()[i]+ " "+keysChecked.toString());
							smallestindex=gridValues.get(grid.getBoxes()[i]).size();
							//keysToCheck.add(grid.getBoxes()[i]);
						//}
					}
				}
				
				for (int i = 0; i < grid.getBoxes().length; i++) {
					if(gridValues.get(grid.getBoxes()[i]).size()==smallestindex) {
						keyToCheck=grid.getBoxes()[i];
						break;
					}
				}
				////////////////////////////////////////////
				System.out.println("KEY to check: "+keyToCheck);
		
					
				for (int val : gridValues.get(keyToCheck)) {
					//check if we should go deeper for solution
					
					if(stopSearch) {
						break;
					}
					
					//COPY GRID
					Map<String, ArrayList<Integer>> copyGrid = new HashMap<>();
					for(int k = 0; k<grid.getBoxes().length; k++) {
						//I think for deep clone() we need to copy values 1 by 1
						copyGrid.put(grid.getBoxes()[k], new ArrayList<>());
						for(int i : gridValues.get(grid.getBoxes()[k])) {
							copyGrid.get(grid.getBoxes()[k]).add(i);
						}
						
					}
					
					//System.out.println("");
					System.out.println(indexRecursion+" KEY: "+keyToCheck+" "+gridValues.get(keyToCheck)+" VAL: "+val +" unsolved: "+afterUnsolvedCount);
					copyGrid.put(keyToCheck, new ArrayList<>());
					copyGrid.get(keyToCheck).add(val);
					/////////////////////////////
					
						attempt = search(copyGrid);
						indexRecursion++;
						if(attempt!=null) {
							if(!stopSearch) {
							System.out.println("SOLUTION");
							}
							return attempt;
						}else {
							System.out.println("MOVING TO THE NEXT POSITION");
						}
				}
				
		

		return null;
		
	}
	
	//get number of unsolved boxes on the grid
	public int getUnsolvedBoxesNumber(Map<String, ArrayList<Integer>> gridValues) {
		int temp = 81;
		for (int i = 0; i < grid.getBoxes().length; i++) {
			if(gridValues.get(grid.getBoxKeyOnPosition(i)).size()==1) {
				temp--;
			}
		}
		return temp;
	}
	
	//Solve sudoku using all 3 strategies and returns true if sudoku is solved. If it doesn't yield effects it returns false
	public Map<String, ArrayList<Integer>> solveSudoku(Map<String, ArrayList<Integer>> gridValues) {
		int index = 0;
		int currentUnsolvedBoxes = 81, resultUnsolvedBoxes = 81;
		boolean stuck = false;
		while(!stuck) {
			currentUnsolvedBoxes = getUnsolvedBoxesNumber(gridValues);
			
			eliminateStrategy(gridValues);
			//System.out.println("ELIMINATE");
			//printGrid(gridValues);
			//System.out.println("");
			onlyChoiceStrategy(gridValues);
			//nakedTwinsStrategy(gridValues);
			//System.out.println("ONLY CHOICE");
			//printGrid(gridValues);
			//System.out.println("");
			index++;
			resultUnsolvedBoxes = getUnsolvedBoxesNumber(gridValues);
			stuck = (currentUnsolvedBoxes==resultUnsolvedBoxes);
			for(int i =0; i<grid.getBoxes().length; i++) {
				if(gridValues.get(grid.getBoxes()[i]).size()==0) {
					//System.out.println("EMPTY AFTER SOLVING STARTING NEXT SEARCH");
					return null;
				}
			}
		}
		//System.out.println("SOLVING...");
		
		//System.out.println("UNSOLVED RESULTS: "+resultUnsolvedBoxes);
		//System.out.println("");
		return gridValues;

	}
	
	public void printGrid() {
		int indexC = 0, indexR = 0;
		String[] temp = grid.getBoxes();
		for (int i = 0; i < temp.length; i++) {
			//System.out.print(temp[i] +" : "+grid.getGridValues().get(temp[i])+" ");
			System.out.print(grid.getGridValues().get(temp[i])+" ");
			indexC++;
			if(indexC==3 || indexC == 6) {
				System.out.print(" | ");
			}
			if(indexC==9) {
				indexR++;
				if(indexR==3 || indexR==6){
					System.out.println("");
					System.out.println("----------------------------------------------------------------------------------");
				}else {
					System.out.println("");
				}
				
				indexC=0;
				
			}
				
		}
	}
	
	public void printGrid(Map<String, ArrayList<Integer>> gridValues) {
		int indexC = 0, indexR = 0;
		String[] temp = grid.getBoxes();
		for (int i = 0; i < temp.length; i++) {
			//System.out.print(temp[i] +" : "+grid.getGridValues().get(temp[i])+" ");
			System.out.print(gridValues.get(temp[i])+" ");
			indexC++;
			if(indexC==3 || indexC == 6) {
				System.out.print(" | ");
			}
			if(indexC==9) {
				indexR++;
				if(indexR==3 || indexR==6){
					System.out.println("");
					System.out.println("----------------------------------------------------------------------------------");
				}else {
					System.out.println("");
				}
				
				indexC=0;
				
			}
				
		}
	}
	
	public void changeBoxValue(Map<String, ArrayList<Integer>> gridValues, String key, int value) {
		gridValues.put(key, new ArrayList<>());
		gridValues.get(key).add(new Integer(value));
	}
	
	public void test(Map<String, ArrayList<Integer>> gridValues) {

		boolean equalPeers = false;
		for(String box : grid.getBoxes()) {
			if(gridValues.get(box).size()==1) {
				for(int i =0; i<3; i++) {
					for(String peer : grid.getPeersMap().get(box).get(i)) {
						if(gridValues.get(peer).size()==1) {

							if(gridValues.get(box).get(0)==gridValues.get(peer).get(0)) {
								equalPeers=true;
							}
						}
					}
				}
			}
		}
		System.out.println(equalPeers);
		
			/*System.out.println(Arrays.toString(grid.getPeersMap().get("A1").get(0))+" "+Arrays.toString(grid.getPeersMap().get("A1").get(1))+" "+Arrays.toString(grid.getPeersMap().get("A1").get(2)));
			System.out.println(Arrays.toString(grid.getPeersMap().get("I7").get(0))+" "+Arrays.toString(grid.getPeersMap().get("I7").get(1))+" "+Arrays.toString(grid.getPeersMap().get("I7").get(2)));
			System.out.println(grid.getPeersMap().get("A3").get(2).length);
			System.out.println(grid.getGridValues().get("A2"));*/
	}


	public int getIndexRecursion() {
		return indexRecursion;
	}
	
	
}
