
public class Caselles {
	private int operacio;				
	private int valor;					
	private Coordenades punt;
	private boolean accedida;
	
	public Caselles(int operacio, int valor, int x, int y) {
		this.operacio = operacio;
		this.valor = valor;
		setPunt(new Coordenades(x, y));
		accedida = false;
	}
	
	@Override
	public String toString() {
		return punt + " Accedida: " + isAccedida() +
				" Valor: " + valor;
	}
	
	public int getOperacio() {
		return operacio;
	}
	
	public int getValor() {
		return valor;
	}

	public Coordenades getPunt() {
		return punt;
	}
	
	public boolean isAccedida() {
		return accedida;
	}
	
	public void setPunt(Coordenades punt) {
		this.punt = punt;
	}
	
	public void setAccedida(boolean accedida) {
		this.accedida = accedida;
	}
	
	public void setOperacio(int operacio) {
		this.operacio = operacio;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
