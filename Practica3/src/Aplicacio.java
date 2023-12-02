import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Aplicacio {


	private static int camins;	
	private static String fitxerResultats = "resultats.txt";
	static FileWriter fw;
	 
	public static void main(String[] args) {

		try
		{
			fw = new FileWriter(fitxerResultats, false);	
			fw.write("");
			fw.close();
			fw = new FileWriter(fitxerResultats, true); 
		}
		catch(IOException ioe)
		{
			System.err.println("IOException: " + ioe.getMessage());
		}
		
		long timerIni, timerFi;
	
		String [] nomFitxer = {"laberint.txt"};
	
		Matriu matriu;	
		
		for (int i = 0; i < nomFitxer.length; i++) {
			try {
				if (i != 0)
					fw.write("\n");
				fw.write("--------" + nomFitxer[i] + "--------" + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			matriu = llegirFitxer(nomFitxer[i]);

			mostrarMatriu(matriu);	
			System.out.println("\n");
			timerIni = System.nanoTime();
			System.out.println("Laberint superat per algorisme avid: " + LaberintAvid(matriu));
			System.out.println("\n");
			timerFi = System.nanoTime();
			System.out.println("Temps de'execucio: " + (timerFi-timerIni)/1000 + " microsegons");
			System.out.println("Algorisme Backtracking");
			prepararMatriu(matriu);
			timerIni = System.nanoTime();
			System.out.println("Laberint superat per algorisme de cerca exhaustiva: " + resoldreLaberintExhaustiva(matriu));
			timerFi = System.nanoTime();
			System.out.println("Temps de'execucio: " + (timerFi-timerIni)/1000 + " microsegons");
			System.out.println(camins);
			System.out.println("\n");
		}
		try
		{
			fw.close();
		}
		catch (IOException e)
		{
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	private static void prepararMatriu(Matriu matriu) {
		for (int i = 0; i < matriu.getDimFila(); i++) {
			for (int j = 0; j < matriu.getDimCol(); j++) {
				if (matriu.getMatriu()[i][j] != null)
					matriu.getMatriu()[i][j].setAccedida(false);
			}
			
		}
	}
	
	private static void mostrarMatriu(Matriu matriu) {
		Caselles c;
		char op = '&';
		System.out.print(matriu.getDimFila() + ", " + matriu.getDimCol() + ", " +
				matriu.getInici().getY() + ", " + matriu.getInici().getX() + ", " +
				matriu.getFi().getY() + ", " + matriu.getFi().getX());
		for (int i = 0; i < matriu.getDimFila(); i++)
		{
			System.out.println();
			if (matriu.getMatriu()[i][0] != null)
			{
				switch (matriu.getMatriu()[i][0].getOperacio())
				{
					case 0:	
						op = '-';
						break;
					case 1:	
						op = '+';
						break;
					case 2:	
						op = '/';
						break;
					case 3:	
						op = '*';
						break;
	
					default:
						throw new IllegalArgumentException("Unexpected value: " + op);
				}
				System.out.print(op + "" + matriu.getMatriu()[i][0].getValor());
			}
			else
				System.out.print("NA");
			for (int j = 1; j < matriu.getDimCol(); j++)
			{
				c = matriu.getMatriu()[i][j];
				if (c != null)
				{
					switch (c.getOperacio())
					{
						case 0:	
							op = '-';
							break;
						case 1:	
							op = '+';
							break;
						case 2:	
							op = '/';
							break;
						case 3:	
							op = '*';
							break;
		
						default:
							throw new IllegalArgumentException("Unexpected value: " + op);
					}
				
					System.out.print(", " + op + c.getValor());
				}
				else
					System.out.print(", NA");
			}
		}
		System.out.println();
	}
	

	private static int esSigne(char c) {
		if (c == '-')
			return 0;
		else if (c == '+')
			return 1;
		else if (c == '/')
			return 2;
		else if (c == '*')
			return 3;
		return -1;	
	}
	

	private static Matriu llegirFitxer(String fitxer) {
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(fitxer));
			int cont=0;
			String frase, entrada;
			int dimFila, dimCol,
				filaInici, colInici,
				filaFi,	colFi,
				signe, j = 0;
			Matriu matriu = null;
			StringTokenizer st;
			frase = f.readLine();
			while(frase != null)
			{
				if (cont == 0)
				{
					try
					{
						st = new StringTokenizer(frase,",");
					
						dimFila  = Integer.parseInt(st.nextToken());
						dimCol = Integer.parseInt(st.nextToken().substring(1));
						filaInici = Integer.parseInt(st.nextToken().substring(1));
						colInici = Integer.parseInt(st.nextToken().substring(1));
						filaFi = Integer.parseInt(st.nextToken().substring(1));
						colFi = Integer.parseInt(st.nextToken().substring(1));
						matriu = new Matriu(dimFila, dimCol, filaInici, colInici, filaFi, colFi);
					} catch(IllegalArgumentException e) {
						e.printStackTrace();
						System.out.println("Mapa d'entrada erroni+");
					}
				}
				else
				{
					if( cont > matriu.getDimFila()) 
					{
						f.close();
						return matriu;
					}
					
					try
					{
						st = new StringTokenizer(frase,",");
						dimCol = matriu.getDimCol();
						for (int i = 0; i < dimCol; i++)
						{
							entrada = st.nextToken();
							if (i != 0)
								entrada = entrada.substring(1);
							signe = esSigne(entrada.charAt(0));
							if ( entrada.contains("NA") )
								matriu.getMatriu()[j/dimCol][j%dimCol] = null;
							else if (signe != -1)
								matriu.getMatriu()[j/dimCol][j%dimCol] = new Caselles(signe, (int) entrada.charAt(1) - '0', j%dimCol, j/dimCol);
							else
								throw new IllegalArgumentException();
							j++;
						}
					} catch(IllegalArgumentException e) {
						e.printStackTrace();
						System.out.println("Mapa d'entrada erroni");
					};
				}
				
				cont++;
				frase = f.readLine();
			}
			
			f.close();
			return matriu;
		}catch(FileNotFoundException e) {
			System.out.println("No s'ha trobat l'arxiu amb el nom " + fitxer);
		}catch(IOException e) {
			System.out.println("ERROR EN OBRIR EL FITXER");
		}
		
		return null;
	}
	
	private static boolean LaberintAvid(Matriu matriu) {

		int puntuacio;	
		String strRecorregut = matriu.getInici().getY() + ", " + matriu.getInici().getX();
		
		matriu.setActual(matriu.getInici());
		matriu.casellaActual().setAccedida(true);
		puntuacio = matriu.casellaActual().getValor();
		while ( !matriu.getFi().equals(matriu.getActual()) )
		{
			if ( !limits(matriu, puntuacio) )
			{
				puntuacio = seleccionaDireccio(matriu, puntuacio);	// Selecciona casella i efectua el moviment
				strRecorregut += " -> " + matriu.getActual().getY() + ", " + matriu.getActual().getX();
				matriu.setMoviments(matriu.getMoviments() + 1);	// Nomes es fan moviments d'una casella
			}
			else
			{
				escriureRecorregut(strRecorregut);
				System.out.println("Puntuacio: " + puntuacio);
				return false;
			}
		}
		
		escriureRecorregut(strRecorregut);
		System.out.println("Puntuacio: " + puntuacio);
		return true;
	}
	
	private static boolean limits(Matriu mat, int puntuacio) {
		int noAccessibles = 0;	
		if ( mat.casellaNord() == null  || mat.casellaNord().isAccedida() || resultat(mat.casellaNord(), puntuacio) <= 0 )
			noAccessibles++;
		if ( mat.casellaSud() == null || mat.casellaSud().isAccedida() || resultat(mat.casellaSud(), puntuacio) <= 0 )
			noAccessibles++;
		if ( mat.casellaEst() == null || mat.casellaEst().isAccedida() || resultat(mat.casellaEst(), puntuacio) <= 0 )
			noAccessibles++;
		if ( mat.casellaOest() == null || mat.casellaOest().isAccedida() || resultat(mat.casellaOest(), puntuacio) <= 0 )
			noAccessibles++;
		if ( noAccessibles == 4)
			return true;
		
		return false;
	}

	private static int resultat(Caselles c, int p) {
		int resultat;
		
		switch (c.getOperacio()) {
			case 0:	
				resultat = p - c.getValor();
				break;
			case 1:	
				resultat = p + c.getValor();
				break;
			case 2:	
				resultat = p / c.getValor();
				break;
			case 3:	
				resultat = p * c.getValor();
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + c.getOperacio());
		}
		return resultat;
	}

	private static int seleccionaDireccio(Matriu mat, int puntuacio) {
		int maxim = 0, nord = -1, sud = -1, est = -1, oest = -1;	
		Caselles c;
		c = mat.casellaNord();
		if (c != null && !c.isAccedida())
		{
			nord = resultat(c, puntuacio);
			if (maxim < nord)
				maxim = nord;
		}
		
		c = mat.casellaSud();
		if (c != null && !c.isAccedida())
		{
			sud = resultat(c, puntuacio);
			if (maxim < sud)
				maxim = sud;
		}
		
		c = mat.casellaEst();
		if (c != null && !c.isAccedida())
		{
			est = resultat(c, puntuacio);
			if (maxim < est)
				maxim = est;
		}
		
		c = mat.casellaOest();
		if (c != null && !c.isAccedida())
		{
			oest = resultat(c, puntuacio);
			if (maxim < oest)
				maxim = oest;
		}
		
		if (maxim > 0)
		{
			if (maxim == nord && mat.casellaNord() != null && !mat.casellaNord().isAccedida())
				seguentCasella(mat, 'n');
			if (maxim == sud && mat.casellaSud() != null && !mat.casellaSud().isAccedida())
				seguentCasella(mat, 's');
			if (maxim == est && mat.casellaEst() != null && !mat.casellaEst().isAccedida())
				seguentCasella(mat, 'e');
			if (maxim == oest && mat.casellaOest() != null && !mat.casellaOest().isAccedida())
				seguentCasella(mat, 'o');
			
			puntuacio = maxim;
		}
		
		return puntuacio;
	}
	
	private static void seguentCasella(Matriu mat, char dir) {
		switch (dir)
		{
			case 'n':
				mat.setActual(mat.casellaNord().getPunt());
				break;
			case 's':
				mat.setActual(mat.casellaSud().getPunt());
				break;
			case 'e':
				mat.setActual(mat.casellaEst().getPunt());
				break;
			case 'o':
				mat.setActual(mat.casellaOest().getPunt());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + dir);
		}
		mat.casellaActual().setAccedida(true);
	}

	private static boolean resoldreLaberintExhaustiva(Matriu matriu) {
		matriu.setActual(matriu.getInici());
		matriu.casellaActual().setAccedida(true);
		Recorregut recorregut = new Recorregut(matriu.casellaActual());
		camins = 0;
		
		try {	
			fw.write("\nAlgorisme Recursiu\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		laberintRecursiu(matriu, matriu.casellaActual().getValor(), recorregut);
		
		if ( recorregut.getPuntuacio() > 0)
		{
			System.out.println(recorregut.getPuntuacio());
			return true;
		}
		return false;
	}

	private static boolean laberintRecursiu(Matriu matriu, int puntuacio, Recorregut recorregut) {
		int dir = 0;	
		int puntuacioAux = puntuacio;
		boolean casella;
		Moviment auxActual;
		while (dir <= 3)
		{
			if ( matriu.getFi().equals(matriu.getActual()) )	
			{
				recorregut.setPuntuacio(puntuacio);
				escriureRecorregut(recorregut);
				camins++;
				recorregut.borrarUltimMoviment();
				matriu.casellaActual().setAccedida(false);
				matriu.setActual(recorregut.ultimMoviment().getPunt());
				return true;
			}
			casella = seguentCasella(matriu, dir, puntuacio);
			if (casella)
			{
				puntuacioAux = resultat(matriu.casellaActual(), puntuacio);
				auxActual = new Moviment(matriu.casellaActual().getPunt());
				recorregut.afegirMoviment(auxActual);
				laberintRecursiu(matriu, puntuacioAux, recorregut);
				puntuacioAux = puntuacio;
			}
			dir++;
		}
		recorregut.borrarUltimMoviment();
		matriu.casellaActual().setAccedida(false);
		matriu.setActual(recorregut.ultimMoviment().getPunt());
		
		return false;
	}


	
	private static boolean seguentCasella(Matriu mat, int dir, int puntuacio) {
		switch (dir)
		{
			case 0:
				if (!esAccessible(mat, puntuacio, mat.casellaNord()))
					return false;
				mat.setActual(mat.casellaNord().getPunt());
				break;
			case 1:
				if (!esAccessible(mat, puntuacio, mat.casellaSud()))
					return false;
				mat.setActual(mat.casellaSud().getPunt());
				break;
			case 2:
				if (!esAccessible(mat, puntuacio, mat.casellaEst()))
					return false;
				mat.setActual(mat.casellaEst().getPunt());
				break;
			case 3:
				if (!esAccessible(mat, puntuacio, mat.casellaOest()))
					return false;
				mat.setActual(mat.casellaOest().getPunt());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + dir);
		}
		mat.casellaActual().setAccedida(true);
		return true;
	}

	private static boolean esAccessible(Matriu mat, int puntuacio, Caselles c) {
		if (c != null && !c.isAccedida() && resultat(c, puntuacio) > 0)
			return true;
		
		return false;
	}
	
	private static void escriureRecorregut(Object recorregut) {

			if (recorregut instanceof Recorregut)
				System.out.println(((Recorregut) recorregut).toString() + "\n" +
						"\tPuntuacio: " + ((Recorregut) recorregut).getPuntuacio() + "\n");
			else if (recorregut instanceof String)
				System.out.println("Algorisme Avid\n" + ((String) recorregut) + "\n");

	}
}
