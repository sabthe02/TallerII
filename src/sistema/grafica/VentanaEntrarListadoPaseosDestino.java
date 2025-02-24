package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaEntrarListadoPaseosDestino extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JInternalFrame frmListadoDePaseos;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEntrarListadoPaseosDestino window = new VentanaEntrarListadoPaseosDestino();
					window.frmListadoDePaseos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEntrarListadoPaseosDestino() {
		initialize();
		this.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListadoDePaseos = this;
		frmListadoDePaseos.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		frmListadoDePaseos.setTitle("Listado de paseos por destino");
		frmListadoDePaseos.setBounds(100, 100, 322, 146);
		frmListadoDePaseos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListadoDePaseos.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar destino:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(28, 36, 120, 13);
		frmListadoDePaseos.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(143, 33, 85, 19);
		frmListadoDePaseos.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(143, 71, 85, 21);
		frmListadoDePaseos.getContentPane().add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			private void actionPerformed (ActionEvent e) {
				frmListadoDePaseos.setVisible(false);
			}
		});
	}

	public void setVisible (boolean b) {
		this.setVisible(b);
	}
}
