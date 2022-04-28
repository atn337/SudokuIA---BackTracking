package sudokuIAback;

public class SudokoSolverBackTracking {
	static long start = System.currentTimeMillis();
	static int c = 0;

	public static boolean solve(int[][] sudoku) {
		int i = 0, j = 0;
		boolean found = false;

		// procura por valores vazios(valor 0)
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		// nenhum valor vazio encontrado, retorna True
		if (i == 9 && j == 9) {
			return true;
		}
		// Um por um tenta todos os valores na célula atual
		for (int n = 1; n <= 9; n++) {
			// verifique se é válido atribuir o valor n à célula atual
			if (isSafe(i, j, n, sudoku)) {
				sudoku[i][j] = n;
				// Resolva recursivamente o sudoku
				if (solve(sudoku)) {
					return true;

				}
				// voltar atrás se a recursão retornar false (backtracking)
				sudoku[i][j] = 0;
				c++;
			}
		}

		// Retorna false se nenhum valor couber
		return false;
	}

	public static boolean isSafe(int i, int j, int n, int[][] sudoku) {
		// Verifique na linha e coluna atuais
		for (int x = 0; x < 9; x++) {
			// Linhas
			if (sudoku[i][x] == n) {
				return false;
			}
			// Colunas
			if (sudoku[x][j] == n) {
				return false;
			}
		}

		// Calcular o índice inicial da linha e coluna da subcaixa 3x3 atual
		int rs = i - (i % 3);
		int cs = j - (j % 3);
		// Verifique na subcaixa atual
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (sudoku[x + rs][y + cs] == n) {
					return false;
				}
			}
		}
		// Retorna true
		return true;
	}

	public static void main(String[] args) {

		// Sudoku parcialmente preenchido
		int[][] sudoko = new int[][] { { 2, 0, 0, 0, 0, 0, 0, 3, 5 }, { 0, 5, 0, 0, 1, 9, 6, 4, 0 },
				{ 0, 0, 0, 0, 4, 0, 7, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 6, 0 }, { 0, 4, 0, 0, 8, 1, 5, 9, 0 },
				{ 0, 0, 9, 7, 5, 0, 0, 0, 0 }, { 9, 0, 0, 8, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 0, 0, 8, 0 },
				{ 0, 0, 5, 0, 0, 0, 0, 0, 3 } };

		solve(sudoko);
		// printa a resposta
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoko[i][j] + " ");

			}
			System.out.println();

		}
		long finish = System.currentTimeMillis();
		long total = finish - start;
		System.out.println("Resolvido com sucesso! em " + total * 0.001 + " segundos");
		System.out.println("Número total de nós de backtracking é " +c);
	}
}
