package sistema.grafica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorListadoPaseosPorBoletos;
import sistema.grafica.Controladores.ControladorRegistroMinivan;
import sistema.logica.ValueObject.VOPaseosListado;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListadoPaseosDispBoletos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JInternalFrame frame;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JLabel lblNewLabel;
	private ControladorListadoPaseosPorBoletos controlador;
	private JFrame jframePrinc;

	
	
	/**
	 * Create the application.
	 */
	public VentanaListadoPaseosDispBoletos(JFrame jframePadre) {
		super("Listado de paseos por disponibilidad de boletos", true, true, true, true);
		frame = this;
		setBounds(50, 80, 800, 389);
		getContentPane().setLayout(null);
		jframePrinc = jframePadre;

		JLabel lblCantBoletos = new JLabel("Cantidad de Boletos:");
		lblCantBoletos.setToolTipText("Formato numero entero");
		lblCantBoletos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantBoletos.setBounds(10, 32, 135, 15);
		getContentPane().add(lblCantBoletos);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscar.setBounds(254, 30, 85, 21);
		getContentPane().add(btnBuscar);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		JFormattedTextField cantBoletos = new JFormattedTextField(formatter);
		cantBoletos.setToolTipText("Formato numero entero");
		cantBoletos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantBoletos.setBounds(157, 30, 85, 19);
		frame.getContentPane().add(cantBoletos);

		modeloTabla = new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Destino", "Hora de partida", "Hora de regreso", "Precio base",
						"Cantidad maxima de boletos vendibles", "Cantidad de boletos disponibles" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				
			}
		});
		scrollPane.setBounds(6, 59, 800, 278);
		getContentPane().add(scrollPane);
		
		table = new JTable(modeloTabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
				
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(new Color(240, 240, 240));
		table.setGridColor(Color.GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);
		
		JButton btnComprarBoleto = new JButton("Comprar Boleto");
		btnComprarBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRow()>=0)
				{
					
					VentanaVentaBoleto v = new VentanaVentaBoleto(table.getValueAt(table.getSelectedRow(), 0).toString());
					jframePrinc.add(v);
					v.setVisible(true);
			
				}else 
				{
					mostrarError("Primero debe seleccionar un paseo de la tabla.");
					
				}
			}
		});
		btnComprarBoleto.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnComprarBoleto.setBounds(629, 26, 141, 29);
		getContentPane().add(btnComprarBoleto);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<VOPaseosListado> resp = controlador.ObtenerPaseos(Integer.parseInt(cantBoletos.getText()));

				if (resp != null) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					resp.forEach(arg0 -> model.addRow(new Object[] { arg0.getCodigo(), arg0.getDestino(),
							arg0.getHoraPartida(), arg0.getHoraRegreso(), arg0.getPrecioBase(),
							arg0.getCantidadMaximaBoletosVendibles(), arg0.getCantidadBoletosDisponibles() }));
				}

			}

		});

		controlador = new ControladorListadoPaseosPorBoletos(this);

	}

	public void mostrarError(String error) {

		JOptionPane.showMessageDialog(null, "error: " + error);

	}
}
