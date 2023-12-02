package Dades;

public class Node {
	private String alies;
	private String recurs;
	private Data data = new Data();
	private int hora;
	private int minut;
	private Node seg;
	
	public Node (String alies, String recurs, Data data, int hora, int minut, Node seg) {
		this.alies = alies;
		this.recurs = recurs;
		this.data = data;
		this.hora = hora;
		this.minut = minut;
		this.seg = seg;
	}
	
	public String getAlies() {
		return alies;
	}

	public void setAlies(String alies) {
		this.alies = alies;
	}

	public String getRecurs() {
		return recurs;
	}

	public void setRecurs(String recurs) {
		this.recurs = recurs;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinut() {
		return minut;
	}

	public void setMinut(int minut) {
		this.minut = minut;
	}

	public Node getSeg() {
		return seg;
	}

	public void setSeg(Node seg) {
		this.seg = seg;
	}
	
	public boolean esIgualN(Node node) {
		if (this.alies == node.getAlies() && this.recurs == node.getRecurs() && this.data == node.getData() && this.hora == node.getHora() && this.minut == node.getMinut() && this.seg == node.getSeg()) {
			return true;
		}
		return false;
	}
	
	public Node copia() {
		return new Node(alies, recurs, data, hora, minut, seg);
	}
}
