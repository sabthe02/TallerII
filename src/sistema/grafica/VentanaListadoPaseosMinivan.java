package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VentanaListadoPaseosMinivan extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private JInternalFrame frmListadoPaseosDe;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoPaseosMinivan window = new VentanaListadoPaseosMinivan();
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
	public VentanaListadoPaseosMinivan() {	

		//super();

		frmListadoPaseosDe = this;
		frmListadoPaseosDe.setTitle("Listado Paseos de una minivan");
		frmListadoPaseosDe.setBounds(100, 100, 778, 486);
		//frmListadoPaseosDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListadoPaseosDe.getContentPane().setLayout(null);
		setResizable(true);
	  setClosable(true);
	  setMaximizable(true);
	  setIconifiable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 764, 352);
		frmListadoPaseosDe.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 7));
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Destino", "Hora de partida", "Hora de regreso", "Precio base", "Cantidad m\u00E1xima de boletos vendibles", "Cantidad de boletos disponibles"
			}
		) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Float.class, Integer.class, Integer.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblNewLabel = new JLabel("Listado de Paseos de una minivan");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(211, 10, 291, 13);
		getContentPane().add(lblNewLabel);
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(47);
		table.getColumnModel().getColumn(2).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
		table.getColumnModel().getColumn(5).setPreferredWidth(185);
		table.getColumnModel().getColumn(6).setPreferredWidth(159);
	}
	
	
	
	
}
