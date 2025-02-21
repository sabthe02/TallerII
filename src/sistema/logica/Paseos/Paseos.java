package sistema.logica.Paseos;


import sistema.logica.Diccionario;

import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOListadoBoletos;
import sistema.logica.ValueObject.VOPaseosListado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


public class Paseos extends Diccionario <String, Paseo> implements Serializable{

	private static final long serialVersionUID = 1L;



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
		Iterator<Paseo>iter = super.arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			if (Objects.equals(p.getDestino(), destino)) {
				
				VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
				VOPaseosListadoL.add(VO);
			}
		}
		return VOPaseosListadoL;
	}

	public ArrayList<VOPaseosListado> listadoPaseosDisponible (int cantidad) {
		
		Iterator<Paseo>iter = arbol.values().iterator();
		ArrayList<VOPaseosListado> VOPaseosListadoL = new ArrayList<VOPaseosListado>();
		
		VOPaseosListado VO;
		while (iter.hasNext()) {
			Paseo p = iter.next();
			if ((p.getCantidadBoletosDisponibles()) >= cantidad) {
				VO = new VOPaseosListado (p.getCodigo(), p.getHoraPartida(), p.getHoraRegreso(), p.getPrecioBase(), p.getDestino(), p.getCantMaxBoletos(), (p.getCantMaxBoletos()-p.getCantVendidos()));
				VOPaseosListadoL.add(VO);
			}
		}

		return VOPaseosListadoL;
	}
	
	public void compraBoleto (VOCompraBoleto voCompra) {
		super.find(voCompra.getCodigoPaseo()).compraBoleto(voCompra);
	} 
	
	public ArrayList <VOListadoBoletos> listadoBoletoTipo (String codigo, boolean tipo) {
		return super.find(codigo).listadoBoletosPaseo(tipo);
	}

	
	public int CantidadBoletosVendidos(String codigo)
	{
		return super.find(codigo).getCantVendidos();
		
	}
	
    public double montoRecaudado (String codigo) {
		
		return super.find(codigo).montoRecaudadoPaseo();
	}
	


	public void registroPaseo (Paseo paseo) {
		
		super.arbol.put(paseo.getCodigo(), paseo);
		
	}
	
}
