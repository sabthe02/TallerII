package sistema.persistencia;

import sistema.logica.Boletos.*;
import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.*;
import sistema.logica.ValueObject.*;

import java.io.*;
import java.util.Properties;

public class Respaldo {

	public void respaldarBoleto (String datos, Boleto arre[])throws Excepcion.PersistenciaException{ 
	    if (arre == null) 
             throw new Excepcion.PersistenciaException("El arreglo de boletos es null.");
			
	  { try
			
		   {   // Abro el archivo y creo un flujo de comunicación hacia él
			    FileOutputStream f = new FileOutputStream(datos);
			    ObjectOutputStream o = new ObjectOutputStream(f);
			    // Escribo el arreglo de boletos en el archivo a través del flujo
			    o.writeObject (arre);
			    o.close();
			    f.close();
		    }
			    catch (IOException e)
			{   e.printStackTrace();
			    throw new Excepcion.PersistenciaException("Error al Respaldar");
		    }
	  }
	}


	public Boleto [] recuperarBoleto (String datos) throws Excepcion.PersistenciaException
	
	{ try
		
		{   // Abro el archivo y creo un flujo de comunicación hacia él
	
		    FileInputStream f = new FileInputStream(datos);
		    ObjectInputStream o = new ObjectInputStream(f);
		    
		    // Leo el arreglo de vehículos desde el archivo a través del flujo
		    Boleto arre[] = (Boleto []) o.readObject() ;   // ME DA ERROR, ENCONTRE EN INTERNET Q TENGO Q AGREGAR | ClassNotFoundException e
		    
		    o.close();
		    f.close();
		    return arre;
		}
		    catch (IOException | ClassNotFoundException e)
		{   e.printStackTrace();
		    throw new Excepcion.PersistenciaException("Error al Recuperar");
		}


   }


    public static void main(String args[]) {
        String archivoDatos = "sistema/persistencia/datos.properties";
        String archivoBoletos = "sistema/persistencia/boletos.txt";

        try {
            Properties p = new Properties();
            p.load(new FileInputStream(archivoDatos));

            int numeroBoleto = Integer.parseInt(p.getProperty("numeroBoleto", "0"));
            String nombrePasajero = p.getProperty("nombrePasajero", "").replace("\"", "");
            int edad = Integer.parseInt(p.getProperty("edad", "0"));
            String numeroCel = p.getProperty("numeroCel", "").replace("\"", "");
            double precio = Double.parseDouble(p.getProperty("precio", "0"));

            
            Boleto boleto = new Boleto(numeroBoleto, nombrePasajero, edad, numeroCel, precio);

            Boleto[] boletos = { boleto };

            
            Respaldo respaldo = new Respaldo();

            respaldo.respaldarBoleto(archivoBoletos, boletos);
            System.out.println("Boleto respaldado.");

            Boleto[] boletosRecuperados = respaldo.recuperarBoleto(archivoBoletos);
            System.out.println("Boleto recuperado: " + boletosRecuperados[0].getNombrePasajero());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Excepcion.PersistenciaException e) {
            System.out.println("Error de persistencia: " + e.getMessage());
        }
    }
}

