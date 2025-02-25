package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class VentanaListadoPaseosDispBoletos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JInternalFrame frame;
	private JTable table;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoPaseosDispBoletos window = new VentanaListadoPaseosDispBoletos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaListadoPaseosDispBoletos() {
		setTitle("Listado de Paseos por Disponibilidad de Boletos");
		setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
		frame = this;
		frame.setClosable(true);
		frame.setBounds(100, 100, 783, 395);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPane.setBounds(0, 32, 769, 257);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 6));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Destino", "Hora de partida", "Hora de regreso", "Precio base", "Cantidad maxima de boletos vendibles", "Cantidad de boletos disponibles"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(1).setPreferredWidth(47);
		table.getColumnModel().getColumn(2).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.getColumnModel().getColumn(5).setPreferredWidth(186);
		table.getColumnModel().getColumn(6).setPreferredWidth(156);
		
		lblNewLabel = new JLabel("Listado de Paseos Por Disponibilidad de Boletos");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(206, 9, 377, 13);
		getContentPane().add(lblNewLabel);
		
		
	}
	
	

}
