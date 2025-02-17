package sistema.logica.ValueObject;

import java.io.*;

import sistema.logica.Minivanes.Minivanes;
import sistema.logica.Paseos.Paseos;

public class VOMinivanesYPaseosRespaldo extends Diccionario implements Serializable {

	private static final long serialVersionUID = 1L; 
	private Minivanes colMinivan;
	private Paseos colPaseos;

	public VOMinivanesYPaseosRespaldo() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();

	}
	
	public Minivanes getMinivanes () {	
		return colMinivan;
	}
	public Paseos getPaseos () {		
		return colPaseos;
	}
 
	
}
