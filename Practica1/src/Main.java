import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 100)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		String[] dataset = llegirLiniesFitxer(numLinies);
		
		Primer instancia = new Primer (0, 2, 0);
		
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + ": " + dataset[i]);
		}
		
		metode1(instancia, dataset, numLinies);
		/*metode2(instancia);
		metode3(instancia);
		metode4(instancia);*/
	}
	
	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 5)
			nLinies = 5;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("ValorsN.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}
	
	private static void metode1(Primer instancia, String[] dataset, int numLinies) {
		Primer aux;
		int vueltas = 0;
		while (vueltas < numLinies) {
			aux = instancia.buscarPrimerCutre(instancia, dataset, vueltas);
			System.out.println("Comprova tots els números: " +aux);
			vueltas++;
		}
	}
	
	/*private static void metode2(Primer instanciaAux) {
		Primer aux;
		aux = instanciaAux.buscarPrimerImpars(instanciaAux);
		System.out.println("\nCcomprova tots els impars: " +aux);
	}
	
	private static void metode3(Primer instanciaAux) {
		Primer aux;
		aux = instanciaAux.buscarPrimerNdiv2(instanciaAux);
		System.out.println("\nComprova tots els impars i fins N/2: " +aux);
	}
	
	private static void metode4(Primer instanciaAux) {
		Primer aux;
		aux = instanciaAux.buscarPrimerArrel(instanciaAux);
		System.out.println("\nComprova tots els impars i fins arrel de N: " +aux);
	}*/
}
