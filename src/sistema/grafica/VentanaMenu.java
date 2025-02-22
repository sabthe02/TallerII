package sistema.grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;


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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frm = new JDesktopPane();
		frm.setForeground(new Color(171, 171, 171));
		frm.setFont(new Font("Tahoma", Font.BOLD, 12));
		frm.setName("Menu");
		frm.setBorder(UIManager.getBorder("EditorPane.border"));
		frm.setBounds(100, 100, 895, 533);
		frm.setLayout(null);
		

		JComboBox<String> comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(280, 42, 327, 24);
		frm.add(comboBox);
		comboBox.setMaximumRowCount(10);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Elegir opcion", "Registro de minivan", "Listado general de minivanes", "Registro de paseo", "Listado de paseos asignados a una minivan", "Listado de paseos por destino", "Listado de paseos por disponibilidad de boletos", "Venta de boleo", "Listado de boletos vendidos para un paseo", "Monto recaudado en un paseo", "Respaldo de datos"}));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setBounds(375, 344, 85, 21);
		frm.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBox.getSelectedItem();

				if (selectedItem.contentEquals("Registro de minivan")) {
					VentanaRegistroMinivan ventana = new VentanaRegistroMinivan();
					ventana.setVisible(true);
					
				}
			}
		});

	}
	
	public void setVisible (boolean b) {
	}
