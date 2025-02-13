package sistema.logica.Boletos;

public class BoletoEsp extends Boleto {

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
