package sistema.grafica;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorRegistroMinivan;
import sistema.grafica.Controladores.ControladorVentaBoleto;
import sistema.logica.Excepciones.BoletosNoDisponibles;
import sistema.logica.Excepciones.CelularMayorQue1000;
import sistema.logica.Excepciones.MenorDe0;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOMinivan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class VentanaVentaBoleto extends JInternalFrame {
	private static final long serialVersionUID = 2198985395810256235L;
	private JTextField txtCodigoPaseo;
	private JTextField txtNombre;
	private JButton btnRegistrar;
	private JInternalFrame fm;
	private ControladorVentaBoleto controlador;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtEdad;
	private ButtonGroup grupoBoletos;
	private JInternalFrame panelTipoBoleto;
	private JRadioButton radiobuttonComun;
	private Component radiobuttonEspecial;
	private JTextField txtDescuento;
	private JLabel label_5;

	public VentanaVentaBoleto() {
		Inicializar();
	}
	
	public VentanaVentaBoleto(String idPaseo) {
		Inicializar();
		txtCodigoPaseo.setText(idPaseo);
	}
	
	
	public void Inicializar()
	{
		fm = this;
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(50, 80, 572, 331);
		setTitle("Venta de Boletos");
		getContentPane().setLayout(null);

		JLabel label = new JLabel("Código de Paseo:");
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBounds(30, 0, 131, 36);
		getContentPane().add(label);
		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setBounds(197, 6, 187, 26);
		getContentPane().add(txtCodigoPaseo);

		JLabel label_1 = new JLabel("Nombre del Turista:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_1.setBounds(30, 47, 187, 36);
		getContentPane().add(label_1);
		txtNombre = new JTextField();
		txtNombre.setBounds(197, 46, 187, 26);
		getContentPane().add(txtNombre);

		JLabel label_2 = new JLabel("Edad:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_2.setBounds(30, 94, 93, 36);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Número de Celular:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setBounds(30, 138, 167, 36);
		getContentPane().add(label_3);

		btnRegistrar = new JButton("Registrar Compra");
		btnRegistrar.setBounds(196, 253, 143, 26);
		getContentPane().add(btnRegistrar);

		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter sleepFormatter = new NumberFormatter(format);
		sleepFormatter.setValueClass(Integer.class);
		sleepFormatter.setMinimum(1);
		sleepFormatter.setMaximum(110);
		sleepFormatter.setAllowsInvalid(false);

		sleepFormatter.setCommitsOnValidEdit(true);
		txtEdad = new JFormattedTextField(sleepFormatter);
		txtEdad.setBounds(197, 93, 187, 29);
		txtEdad.setToolTipText("Formato numero entero entre 1 y 110");
		getContentPane().add(txtEdad);

		txtCelular = new JFormattedTextField();
		txtCelular.setToolTipText("Formato numero entero mayor a 0");
		txtCelular.setBounds(197, 138, 187, 28);
		getContentPane().add(txtCelular);

		JLabel label_4 = new JLabel("Tipo de Boleto:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_4.setBounds(30, 173, 115, 36);
		getContentPane().add(label_4);

		JRadioButton radioButtonComun = new JRadioButton("Comun");
		radioButtonComun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioButtonComun.isSelected()) {
					txtDescuento.setVisible(false);
					label_5.setVisible(false);
				}
			}
		});
		radioButtonComun.setBounds(197, 181, 73, 23);
		radioButtonComun.setFont(new Font("Arial", Font.BOLD, 10));
		getContentPane().add(radioButtonComun);

		JRadioButton radioButtonEspecial = new JRadioButton("Especial");
		radioButtonEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioButtonEspecial.isSelected()) {
					txtDescuento.setVisible(true);
					label_5.setVisible(true);
				}

			}
		});
		radioButtonEspecial.setBounds(272, 181, 81, 23);
		radioButtonEspecial.setFont(new Font("Arial", Font.BOLD, 10));
		getContentPane().add(radioButtonEspecial);

		grupoBoletos = new ButtonGroup();
		grupoBoletos.add(radioButtonComun);
		grupoBoletos.add(radioButtonEspecial);

		label_5 = new JLabel("Valor de Descuento:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_5.setBounds(30, 210, 115, 36);
		getContentPane().add(label_5);

		txtDescuento = new JTextField();
		txtDescuento.setBounds(197, 214, 187, 28);
		getContentPane().add(txtDescuento);

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

				List<String> errores = validarCampos();
				if (errores.isEmpty()) {
					
					
					String codigo = txtCodigoPaseo.getText();
					double descuento = 0.0;
					int edad = Integer.parseInt(txtEdad.getText());
					boolean especial = radioButtonEspecial.isSelected();
					if(especial)
					{
						descuento = Double.parseDouble(txtDescuento.getText());
					}
					
					
					controlador = new ControladorVentaBoleto(VentanaVentaBoleto.this);
					
					
					
					if (controlador.ComprarBoleto(codigo, txtNombre.getText(), edad, txtCelular.getText(), especial, descuento)) {
						JOptionPane.showMessageDialog(fm, "Se hizo la compra del boleto correctamente.");
						fm.setVisible(false);
					}
					

				} else {
					String aux = "";
					for (String string : errores) {
						aux += string + "\n";
					}

					JOptionPane.showMessageDialog(null, "Los datos no son correctos.\n" + aux);

				}

			}
		});
	}

	private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (txtCodigoPaseo.getText().trim().equals("")) {
			resp.add("El codigo del Paseo no puede estar vacio.");
		}
		if ((!(txtCodigoPaseo.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("El codigo del Paseo tiene que ser alfanumerico");
		}

		if (txtNombre.getText().trim().equals("")) {
			resp.add("El nombre del turista no puede estar vacio.");
		}
		if (txtEdad.getText().trim().equals("")) {
			resp.add("La edad no puede estar vacia");
		}
		if (!(isNumeric(txtCelular.getText().trim()))) {
			resp.add("El celular tiene que ser un numero");
		}

		return resp;
	}

	private static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * 
	 * private List<String> validarCampos() { List<String> resp = new ArrayList<>();
	 * 
	 * if (txtCodigoPaseo.getText().trim().equals("")) {
	 * resp.add("El codigo del Paseo no puede estar vacio."); } if
	 * ((!(txtCodigoPaseo.getText()).matches("[A-Za-z0-9]+"))) {
	 * resp.add("El codigo del Paseo tiene que ser alfanumerico"); }
	 * 
	 * if (txtPrecioBase.getText().trim().equals("")) {
	 * resp.add("El precio del paseo no puede estar vacio."); }
	 * 
	 * return resp; }
	 */

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, "error: " + mensaje);
	}
}
