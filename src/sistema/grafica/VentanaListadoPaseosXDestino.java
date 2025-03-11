package sistema.grafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListadoPaseosXDestino extends JInternalFrame {

	private static final long serialVersionUID = 5698850134580711712L;
	private JInternalFrame fm;
	private JPanel contentPane;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private JButton btnNewButton;
	private JTextField txtDestino;
	private ControladorListadoGeneralPaseosPorDestino controlador;
	private JFrame jframePrinc;
	private JButton btnComprarBoleto;


	

	/**
	 * Create the frame.
	 */
	public VentanaListadoPaseosXDestino(JFrame jframePadre) {
		super("Listado de Paseos por Destino", true, true, true, true);
		fm = this;
        setBounds(50, 80, 800, 400);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
            scrollPane.setBounds(50, 42, 700, 252); 
            contentPane.add(scrollPane);
            
            btnNewButton = new JButton("Aceptar");
            btnNewButton.setBounds(335, 320, 85, 21);
            contentPane.add(btnNewButton);
            
            JLabel lblNewLabel = new JLabel("Destino:");
            lblNewLabel.setBounds(50, 14, 61, 16);
            contentPane.add(lblNewLabel);
            
            txtDestino = new JTextField();
            txtDestino.setBounds(123, 9, 130, 26);
            contentPane.add(txtDestino);
            txtDestino.setColumns(10);
            
            controlador = new ControladorListadoGeneralPaseosPorDestino(this);
            
            JButton btnNewButton_1 = new JButton("Buscar");
            btnNewButton_1.addActionListener(new ActionListener() {
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
            		
            	}
            });
            btnNewButton_1.setBounds(271, 9, 117, 29);
            contentPane.add(btnNewButton_1);
            
            btnComprarBoleto = new JButton("Comprar Boleto");
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
            btnComprarBoleto.setBounds(640, 9, 130, 29);
            contentPane.add(btnComprarBoleto);
            
            btnNewButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				fm.setVisible(false);
    			}
            });
            
      	
		
	}
	
	public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(fm, "error: "+ mensaje);
    }
}
