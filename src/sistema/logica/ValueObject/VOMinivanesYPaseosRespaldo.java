package sistema.logica.ValueObject;

import java.io.*;

import sistema.logica.Minivanes.Minivanes;
import sistema.logica.Paseos.Paseos;

public class VOMinivanesYPaseosRespaldo implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private Minivanes colMinivan;
	private Paseos colPaseos;

	public VOMinivanesYPaseosRespaldo() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
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
