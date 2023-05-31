import java.util.Iterator;
import java.util.Scanner;

public class Wordle {

	public static void main(String[] args) {

		// ----VARIABLES----
		int intento = 0;
		int longitud;
		String[] palabras = { "SILLA", "RATON", "LAPIZ", "CABLE", "TAPON", "JAULA", "COPIA", "MADRE", "PADRE", "JUEGO",
				"HUEVO", "NOVIA", "AVION", "ALBUM", "MISIL", "PERRO", "LUEGO", "SALTO", "OESTE", "ABEJA", "OVEJA" };
		String palabraU;
		String palabraAux = "";
		String letra = "";
		int posicionU;
		int posicionP;
		int eleccion;
		int contJ = 0;
		boolean terminado = false;
		boolean ganado = false;
		boolean tutorial = false;
		boolean encontrado = false;
		String seguir;

		// presentación
		do {
			Scanner lector = new Scanner(System.in);

			System.out.println("Bienvenido al WORDLE");
			System.out.println("¿Quieres ver el tutorial? [s/n]");
			seguir = lector.nextLine();
			seguir = seguir.toLowerCase();

			// tutorial
			switch (seguir) {
			case "s":
				System.out.println("Has accedido al tutorial");
				System.out.println(
						"Vas a elegir una palabra[1-21] de 5 LETRAS, si no introduces una palabra de 5 letras, no podrás avanzar con el juego");
				System.out.println("Tienes 6 INTENTOS para adivinar la palabra");
				System.out
						.println("Si la palabra que has INTRODUCIDO tiene letras " + ANSI_GREEN + "VERDES" + ANSI_RESET
								+ ", significa que la letra COINCIDE en posición Y en LETRA con la palabra OCULTA");
				System.out.println("Si la palabra que has INTRODUCIDO tiene letras" + ANSI_YELLOW + " AMARILLAS"
						+ ANSI_RESET
						+ ", significa que la letra NO COINCIDE en POSICIÓN PERO SI EN LETRA con la palabra OCULTA");
				System.out.println(
						"Si la palabra que has INTRODUCIDO NO HAN CAMBIADO DE COLOR, significa que la letra NO COINCIDE en POSICIÓN Y EN LETRA con la palabra OCULTA");
				tutorial = true;
				break;

			case "n":
				tutorial = true;
				break;

			default:
				System.out.println("Introduce los carácteres correctos");
				System.out.println("");

				lector.close();
			}
		} while (!tutorial);

		// ----ELEGIR PALABRA----
		do {
			Scanner lector = new Scanner(System.in);

			System.out.println("");
			System.out.println("Vamos a comenzar!!!");
			System.out.println("Elige una palabra [1-21]");
			eleccion = lector.nextInt();
			eleccion = eleccion - 1;
			

		} while (eleccion < 0 || eleccion > 20);

		longitud = palabras[eleccion].length();

		// -----EJECUCIÖN DEL JUEGO-----
		do {
			// pregunto palabra
			do {
				Scanner lector = new Scanner(System.in);

				System.out.println("Escribe una palabra");
				palabraU = lector.nextLine();
				palabraU = palabraU.toUpperCase();

				if (palabraU.length() != 5) {
					System.out.println("Tu palabra no tiene 5 letras");
					System.out.println("Intentalo de nuevo");
					System.out.println("");
				}

			} while (palabraU.length() != 5);

			intento++;
			palabraAux = "";

			for (int i = 0; i < longitud; i++) {
				palabraU.charAt(i);
				posicionU = palabraU.indexOf(palabraU.charAt(i));

				// Miro letras de la palabra introducida respecto a la letra de la palabra
				// elegida

				while (contJ < longitud && !encontrado) {
					palabras[eleccion].charAt(contJ);
					posicionP = palabras[eleccion].indexOf(palabras[eleccion].charAt(contJ));

					if (palabras[eleccion].charAt(contJ) == palabraU.charAt(i)) {
						if (posicionP == posicionU) {
							// Color verde
							letra = ANSI_GREEN + palabraU.charAt(i) + ANSI_RESET;
						} else {
							// Color amarillo
							letra = ANSI_YELLOW + palabraU.charAt(i) + ANSI_RESET;
						}
						encontrado = true;
					}
					contJ++;
				}
				if (!encontrado) {
					// dependiendo del tema de eclipse, el color de la letra sera blanco o negro
					letra = ANSI_WHITE + palabraU.charAt(i) + ANSI_RESET;
				}

				palabraAux = palabraAux + letra + " ";
				contJ = 0;
				encontrado = false;

			}

			// Imprimo palabra del usuario con colores
			System.out.println("");
			System.out.println(palabraAux);

			if (palabras[eleccion].equals(palabraU)) {
				ganado = true;
				// Si ganado es true (palabra[eleccion] = palabraU) salgo del bucle
				if (ganado) {
					terminado = true;
				}

			}
			
			System.out.println("");

			// miro intentos
			switch (intento) {
			case 1:
				System.out.println("Llevas " + intento + " intento");
				break;

			case 6:
				terminado = true;
				break;

			default:
				System.out.println("Llevas " + intento + " intentos");
			}

		} while (!terminado);

		// ------FIN DEL JUEGO------

		System.out.println("");
		
		if (!ganado) {
			System.out.println("PERDISTES¡¡¡¡¡ la palabra es: " + ANSI_GREEN + palabras[eleccion] + ANSI_RESET);
		} else {
			System.out.println("GANASTES¡¡¡¡ la palabra es: " + ANSI_GREEN + palabras[eleccion] + ANSI_RESET);
			System.out.println("Enorabuena, lo has conseguido");
		}
	}

	// ansi añade caracteres
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String BLACK_BACKGROUND = "\033[40m";
	public static final String ANSI_RESET = "\u001B[0m";

}
