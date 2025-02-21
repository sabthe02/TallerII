package sistema.logica.Minivanes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import sistema.logica.Diccionario;
import sistema.logica.ValueObject.VOMinivanListado;
import sistema.logica.ValueObject.VOPaseosListado;

public class Minivanes extends Diccionario<String, Minivan> implements Serializable {

	private static final long serialVersionUID = 1L;

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
	

	public ArrayList <VOPaseosListado> ListadoPaseosEnMinivan(String Matricula)
	{
		return super.find(Matricula).paseos.listadoPaseos();
	}


}
