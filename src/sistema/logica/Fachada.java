package sistema.logica;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.*;
import sistema.logica.ValueObject.*;

public class Fachada {
	
	
	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public Fachada ()
	{
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		
	}
	
	public void RegistroMinivanes (VOMinivan VO) {
		if (VO.getCantidadAsientos() > 0) {
			if (!colMinivan.member(VO.getMatricula())) {
				Minivan m = new Minivan (VO.getMatricula(), 
										VO.getMarca(), 
										VO.getModelo(), 
										VO.getCantidadAsientos());
				
				colMinivan.insert(m.getMatricula(), m);
			}
			else {
				// throw Exception Minivan ya existe
			}
		}
		else {
			// throw Exeption Cantidad de asientos tiene que ser mayor a 0.
		}
	}
	
	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes () {
		return colMinivan.ListadoMinivanes();
	}
	
	public void RegistroPaseo (VOPaseo VO) {
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
				// Throw Exception No hay minivanes disponibles.
			}
	
		}
		else {
		
			// Throw Exception Precio tiene que ser mayor que 0.
		}
		
	}
	
	
	public void ComprarBoleto(VOCompraBoleto voBoleto)
	{
		if(voBoleto.getEdad() > 18)
		{
			
			if(Integer.parseInt(voBoleto.getCelular()) > 0) // verificar si el celular no deberia ser un int
			{/// Pregunta, acá no puede verificar si es >0 (lo que dice la letra)?
				if(colPaseos.member(voBoleto.getCodigoPaseo()))
					
				{ 
					if(colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles()>0)
					{
						colPaseos.compraBoleto(voBoleto);
						
					}else {
						
						//throw Exepcion No hay boletos disponibles
					}
				}else {
					//throw Exepcion El paseo no existe
					//Al momento entra acá porque no existe el Paso todavía.
				}
				
			}else
			{
				
				//throw Exepcion Celular > 10000000
			}
			
		}else
		{
			//throw Exepcion Edad > 18
			
		}
		
	}
	
	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) {
			
		if (colPaseos.member(codigo)) {
			if (colPaseos.find(codigo).getCantVendidos()!=0) {
				return colPaseos.listadoBoletoTipo(codigo, esEsp);
			}
		}
		else {		
			// throw Exception No existe el Paseo con ese codigo
		}
		return null;
	}
	
	public static void main (String args[]) {
	
		
		Fachada f = new Fachada();
		VOMinivan VOm = new VOMinivan("A1", "Volvo", "Modelo1", 8);
		f.RegistroMinivanes(VOm);
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

		
		f.RegistroPaseo(v);
		
		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		f.ComprarBoleto(vo);
		
		f.ListadoBoleto("PDE01", true).forEach((VOListadoBoletos) -> {
			System.out.println(VOListadoBoletos.getNombre());
			System.out.println(VOListadoBoletos.getEdad());
			System.out.println(VOListadoBoletos.getCelular());
			System.out.println(VOListadoBoletos.getDescuento());
			System.out.println(VOListadoBoletos.getNumeroBoleto()); // Ver por qué tira 0 por defecto
	}
	);

}
}
