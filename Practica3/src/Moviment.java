
public class Moviment {
	private Coordenades punt;
	private Moviment next;
	
	public Moviment(Coordenades punt) {
		this.punt = punt;
		next = null;
	}
	
	@Override
	public String toString() {
		return punt.toString();
	}
	
	public Moviment getNext() {
		return next;
	}
	
	public Coordenades getPunt() {
		return punt;
	}
	
	public void setNext(Moviment next) {
		this.next = next;
	}
	
	public void setPunt(Coordenades punt) {
		this.punt = punt;
	}
}
