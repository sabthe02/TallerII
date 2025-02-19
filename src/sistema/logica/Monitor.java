package sistema.logica;

public class Monitor {

	private 
	       int cantLectores;
	       boolean escribiendo;
	       private static final int maxLect = 10; // es agregado, para que no sea infinito el monitor

	public Monitor() {

		this.cantLectores = 0;
		this.escribiendo = false;
	}

	public synchronized void comienzoLectura() {
		while (this.escribiendo || this.cantLectores >= Monitor.maxLect) {
			try {
				wait();
			} catch (InterruptedException e) { 
				Thread.currentThread().interrupt(); // está bien pero es opcional
			}
		}
		this.cantLectores++;
	}

	public synchronized void terminoLectura() {
		this.cantLectores--;
		if (cantLectores == 0)
		notify();
	}
	
	public synchronized void comienzoEscritura() {
		while(this.escribiendo || this.cantLectores > 0) {
			try {
				wait();
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt(); // está bien pero es opcional
					
			} 
			
		}
		this.escribiendo = true;
	}
	
	public synchronized void terminoEscritura() {
		this.escribiendo = false;
		notify();
	}

}
