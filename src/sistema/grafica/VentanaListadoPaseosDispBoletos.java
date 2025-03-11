package sistema.grafica;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorListadoPaseosPorBoletos;
import sistema.logica.ValueObject.VOPaseosListado;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
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
	private ControladorListadoPaseosPorBoletos controlador;
	private JFrame jframePrinc;
	private DefaultTableModel modeloTabla;

	
	
	/**
	 * Create the application.
	 */
	public VentanaListadoPaseosDispBoletos(JFrame jframePadre) {
		super("Listado de paseos por disponibilidad de boletos", true, true, true, true);
		frame = this;
		setBounds(2, 80, 1431, 428);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 200, 145));
		jframePrinc = jframePadre;

		JLabel lblCantBoletos = new JLabel("Ingrese la cantidad de boletos para obtener los paseos que tienen al menos esa cantidad de boletos disponibles");
		lblCantBoletos.setToolTipText("Formato numero entero");
		lblCantBoletos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCantBoletos.setBounds(10, 10, 706, 15);
		getContentPane().add(lblCantBoletos);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnAceptar.setBounds(193, 35, 85, 21);
		btnAceptar.setBackground(Color.GREEN);
		getContentPane().add(btnAceptar);

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
		cantBoletos.setBounds(10, 35, 85, 19);
		frame.getContentPane().add(cantBoletos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				
			}
		});
		scrollPane.setBounds(5, 91, 1425, 300);
		getContentPane().add(scrollPane);
		
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Destino", "Hora de partida", "Hora de regreso", "Precio base", "Cantidad maxima de boletos vendibles", "Cantidad de boletos disponibles"
			}
		));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
					jframePrinc.getContentPane().add(v);
					v.setVisible(true);
			
				}else 
				{
					mostrarError("Primero debe seleccionar un paseo de la tabla.");
					
				}
			}
		});
		btnComprarBoleto.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnComprarBoleto.setBounds(570, 60, 121, 21);
		getContentPane().add(btnComprarBoleto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnCancelar.setBounds(105, 35, 85, 21);
		btnCancelar.setBorder(UIManager.getBorder("Button.border"));
	    btnCancelar.setBackground(Color.RED);
	    btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);	
	        }
        });
		getContentPane().add(btnCancelar);
		
		JLabel lblMarqueUnPaseo = new JLabel("Marque un Paseo de la lista y haga click en \"Comprar Boleto\" para comprar un boleto en ese Paseo");
		lblMarqueUnPaseo.setToolTipText("Formato numero entero");
		lblMarqueUnPaseo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMarqueUnPaseo.setBounds(10, 63, 550, 15);
		getContentPane().add(lblMarqueUnPaseo);

		btnAceptar.addActionListener(new ActionListener() {
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
