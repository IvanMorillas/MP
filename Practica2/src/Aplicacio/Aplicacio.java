package Aplicacio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Dades.*;

public class Aplicacio {
	
	static Scanner teclat=new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {		
		TADcjtRecursos cjt = new InfoDin();
		TADcjtRecursos me = new InfoEst();
		Data data = new Data();
		int dia, mes, any;
		long temps, startTime, endTime;
		boolean finalPro = false;
		boolean carregat = false;
		
		do {
			int cas = mostraOpcions();
			switch(cas) {
			case 1:	int tipMem = mostraOpcions2();
					switch(tipMem) {
					case 1: System.out.println("Indica el número de línies a llegir del fitxer (màxim 1000)");
							int numLinies1 = teclat.nextInt();
							teclat.nextLine();
							String[] dataset1 = llegirLiniesFitxer(numLinies1);
							me.llegirFitxer(dataset1, me);
							me.imprimir();
							carregat = true;
							break;
					
					case 2: System.out.println("Indica el número de línies a llegir del fitxer (màxim 1000)");
							int numLinies = teclat.nextInt();
							teclat.nextLine();
							String[] dataset = llegirLiniesFitxer(numLinies);
							cjt.llegirFitxer(dataset, cjt);
							cjt.imprimirNodes();
							carregat = true;
							break;
					}
					break;
					
			case 2: if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1: startTime = System.nanoTime();
								me.eliminarTot();
								endTime = System.nanoTime();
								System.out.println("Dades esborrades.");
								temps = (endTime-startTime);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								carregat = false;
								break;
				
						case 2: startTime = System.nanoTime();
								cjt.eliminarTot();
								endTime = System.nanoTime();
								System.out.println("Dades esborrades.");
								temps = (endTime-startTime);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								carregat = false;
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
			
			case 3:	if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1:System.out.println("Posa la data: ");
									do {
										System.out.println("Dia: ");
										dia = teclat.nextInt();
										System.out.println("Mes: ");
										mes = teclat.nextInt();
										System.out.println("Any: ");
										any = teclat.nextInt();
										if (!data.esDataCorrecta(dia, mes, any)) {
											System.out.println("Data incorrecta. Torna a provar.\n");
										}
									} while (!data.esDataCorrecta(dia, mes, any));
								startTime = System.nanoTime();
								me.eliminarData(new Data (dia, mes, any));
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
									if (me.getNumElem() == 0) {
										carregat = false;
									}
									me.imprimir();
								break;
								
						case 2: System.out.println("Posa la data: ");
									do {
										System.out.println("Dia: ");
										dia = teclat.nextInt();
										System.out.println("Mes: ");
										mes = teclat.nextInt();
										System.out.println("Any: ");
										any = teclat.nextInt();
											if (!data.esDataCorrecta(dia, mes, any)) {
												System.out.println("Data incorrecta. Torna a provar.\n");
											}
									} while (!data.esDataCorrecta(dia, mes, any));
								startTime = System.nanoTime();
								cjt.eliminarData(new Data (dia, mes, any));
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
									if (cjt.getNumElem() == 0) {
										carregat = false;
									}
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
			
			case 4:	if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1: System.out.println("Posa el recurs: ");
								String recurs1 = teclat.nextLine();						
								startTime = System.nanoTime();
								String[] llistaU1 = me.retornLlistaUsuari(recurs1);
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Els usuaris que han consultat el recurs " +recurs1+ " son: ");
								me.imprimirLlista(llistaU1);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
						
						case 2: System.out.println("Posa el recurs: ");
								String recurs = teclat.nextLine();
								
								startTime = System.nanoTime();
								String[] llistaU = cjt.retornLlistaUsuari(recurs);
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Els usuaris que han consultat el recurs " +recurs+ " son: ");
								cjt.imprimirLlista(llistaU);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
					
			case 5: if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1: System.out.println("Posa la data: ");
									do {
										System.out.println("Dia: ");
										dia = teclat.nextInt();
										System.out.println("Mes: ");
										mes = teclat.nextInt();
										System.out.println("Any: ");
										any = teclat.nextInt();
										if (!data.esDataCorrecta(dia, mes, any)) {
											System.out.println("Data incorrecta. Torna a provar.\n");
										}
									} while (!data.esDataCorrecta(dia, mes, any));
			
								System.out.println("Posa el recurs: ");
								String recurs1 = teclat.next();
						
								startTime = System.nanoTime();
								String[] llistaUD1 = me.retornLlistaUsuariData(recurs1, new Data (dia, mes, any));
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Els usuaris que han consultat el recurs " +recurs1+ " en la data " +dia+"/"+mes+"/"+any+ " son: ");
								me.imprimirLlista(llistaUD1);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
					
						case 2: System.out.println("Posa la data: ");
									do {
										System.out.println("Dia: ");
										dia = teclat.nextInt();
										System.out.println("Mes: ");
										mes = teclat.nextInt();
										System.out.println("Any: ");
										any = teclat.nextInt();
											if (!data.esDataCorrecta(dia, mes, any)) {
												System.out.println("Data incorrecta. Torna a provar.\n");
											}
									} while (!data.esDataCorrecta(dia, mes, any));
									
								teclat.nextLine();
								System.out.println("Posa el recurs: ");
								String recurs = teclat.nextLine();
								
								startTime = System.nanoTime();
								String[] llistaUD = cjt.retornLlistaUsuariData(recurs, new Data (dia, mes, any));
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("Els usuaris que han consultat el recurs " +recurs+ " en la data " +dia+"/"+mes+"/"+any+ " son: ");
								cjt.imprimirLlista(llistaUD);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
			
			case 6: if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1:startTime = System.nanoTime();
								String recursCon1 = me.recursConsultat();
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("El recurs més consultat pels usuaris és: " +recursCon1);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
			
						case 2: startTime = System.nanoTime();
								String recursCon = cjt.recursConsultat();
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println("El recurs més consultat pels usuaris és: " +recursCon);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
			
			case 7: if (carregat) {
						tipMem = mostraOpcions2();
						switch(tipMem) {
						case 1: System.out.println("Posa l'usuari: ");
								String usuari1 = teclat.nextLine();
								
								startTime = System.nanoTime();
								String[] recUsuari1 = me.recursosConsult(usuari1);
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println(usuari1+" ha consultat els següents recursos: ");
								me.imprimirLlista(recUsuari1);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
			
						case 2: System.out.println("Posa l'usuari: ");
								String usuari = teclat.nextLine();
								
								startTime = System.nanoTime();
								String[] recUsuari = cjt.recursosConsult(usuari);
								endTime = System.nanoTime();
								temps = (endTime-startTime);
								System.out.println(usuari+" ha consultat els següents recursos: ");
								cjt.imprimirLlista(recUsuari);
								System.out.println("Temps utilizat: " +temps+ " nanosegons.");
								break;
						}
					} else {
						System.out.println("Primer afegeix les dades.\n");
					}
					break;
					
			case 0: finalPro = true;
					break;
			}
		} while (!finalPro);
	}
	
	public static int mostraOpcions() {
		System.out.println("Operacions:");
		System.out.println("1. Afegir les dades.");
		System.out.println("2. Esborrar totes les dades.");
		System.out.println("3. Esborrar les dades en una data concreta.");
		System.out.println("4. Llista d’usuaris que han consultat cert recurs.");
		System.out.println("5. Llista d’usuaris que han consultat cert recurs en una certa data.");
		System.out.println("6. Recurs més consultat pels usuaris.");
		System.out.println("7. Recursos que ha consultat cert usuari.");
		System.out.println("0. Finalitzar programa.");
		System.out.println("Opció escollida: ");
		return(llegirEnter(7));
	}
	
	public static int mostraOpcions2() {
		System.out.println("\nTipus de memòria:");
		System.out.println("1. Estàtica.");
		System.out.println("2. Dinàmica.");
		System.out.println("0. Tornar.");
		System.out.println("Opció escollida: ");
		return(llegirEnter(2));
	}
	
	public static int llegirEnter(int max) {
		int aux=0;
		boolean llegit=false;
			while (!llegit) {
				try {
					aux=Integer.parseInt(teclat.nextLine());
					if ((aux>max)||(aux<0)) throw new NumberFormatException();
					llegit=true;
				}
				catch(NumberFormatException e) {
					System.out.println("Has d'indicar un valor numeric entre 0 i "+max+ ". Torna a provar.\n");
				}
			}
		return(aux);
	}
	
	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 1000)
			nLinies = 1000;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("Informacio.csv"));

		f.nextLine(); //Saltar la capçalera
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		
		return result;
	}
}

