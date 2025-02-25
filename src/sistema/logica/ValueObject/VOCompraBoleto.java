package sistema.logica.ValueObject;

import java.io.Serializable;

public class VOCompraBoleto extends VOBoletoPasajero implements Serializable{

	private static final long serialVersionUID = 1L;
	private String CodigoPaseo;
	private String Edad;

	
	public VOCompraBoleto() {
		super();
		this.CodigoPaseo = "";
		
	}

	

	public VOCompraBoleto(String nombre, int edad, String celular, boolean esEspecial, Double descuento,
			String codigoPaseo) {
		super(nombre, edad, celular, esEspecial, descuento);
		CodigoPaseo = codigoPaseo;
	}



	public String getCodigoPaseo() {
		return CodigoPaseo;
	}

	public void setCodigoPaseo(String codigoPaseo) {
		CodigoPaseo = codigoPaseo;
	}



	public void setEdad(String edad) {
	    Edad = edad;
		
	}

	
	
	
}
