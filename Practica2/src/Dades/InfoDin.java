package Dades;

public class InfoDin implements TADcjtRecursos{
	private Node primer;
	private int numElem;
	private int nAlies;
	private int nLlista;

	public InfoDin() {
		primer = null;
		numElem = 0;
		nAlies = 0;
		nLlista = 0;
	}
	
	@Override
	public void llegirFitxer (String[] dataset, TADcjtRecursos aux) {
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
	public void afegir(String alies, String recurs, Data data, int hora, int minut) {
		Node nouNode = new Node(alies, recurs, data, hora, minut, primer);
			//Si hay 2 o más nodos en la lista utilizo este condicional
			if (numElem >= 2) {
				Node actualNode = primer;
				boolean acabat = false;
				//Miro si el nuevo nodo es más pequeño que el primer nodo
				if (nouNode.getData().esDataInferiorOigual(primer.getData())) {
					primer = nouNode;
					numElem++;
					nAlies++;
					acabat = true;
				}
				//Miro si el nuevo nodo es más grande que el último nodo
				if (!acabat) {
					while (actualNode.getData().esDataInferiorOigual(nouNode.getData()) && (!acabat)) {
		            		if (actualNode.getSeg() == null) {
			                    actualNode.setSeg(nouNode);
			                    actualNode.getSeg().setSeg(null);
			            		acabat = true;
			            		numElem++;
								nAlies++;
			            	} else if (!acabat) {
			            		actualNode = actualNode.getSeg();
			            	}
					}
				}
				//Pongo el nodo entre los nodos que le toquen si los dos condicionales anteriores no sirven
				if (!acabat) {
					actualNode = primer;
					Node anteriorNode = null;
						while (actualNode.getData().esDataInferiorOigual(nouNode.getData()) && (!acabat)) {
						    Node auxNode2 = actualNode.getSeg();
						    actualNode.setSeg(nouNode);
					        actualNode.getSeg().setSeg(auxNode2);
						    	if (actualNode.getData().esDataInferiorOigual(nouNode.getData())) {
						    		anteriorNode = actualNode;
						    		actualNode = actualNode.getSeg();
						        } else {
						        	acabat = true;
							    }
						    	
						    	while ((actualNode == nouNode) && (actualNode.getSeg().getData().esDataInferiorOigual(actualNode.getData())) && (!acabat)) {
							    		if ((actualNode == nouNode) && (actualNode.getSeg().getData().esDataInferiorOigual(actualNode.getData()))) {
								    		actualNode = actualNode.getSeg();
								    		auxNode2 = actualNode.getSeg();
								    		actualNode.setSeg(nouNode);
								    		actualNode.getSeg().setSeg(auxNode2);
								    		anteriorNode.setSeg(actualNode);
								    	}
							    	actualNode = actualNode.getSeg();
							    	anteriorNode = anteriorNode.getSeg();
							    		if ((actualNode.getData().esDataInferiorOigual(actualNode.getSeg().getData()))) {
							    			acabat = true;
							    		}
						    	}
						}
					numElem++;
					nAlies++;
				}
			}
			//Si la lista está vacía o solo hay un nodo, utilizo este condicional
			if ((numElem == 1) && (primer.getData().esDataInferiorOigual(nouNode.getData()))) {
				primer.setSeg(nouNode);
				primer.getSeg().setSeg(null);
				numElem++;
				nAlies++;
			} else if ((numElem == 0) || ((numElem == 1) && (nouNode.getData().esDataInferiorOigual(primer.getData())))) {
				primer = nouNode;
				numElem++;
				nAlies++;
			}
	}

	@Override
	public void eliminarTot() {
		primer = null;
		numElem = 0;
		nAlies = 0;
	}
	
	@Override
	public void eliminarData(Data data) {
		if (primer != null) {
			Node actualNode = primer;
			Node anteriorNode = primer;
			if (pertanyData(data)) {
				while (actualNode != null) {
						if (primer.getData().esIgual(data)) {
							while ((primer != null) && (primer.getData().esIgual(data))) {
								primer = primer.getSeg();
								actualNode = primer;
								numElem--;
								nAlies--;
							}
						} else if (actualNode.getData().esIgual(data)) {
							anteriorNode.setSeg(actualNode.getSeg());
							numElem--;
							nAlies--;
						}
						if (actualNode != null) {
							anteriorNode = actualNode;
							actualNode = actualNode.getSeg();
						}		
				}
			}
		}
	}
	
	@Override
	public boolean pertanyData(Data data) {
		boolean hiEs = false;
		Node actualNode = primer;
			if (primer != null) {
				while ((!hiEs) && (actualNode != null)) {
						if (actualNode.getData().esIgual(data)) {
							hiEs = true;
						} else if (!hiEs) {
							actualNode = actualNode.getSeg();
						}
				}
			}
		return (hiEs);
	}
	
	@Override
	public String[] retornLlistaUsuari (String recurs) {
		String[] llistaU = new String[nAlies];;
		Node actualNode = primer;
		nLlista = 0;
			while (actualNode != null) {
					if (actualNode.getRecurs().equals(recurs)) {
						llistaU[nLlista] = actualNode.getAlies();
						nLlista++;
					}
				actualNode = actualNode.getSeg();
			}
		return (llistaU);
	}
	
	@Override
	public String[] retornLlistaUsuariData (String recurs, Data data) {
		String[] llistaUD = new String[nAlies];
		Node actualNode = primer;
		nLlista = 0;
			while (actualNode != null) {
					if (actualNode.getRecurs().equals(recurs) && (actualNode.getData().esIgual(data))) {
						llistaUD[nLlista] = actualNode.getAlies();
						nLlista++;
					}
				actualNode = actualNode.getSeg();
			}
		return (llistaUD);
	}
	
	@Override
	public String[] recursosConsult (String alies) {
		String[] llistaR = new String[nAlies];
		Node actualNode = primer;
		nLlista = 0;
			while (actualNode != null) {
					if (actualNode.getAlies().equals(alies)) {
						llistaR[nLlista] = actualNode.getRecurs();
						nLlista++;
					}
				actualNode = actualNode.getSeg();
			}
		return (llistaR);
	}
	
	@Override
	public void imprimirNodes() {
		Node actualNode = primer;
			while (actualNode != null) {
					if ((actualNode.getMinut() < 10) && (actualNode.getMinut() >= 0)) {
						System.out.println("Alies: " +actualNode.getAlies()+ "  Recurs: " +actualNode.getRecurs()+ "  Data: " +actualNode.getData()+ "  Hora: " +actualNode.getHora()+ ":0" +actualNode.getMinut());
					} else {
						System.out.println("Alies: " +actualNode.getAlies()+ "  Recurs: " +actualNode.getRecurs()+ "  Data: " +actualNode.getData()+ "  Hora: " +actualNode.getHora()+ ":" +actualNode.getMinut());
					}
				actualNode = actualNode.getSeg();
		    }
	}
	
	@Override
	public void imprimirLlista(String[] llista) {
		int i = 0;
			while (i < nLlista) {
				System.out.println(llista[i]);
				i++;
		    }
	}
	
	@Override
	public String recursConsultat() {
		Node auxPrimer = primer.copia();
		Node anteriorNode = primer.copia();
		Node actualNode;
		int nElem = numElem;
		int contB = 0;
		String recursAux;
		String recursVisitat = null;
			if (primer != null) {
				while (nElem != 0) {
					int contA = 0;
					actualNode = auxPrimer;
					recursAux = actualNode.getRecurs();
						while (actualNode != null) {					
							if (auxPrimer.getRecurs().equals(recursAux)) {
								while ((auxPrimer != null) && (auxPrimer.getRecurs().equals(recursAux))) {
									auxPrimer = auxPrimer.getSeg();
									actualNode = auxPrimer;
									nElem--;
									contA++;
								}
							} else if (actualNode.getRecurs().equals(recursAux)) {
								anteriorNode.setSeg(actualNode.getSeg());
								nElem--;
								contA++;
							}
							if (actualNode != null) {
								anteriorNode = actualNode;
								actualNode = actualNode.getSeg();
							}
								
						}
						if (contA > contB) {
							recursVisitat = recursAux; 
							contB = contA;
						}
				}
			}
		return (recursVisitat);
	}
	
	@Override
	public int getNumElem() {
		return numElem;
	}
	
	@Override
	public void imprimir() {
		//Quitar esto
	}
}
