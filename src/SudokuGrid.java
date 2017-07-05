import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuGrid {
	
	//pythons dict<> equivalent Hashmap
	private Map<String, ArrayList<Integer>> gridValues = new HashMap<>();
	private Map<String, ArrayList<String[]>> peersMap = new HashMap<>();
	private String rows = "ABCDEFGHI", cols = "123456789";
	private String[] boxes = new String[81];
	private String[][] rowUnits = new String[9][1];
	private String[][] colUnits = new String[9][1];
	private String[][] boxUnits = new String[9][1];
	private String[][] unitList = new String[9][3];
	
	public SudokuGrid(String grid) {
		//to print array Arrays.ToString(arr)!!!!
		//initialize ArrayList - very excessive
		boxes = cross(rows, cols);
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 0; i < boxes.length; i++) {
			if(grid.substring(i,i+1).equals(".")) {
				gridValues.put(boxes[i], new ArrayList<Integer>());
				for (int j = 1; j < 10; j++) {
					gridValues.get(boxes[i]).add(j);
				}
			}else {
				temp = new ArrayList<>();
				temp.add(Integer.parseInt(grid.substring(i,i+1)));
				gridValues.put(boxes[i], temp);
			}
		}
		
		//creates columnt_units
		for (int i = 0; i < colUnits.length; i++) {
			colUnits[i]=cross(rows, cols.substring(i,i+1));
			//System.out.println(Arrays.toString(colUnits[i]));
		}
		
		//creates rows_units
		for (int i = 0; i < rowUnits.length; i++) {
			rowUnits[i]=cross(rows.substring(i,i+1), cols);
			//System.out.println(Arrays.toString(rowUnits[i]));
		}
		
		//additional lists for creating cos_units
		List<String> rowsList = new ArrayList<>();
		rowsList.add("ABC");
		rowsList.add("DEF");
		rowsList.add("GHI");
		
		List<String> colList = new ArrayList<>();
		colList.add("123");
		colList.add("456");
		colList.add("789");
		
		//creates box_units
		for (int i = 0; i < boxUnits.length; i++) {
			if(i<3) {
				boxUnits[i]=cross(rowsList.get(0), colList.get(i));
			}else if(i<6) {
				boxUnits[i]=cross(rowsList.get(1), colList.get(i-3));
			}else if(i<9) {
				boxUnits[i]=cross(rowsList.get(2), colList.get(i-6));
			}
			//System.out.println(Arrays.toString(boxUnits[i]));
		}
		
		
		
		//creates peers
		for (int i = 0; i < boxes.length; i++) { //boxes.length
			//prepere place for new peer arraylist
			peersMap.put(boxes[i], new ArrayList<String[]>());
			//iterate through every rowUnit
			for(String[] row : rowUnits) {
				//check if any of the positions in the row is equal to the box we are checking
				for(String rowKey : row) {
					if(rowKey.equals(boxes[i])) {
						//if yes take values from this row excluding the boexs[i] value because it is peers only list
						int index = 0;
						//create new string to store the new string
						String[] excludeBoxValue = new String[8];
						for (int j = 0; j < row.length; j++) {
							if(!row[j].equals(boxes[i])) {
								excludeBoxValue[index]=row[j];
								index++;
							}
						}
						//peersMap.get(boxes[i]).add(row);
						//add values to peersMap arraylist previously created (above)
						peersMap.get(boxes[i]).add(excludeBoxValue);
					}
				}
			}
			
			//same for colum_units
			for(String[] col : colUnits) {
				for(String colKey : col) {
					if(colKey.equals(boxes[i])) {
						int index = 0;
						String[] excludeBoxValue = new String[8];
						for (int j = 0; j < col.length; j++) {
							if(!col[j].equals(boxes[i])) {
								excludeBoxValue[index]=col[j];
								index++;
							}
						}
						//peersMap.get(boxes[i]).add(col);
						peersMap.get(boxes[i]).add(excludeBoxValue);
					}
				}
			}
			
			//same for box_units
			for(String[] box : boxUnits) {
				for(String boxKey : box) {
					if(boxKey.equals(boxes[i])) {
						int index = 0;
						String[] excludeBoxValue = new String[8];
						for (int j = 0; j < box.length; j++) {
							if(!box[j].equals(boxes[i])) {
								excludeBoxValue[index]=box[j];
								index++;
							}
						}
						//peersMap.get(boxes[i]).add(box);
						peersMap.get(boxes[i]).add(excludeBoxValue);
					}
				}
			}
			//System.out.println(boxes[i]+" : "+Arrays.toString(peersMap.get(boxes[i]).get(0))+" "+Arrays.toString(peersMap.get(boxes[i]).get(1))+" "+Arrays.toString(peersMap.get(boxes[i]).get(2)));
		}
		
	}
	
	//a lot more code than cross() in python
	public String[] cross(String a, String b) {
		int index = 0;
		String[] result = new String[a.length()*b.length()];
		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				result[index]=a.substring(i,i+1)+b.substring(j,j+1)+"";
				index++;
			}
		}
		return result;
	}

	public String getBoxKeyOnPosition(int position) {
		return boxes[position];
	}
	
	
	public Map<String, ArrayList<Integer>> getGridValues() {
		return gridValues;
	}

	public Map<String, ArrayList<String[]>> getPeersMap() {
		return peersMap;
	}

	public String[] getBoxes() {
		return boxes;
	}

	public String[][] getRowUnits() {
		return rowUnits;
	}

	public String[][] getBoxUnits() {
		return boxUnits;
	}
	
	public String[] getBoxUnits(int val) {
		return boxUnits[val];
	}

	public String[][] getUnitList() {
		return unitList;
	}

	public String[][] getColUnits() {
		return colUnits;
	}

	
}
