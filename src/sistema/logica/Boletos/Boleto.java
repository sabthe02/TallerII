package sistema.logica.Boletos;
import java.io.Serializable;


public class Boleto implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private 
		int numeroBoleto;
		String nombrePasajero;
		int edad;
		String numeroCel;
		double precio;
		
		
	public Boleto(int numeroBoleto, String nombrePasajero, int edad, String numeroCel, double precio) {
			this.numeroBoleto = numeroBoleto;
			this.nombrePasajero = nombrePasajero;
			this.edad = edad;
			this.numeroCel = numeroCel;
			this.precio = precio;
	}
	
	public Boleto() {
		this.numeroBoleto = 0;
		this.nombrePasajero = "";
		this.numeroCel = "";
		this.precio = 0;
}

	public int getNumeroBoleto() {
		return numeroBoleto;
}
	public String getNombrePasajero() {
		return nombrePasajero;
	}
	
	public void setNombrePasajero(String nombrePasajero) {
		this.nombrePasajero = nombrePasajero;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
			this.edad = edad;
	}
	
	public String getNumeroCel() {
			return numeroCel;
	}
	
	public void setNumeroCel(String numeroCel) {
			this.numeroCel = numeroCel;
	}
	
	public double getPrecio() {
			return precio;
	}
	public void setPrecio(double precio) {
			this.precio = precio;
	}
	
	//public double montoRecaudadoPaseo ()
		
	public static void main (String args[]) {
		Boleto b1 = new Boleto(1, "Persona1", 17, "099000000", 15.0);
		System.out.println(b1.getNumeroBoleto());
		System.out.println(b1.getNombrePasajero());
		System.out.println(b1.getEdad());
		System.out.println(b1.getNumeroCel());
		System.out.println(b1.getPrecio());
	}
			
	

}