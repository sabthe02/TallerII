package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

public class VentanaRegistroMinivan extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	private JInternalFrame frmRegistroMinivan;
	private JTextField txtCantAsientos;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JLabel lblCantAsientos;
	private JTextField textModelo;
	private JButton btnAceptar;
	private JLabel lblIngresarDatos;
	private JLabel lblCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroMinivan window = new VentanaRegistroMinivan();
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
	public VentanaRegistroMinivan() {
		initialize();
		this.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRegistroMinivan = new JInternalFrame();
		frmRegistroMinivan.setTitle("Registro Minivan");
		frmRegistroMinivan.setBounds(100, 100, 450, 300);
		frmRegistroMinivan.getContentPane().setLayout(null);
			
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 28, 114, 43);
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtMatricula = new JTextField();
		txtMatricula.setToolTipText("Formato alfanumerico");
		txtMatricula.setForeground(new Color(0, 0, 0));
		txtMatricula.setBounds(125, 36, 301, 28);
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmRegistroMinivan.getContentPane().add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(125, 77, 301, 28);
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmRegistroMinivan.getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		txtCantAsientos = new JTextField();
		txtCantAsientos.setToolTipText("Formato de numero entero");
		txtCantAsientos.setForeground(new Color(0, 0, 0));
		txtCantAsientos.setBounds(125, 159, 301, 28);
		txtCantAsientos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmRegistroMinivan.getContentPane().add(txtCantAsientos);
		txtCantAsientos.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarca.setBounds(10, 69, 114, 43);
		frmRegistroMinivan.getContentPane().add(lblMarca);
		
		lblCantAsientos = new JLabel("Cantidad de asientos");
		lblCantAsientos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCantAsientos.setBounds(10, 152, 114, 43);
		frmRegistroMinivan.getContentPane().add(lblCantAsientos);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(199, 217, 114, 36);
		frmRegistroMinivan.getContentPane().add(btnAceptar);
		
		textModelo = new JTextField();
		textModelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textModelo.setColumns(10);
		textModelo.setBounds(125, 115, 301, 28);
		frmRegistroMinivan.getContentPane().add(textModelo);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelo.setBounds(10, 110, 114, 43);
		frmRegistroMinivan.getContentPane().add(lblModelo);
		
		lblIngresarDatos = new JLabel("Por favor ingresar datos de la nueva minivan");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIngresarDatos.setBounds(10, 13, 267, 13);
		frmRegistroMinivan.getContentPane().add(lblIngresarDatos);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(10, 28, 114, 43);
		frmRegistroMinivan.getContentPane().add(lblCodigo);
		
//		btnAceptar.addActionListener(
//			new ActionListener() {
//			public void actionPerformed (ActionEvent e) {
//				String matricula = txtMatricula.getText();
//				String marca = txtMarca.getText();
//				String modelo = textModelo.getText();
//				String cantAsientos = txtCantAsientos.getText();
//				JOptionPane.showMessageDialog(frmRegistroMinivan, matricula);
//			}
//			});
//		
	}
			
	
	public void setVisible (boolean b)
	{
		frmRegistroMinivan.setVisible(b);
	}
}
