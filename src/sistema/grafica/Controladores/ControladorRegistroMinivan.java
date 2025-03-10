package sistema.grafica.Controladores;

import sistema.grafica.VentanaRegistroMinivan;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import sistema.grafica.VentanaRegistroMinivan;
import sistema.logica.Excepciones.CantAsientosMayorCeroException;
import sistema.logica.Excepciones.*;
import sistema.logica.ValueObject.VOMinivan;

public class ControladorRegistroMinivan extends ConexionRMI {

	private VentanaRegistroMinivan ventana;

	private boolean conectado;

	public ControladorRegistroMinivan(VentanaRegistroMinivan v) {
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

	public boolean RegistrarMinivan(String matricula, String marca, String modelo, int asientos) {
		boolean resp = false;
		if (conectado) {
			try {
                VOMinivan m = new VOMinivan(matricula, marca, modelo, asientos);
				super.iFac.RegistroMinivanes(m);
				JOptionPane.showMessageDialog(ventana, "Resgistrado con exito");
				resp = true;
				
			} catch (MinivanYaExisteException e) {
				ventana.mostrarError("La minivan a ingresar ya existe");

			} catch (CantAsientosMayorCeroException e) {
				ventana.mostrarError("La cantidad de asientos tiene que ser mayor a 0");
			} catch (RuntimeException e) {
				ventana.mostrarError("Error Runtime, fallo algo del sistema");
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor"); 
			}

		}
		return resp;

	}

}
