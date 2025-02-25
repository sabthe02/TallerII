package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import sistema.grafica.MainProgram;
import sistema.logica.Excepciones.*;

public class Respaldo extends ConexionRMI{

	boolean conectado;
	MainProgram ventana;
		
		public Respaldo(MainProgram v)
		{
			super();
			ventana = v;
			
			try {
				conectado = Conectar();
			} catch (MalformedURLException e) {
				ventana.mostrarError("Problema de formar la URL");
				
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor");
				
			} catch (NotBoundException e) {
				ventana.mostrarError("Problema con la direccion del servidor");
			}

		}
		
		public void Respaldar ()
		{
			if(conectado)
			{
				try {
					super.iFac.RespaldarDatos();
					JOptionPane.showMessageDialog(ventana.getFrame(), "Respaldado con exito");
				}
				catch (PersistenciaException e) {
					ventana.mostrarError("Error al respaldar");

				}catch (RemoteException e) {
					ventana.mostrarError("Problemas de conexion al servidor");
				}
			}
			
		}
}
