package sistema.logica.Paseos;
import sistema.logica.Diccionario;

import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOListadoBoletos;
import sistema.logica.ValueObject.VOPaseosListado;
import sistema.logica.Boletos.*;
import sistema.logica.Excepciones.Excepcion;
import sistema.logica.Excepciones.Excepcion.ListaPaseosVaciaException;
import sistema.logica.Excepciones.Excepcion.DestinoNoPerteneceException;


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
	// REQUERIMIENTO 5
	public ArrayList <VOPaseosListado> listadoPaseosDestino (String destino) throws Excepcion.DestinoNoPerteneceException {
		Iterator<Paseo>iter = arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			if (p.getDestino()== destino) {
				VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
				VOPaseosListadoL.add(VO);
			} else {
				String mensajeError = String.format("El destino: %s no pertenece a los destinos visitados", destino);
		        throw new Excepcion.DestinoNoPerteneceException(mensajeError);
			}
		}
		return VOPaseosListadoL;
	}
	// REQUERIMIENTO 6
	public ArrayList <VOPaseosListado> listadoPaseosDisponible (int cantidad) throws Excepcion.ListaPaseosVaciaException {
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
		  if (VOPaseosListadoL.isEmpty()) {
		        String mensajeError =  String.format("No hay paseos con %d boletos disponibles",cantidad);
		        throw new Excepcion.ListaPaseosVaciaException(mensajeError);
		    }
		return VOPaseosListadoL;
	}
	
	public void compraBoleto (VOCompraBoleto voCompra) {
		super.find(voCompra.getCodigoPaseo()).compraBoleto(voCompra);
	} 
	
		
}
	

	
	
