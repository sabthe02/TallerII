package sistema.logica.ValueObject;

public class VOBoletoPasajero {

	private String Nombre;
	private int Edad;
	private int Celular;
	private boolean EsEspecial;
	private Double Descuento;
	
	
	public VOBoletoPasajero() {
		// TODO Auto-generated constructor stub
		this.Nombre = "";
		this.Edad = 18;
		this.Celular = 0;
	}


	


	public VOBoletoPasajero(String nombre, int edad, int celular, boolean esEspecial, Double descuento) {
		super();
		Nombre = nombre;
		Edad = edad;
		Celular = celular;
		EsEspecial = esEspecial;
		Descuento = descuento;
	}





	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public int getEdad() {
		return Edad;
	}


	public void setEdad(int edad) {
		Edad = edad;
	}


	public int getCelular() {
		return Celular;
	}


	public void setCelular(int celular) {
		Celular = celular;
	}
	
	public boolean isEsEspecial() {
		return EsEspecial;
	}

	public void setEsEspecial(boolean esEspecial) {
		EsEspecial = esEspecial;
	}

	public Double getDescuento() {
		return Descuento;
	}

	public void setDescuento(Double descuento) {
		Descuento = descuento;
	}
	
	
	
	
	
}
