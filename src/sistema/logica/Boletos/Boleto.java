package sistema.logica.Boletos;

public class Boleto {
	private 
		int numeroBoleto;
		String nombrePasajero;
		int edad;
		String numeroCel;
		double precio;
		double descuento;
		
		
	public Boleto(int numeroBoleto, String nombrePasajero, int edad, String numeroCel, double precio,
				double descuento) {
			this.numeroBoleto = numeroBoleto;
			this.nombrePasajero = nombrePasajero;
			this.edad = edad;
			this.numeroCel = numeroCel;
			this.precio = precio;
			this.descuento = descuento;
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
	
	public double getDescuento() {
			return descuento;
	}
	
	public void setDescuento(double descuento) {
			this.descuento = descuento;
	}
	
		
	public static void main (String args[]) {
		Boleto b1 = new Boleto(1, "Persona1", 17, "099000000", 15.0, 0.75);
		System.out.println(b1.getNumeroBoleto());
		System.out.println(b1.getNombrePasajero());
		System.out.println(b1.getEdad());
		System.out.println(b1.getNumeroCel());
		System.out.println(b1.getPrecio());
		System.out.print(b1.getDescuento());
	}
			
	

}
