package sistema.logica.ValueObject;

import java.io.Serializable;

public class VOListadoBoletos extends VOBoletoPasajero implements Serializable{

	private static final long serialVersionUID = 1L;
	private int NumeroBoleto;
	
	public VOListadoBoletos() {
		super();
		// TODO Auto-generated constructor stub
		this.NumeroBoleto = 0;
	}
	
	
	

	public VOListadoBoletos(String nombre, int edad, String celular, boolean esEspecial, Double descuento,
			int numeroBoleto) {
		super(nombre, edad, celular, esEspecial, descuento);
		NumeroBoleto = numeroBoleto;
	}




	public int getNumeroBoleto() {
		return NumeroBoleto;
	}

	public void setNumeroBoleto(int numeroBoleto) {
		NumeroBoleto = numeroBoleto;
	}
	
	
	
	
	
	
}
