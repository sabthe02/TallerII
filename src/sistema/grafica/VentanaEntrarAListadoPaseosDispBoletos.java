package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class VentanaEntrarAListadoPaseosDispBoletos extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JInternalFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEntrarAListadoPaseosDispBoletos window = new VentanaEntrarAListadoPaseosDispBoletos();
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
	public VentanaEntrarAListadoPaseosDispBoletos() {
		frame = this;
		frame.setTitle("Listado de paseos por disponibilidad de boletos");
		frame.setBounds(100, 100, 450, 213);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCantBoletos = new JLabel("Ingresar cantidad de boletos disponibles que debe tener el paseo");
		lblCantBoletos.setToolTipText("Formato numero entero");
		lblCantBoletos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantBoletos.setBounds(10, 32, 414, 15);
		frame.getContentPane().add(lblCantBoletos);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(160, 110, 85, 21);
		frame.getContentPane().add(btnBuscar);
		
		JFormattedTextField cantBoletos = new JFormattedTextField();
		cantBoletos.setToolTipText("Formato numero entero");
		cantBoletos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantBoletos.setBounds(160, 57, 85, 19);
		frame.getContentPane().add(cantBoletos);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
			
				frame.setVisible(false);
					}

		});
		
	}
	
	
}
