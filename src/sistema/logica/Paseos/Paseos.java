package sistema.logica.Paseos;
import sistema.logica.Diccionario;
import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOListadoBoletos;
import sistema.logica.ValueObject.VOPaseosListado;
import sistema.logica.Boletos.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;


public class Paseos extends Diccionario <String, Paseo> {

	public Paseos() {
		super();
	}
	
	public ArrayList <VOPaseosListado> listadoPaseos () {
		Iterator<Paseo>iter = arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
			VOPaseosListadoL.add(VO);
		}
		return VOPaseosListadoL;
	}
	
	public ArrayList <VOPaseosListado> listadoPaseosDestino (String destino) {
		Iterator<Paseo>iter = arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			if (p.getDestino()== destino) {
				VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
				VOPaseosListadoL.add(VO);
			}
		}
		return VOPaseosListadoL;
	}
	
	public ArrayList <VOPaseosListado> listadoPaseosDisponible (int cantidad) {
		Iterator<Paseo>iter = arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			if ((p.getCantMaxBoletos() - p.getCantVendidos()) >= cantidad) {
				VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
				VOPaseosListadoL.add(VO);
			}
		}
		return VOPaseosListadoL;
	}
	
	public void compraBoleto (VOCompraBoleto voCompra) {
		super.find(voCompra.getCodigoPaseo()).compraBoleto(voCompra);
	} 
	
		
}
	

	
	
