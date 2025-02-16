package sistema.logica;


import java.util.ArrayList;
import java.io.Serializable;

import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.*;
import sistema.logica.ValueObject.*;
import sistema.logica.Excepciones.Excepcion;




public class Fachada implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public Fachada ()
	{
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		
	}
	
	public void RegistroMinivanes (VOMinivan VO) throws Excepcion.MinivanYaExisteException,Excepcion.CantAsientosMayorCeroException {
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
		    	throw new Excepcion.MinivanYaExisteException(mensajeError);
			}
		}
		else {
			
	    	String mensajeError = "La cantidad de asientos tiene que ser mayor que cero";
	    	throw new Excepcion.CantAsientosMayorCeroException(mensajeError);
		}
	}
	
	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes () {
		return colMinivan.ListadoMinivanes();
	}
	
	
	public void ComprarBoleto(VOCompraBoleto voBoleto) throws Excepcion.BoletosNoDisponibles,Excepcion.PaseoNoExiste,Excepcion.CelularMayorQue1000,Excepcion.MenorDe18
	{
		if(voBoleto.getEdad() > 18)
		{
			
			if(Integer.parseInt(voBoleto.getCelular()) > 10000000) // verificar si el celular no deberia ser un int
			{/// Pregunta, acá no puede verificar si es >0 (lo que dice la letra)?
				if(colPaseos.member(voBoleto.getCodigoPaseo()))
				{
					if(colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles()>0)
					{
						colPaseos.compraBoleto(voBoleto);
						
					}else {
						
				    	String mensajeError = "No hay boletos disponibles.";
				    	throw new Excepcion.BoletosNoDisponibles(mensajeError);
					}
				}else {
					
			    	String mensajeError = String.format("El paseo con código: %s no existe.", voBoleto.getCodigoPaseo());
			    	throw new Excepcion.PaseoNoExiste(mensajeError);
				}
				
			}else
			{
		
		    	String mensajeError = "Ingreso un número de celular como string, vuelva a intentarlo.";
		    	throw new Excepcion.CelularMayorQue1000(mensajeError);
			}
			
		}else
		{
	    	String mensajeError = String.format("Su edad: %d es menor que 18", voBoleto.getEdad());
	    	throw new Excepcion.MenorDe18(mensajeError);
			
		}
		
	}
	
	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws Excepcion.PaseoNoExisteConCodigo {
			
		if (colPaseos.member(codigo)) {
			if (colPaseos.find(codigo).getCantVendidos()!=0) {
				return colPaseos.listadoBoletoTipo(codigo, esEsp);
			}
		}
		else {		
	    	String mensajeError = String.format("No existe ningún Paseo con el código: %s" + codigo);
	    	throw new Excepcion.PaseoNoExisteConCodigo(mensajeError);
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
		

	
		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		f.ComprarBoleto(vo);
		
		f.ListadoBoleto("PDE01", true).forEach((VOListadoBoletos) -> {
			System.out.println(VOListadoBoletos.getNombre());
			System.out.println(VOListadoBoletos.getEdad());
			System.out.println(VOListadoBoletos.getCelular());
			System.out.println(VOListadoBoletos.getDescuento());
			System.out.println(VOListadoBoletos.getNumeroBoleto());
	}
	);

}
}
