package Dades;

public interface TADcjtRecursos {
	
	/**
	 * Metode per posar a la llista el que es llegeix del fitxer.
	 * @param dataset (dades del fitxer)
	 * @param aux (node auxiliar)
	 */
	void llegirFitxer (String[] dataset, TADcjtRecursos aux);
	/**
	 * Metode que serveix per afegir nodes a la llista enllaçada
	 * @param alies (nom)
	 * @param recurs (recurs consultat)
	 * @param data (quan es va consultar)
	 * @param hora (hora de la consulta)
	 * @param minut (minut de la consulta)
	 */
	void afegir(String alies, String recurs, Data data, int hora, int minut);
	/**
	 * Metode que serveix per eliminar tot el que hi ha a la llista i llista enllaçada
	 */
	void eliminarTot();
	/**
	 * Metode que serveix per eliminar de la llista i la llista enllaçada els recursos consultats en una determinada data
	 * @param data (data dels recursos que volem borrar)
	 */
	void eliminarData(Data data);
	/**
	 * Metode que ens diu si hi ha un element a la llista amb una determinada data
	 * @param data (data que volem comprobar si hi es a la llista)
	 * @return hiEs (si existeis aquesta data a la llista)
	 */
	boolean pertanyData(Data data);
	/**
	 * Metode que ens retorna una llista amb els usuaris que han consultat un recurs
	 * @param recurs (recurs que volem comprovar quins usuaris l'han consultat)
	 * @return llistaU (llista amb els usuaris que han consultat el recurs)
	 */
	String[] retornLlistaUsuari(String recurs);
	/**
	 * Metode que ens retorna una llista amb els usuaris que han consultat un recurs en una determinada data
	 * @param recurs (recurs al que volem comprovar quins usuaris l'han consultat)
	 * @param data (data al que volem comprovar quins usuaris l'han consultat)
	 * @return llistaUD
	 */
	String[] retornLlistaUsuariData(String recurs, Data data);
	/**
	 * Metode que ens retorna una llista amb els recursos que ha consultat cert usuari
	 * @param alies (usuari que volem comprovar quins recursos ha visitat)
	 * @return llistaR (llista amb els recursos que ha consultat un usuari)
	 */
	String[] recursosConsult(String alies);
	/**
	 * Metode que serveix per mostrar per pantalla la llista enllaçada
	 */
	void imprimirNodes();
	/**
	 * Metode que ens serveix per mostrar per pantalla una llista
	 * @param llista (llista que volem mostrar per pantalla)
	 */
	void imprimirLlista(String[] llista);
	/**
	 * Metode que ens retorna el metode mes visitat pels usuaris
	 * @return recursVisitat (recurs més visitat)
	 */
	String recursConsultat();
	/**
	 * Getter
	 * @return numElem (elements que hi ha a la llista)
	 */
	int getNumElem();
	/**
	 * Metode que serveix per mostrar per pantalla la llista
	 */
	void imprimir();
}
