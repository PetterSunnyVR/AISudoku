
public class app {
	public static void main(String[] args) {
		//Board sudoku = new Board("..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..");
		//Board sudoku = new Board("7...3...1...178.....5...2...26...49...........84...72...3...6.....359...4...1...9"); //hard
		Board sudoku = new Board("..3.92...4...3..1.27........1.3....8.5.167.3.3....8.6........53.3..8...9...62.1..");
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
/*		while(sudoku.returnEmptyPositionCounter()>0){
			utils.ultimateSolver();
			counter++;
		}
		System.out.println(counter);*/
		//utils.createTreeOfSolutions();
		//utils.onlyChoiceSolutionBox(0,3);
		
		//sudoku.printBoard();
		//utils.createTreeOfSolutions();
		
/*		utils.createTreeOfSolutions();
		sudoku.printBoard();
		utils.ultimateSolver();
		sudoku.printBoard();
		utils.ultimateSolver();
		sudoku.printBoard();*/
		utils.ultimateSolver();
		utils.ultimateSolver();
		utils.ultimateSolver();
		utils.ultimateSolver();
		utils.ultimateSolver();
		utils.ultimateSolver();
		utils.ultimateSolver();
		sudoku.printBoard();

		//System.out.println(sudoku.returnEmptyPositionCounter());


	}
}
