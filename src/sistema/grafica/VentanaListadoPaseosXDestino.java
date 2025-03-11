package sistema.grafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.Controladores.ControladorListadoGeneralPaseosPorDestino;
import sistema.logica.ValueObject.VOPaseosListado;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;

public class VentanaListadoPaseosXDestino extends JInternalFrame {

	private static final long serialVersionUID = 5698850134580711712L;
	private JInternalFrame fm;
	private JPanel contentPane;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private JButton btnCancelar;
	private JTextField txtDestino;
	private ControladorListadoGeneralPaseosPorDestino controlador;
	private JFrame jframePrinc;
	private JButton btnComprarBoleto;
	private JLabel lblNewLabel_1;


	

	/**
	 * Create the frame.
	 */
	public VentanaListadoPaseosXDestino(JFrame jframePadre) {
		super("Listado de Paseos por Destino", true, true, true, true);
		fm = this;
        setBounds(180, 80, 800, 377);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 200, 145));
		
		jframePrinc = jframePadre;

        
        modeloTabla = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "Código", "Destino", "Hora Partida", "Hora Regreso", 
                    "Precio Base", "Máx. Boletos", "Boletos Disponibles"
                }
            ) {
                private static final long serialVersionUID = 1L;
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };
            
            table = new JTable(modeloTabla);
            
            table.setBackground(new Color(240, 240, 240));
            table.setGridColor(Color.GRAY);
            table.getTableHeader().setReorderingAllowed(false);
            table.setRowHeight(25);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            scrollPane.setBounds(10, 79, 764, 252); 
            contentPane.add(scrollPane);
            
            btnCancelar = new JButton("Salir");
            btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
            btnCancelar.setBorder(UIManager.getBorder("Button.border"));
            btnCancelar.setBackground(Color.RED);
            btnCancelar.setBounds(647, 10, 123, 23);
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
    	             fm.setVisible(false);	
    	        }
            });
            contentPane.add(btnCancelar);
            
            txtDestino = new JTextField();
            txtDestino.setBounds(87, 6, 166, 26);
            contentPane.add(txtDestino);
            txtDestino.setColumns(10);
            
            controlador = new ControladorListadoGeneralPaseosPorDestino(this);
            
            JButton btnAceptar = new JButton("Buscar");
            btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
            btnAceptar.setBorder(UIManager.getBorder("Button.border"));
            btnAceptar.setBackground(Color.GREEN);
            btnAceptar.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		
            		String destino = txtDestino.getText();
            		if(!destino.trim().equals(""))
            		{
            			ArrayList<VOPaseosListado> resp = controlador.ObtenerPaseos(destino);

        				if (resp != null) {
        					DefaultTableModel model = (DefaultTableModel) table.getModel();
        					model.setRowCount(0);
        					
        					resp.forEach(arg1-> model.addRow(new Object[] { arg1.getCodigo(), arg1.getDestino(),
        							arg1.getHoraPartida(), arg1.getHoraRegreso(), arg1.getPrecioBase(),
        							arg1.getCantidadMaximaBoletosVendibles(), arg1.getCantidadBoletosDisponibles() }));
        				}
            			
            		}
            		else {
            			JOptionPane.showMessageDialog(fm, "Destino no debe ser vacio");
            		}
            		
            	}
            });
            btnAceptar.setBounds(256, 6, 85, 23);
            contentPane.add(btnAceptar);
            
            btnComprarBoleto = new JButton("Comprar Boleto");
            btnComprarBoleto.setToolTipText("Marcar un destino en la tabla y hacer clic para adquirir un boleto en ese paseo");
            btnComprarBoleto.setFont(new Font("Segoe UI", Font.BOLD, 10));
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
            btnComprarBoleto.setBounds(647, 43, 123, 23);
            contentPane.add(btnComprarBoleto);
            
            lblNewLabel_1 = new JLabel("Destino:");
            lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblNewLabel_1.setBounds(24, 15, 65, 13);
            contentPane.add(lblNewLabel_1);
            
            btnCancelar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				fm.setVisible(false);
    			}
            });
            
      	
		
	}
	
	public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(fm, "error: "+ mensaje);
    }
}
