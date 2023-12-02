
public class Matriu {
	
	private Caselles [][] matriu;		
	private int moviments;				
	private Coordenades inici;			
	private Coordenades fi;			
	private int dimFila;				
	private int dimCol;			
	private Coordenades actual;			
	
	public Matriu(int dimFila, int dimCol, int filaInici, int colInici, int filaFi, int colFi) {
		this.dimFila = dimFila;
		this.dimCol = dimCol;
		this.inici = new Coordenades(colInici, filaInici);
		this.fi = new Coordenades(colFi, filaFi);
		matriu = new Caselles [dimFila][dimCol];
		moviments = 0;
	}
	
	public Caselles consultaCasella(Coordenades punt) {
		for (int i = 0; i < dimFila; i++) {
			for (int j = 0; j < dimCol; j++) {
				if ( matriu[i][j].getPunt().equals(punt) )
					return matriu[i][j];
			}
			
		}
		return null;
	}
	
	public Caselles casellaActual() {
		return matriu[actual.getY()][actual.getX()];
	}
	
	public Caselles casellaNord() {
		if (actual.getY() - 1 < 0)
			return null;
		return matriu[actual.getY() - 1][actual.getX()];
	}
	
	public Caselles casellaSud() {
		if (actual.getY() + 1 >= dimFila)
			return null;
		return matriu[actual.getY() + 1][actual.getX()];
	}

	public Caselles casellaEst() {
		if (actual.getX() + 1 >= dimCol)
			return null;
		return matriu[actual.getY()][actual.getX() + 1];
	}

	public Caselles casellaOest() {
		if (actual.getX() - 1 < 0)
			return null;
		return matriu[actual.getY()][actual.getX() - 1];
	}
	
	public Coordenades getFi() {
		return fi;
	}
	
	public Coordenades getInici() {
		return inici;
	}
	
	public Coordenades getActual() {
		return actual;
	}
	
	public int getDimCol() {
		return dimCol;
	}
	
	public int getDimFila() {
		return dimFila;
	}
	
	public Caselles[][] getMatriu() {
		return matriu;
	}
	
	public int getMoviments() {
		return moviments;
	}
	
	public void setActual(Coordenades actual) {
		this.actual = actual;
	}
	
	public void setFi(Coordenades fi) {
		this.fi = fi;
	}
	
	public void setInici(Coordenades inici) {
		this.inici = inici;
	}
	
	public void setDimCol(int dimCol) {
		this.dimCol = dimCol;
	}
	
	public void setDimFila(int dimFila) {
		this.dimFila = dimFila;
	}
	
	public void setMoviments(int moviments) {
		this.moviments = moviments;
	}
}
