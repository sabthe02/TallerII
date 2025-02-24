package sistema.grafica.Controladores;

import java.rmi.RemoteException;

import sistema.logica.Excepciones.*;

public class Respaldo extends ConexionRMI{

	boolean conectado;
		
		public Respaldo()
		{
			super();
			
			conectado = Conectar();

		}
		
		public void Respaldar ()
		{
			if(conectado)
			{
				try {
					super.iFac.RespaldarDatos();
				}
				catch (PersistenciaException e) {
					System.out.println("Error al recuperar datos: " + e.darMensaje());

				}catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
		}
}
