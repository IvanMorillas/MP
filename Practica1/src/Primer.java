
public class Primer {
	private long N;
	private long P;
	private long T;
	
	public Primer (long N, long P, long T) {
		this.N = N;
		this.P = 2;
		this.T = 0;
	}
	
	public Primer buscarPrimerCutre (Primer aux, String[] dataset, int vueltas) {
		int den, cont;
		aux.N = Long.parseLong(dataset[vueltas]);
		int preN = 2;
		long startTime = System.nanoTime();
			while (preN <= aux.N) {
				den = 1;
				cont = 0;
					while (den <= preN) {
							if (preN % den == 0) {
								cont++;
							}
						den++;
					}
					if (cont == 2) {
						aux.P = preN;
					}
				preN++;
			}
		long endTime = System.nanoTime();
		aux.T = endTime-startTime;
		return (aux);
	}
	
	public Primer buscarPrimerImpars (Primer aux) {
		int preN = 2;
		int den, cont;
		long startTime = System.nanoTime();
			while (preN <= aux.N) {
				den = 1;
				cont = 0;
					while (den <= preN) {
							if (preN % den == 0) {
								cont++;
							}
						den++;
					}
					if (cont == 2) {
						aux.P = preN;
					}
				if (preN == 2) {
					preN++;
				}
				else {
					preN = preN + 2;
				}
			}
		long endTime = System.nanoTime();
		aux.T = endTime-startTime;
		return (aux);
	}
	
	public Primer buscarPrimerNdiv2 (Primer aux) {
		int preN = 2;
		int den, cont;
		long startTime = System.nanoTime();
			while (preN <= aux.N) {
				den = 1;
				cont = 0;
				
					while (den <= (preN/2)) {
							if (preN % den == 0) {
								cont++;
							}
						den++;
					}
					if (cont < 2) {
						aux.P = preN;
					}
				if (preN == 2) {
					preN++;
				}
				else {
					preN = preN + 2;
				}
			}
		long endTime = System.nanoTime();
		aux.T = endTime-startTime;
		return (aux);
	}

	public Primer buscarPrimerArrel (Primer aux) {
		int preN = 2;
		int den, cont;
		long startTime = System.nanoTime();
			while (preN <= aux.N) {
				den = 1;
				cont = 0;
					while (den <= (Math.sqrt(preN))) {
							if (preN % den == 0) {
								cont++;
							}
						den++;
					}
					if (cont < 2) {
						aux.P = preN;
					}
				if (preN == 2) {
					preN++;
				}
				else {
					preN = preN + 2;
				}
			}
		long endTime = System.nanoTime();
		aux.T = endTime-startTime;
		return (aux);
	}
	
	public String toString() {
		return ("\nN: " +N+ "\nP: " +P+ "\nT: " +T+ " nanosegons");
	}
}