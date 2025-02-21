package sistema.grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaMenu {

	private JFrame frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu window = new VentanaMenu();
					window.frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMenu() {
		initialize();
		this.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		
		JPanel p = new JPanel();
		
		frm.getContentPane().add(p);
		
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Elegir opcion", "Registro de minivan", "Listado general de minivanes", "Registro de paseo", "Listado de paseos asignados a una minivan", "Listado de paseos por destino", "Listado de paseos por disponibilidad de boletos", "Venta de boleo", "Listado de boletos vendidos para un paseo", "Monto recaudado en un paseo", "Respaldo de datos"}));
		frm.getContentPane().add(comboBox, BorderLayout.NORTH);
		
//		JButton btnNewButton = new JButton("Aceptar");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String selectedItem = (String) coComboBox.getSelectedItem();
//
//				if (selectedItem.contentEquals("Registro de minivan")) {
//					
//				}
//			}
//		});
	}
	
	public void setVisible (boolean b) {
	}
}
