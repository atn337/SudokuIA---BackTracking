package sudokuIAback;

public class SimulatedAnnealingSudoku {

	public static void simulatedAnnealing() {

		Sudoku vizinho; // objeto sucessor candidato
		final Double temperature = 2.0; // temperatura inicial
		final Double coolingFactor = 0.999; // constante de resfriamento
		final int maxIterations = 1000; // número de iterações

		for (Double t = temperature; t > 1; t *= coolingFactor) {

			Sudoku sudoku = null;
			for (int i = 0; i < maxIterations; i++) {

				vizinho = sudoku.generateSuccessor(); // definir vizinho aleatório
				int delta = vizinho.cost() - sudoku.cost(); // calcule delta

				if (delta <= 0) {
					sudoku = vizinho; // sempre aceitar bom passo.
				} else {
					if (Math.exp(-delta / temperature) > Math.random()) { // Recozimento simulado
						sudoku = vizinho;
					}
				}
			}

			System.out.println(sudoku.cost());
			if (sudoku.cost() == 0) {
				break;
			} // se o quebra-cabeça for resolvido

		}
	}

//------------------------------------------------

	public Sudoku generateSuccessor() {

		int[][] newGrid = new int[9][9];

		int[][] grid = null;
		for (int o = 0; o < 9; o++) { // clonando a matriz de grade atual
			for (int g = 0; g < 9; g++) {
				newGrid[o][g] = grid[o][g];
			}
		}

		Sudoku rndm = new Sudoku(newGrid); // objeto Sudoku aleatório.

		for (int i = 0; i < 2; i++) { // irá randomizar 2 células na grade 9x9.

			int rndmCell = rndmValue();
			int randomRow = rndm();
			int randomCol = rndm();// coluna aleatória que será aleatória

			// evitar a randomização de determinadas células no sudoku (na definição do
			// problema)
			boolean shouldContinue = false;
			for (Coordinate c : SudokuSolver.concreteCoordinates) {
				if (c.row == randomRow && c.col == randomCol) {
					shouldContinue = true;
					break;
				}
			}
			if (shouldContinue) {
				i--;
				continue;
			}
			// fim da prevenção.

			rndm.grid[randomRow][randomCol] = rndmCell;
		}

		return rndm;

	}

	private int rndm() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int rndmValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	// ---------------------------------

	public int cost() {
		if (hasZeros()) { // se a grade não estiver totalmente preenchida com números, não calcule seu
							// custo.
			return -1;
		}

		int cost = 0;
		int[][] grid = null;
		for (int i = 0; i < 9; i++) { // encontre o total de conluios em linhas e colunas.
			cost += findNumberOfCollusions(grid[i]); // encontre coleções na linha 'i'.
			cost += findNumberOfCollusions(getColumn(grid, i)); // encontre coleções na coluna 'i'.
		}

		for (int r = 0; r < 9; r += 3) { // encontre o total de colusões em blocos (3x3).
			for (int c = 0; c < 9; c += 3) {
				int[] block = new int[9];
				int ctr = 0;
				for (int i = r; i < r + 3; i++) {
					for (int y = c; y < c + 3; y++) {
						block[ctr] = grid[i][y];
						ctr++;
					}
				}
				cost += findNumberOfCollusions(block);
			}
		}
		return cost;
	}

	private int[] getColumn(int[][] grid, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private int findNumberOfCollusions(int[] block) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean hasZeros() {
		// TODO Auto-generated method stub
		return false;
	}
}
