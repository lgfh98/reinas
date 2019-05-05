package entrevista;

public class Main {

	public static void main(String[] args) {

		int filas[] = new int[8];
		int columnas[] = new int[8];

		for (int i = 0; i < columnas.length; i++) {
			filas[i] = -1;
			columnas[i] = -1;
		}

		ubicarReinas(0, 0, 0, filas, columnas);

		int tablero_muestra[][] = new int[8][8];

		for (int i = 0; i < columnas.length; i++) {
			System.out.println("pocision(" + filas[i] + "," + columnas[i] + ")");
			tablero_muestra[filas[i]][columnas[i]] = 1;
		}

		for (int i = 0; i < tablero_muestra.length; i++) {
			System.out.println();
			for (int j = 0; j < tablero_muestra[0].length; j++) {
				System.out.print(tablero_muestra[i][j] + " ");
			}
		}

	}

	static void ubicarReinas(int conteo, int pos_i, int pos_j, int[] filas, int[] columnas) {

		if (conteo == 8) {
			return;
		} else {
			if (validar(pos_i, pos_j, filas, columnas)) {
				filas[conteo] = pos_i;
				columnas[conteo] = pos_j;
				conteo++;
				pos_i++;

				ubicarReinas(conteo, pos_i, 0, filas, columnas);
				return;
			} else {

				if (pos_j < 7) {
					pos_j++;
					ubicarReinas(conteo, pos_i, pos_j, filas, columnas);
					return;
				} else {

					conteo--;
					pos_i = filas[conteo];
					pos_j = columnas[conteo];
					filas[conteo] = -1;
					columnas[conteo] = -1;

					while (pos_j == 7) {
						conteo--;
						pos_i = filas[conteo];
						pos_j = columnas[conteo];
						filas[conteo] = -1;
						columnas[conteo] = -1;
					}
					pos_j++;
					ubicarReinas(conteo, pos_i, pos_j, filas, columnas);
					return;
				}
			}
		}
	}

	static boolean validar(int i_aux, int j_aux, int[] filas, int[] columnas) {

		int i = i_aux;
		int j = j_aux;
		// validacion horizontal
		for (int k = 0; k < filas.length; k++) {
			if (filas[k] == i) {
				return false;
			}
		}
		// validacion vertical
		for (int k = 0; k < columnas.length; k++) {
			if (columnas[k] == j) {
				return false;
			}
		}
		j++;
		i--;
		// validacion diagonal superior derecha*
		while (i >= 0 && j <= 7) {

			if (buscarNumero(i, j, filas, columnas)) {
				return false;
			}
			j++;
			i--;
		}

		i = i_aux + 1;
		j = j_aux - 1;

		// validacion diagonal inferior izquierda
		while (i <= 7 && j >= 0) {

			if (buscarNumero(i, j, filas, columnas)) {
				return false;
			}
			j--;
			i++;
		}
		i = i_aux - 1;
		j = j_aux - 1;

		// validacion diagonal superior izquierda
		while (i >= 0 && j >= 0) {

			if (buscarNumero(i, j, filas, columnas)) {
				return false;
			}
			j--;
			i--;
		}
		i = i_aux + 1;
		j = j_aux + 1;

		// validacion diagonal inferior derecha
		while (i <= 7 && j <= 7) {

			if (buscarNumero(i, j, filas, columnas)) {
				return false;
			}
			j++;
			i++;
		}

		return true;

	}

	static boolean buscarNumero(int i, int j, int[] filas, int[] columnas) {
		for (int k = 0; k < columnas.length; k++) {
			if (filas[k] == i && columnas[k] == j) {
				return true;
			}
		}

		return false;
	}

}
