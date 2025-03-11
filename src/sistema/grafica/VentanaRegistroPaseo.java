package sistema.grafica;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import sistema.grafica.Controladores.ControladorRegistroPaseo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import javax.swing.JFormattedTextField;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public  class VentanaRegistroPaseo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	// private JInternalFrame frmRegistroPaseo;

	private JTextField txtCodigoPaseo;
	private JTextField txtPrecioBase;
	private JTextField txtDestino;
	private ControladorRegistroPaseo controlador;
	private JFormattedTextField formattedTextHoraPartida;
	private JFormattedTextField formattedTextHoraRegreso;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JInternalFrame fm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					VentanaRegistroPaseo window = new VentanaRegistroPaseo();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public VentanaRegistroPaseo() {
		super("Registro Paseos", true, true, true, true);
		fm = this;
		setBounds(420, 100, 568, 296);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 200, 145));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setToolTipText("Alfanumerico");
		txtCodigoPaseo.setForeground(new Color(0, 0, 0));
		txtCodigoPaseo.setBounds(195, 41, 143, 26);
		panel.add(txtCodigoPaseo);
		txtCodigoPaseo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Codigo de paseo");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(61, 45, 137, 15);
		panel.add(lblNewLabel);

		JLabel lblTitulo = new JLabel("Por favor ingresar los datos de un nuevo paseo");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(96, 10, 311, 16);
		panel.add(lblTitulo);

		JLabel lblHoraDePartida = new JLabel("Hora de partida");
		lblHoraDePartida.setToolTipText("");
		lblHoraDePartida.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblHoraDePartida.setBounds(61, 77, 137, 15);
		panel.add(lblHoraDePartida);

		JLabel lblHoraDeRegreso = new JLabel("Hora de regreso");
		lblHoraDeRegreso.setToolTipText("");
		lblHoraDeRegreso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblHoraDeRegreso.setBounds(61, 109, 137, 15);
		panel.add(lblHoraDeRegreso);

		JLabel lblPrecioBase = new JLabel("Precio base");
		lblPrecioBase.setToolTipText("");
		lblPrecioBase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPrecioBase.setBounds(61, 173, 128, 15);
		panel.add(lblPrecioBase);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setToolTipText("");
		lblDestino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDestino.setBounds(61, 142, 137, 15);
		panel.add(lblDestino);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(Color.GREEN);
		btnAceptar.setBounds(349, 215, 84, 23);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnAceptar.setBorder(UIManager.getBorder("Button.border"));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> errores = validarCampos();
				if (errores.isEmpty()) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
					
					LocalTime horaPartida = LocalTime.parse(formattedTextHoraPartida.getText(), formatter);
					LocalTime horaRegreso = LocalTime.parse(formattedTextHoraRegreso.getText(), formatter);

					controlador = new ControladorRegistroPaseo(VentanaRegistroPaseo.this);
					if (controlador.RegistrarPaseo(txtCodigoPaseo.getText(), txtDestino.getText(), Double.parseDouble(txtPrecioBase.getText()), horaPartida, horaRegreso)) 
					{

						JOptionPane.showMessageDialog(null, "Se ingreso el paseo correctamente.");
						VentanaRegistroPaseo.this.setVisible(false);
					}
				} else {
					String aux = "";
					for (String string : errores) {
						aux += string + "\n";
					}

					JOptionPane.showMessageDialog(null,
							"Los datos no son correctos. \n"
									+ aux);

				}
			}
		});
		
		panel.add(btnAceptar);
		
		 	btnCancelar = new JButton("Cancelar");
	        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
	        btnCancelar.setBorder(UIManager.getBorder("Button.border"));
	        btnCancelar.setBackground(Color.RED);
	        btnCancelar.setBounds(257, 215, 81, 23);
	        btnCancelar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
		             fm.setVisible(false);	
		        }
	        });
	        panel.add(btnCancelar);

		txtPrecioBase = new JTextField();
		txtPrecioBase.setToolTipText("Formato Numero");
		txtPrecioBase.setForeground(new Color(0, 0, 0));
		txtPrecioBase.setColumns(10);
		txtPrecioBase.setBounds(195, 167, 143, 26);
		panel.add(txtPrecioBase);

		txtDestino = new JTextField();
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setColumns(10);
		txtDestino.setBounds(195, 137, 143, 26);
		panel.add(txtDestino);

		try {
			formattedTextHoraPartida = new JFormattedTextField(new MaskFormatter("##:##"));
			formattedTextHoraPartida.setBounds(195, 73, 143, 26);
			panel.add(formattedTextHoraPartida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			formattedTextHoraRegreso = new JFormattedTextField(new MaskFormatter("##:##"));
			formattedTextHoraRegreso.setBounds(195, 106, 143, 26);
			panel.add(formattedTextHoraRegreso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlador = new ControladorRegistroPaseo(this);

	}

	private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (!ValidarHora(formattedTextHoraPartida.getText())) {
			resp.add("Hora de Partida tiene un formato invalido.");
		}

		if (!ValidarHora(formattedTextHoraRegreso.getText())) {
			resp.add("Hora de Regreso tiene un formato invalido.");
		}

		if (txtCodigoPaseo.getText().trim().equals("")) {
			resp.add("El codigo del Paseo no puede estar vacio.");
		}
		if ((!(txtCodigoPaseo.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("El codigo del Paseo tiene que ser alfanumerico");
		}

		if (txtPrecioBase.getText().trim().equals("")) {
			resp.add("El precio del paseo no puede estar vacio.");
		}

		return resp;
	}

	private boolean ValidarHora(String hora) {
		boolean resp = false;

		String[] sep = hora.split(":");

		try {
			if (Integer.parseInt(sep[0]) <= 23 && Integer.parseInt(sep[0]) >= 0) {
				if (Integer.parseInt(sep[1]) < 60 && Integer.parseInt(sep[1]) >= 0) {
					resp = true;
				}
			}

		} catch (Exception e) {
		}

		return resp;

	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, "error: " + mensaje);
	}
	
}
