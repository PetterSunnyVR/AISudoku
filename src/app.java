
public class app {
	public static void main(String[] args) {
		Board sudoku = new Board("..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..");
		sudoku.printBoard();
		/*sudoku.getBox("H7");
		sudoku.getLines("H7").toString();*/
		Utils utils = new Utils(sudoku);
		//utils.getValuesAfterElimination("H7");
		//sudoku.getLines("I7");
/*		System.out.println(sudoku.returnEmptyPositionCounter());
		utils.createTreeOfSolutions();
		System.out.println(sudoku.returnEmptyPositionCounter());*/
		int counter = 0;
		while(sudoku.returnEmptyPositionCounter()>0){
			utils.createTreeOfSolutions();
			counter++;
		}
		System.out.println(counter);
		//utils.onlyChoiceSolutionBox("I9");
		
	}
}
