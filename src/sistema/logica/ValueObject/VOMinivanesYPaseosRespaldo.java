package sistema.logica.ValueObject;

import java.io.*;

import sistema.logica.Minivanes.Minivanes;
import sistema.logica.Paseos.Paseos;

public class VOMinivanesYPaseosRespaldo implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private static VOMinivanesYPaseosRespaldo instancia;
	
	private Minivanes colMinivan;
	private Paseos colPaseos;

	private VOMinivanesYPaseosRespaldo() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
	}
	
	public static VOMinivanesYPaseosRespaldo getInstancia () {
		if (instancia == null) {
			instancia = new VOMinivanesYPaseosRespaldo();
		}
		return instancia;
	}
	
	


	public VOMinivanesYPaseosRespaldo(Minivanes colMinivan, Paseos colPaseos) {
		super();
		this.colMinivan = colMinivan;
		this.colPaseos = colPaseos;
	}




	public void setColMinivan(Minivanes colMinivan) {
		this.colMinivan = colMinivan;
	}



	public void setColPaseos(Paseos colPaseos) {
		this.colPaseos = colPaseos;
	}



	public Minivanes getColMinivan() {
		return colMinivan;
	}



	public Paseos getColPaseos() {
		return colPaseos;
	}
 
	
	
	
}
