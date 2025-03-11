package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import sistema.grafica.VentanaRegistroPaseo;
import sistema.logica.Excepciones.CodigoPaseoYaExiste;
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

	public boolean RegistrarPaseo(String CodigoPaseo, String Destino, Double PrecioBase, LocalTime horaPartida, LocalTime horaRegreso) {

		boolean resp = false;
		if (conectado) {
			try {
				
				VOPaseo vo = new VOPaseo();
				vo.setCodigo(CodigoPaseo);
				vo.setDestino(Destino);
				vo.setPrecioBase(PrecioBase);
				vo.setHoraPartida(horaPartida);
				vo.setHoraRegreso(horaRegreso);
				
				super.iFac.RegistroPaseo(vo);
				resp = true;
			} catch (CodigoPaseoYaExiste e) {
				ventana.mostrarError("Ya existe un paseo con el codigo ingresado.");
			}catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor");
			} catch (MinivanNoExiste e) {
				ventana.mostrarError("No hay minivan disponible para ese paseo");
			} catch (PrecioMenorCero e) {
				ventana.mostrarError("El precio tiene que ser mayor que 0");
			}
		}

		return resp;

	}

}
