package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaEntrarAListadoPaseosMinivan extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmListadoPaseosPor;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEntrarAListadoPaseosMinivan window = new VentanaEntrarAListadoPaseosMinivan();
					window.frmListadoPaseosPor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEntrarAListadoPaseosMinivan() {
		frmListadoPaseosPor = new JFrame();
		frmListadoPaseosPor.setTitle("Listado paseos por minivan");
		frmListadoPaseosPor.setBounds(100, 100, 450, 165);
		frmListadoPaseosPor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListadoPaseosPor.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar matricula de la minivan para buscar sus paseos asignados");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 426, 13);
		frmListadoPaseosPor.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(172, 44, 85, 19);
		frmListadoPaseosPor.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(172, 83, 85, 21);
		frmListadoPaseosPor.getContentPane().add(btnNewButton);
	}

	/**
	 * Initialize the contents of the frame.
	 */


}
