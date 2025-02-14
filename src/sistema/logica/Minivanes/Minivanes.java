package sistema.logica.Minivanes;

import java.util.ArrayList;
import java.util.Iterator;

import sistema.logica.Diccionario;
import sistema.logica.ValueObject.VOMinivanListado;
import sistema.logica.ValueObject.VOPaseosListado;
import sistema.logica.Excepciones.Excepcion;

public class Minivanes extends Diccionario<String, Minivan> {

	public Minivanes() {
		super();
	}

	public ArrayList<VOMinivanListado> ListadoMinivanes() {
		Iterator<Minivan> iter = super.arbol.values().iterator();
		ArrayList<VOMinivanListado> VOMinivanListadoL = new ArrayList<VOMinivanListado>();

		while (iter.hasNext()) {
			Minivan p = iter.next();

			VOMinivanListado VO = new VOMinivanListado(p.getMatricula(), 
					p.getMarca(), 
					p.getModelo(), 
					p.getCantAsientos(),
					p.paseos.size()
					);
			VOMinivanListadoL.add(VO);

		}
		return VOMinivanListadoL;

	}
	
	//No se controlan errores, se deja para la fachada
	// REQUERIMIENTO 4
	public ArrayList<VOPaseosListado> ListadoPaseosEnMinivan(String Matricula) throws Excepcion.MinivanNoEncontradaException {
	    
	    var minivan = super.find(Matricula);
	    
	    if (minivan == null) {
	    	String mensajeError = String.format("No se encontro una minivan con la matrícula: %s", Matricula);
	    	throw new Excepcion.MinivanNoEncontradaException(mensajeError);
	
	    }

	    return minivan.paseos.listadoPaseos();
	}


}
