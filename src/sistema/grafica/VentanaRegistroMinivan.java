package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaRegistroMinivan {

	private JFrame frmRegistroMinivan;
	private JTextField txtCantAsientos;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JLabel lblCantAsientos;
	private JTextField textModelo;
	private JButton btnAceptar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroMinivan window = new VentanaRegistroMinivan();
					window.frmRegistroMinivan.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroMinivan() {
		this.initialize();
		this.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistroMinivan = new JFrame();
		frmRegistroMinivan.setTitle("Registro Minivan");
		frmRegistroMinivan.setBounds(100, 100, 450, 300);
		frmRegistroMinivan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmRegistroMinivan.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 28, 114, 43);
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setToolTipText("Formato alfanumerico");
		txtMatricula.setForeground(new Color(0, 0, 0));
		txtMatricula.setBounds(125, 36, 301, 28);
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(125, 77, 301, 28);
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtMarca);
		txtMarca.setColumns(10);
		
		txtCantAsientos = new JTextField();
		txtCantAsientos.setToolTipText("Formato de numero entero");
		txtCantAsientos.setForeground(new Color(0, 0, 0));
		txtCantAsientos.setBounds(125, 159, 301, 28);
		txtCantAsientos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtCantAsientos);
		txtCantAsientos.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarca.setBounds(10, 69, 114, 43);
		panel.add(lblMarca);
		
		lblCantAsientos = new JLabel("Cantidad de asientos");
		lblCantAsientos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCantAsientos.setBounds(10, 152, 114, 43);
		panel.add(lblCantAsientos);
		
		JButton btnAceptar = new JButton("Aceptar");
		
		btnAceptar.setBounds(199, 217, 114, 36);
		panel.add(btnAceptar);
		
		textModelo = new JTextField();
		textModelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textModelo.setColumns(10);
		textModelo.setBounds(125, 115, 301, 28);
		panel.add(textModelo);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelo.setBounds(10, 110, 114, 43);
		panel.add(lblModelo);
		
		lblNewLabel = new JLabel("Por favor ingresar datos de la nueva minivan");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 13, 267, 13);
		panel.add(lblNewLabel);
		
//		btnAceptar.addActionListener(
//			new ActionListener() {
//			public void actionPerformed (ActionEvent e) {
//				String matricula = txtMatricula.getText();
//				String marca = txtMarca.getText();
//				String modelo = textModelo.getText();
//				String cantAsientos = txtCantAsientos.getText();
//				JOptionPane.showMessageDialog(frmRegistroMinivan, matricula);
//			}
//			}
//		);
		
	}
	
		
	
	public void setVisible (boolean b)
	{
		frmRegistroMinivan.setVisible(b);
	}
}
