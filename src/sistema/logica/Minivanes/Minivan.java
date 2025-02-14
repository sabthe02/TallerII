package sistema.logica.Minivanes;
import java.util.Iterator;
import java.util.TreeMap;

import sistema.logica.Paseos.Paseo;
import sistema.logica.Paseos.Paseos;

public class Minivan {
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

public static void main (String args[]) {
	
	/*Paseo p1 = new Paseo ("PDP1", "09:00", "13:00", 5, 7, 15.0, "Punta del Este");
	Minivan m1 = new Minivan ("A1", "Volvo", "Modelo1", 6);
	m1.getPaseos().put(p1.getCodigo(), p1);
	
	System.out.println(m1.getMatricula());
	System.out.println(m1.getCantAsientos());
	System.out.println(m1.getMarca());
	System.out.println(m1.getModelo());
	
	Iterator<Paseo> iter= m1.getPaseos().values().iterator();
	while(iter.hasNext())
	{ 
		Paseo paseo = iter.next();
		System.out.println(paseo.getCodigo());
		System.out.println(paseo.getHoraPartida());
		System.out.println(paseo.getHoraRegreso());
		System.out.println(paseo.getCantVendidos());
		System.out.println(paseo.getCantMaxBoletos());
		System.out.println(paseo.getPrecioBase());
		System.out.println(paseo.getDestino());
	}*/
}


}
