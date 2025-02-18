package sistema.logica;

public class Monitor {

	private 
	       int cantLectores;
	       boolean escribiendo;
	       private static final int maxLect = 10; 

	public Monitor() {

		this.cantLectores = 0;
		this.escribiendo = false;
	}

	public synchronized void comienzoLectura() {
		while (this.escribiendo || this.cantLectores >= Monitor.maxLect) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		this.cantLectores++;
	}

	public synchronized void terminoLectura() {
		this.cantLectores--;
		notifyAll();
	}
	
	public synchronized void comienzoEscritura() {
		while(this.escribiendo || this.cantLectores >= Monitor.maxLect) {
			try {
				wait();
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
					
			} 
			
		}
		this.escribiendo = true;
	}
	
	public synchronized void terminoEscritura() {
		this.escribiendo = false;
		notifyAll(); 
	}

}

//public Monitor void comienzoLectura

//terminoLectura
//comienzoEscritura
//terminoEscritura