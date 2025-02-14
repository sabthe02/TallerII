package sistema.logica;


import sistema.logica.Minivanes.Minivanes;
import sistema.logica.Paseos.Paseos;
import sistema.logica.ValueObject.VOCompraBoleto;

public class Fachada {
	
	
	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public Fachada ()
	{
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		
	}
	
	
	public void ComprarBoleto(VOCompraBoleto voBoleto)
	{
		if(voBoleto.getEdad() > 18)
		{
			
			if(Integer.parseInt(voBoleto.getCelular()) > 10000000) // verificar si el celular no deberia ser un int
			{
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
	
	public static void main (String args[]) {
	
		
		Fachada f = new Fachada();
		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		f.ComprarBoleto(vo);
		
		
	}

}
