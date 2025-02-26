package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.Controladores.ControladorListadoGeneralPaseos;
import sistema.grafica.Controladores.ControladorRegistroPaseo;
import sistema.logica.ValueObject.VOMinivanListado;
import sistema.logica.ValueObject.VOPaseosListado;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaListadoPaseosMinivan extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private JInternalFrame frmListadoPaseosDe;
	private JTable table;
	private JTextField textField;
	private ControladorListadoGeneralPaseos controlador;

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
		frmListadoPaseosDe.setBounds(50, 80, 800, 389);
//		frmListadoPaseosDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListadoPaseosDe.getContentPane().setLayout(null);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 111, 764, 186);
		frmListadoPaseosDe.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setBackground(new Color(240, 240, 240));
	    table.setGridColor(Color.GRAY);
	    table.getTableHeader().setReorderingAllowed(false);
	    table.setRowHeight(25);
	
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
		
		JLabel lblTitulo = new JLabel("Listado de Paseos de una minivan");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBounds(211, 10, 291, 13);
		getContentPane().add(lblTitulo);
		
		
		textField = new JTextField();
		textField.setBounds(323, 33, 85, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIngresarCodigo = new JLabel("Ingresar codigo:");
		lblIngresarCodigo.setFont(new Font("Arial", Font.BOLD, 12));
		lblIngresarCodigo.setBounds(197, 36, 116, 13);
		getContentPane().add(lblIngresarCodigo);
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(47);
		table.getColumnModel().getColumn(2).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
		table.getColumnModel().getColumn(5).setPreferredWidth(185);
		table.getColumnModel().getColumn(6).setPreferredWidth(159);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(323, 62, 85, 21);
		
		
		btnAceptar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				List<String> errores = validarCampos();
            	if (errores.isEmpty()) {
            		String matricula = textField.getText().trim();
            		
            		controlador = new ControladorListadoGeneralPaseos(VentanaListadoPaseosMinivan.this);
            		ArrayList<VOPaseosListado> resp = controlador.ObtenerPaseos(matricula);
			        
					if (resp != null) {
    					DefaultTableModel model = (DefaultTableModel) table.getModel();
    					model.setRowCount(0);
    					
    					resp.forEach(arg1-> model.addRow(new Object[] { 
    							arg1.getCodigo(), arg1.getDestino(),
    							arg1.getHoraPartida(), arg1.getHoraRegreso(), 
    							arg1.getPrecioBase(),arg1.getCantidadMaximaBoletosVendibles(), 
    							arg1.getCantidadBoletosDisponibles() 
    							
    					}));
    				}
			
            	} 
            	else {
					String aux = "";
					for (String string : errores) {
						aux += string + "\n";
					}

					JOptionPane.showMessageDialog(null,
							"Los datos no son correctos\n"
									+ aux);

				}
            	
            }
		});
		getContentPane().add(btnAceptar);
		
		
	}
	
	private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (textField.getText().trim().equals("")) {
			resp.add("La matricula no puede estar vacio");
		}
		if ((!(textField.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("La matricula tiene que ser alfanumerica");
		}

		return resp;
	}
	
	
	public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(frmListadoPaseosDe, "error: "+ mensaje);
    }
}
