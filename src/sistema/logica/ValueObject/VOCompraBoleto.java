package sistema.logica.ValueObject;

public class VOCompraBoleto extends VOBoletoPasajero {

	private String CodigoPaseo;

	
	public VOCompraBoleto() {
		super();
		this.CodigoPaseo = "";
		
	}

	

	public VOCompraBoleto(String nombre, int edad, int celular, boolean esEspecial, Double descuento,
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

	
	
	
	
	
}
