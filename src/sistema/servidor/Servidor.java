package sistema.servidor;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import sistema.logica.Fachada;
import sistema.logica.Excepciones.PersistenciaException;

public class Servidor {

	public static void main(String args[]) {

		String ipServ = "";
		int puerto = 0;

		try {

			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ipServ = p.getProperty("ipServidor");
			puerto = Integer.parseInt(p.getProperty("puertoServidor"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			LocateRegistry.createRegistry(puerto);
			Fachada f = new Fachada();
			try {
				f.RecuperarDatos();
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Antes de publicarlo");
			Naming.rebind("//" + ipServ + ":" + puerto + "/fachada", f);
			System.out.println("Luego de publicarlo: " + ipServ + ":" + puerto);
		}

		catch (RemoteException e) {
			System.out.println("Error RemoteException: " + e.getMessage());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Error MalformedURLException: " + e.getMessage());
			e.printStackTrace();
		} catch(Exception e)
		{
			
			System.out.println("Error Exception: " + e.getMessage());

		}
	}

}