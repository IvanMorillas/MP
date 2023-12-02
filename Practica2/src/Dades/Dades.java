package Dades;

public class Dades {
	private String alies;
	private String recurs;
	private Data data = new Data();
	private int hora;
	private int minut;
	
	public Dades (String alies, String recurs, Data data, int hora, int minut) {
		this.alies = alies;
		this.recurs = recurs;
		this.data = data; 
		this.hora = hora;
		this.minut = minut;
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

	public boolean esIgualR(String recurs) {
		if (this.recurs == recurs) {
			return true;
		}
		return false;
	}
}
