package sistema.persistencia;

import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.*;
import sistema.logica.ValueObject.*;

import java.io.*;
import java.util.Properties;
import java.util.TreeMap;

public class Respaldo {

	public Respaldo() {
		
	}

	public void respaldar(String nombreArchivo, VOMinivanesYPaseosRespaldo VO) throws PersistenciaException {
		try {
			
			
			FileOutputStream f = new FileOutputStream(nombreArchivo);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(VO);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			String mensajeError = "Error al respaldar";
			throw new PersistenciaException(mensajeError);
		}catch(Exception e)
		{
			e.printStackTrace();
			String mensajeError = "Error al respaldar";
			throw new PersistenciaException(mensajeError);
		}

	}

	public VOMinivanesYPaseosRespaldo recuperar (String nombreArchivo) throws PersistenciaException
	{
		VOMinivanesYPaseosRespaldo VO = null;
		
		try
		{ 
//			System.out.println(nombreArchivo);
			File file = new File (nombreArchivo);
			if (file.exists()) {
				FileInputStream f = new FileInputStream(file);
				ObjectInputStream o = new ObjectInputStream(f);
	
				VO = (VOMinivanesYPaseosRespaldo) o.readObject();
	
				o.close();
				f.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			String mensajeError = "Error al Recuperar";
			throw new PersistenciaException(mensajeError);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return VO;	
	}

}
