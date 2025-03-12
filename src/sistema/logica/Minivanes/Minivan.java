package sistema.logica.Minivanes;
import java.util.Iterator;
import java.util.TreeMap;

import sistema.logica.Paseos.*;

import java.io.Serializable;

public class Minivan implements Serializable {
	
private static final long serialVersionUID = 1L; 

private 
	String matricula;
	String marca;
	String modelo;
	int cantAsientos;
	Paseos paseos;

	
public Minivan(String matricula, String marca, String modelo, int cantAsientos) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.cantAsientos = cantAsientos;
		this.paseos = new Paseos ();
	}

public Minivan() {
	this.matricula = "";
	this.marca = "";
	this.modelo = "";
	this.cantAsientos = 0;
	this.paseos = new Paseos ();
}

public String getMatricula() {
	return this.matricula;
}

public String getMarca() {
	return this.marca; 
}
public void setMarca(String marca) {
	this.marca = marca;
}
public String getModelo() {
	return modelo;
}
public void setModelo(String modelo) {
	this.modelo = modelo;
}
public int getCantAsientos() {
	return this.cantAsientos;
}
public void setCantAsientos(int cantAsientos) {
	this.cantAsientos = cantAsientos;
}
public Paseos getPaseos() {
	return this.paseos;
}
public void setPaseos(Paseos pas) {
	this.paseos = pas;
}

public int getCantidadPaseos()
{
	return this.paseos.size();
}


}
