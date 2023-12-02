
public class Coordenades {
	private int x;		
	private	int y;		
	
	
	public Coordenades(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this.x == ((Coordenades) obj).x && this.y == ((Coordenades) obj).y )
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "F: " + y + " C: " + x;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
