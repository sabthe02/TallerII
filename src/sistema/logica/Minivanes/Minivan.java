package sistema.logica.Minivanes;
import java.util.TreeMap;
import sistema.logica.Paseos.Paseo;

public class Minivan {
private 
	String matricula;
	String marca;
	String modelo;
	int cantAsientos;
	TreeMap<String,Paseo> Paseos;

	
public Minivan(String matricula, String marca, String modelo, int cantAsientos, TreeMap<String, Paseo> paseos) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.cantAsientos = cantAsientos;
		Paseos = paseos;
	}

public String getMatricula() {
	return matricula;
}

public String getMarca() {
	return marca;
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
	return cantAsientos;
}
public void setCantAsientos(int cantAsientos) {
	this.cantAsientos = cantAsientos;
}
public TreeMap<String, Paseo> getPaseos() {
	return Paseos;
}
public void setPaseos(TreeMap<String, Paseo> Paseos) {
	this.Paseos = Paseos;
}

public void main (String args[]) {
	
//	Paseo p1 = new Paseo ("PDE1",)
//	TreeMap <String, Paseo> m1 = new Treemap (p1.getCodigo(), p1);
//	Minivan m1 = new Minivan ("A1", "Volvo", "Modelo1", 6, m1);
}


}
