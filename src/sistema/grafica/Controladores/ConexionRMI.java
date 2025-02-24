package sistema.grafica.Controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import sistema.logica.IFachada;

public class ConexionRMI {

	protected IFachada iFac = null;
	
	private String ipServ = "";
	private String puerto = "";
	
	
	
	public ConexionRMI()
	{
	}
	
	protected boolean Conectar()
	{
		boolean resp = false;
		try
		{ 
			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ipServ = p.getProperty("ipServidor");
			puerto = p.getProperty("puertoServidor");

		}
		catch (IOException e)
		{ 
			e.printStackTrace(); 
		}
		

		try
		{ 
			iFac = (IFachada) Naming.lookup("//"+ipServ+":"+puerto+"/fachada");
			resp = true;
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
		}
		
		return resp;
	}
	
}
