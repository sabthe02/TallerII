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
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JInternalFrame fm;
	private ControladorVentaBoleto controlador;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtEdad;
	private ButtonGroup grupoBoletos;
	private JTextField txtDescuento;
	private JLabel label_5; 
	private JRadioButton radioButtonEspecial;
	private JRadioButton radioButtonComun;

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
		setBounds(420, 100, 548, 323);
		setTitle("Venta de Boletos");
		getContentPane().setLayout(null);
		setBackground(new Color(255, 200, 145));

		JLabel label = new JLabel("Código de Paseo:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(30, 35, 133, 26);
		getContentPane().add(label);
		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCodigoPaseo.setBounds(167, 35, 256, 26); 
		getContentPane().add(txtCodigoPaseo);

		JLabel label_1 = new JLabel("Nombre del Turista:");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(30, 71, 133, 26);
		getContentPane().add(label_1);
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtNombre.setBounds(167, 71, 256, 26);
		getContentPane().add(txtNombre);

		JLabel label_2 = new JLabel("Edad:");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_2.setBounds(30, 107, 133, 26);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Número de Celular:");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_3.setBounds(30, 145, 133, 28);
		getContentPane().add(label_3);

		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter sleepFormatter = new NumberFormatter(format);
		sleepFormatter.setValueClass(Integer.class);
		sleepFormatter.setMinimum(1);
		sleepFormatter.setMaximum(110);
		sleepFormatter.setAllowsInvalid(false);

		sleepFormatter.setCommitsOnValidEdit(true);
		txtEdad = new JFormattedTextField(sleepFormatter);
		txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtEdad.setBounds(167, 106, 256, 29);
		txtEdad.setToolTipText("Formato numero entero entre 1 y 110");
		getContentPane().add(txtEdad);

		txtCelular = new JFormattedTextField();
		txtCelular.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCelular.setToolTipText("Formato numero entero mayor a 0");
		txtCelular.setBounds(167, 145, 256, 28);
		getContentPane().add(txtCelular);

		JLabel label_4 = new JLabel("Tipo de Boleto:");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_4.setBounds(30, 183, 133, 26);
		getContentPane().add(label_4);

		radioButtonComun = wnew JRadioButton("Comun");
		radioButtonComun.setSelected(true);
		radioButtonComun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioButtonComun.isSelected()) {
					txtDescuento.setVisible(false);
					label_5.setVisible(false);
				}
			}
		});
		radioButtonComun.setBounds(167, 186, 73, 23);
		radioButtonComun.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		radioButtonComun.setBackground(new Color(255, 200, 145));
		getContentPane().add(radioButtonComun);

	    radioButtonEspecial = new JRadioButton("Especial");
		radioButtonEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioButtonEspecial.isSelected()) {
					txtDescuento.setVisible(true);
					label_5.setVisible(true);
				}

			}
		});
		radioButtonEspecial.setBounds(253, 185, 81, 23);
		radioButtonEspecial.setBackground(new Color(255, 200, 145));
		radioButtonEspecial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(radioButtonEspecial);

		grupoBoletos = new ButtonGroup();
		grupoBoletos.add(radioButtonComun);
		grupoBoletos.add(radioButtonEspecial);

		label_5 = new JLabel("Valor de Descuento:");
		label_5.setVisible(false);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_5.setBounds(30, 224, 133, 23);
		getContentPane().add(label_5);

		txtDescuento = new JTextField();
		txtDescuento.setVisible(false);
		txtDescuento.setBounds(167, 219, 256, 24);
		getContentPane().add(txtDescuento);

		btnAceptar = new JButton("Aceptar");
        btnAceptar.setText("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
		btnAceptar.setBounds(409, 254, 84, 23);

        btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnAceptar.addActionListener(new ActionListener() {
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
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnCancelar.setBorder(UIManager.getBorder("Button.border"));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setBounds(318, 254, 81, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	             fm.setVisible(false);	
	        }
        });
        getContentPane().add(btnCancelar);
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

		if (radioButtonEspecial.isSelected()) {

			if (txtDescuento.getText().equals("")) {
				resp.add("El campo descuento no puede estar vacio.");
			} else {
				try {
					Double.parseDouble(txtDescuento.getText());
				} catch (NumberFormatException e) {
					resp.add("El campo descuento tiene que ser un numero");
				}
			}
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

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, "error: " + mensaje);
	}
}
