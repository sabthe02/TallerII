package sistema.grafica;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
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
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JInternalFrame fm;

    
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
    	
	 	fm = this;
        setTitle("Listado de Boletos Vendidos para un Paseo");
        setBounds(300, 100, 800, 322);
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
        panelCodigo.setBounds(16, 32, 764, 33);
        panelCodigo.setMaximumSize(new Dimension(400, 33));
        
        JLabel labelCodigo = new JLabel("Código de Paseo:");
        labelCodigo.setBounds(6, 8, 113, 17);
        labelCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        txtCodigoPaseo = new JTextField(15);
        txtCodigoPaseo.setBounds(131, 5, 150, 23);
        txtCodigoPaseo.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        panelCodigo.setLayout(null);
        
        panelCodigo.add(labelCodigo);
        panelCodigo.add(txtCodigoPaseo);
        PanelPrincipal.add(panelCodigo);
        radiobuttonComun = new JRadioButton("Común");
        radiobuttonComun.setSelected(true);
        radiobuttonComun.setBackground(new Color(255, 200, 145));
        radiobuttonComun.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        radiobuttonComun.setBounds(331, 3, 100, 25);
        panelCodigo.add(radiobuttonComun);
        
        grupoBoletos = new ButtonGroup();
		grupoBoletos.add(radiobuttonComun);

        
        controlador = new ControladorListadoBoletosVendidosPorPaseo(this);
        
        
        panelCodigo.setLayout(new BorderLayout(0, 0));
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(null);
        radioPanel.setBackground(new Color(255, 200, 145));
        panelCodigo.add(radioPanel);
        
        JButton btnAceptar = new JButton("Buscar");
        btnAceptar.setBounds(576, 5, 85, 21);
        radioPanel.add(btnAceptar);
        btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10)); 
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setBorder(UIManager.getBorder("Button.border"));
        
        btnCancelar = new JButton("Salir");
        btnCancelar.setBounds(673, 5, 85, 21);
        radioPanel.add(btnCancelar);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnCancelar.setBorder(UIManager.getBorder("Button.border"));
        btnCancelar.setBackground(Color.RED);
        
        radiobuttonEspecial = new JRadioButton("Especial");
        radiobuttonEspecial.setBounds(434, 3, 100, 25);
        radioPanel.add(radiobuttonEspecial);
        radiobuttonEspecial.setBackground(new Color(255, 200, 145));
        radiobuttonEspecial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        grupoBoletos.add(radiobuttonEspecial);
  
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
        	             fm.setVisible(false);	
        	        }
                });
                
        
        btnAceptar.addActionListener(new ActionListener() {
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
        scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scrollPane.setBackground(new Color(255, 200, 145));
        scrollPane.setBounds(16, 75, 754, 195);
        
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
    		datos.forEach(boleto -> modeloTabla.addRow(new String[] {String.valueOf(boleto.getNumeroBoleto()),boleto.getNombre(),   String.valueOf(boleto.getEdad()),boleto.getCelular(), String.valueOf(boleto.getDescuento())}));
    	}else
    	{
        	datos.forEach(boleto -> modeloTabla.addRow(new String[] {String.valueOf(boleto.getNumeroBoleto()), boleto.getNombre(), String.valueOf(boleto.getEdad()),boleto.getCelular()}));

    	}
    	
    }
    
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, "error: " + mensaje);
	}
}
