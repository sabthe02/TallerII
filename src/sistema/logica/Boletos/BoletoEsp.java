package sistema.logica.Boletos;
import java.io.Serializable;

public class BoletoEsp extends Boleto implements Serializable {
	
private static final long serialVersionUID = 1L; 

private 
	double descuentoEsp;


public BoletoEsp(int numeroBoleto, String nombrePasajero, int edad, String numeroCel, double precio,
		double descuentoEsp) {
	super(numeroBoleto, nombrePasajero, edad, numeroCel, precio);
	this.descuentoEsp = descuentoEsp;
}

public double getDescuentoEsp() {
	return descuentoEsp;
}

public void setDescuentoEsp(double descuentoEsp) {
	this.descuentoEsp = descuentoEsp;
}


}