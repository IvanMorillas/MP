package Dades;

/**
 * Clase con los metodos de memoria estatica que implementa el TAD 
 * @author 
 *
 */
public class InfoEst implements TADcjtRecursos {
	
	//Atributos
	private int nAlies;
	private Dades taula[] = new Dades[nAlies];
	private int numElem;
	
	/**
	 * Constructor
	 */
	public InfoEst () {
		numElem = 0;
		nAlies = 1000;
	}
	
	@Override
	//Metodo para leer los datos de un fichero 
	public void llegirFitxer(String[] dataset, TADcjtRecursos aux) {
		for (int i = 0; i < dataset.length; i++) {
			String[] separador = dataset[i].split(",");
			String alies = separador[0];
			String recurs = separador[1];
			String dataux = separador[2];
			int hora = Integer.parseInt(separador[3]);
			int minut = Integer.parseInt(separador[4]);
			
			String[] separador2 = dataux.split("/");
			int dia = Integer.parseInt(separador2[0]);
			int mes = Integer.parseInt(separador2[1]);
			int any = Integer.parseInt(separador2[2]);
			Data data = new Data(dia, mes, any);

			aux.afegir(alies, recurs, data, hora, minut);
		}
	}
	
	@Override 
	//Metodo que añade una consulta nueva 
	public void afegir (String alies, String recurs, Data data, int hora, int minut) {
		Dades consulta = new Dades(alies, recurs, data, hora, minut);
		if (numElem >= taula.length) {
			Dades aux[] = new Dades [numElem + 5];
			for(int i=0; i<numElem; i++) {
				aux[i] = taula[i];
			}
			taula = aux;
		}
		taula[numElem] = new Dades (consulta.getAlies(), consulta.getRecurs(), consulta.getData(), consulta.getHora(), consulta.getMinut());
		numElem++;
	}
	
	@Override
	//Metodo que elimina todas las consultas existentes
	public void eliminarTot() {
		taula = new Dades[0];
		numElem = 0;
		nAlies = 1000;
	}
	
	@Override
	//Metodo que elimina una consulta con una fecha concreta
	public void eliminarData(Data data) {
		Data data2;
		for (int i=0; i<numElem; i++) {
			data2 = taula[i].getData();
			if (data.esIgual(data2)) {
				for (int j=i; j<numElem-1; j++) {
					taula[j] = taula[j+1];
				}
				numElem--;
				nAlies--;
			}
		}
	}
	
	@Override
	//Metodo que dado el nombre de un recurso nos da la lista con los usuarios que lo han consultado 
	public String[] retornLlistaUsuari(String recurs) {
		String llistaU[]= new String [nAlies];
		String recurs2, nomUsuari;
		int j = 0;
		for (int i=0; i<numElem; i++) {
			recurs2 = taula[i].getRecurs();
			if (recurs.equals(recurs2)) {
				nomUsuari = taula[i].getAlies();
				llistaU[j] = nomUsuari;
				j++;
			}
		}
		return (llistaU);
	}
	
	@Override
	//Metodo que dados un recurso y fecha nos da una lista con los usuarios que los han consultado
	public String[] retornLlistaUsuariData(String recurs, Data data) {
		String llistaUD[] = new String[nAlies];
		String recurs2, nomUsuari;
		Data data2;
		int j = 0;
		for (int i=0; i<numElem; i++) {
			recurs2 = taula[i].getRecurs();
			data2 = taula[i].getData();
			if ((recurs.equals(recurs2)) && (data.esIgual(data2))) {
				nomUsuari = taula[i].getAlies();
				llistaUD[j] = nomUsuari;
				j++;
			}
		}
		return (llistaUD);
	}
	
	@Override
	//Metodo que nos da el recurso mas consultado
	public String recursConsultat() {
		String recurs, recurs2, mesVisitat ="";
		int cont=0;
		int max=0, x=0;
		for (int i=0; i<numElem; i++) {
			recurs = taula[i].getRecurs();
			for (int j=0; j<numElem; j++) {
				recurs2 = taula[j].getRecurs();
				if (recurs.equals(recurs2)) {
					cont++;
				}
			}
			x = cont;
			if (x>max) {
				max = x;
				mesVisitat = taula[i].getRecurs();
			}
		}
		return (mesVisitat);
	}
	
	@Override
	// Metodo que dado un alies nos da los recursos que ha consultado
	public String[] recursosConsult(String alies) {
		String llistaC[] = new String[nAlies];
		String nom, recurs;
		int j = 0;
		for (int i=0; i<numElem; i++) {
			nom = taula[i].getAlies();
			if (nom.equals(alies)) {
				recurs = taula[i].getRecurs();
				llistaC[j] = recurs;
				j++;
			}
		}
		return (llistaC);
	}
	
	@Override 
	// Metodo que nos imprime el nombre de los datos de la tabla
	public void imprimirLlista (String taula[]) {
		String nom="";
		for (int i=0; i<numElem; i++) {
			if (taula[i]!=null) {
				nom = taula[i];
				System.out.println(nom);
			}
		}
	}
	
	//Metodo que imprime todos los datos
	public void imprimir() {
		String alies, recurs;
		Data data;
		int hora, minut;
		for (int i=0; i<numElem; i++) {
			if (taula[i] != null) {
				alies = taula[i].getAlies();
				recurs = taula[i].getRecurs();
				data = taula[i].getData();
				hora = taula[i].getHora();
				minut = taula[i].getMinut();
				System.out.println("Alies: "+alies+ " Recurs: "+recurs+" Data: "+data+" Hora: "+hora+":"+minut);
			}
		}
	}
	
	@Override
	public int getNumElem() {
		return numElem;
	}
	
	@Override
	public void imprimirNodes() {
		//Mirar de arreglar esto. Quitarlo
	}
	
	@Override
	public boolean pertanyData(Data data) {
		return true;
	}
}

