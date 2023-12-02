
public class Recorregut {
	private Moviment inici;
	private int puntuacio;
	
	public Recorregut(Caselles casellaInici) {
		inici = new Moviment(casellaInici.getPunt());
	}
	
	public Recorregut(Coordenades punt) {
		inici = new Moviment(punt);
	}
	
	public void afegirMoviment(Moviment mov) {
		Moviment node = inici;
		while (node.getNext() != null)
		{
			node = node.getNext();
		}
		node.setNext(mov);
	}
	
	public void borrarUltimMoviment() {
		Moviment node = inici;
		if (node.getNext() != null)
		{
			while (node.getNext().getNext() != null)
			{
				node = node.getNext();
			}
			node.setNext(null);
		}
		else
			node.setNext(null);
	}
	
	public Moviment ultimMoviment() {
		Moviment node = inici;
		if (node != null)
		{
			while (node.getNext() != null)
			{
				node = node.getNext();
			}
		}
		
		return node;
	}
	
	public String recorregut() {
		Moviment node = inici;
		String str = "";
		if (node != null)
			str += node.getPunt();
		while (node != null)
		{
			node = node.getNext();
			str += " -> " + node.getPunt();
		}
		
		return str;
	}
	
	public Recorregut copia() {
		Recorregut rec = new Recorregut(this.inici.getPunt());
		Moviment node = inici;
		Moviment nou = rec.getInici();
		while (node.getNext() != null)
		{
			node = node.getNext();
			nou.setNext( new Moviment(node.getPunt()) );
			nou = nou.getNext();
		}
		
		return rec;
	}
	
	@Override
	public String toString() {
		Moviment node = inici;
		String str = "";
		
		str += node;
		while (node.getNext() != null)
		{
			node = node.getNext();
			str += " -> " + node;
		}
		return str;
	}
	
	public Moviment getInici() {
		return inici;
	}
	
	public int getPuntuacio() {
		return puntuacio;
	}
	
	public void setInici(Moviment inici) {
		this.inici = inici;
	}
	
	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}
}
