

public class app {
	public static void main(String[] args) {
		/*Board sudoku = new Board("..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..");
		//Board sudoku = new Board("7...3...1...178.....5...2...26...49...........84...72...3...6.....359...4...1...9"); //hard
		//Board sudoku = new Board("..3.92...4...3..1.27........1.3....8.5.167.3.3....8.6........53.3..8...9...62.1..");
		//Board sudoku = new Board("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......");
		sudoku.printBoard();
		Utils utils = new Utils(sudoku);
		int counter = 0;
		System.out.println(utils.search());
		sudoku.printBoard();
*/
		//.6.1.4.5...83.56..2.......18..4.7..6..6...3..7..9.1..45.......2..72.69...4.5.8.7.
		SudokuGrid grid = new SudokuGrid("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......");
		SudokuUtils solver = new SudokuUtils(grid);

		//solver.eliminateStrategy(grid.getGridValues());
		//solver.printGrid();
		//System.out.println("");
		//solver.nakedTwinsStrategy();

		System.out.println(solver.search(grid.getGridValues()));
		//System.out.println(solver.solveSudoku(grid.getGridValues()));
		solver.printGrid();
		//solver.nakedTwinsStrategy();
		//solver.test(grid.getGridValues());
	}
}
