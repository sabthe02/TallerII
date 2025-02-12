package sistema.logica.ValueObject;

import java.sql.Time;

public class VOPaseosListado extends VOPaseo{

	private int CantidadMaximaBoletosVendibles;
	private int CantidadBoletosDisponibles;
	
	
	public VOPaseosListado() {
		super();
		// TODO Auto-generated constructor stub
		this.CantidadBoletosDisponibles = 0;
		this.CantidadMaximaBoletosVendibles = 0;
	}


	public VOPaseosListado(String codigo, Time horaPartida, Time horaRegreso, Double precioBase, String destino,
			int cantidadMaximaBoletosVendibles, int cantidadBoletosDisponibles) {
		super(codigo, horaPartida, horaRegreso, precioBase, destino);
		CantidadMaximaBoletosVendibles = cantidadMaximaBoletosVendibles;
		CantidadBoletosDisponibles = cantidadBoletosDisponibles;
	}


	public int getCantidadMaximaBoletosVendibles() {
		return CantidadMaximaBoletosVendibles;
	}


	public void setCantidadMaximaBoletosVendibles(int cantidadMaximaBoletosVendibles) {
		CantidadMaximaBoletosVendibles = cantidadMaximaBoletosVendibles;
	}


	public int getCantidadBoletosDisponibles() {
		return CantidadBoletosDisponibles;
	}


	public void setCantidadBoletosDisponibles(int cantidadBoletosDisponibles) {
		CantidadBoletosDisponibles = cantidadBoletosDisponibles;
	}
	
	
	
	
	
}
