package sistema.persistencia;

import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.*;
import sistema.logica.ValueObject.*;

import java.io.*;
import java.util.Properties;
import java.util.TreeMap;

public class Respaldo {
	
	public Respaldo () {
		Respaldo respaldo = new Respaldo();
	}

	public void respaldar (String nombreArchivo, VOMinivanesYPaseos VO)throws PersistenciaException{ 
	  try		
		   {   // Abro el archivo y creo un flujo de comunicación hacia él
			    FileOutputStream f = new FileOutputStream(nombreArchivo);
			    ObjectOutputStream o = new ObjectOutputStream(f);
			    // Escribo el arreglo de boletos en el archivo a través del flujo
			    o.writeObject (VO);
			    o.close();
			    f.close();
		    }
			    catch (IOException e)
			{   e.printStackTrace();
				String mensajeError = "Error al respaldar";
			    throw new PersistenciaException(mensajeError);
		    }
	  
	  }


	public VOMinivanesYPaseos recuperarBoleto (String nombreArchivo) throws PersistenciaException
	
	{ try
		
		{   // Abro el archivo y creo un flujo de comunicación hacia él
	
		    FileInputStream f = new FileInputStream(nombreArchivo);
		    ObjectInputStream o = new ObjectInputStream(f);
		    
		    // Leo el arreglo de vehículos desde el archivo a través del flujo
		    VOMinivanesYPaseos VO =  o.readObject();
		    
		    o.close();
		    f.close();
		    return VO;
		}
		    catch (IOException e)
		{   e.printStackTrace();
			String mensajeError = "Error al Recuperar";
		    throw new PersistenciaException(mensajeError);
		}
		catch (ClassNotFoundException e){
	  		e.printStackTrace();
  	}
	return null;


   }


    
}

