package sistema.logica.ValueObject;

public class VOMinivanListado extends VOMinivan{
	
	private int CantidadPaseos;
	
	
	public VOMinivanListado() {
		// TODO Auto-generated constructor stub
		super();
		this.CantidadPaseos = 0;
	}
	

	public VOMinivanListado(String matricula, String marca, String modelo, int cantidadAsientos, int cantidadPaseos) {
		super(matricula, marca, modelo, cantidadAsientos);
		CantidadPaseos = cantidadPaseos;
	}




	public int getCantidadPaseos() {
		return CantidadPaseos;
	}


	public void setCantidadPaseos(int cantidadPaseos) {
		CantidadPaseos = cantidadPaseos;
	}

	
	

	
	
	

}
