package sistema.logica;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.Paseos;
import sistema.logica.Paseos.Paseo;
import sistema.logica.ValueObject.*;

public class Fachada {
	
	
	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public Fachada ()
	{
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		
	}
	
	public void RegistroMinivanes (VOMinivan VO) throws MinivanYaExisteException, CantAsientosMayorCeroException {
		if (VO.getCantidadAsientos() > 0) {
			if (!colMinivan.member(VO.getMatricula())) {
				Minivan m = new Minivan (VO.getMatricula(), 
										VO.getMarca(), 
										VO.getModelo(), 
										VO.getCantidadAsientos());
				
				colMinivan.insert(m.getMatricula(), m);
			}
			else {
				String mensajeError = String.format("Ya existe una minivan con la matrícula: %s", VO.getMatricula());
		    	throw new MinivanYaExisteException(mensajeError);
			}
		}
		else {
			String mensajeError = "La cantidad de asientos tiene que ser mayor que cero";
	    	throw new CantAsientosMayorCeroException(mensajeError);
		}
}
	
	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes () {
		return colMinivan.ListadoMinivanes();
	}
	
	public void RegistroPaseo (VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero {
		boolean agregar;
		boolean vanDisponible;
		if (VO.getPrecioBase() > 0) {
			agregar = false;
			
			Iterator<Minivan>iterm = colMinivan.arbol.values().iterator();
			
			while (iterm.hasNext() && !agregar) {
				vanDisponible = true;
				Minivan m = iterm.next();
				Iterator<Paseo>iterp = m.getPaseos().arbol.values().iterator();
				
				
				while (iterp.hasNext() && vanDisponible) {
					Paseo p = iterp.next();
					if (p.getHoraPartida().isAfter(VO.getHoraPartida())) {
						if (p.getHoraPartida().isBefore(VO.getHoraRegreso())) {
							vanDisponible = false;
						}
					}
					else {
						if (p.getHoraPartida().isBefore(VO.getHoraPartida())) {
							if (p.getHoraRegreso().isAfter(VO.getHoraPartida())) {
								vanDisponible = false;
								
							}
						}
						else {
							vanDisponible = false;
						}
					}
				}
				if (vanDisponible) {
					agregar = true;	
				}
			if (agregar) {
				Paseo paseo = new Paseo (VO.getCodigo(),
										VO.getHoraPartida(),
										VO.getHoraRegreso(),
										0,
										m.getCantAsientos(),
										VO.getPrecioBase(),
										VO.getDestino());
				
				m.getPaseos().registroPaseo(paseo);
				colPaseos.insert(VO.getCodigo(), paseo);
				colMinivan.insert(VO.getCodigo(), m);
				
			}
	
		
			}
			if (!agregar) {
				String mensajeError = String.format ("La minivan con codigo %s no existe",VO.getCodigo());
				throw new MinivanNoExiste (mensajeError);
			}
	
		}
		else {
		
			String mensajeError = "Por favor ingresar un precio mayor a cero";
			throw new PrecioMenorCero (mensajeError);
		}
		
	}
	
	public ArrayList<VOPaseosListado> ListadoPaseosMinivan (String matricula) throws MinivanNoExiste {
		
		if (colMinivan.member(matricula)) {
			
			return colMinivan.ListadoPaseosEnMinivan(matricula);
		}
		else {
			String mensajeError = String.format ("La minivan con codigo %s no existe",matricula);
			throw new MinivanNoExiste (mensajeError);
		}
	}
	
public ArrayList<VOPaseosListado> ListadoPaseosDestino (String destino) throws DestinoNoPerteneceException {
		
	boolean existe;
		switch(destino) {
		
				case "Punta del Este": 								
				case "Piriapolis":					
				case "Canelones":
				case "Maldonado":
				case "Rocha":
				existe = true;
				break;
				
				default: 
					existe = false;
		}
		if (existe) {		
			return colPaseos.listadoPaseosDestino(destino);
		}
		else {
			String mensajeError = String.format ("El destino %s no partenece a la lista de posibles destinos",destino);
			throw new DestinoNoPerteneceException (mensajeError);
		}
	}
	
//public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos (int disponibles) throws PaseoNoExiste {
//	
//	if ()
//	
//	return colPaseos.listadoPaseosDisponible(disponibles);
//	
//	else {
//		
//	}
//}

	public void ComprarBoleto(VOCompraBoleto voBoleto) throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0
	{
		if(voBoleto.getEdad() > 0)
		{
			
			if(Integer.parseInt(voBoleto.getCelular()) > 0) // verificar si el celular no deberia ser un int
			{/// Pregunta, acá no puede verificar si es >0 (lo que dice la letra)?
				if(colPaseos.member(voBoleto.getCodigoPaseo()))
					
				{ 
					if(colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles()>0)
					{
						colPaseos.compraBoleto(voBoleto);
						
					}else {
						
						String mensajeError = "No hay boletos disponibles.";
						throw new BoletosNoDisponibles (mensajeError);
					}
				}else {
					String mensajeError = String.format("El paseo con código: %s no existe.", voBoleto.getCodigoPaseo());
			    	throw new PaseoNoExiste(mensajeError);
				}
				
			}else
			{
				
				String mensajeError = "Ingresar un numero de celular mayor que 0";
		    	throw new CelularMayorQue1000(mensajeError);
			}
			
		}else
		{
			String mensajeError = String.format("Edad: %d es menor que 0, ingresar edad valida", voBoleto.getEdad());
	    	throw new MenorDe0(mensajeError);
			
		}
		
	}
	
	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws PaseoNoExiste {
			
		if (colPaseos.member(codigo)) {
			if (colPaseos.find(codigo).getCantVendidos()!=0) {
				return colPaseos.listadoBoletoTipo(codigo, esEsp);
			}
		}
		else {		
			String mensajeError = String.format("El paseo con código: %s no existe.", codigo);
	    	throw new PaseoNoExiste(mensajeError);
		}
		return null;
	}
	

	
	public static void main (String args[]) {
	
		
		Fachada f = new Fachada();
		VOMinivan VOm = new VOMinivan("A1", "Volvo", "Modelo1", 8);
		try {
			f.RegistroMinivanes(VOm);
		} catch (MinivanYaExisteException | CantAsientosMayorCeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f.ListadoGeneralMinivanes().forEach((VOMinivanListado) -> { 
			System.out.println(VOMinivanListado.getMatricula());
			System.out.println(VOMinivanListado.getMarca());
			System.out.println(VOMinivanListado.getModelo());
			System.out.println(VOMinivanListado.getCantidadAsientos());
		}
		);
		
		//funciona
		
		VOPaseo v = new VOPaseo ("PDE01", LocalDateTime.of(LocalDateTime.now().
				getYear(),LocalDateTime.now().
				getMonth(),LocalDateTime.now().
				getDayOfMonth(),
				13,0), 
				LocalDateTime.of(LocalDateTime.now().
				getYear(),LocalDateTime.now().
				getMonth(),LocalDateTime.now().
				getDayOfMonth(),
				20,0),15.0,"Punta del Este");

	
		try {
			f.RegistroPaseo(v);
		} catch (MinivanNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrecioMenorCero e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			f.ListadoPaseosMinivan("A1").forEach((VOPaseosListado) -> { 
				System.out.println(VOPaseosListado.getCodigo());
				System.out.println(VOPaseosListado.getHoraPartida());
				System.out.println(VOPaseosListado.getHoraRegreso());
				System.out.println(VOPaseosListado.getCantidadBoletosDisponibles());
				System.out.println(VOPaseosListado.getCantidadMaximaBoletosVendibles());
				System.out.println(VOPaseosListado.getDestino());
			}
			);
			}
		catch (MinivanNoExiste e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		// funciona
		
		try {
			f.ListadoPaseosDestino("Punta del Este").forEach((VOPaseosListado) -> { 
				System.out.println(VOPaseosListado.getCodigo());
				System.out.println(VOPaseosListado.getHoraPartida());
				System.out.println(VOPaseosListado.getHoraRegreso());
				System.out.println(VOPaseosListado.getCantidadBoletosDisponibles());
				System.out.println(VOPaseosListado.getCantidadMaximaBoletosVendibles());
				System.out.println(VOPaseosListado.getDestino());
			}
			);
			}
		catch (DestinoNoPerteneceException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		// funciona
		
		
		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		try {
			f.ComprarBoleto(vo);
		} catch (BoletosNoDisponibles e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaseoNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CelularMayorQue1000 e) {
			// TODO Auto-generated catch block
				
		} catch (MenorDe0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			f.ListadoBoleto("PDE01", true).forEach((VOListadoBoletos) -> {
				System.out.println(VOListadoBoletos.getNombre());
				System.out.println(VOListadoBoletos.getEdad());
				System.out.println(VOListadoBoletos.getCelular());
				System.out.println(VOListadoBoletos.getDescuento());
				System.out.println(VOListadoBoletos.getNumeroBoleto()); // Ver por qué tira 0 por defecto
}
);
		} catch (PaseoNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
