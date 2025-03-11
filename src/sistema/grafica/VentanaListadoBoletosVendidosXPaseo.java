package sistema.grafica;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.Controladores.ControladorListadoBoletosVendidosPorPaseo;
import sistema.logica.ValueObject.VOListadoBoletos;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaListadoBoletosVendidosXPaseo extends JInternalFrame {
    private static final long serialVersionUID = 1L;
	private JTextField txtCodigoPaseo;
    private JRadioButton radiobuttonComun;
    private JRadioButton radiobuttonEspecial;
	private DefaultTableModel modeloTabla;
	private ButtonGroup grupoBoletos;
    //private JTable table;
    
    private ControladorListadoBoletosVendidosPorPaseo controlador;
    private JTable table_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoBoletosVendidosXPaseo frame = new VentanaListadoBoletosVendidosXPaseo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaListadoBoletosVendidosXPaseo() {
    	
        setTitle("Listado de Boletos Vendidos para un Paseo");
        setBounds(100, 100, 800, 269);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        JPanel PanelPrincipal = new JPanel();
        PanelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        PanelPrincipal.setBackground(new Color(255, 200, 145));
        PanelPrincipal.setLayout(null);
        
        JPanel panelCodigo = new JPanel();
        panelCodigo.setBounds(6, 6, 764, 33);
        panelCodigo.setMaximumSize(new Dimension(400, 33));
        
        JLabel labelCodigo = new JLabel("Código de Paseo:");
        labelCodigo.setBounds(6, 8, 113, 17);
        labelCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        txtCodigoPaseo = new JTextField(15);
        txtCodigoPaseo.setBounds(131, 5, 171, 23);
        txtCodigoPaseo.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        panelCodigo.setLayout(null);
        
        panelCodigo.add(labelCodigo);
        panelCodigo.add(txtCodigoPaseo);
        PanelPrincipal.add(panelCodigo);
        radiobuttonComun = new JRadioButton("Común");
        radiobuttonComun.setBackground(new Color(255, 200, 145));
        radiobuttonComun.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        radiobuttonComun.setBounds(331, 3, 136, 25);
        panelCodigo.add(radiobuttonComun);
        
        radiobuttonEspecial = new JRadioButton("Especial");
        radiobuttonEspecial.setBackground(new Color(255, 200, 145));
        radiobuttonEspecial.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        radiobuttonEspecial.setBounds(479, 3, 136, 25);
        panelCodigo.add(radiobuttonEspecial);
        
        grupoBoletos = new ButtonGroup();
		grupoBoletos.add(radiobuttonComun);
		grupoBoletos.add(radiobuttonEspecial);

        
        controlador = new ControladorListadoBoletosVendidosPorPaseo(this);
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        btnNewButton.setBackground(Color.GREEN);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		if(!txtCodigoPaseo.getText().trim().equals(""))
        		{
        			ArrayList<VOListadoBoletos> resp = controlador.obtenerListado(txtCodigoPaseo.getText(), radiobuttonEspecial.isSelected());
        			
        			if(resp != null)
        			{
        				
        				cargarDatosTabla(resp);
        			}
        			
        		}else {
        			mostrarError("El campo Codigo Paseo no puede estar vacio.");
        			
        		}
        
        	}
        });
        btnNewButton.setBounds(673, 7, 85, 21);
        panelCodigo.add(btnNewButton);
        
        
        
        panelCodigo.setLayout(new BorderLayout(0, 0));
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(null);
        radioPanel.setBackground(new Color(255, 200, 145));
        panelCodigo.add(radioPanel);
        
        PanelPrincipal.add(panelCodigo);
        
        getContentPane().add(PanelPrincipal, BorderLayout.CENTER);
        
        modeloTabla = new DefaultTableModel(new Object[][] {},
				new String[] { "Numero de Boleto", "Nombre Pasajero", "Edad Pasajero", "Celular", "Descuento"}) {
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
        scrollPane.setBackground(new Color(255, 200, 145));
        scrollPane.setBounds(16, 51, 754, 166);
        
        PanelPrincipal.add(scrollPane);
        
        table_1 = new JTable(modeloTabla);
        table_1.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        table_1.setBackground(new Color(240, 240, 240));
        table_1.setGridColor(Color.GRAY);
        table_1.getTableHeader().setReorderingAllowed(false);
        table_1.setRowHeight(25);
        scrollPane.setViewportView(table_1);
    }
    
    
    public void cargarDatosTabla(ArrayList<VOListadoBoletos> datos)
    {
    	
    	
    	modeloTabla.setRowCount(0);
    	if(radiobuttonEspecial.isSelected())
    	{
    		if(modeloTabla.getColumnCount() == 4)
    		{
    			modeloTabla.addColumn("Descuento");    			
    		}
    	}else
    	{
    		if(modeloTabla.getColumnCount() == 5)
    		{
    			modeloTabla.setColumnCount(4);
    		}    
    	}
    	

    
    	
    	if(radiobuttonEspecial.isSelected())
    	{
    		datos.forEach(boleto -> modeloTabla.addRow(new String[] {String.valueOf(boleto.getNumeroBoleto()), boleto.getCelular(), boleto.getNombre(), String.valueOf(boleto.getEdad()), String.valueOf(boleto.getDescuento())}));
    	}else
    	{
        	datos.forEach(boleto -> modeloTabla.addRow(new String[] {String.valueOf(boleto.getNumeroBoleto()), boleto.getCelular(), boleto.getNombre(), String.valueOf(boleto.getEdad())}));

    	}
    	
    }
    
    public void mostrarError(String mensaje)
    {
    	
    	JOptionPane.showMessageDialog(null, "error: "+ mensaje);
    }
}