package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import sistema.grafica.VentanaRegistroPaseo;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOPaseo;

public class ControladorRegistroPaseo extends ConexionRMI {

	private boolean conectado;
	private VentanaRegistroPaseo ventana;

	public ControladorRegistroPaseo(VentanaRegistroPaseo v) {
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

	public boolean RegistrarPaseo(VOPaseo voPaseo) {

		boolean resp = false;
		if (conectado) {
			try {
				super.iFac.RegistroPaseo(voPaseo);
				resp = true;
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor");
			} catch (MinivanNoExiste e) {
				ventana.mostrarError("La minivan con el codigo ingresado no existe");
			} catch (PrecioMenorCero e) {
				ventana.mostrarError("El precio tiene que ser mayor que 0");
			}
		}

		return true;

	}

}
