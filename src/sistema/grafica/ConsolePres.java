package sistema.grafica;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import sistema.logica.IFachada;
import sistema.logica.Excepciones.CantAsientosMayorCeroException;
import sistema.logica.Excepciones.DestinoNoPerteneceException;
import sistema.logica.Excepciones.MinivanYaExisteException;
import sistema.logica.Minivanes.Minivan;
import sistema.logica.ValueObject.VOMinivan;

public class ConsolePres {
	
	
	public static void main(String[] args) {
		
		
		String ipServ = "";
		String puerto = "";
		
		try
		{ 
			
			
			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ipServ = p.getProperty("ipServidor");
			puerto = p.getProperty("puertoServidor");

		}
		catch (IOException e)
		{ e.printStackTrace(); }
		
		
		try
		{ 
			
			
			
			
			IFachada fachada = (IFachada) Naming.lookup("//"+ipServ+":"+puerto+"/fachada");
			
			if(fachada!= null)
			{
				try {
					fachada.RegistroMinivanes(new VOMinivan("AAB185", "Volvo", "Minivan", 55));
					
					System.out.println("INICIO //  (Req 2) Prueba funcion Listado General Minivanes. ");
					
				
					fachada.ListadoGeneralMinivanes().forEach((VOMinivanListado) -> {
						System.out.println("Minivan: ");
						System.out.println("Matricula: " + VOMinivanListado.getMatricula());
						System.out.println("Marca: " + VOMinivanListado.getMarca());
						System.out.println("Modelo: " + VOMinivanListado.getModelo());
						System.out.println("Cantidad Asientos: " + VOMinivanListado.getCantidadAsientos());
						System.out.println("");
	
					});
					
					
					System.out.println("");
					System.out.println("INICIO //  (Req 5)  Prueba Listado Paseos por Destino");
					System.out.println();
					System.out.println();

					try {
						fachada.ListadoPaseosDestino("Punta del Este").forEach((VOPaseosListado) -> {
							System.out.println("Codigo: " + VOPaseosListado.getCodigo());
							System.out.println("Destino: " + VOPaseosListado.getDestino());
							DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
							System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
							DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
							System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
							System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
							System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
							System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
							System.out.println();
						});
					} catch (DestinoNoPerteneceException e) {
						e.printStackTrace();
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					catch (RemoteException e) {
						e.printStackTrace();
					}
				
				}catch (MinivanYaExisteException e) {
					System.out.println("La matricula de la minivan ya existe");
				}catch (RemoteException e) {
					e.printStackTrace();
				}
	
				System.out.println("FIN // Prueba funcion Listado General Minivanes. ");
			}else {
				
				System.out.println("No se establecio la conexion correctamente.");

			}
			
		}
		catch (MalformedURLException e) 
		{
			System.out.println("No se establecio la conexion correctamente. MalformedURLException");
		}
		catch (RemoteException e)
		{
			System.out.println("No se establecio la conexion correctamente. RemoteException");
		}
		catch (NotBoundException e)
		{
			System.out.println("No se establecio la conexion correctamente. NotBoundException");
		}catch (CantAsientosMayorCeroException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
