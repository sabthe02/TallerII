package sistema.logica.ValueObject;

import java.io.Serializable;

public class VOMinivan implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Matricula;
	private String Marca;
	private String Modelo;
	private int CantidadAsientos;
	
	public VOMinivan() {

		this.Matricula = "";
		this.Marca = "";
		this.Modelo = "";
		this.CantidadAsientos = 0;
	}
	
	
	public VOMinivan(String matricula, String marca, String modelo, int cantidadAsientos) {
		Matricula = matricula;
		Marca = marca;
		Modelo = modelo;
		CantidadAsientos = cantidadAsientos;
	}
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public int getCantidadAsientos() {
		return CantidadAsientos;
	}
	public void setCantidadAsientos(int cantidadAsientos) {
		CantidadAsientos = cantidadAsientos;
	}
	
	
	
}
