package sistema.logica;


import java.util.ArrayList;

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
	
	
	public void ComprarBoleto(VOCompraBoleto voBoleto)
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
						
						//throw Exepcion No hay boletos disponibles
					}
				}else {
					
					//throw Exepcion El paseo no existe
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
