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
		setBounds(50, 80, 468, 317);
		setTitle("Venta de Boletos");
		getContentPane().setLayout(null);
		setBackground(new Color(255, 200, 145));

		JLabel label = new JLabel("Código de Paseo:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(30, 35, 133, 26);
		getContentPane().add(label);
		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCodigoPaseo.setBounds(167, 35, 217, 26); 
		getContentPane().add(txtCodigoPaseo);

		JLabel label_1 = new JLabel("Nombre del Turista:");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(30, 71, 133, 26);
		getContentPane().add(label_1);
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtNombre.setBounds(167, 71, 217, 26);
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
		txtEdad.setBounds(167, 106, 217, 29);
		txtEdad.setToolTipText("Formato numero entero entre 1 y 110");
		getContentPane().add(txtEdad);

		txtCelular = new JFormattedTextField();
		txtCelular.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCelular.setToolTipText("Formato numero entero mayor a 0");
		txtCelular.setBounds(167, 145, 217, 28);
		getContentPane().add(txtCelular);

		JLabel label_4 = new JLabel("Tipo de Boleto:");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_4.setBounds(30, 183, 133, 26);
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
		radioButtonComun.setBounds(167, 186, 73, 23);
		radioButtonComun.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		radioButtonEspecial.setBounds(303, 186, 81, 23);
		radioButtonEspecial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(radioButtonEspecial);

		grupoBoletos = new ButtonGroup();
		grupoBoletos.add(radioButtonComun);
		grupoBoletos.add(radioButtonEspecial);

		label_5 = new JLabel("Valor de Descuento:");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_5.setBounds(30, 224, 133, 23);
		getContentPane().add(label_5);

		txtDescuento = new JTextField();
		txtDescuento.setBounds(167, 219, 217, 24);
		getContentPane().add(txtDescuento);
		
		JLabel lblNewLabel = new JLabel("Por favor ingresar datos del boleto a registrar");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(30, 12, 354, 13);
		getContentPane().add(lblNewLabel);

		btnAceptar = new JButton("Aceptar");
        btnAceptar.setText("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
		btnAceptar.setBounds(352, 253, 84, 23);

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
        btnCancelar.setBounds(261, 253, 81, 23);
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
