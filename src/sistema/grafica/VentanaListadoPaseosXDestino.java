package sistema.grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class VentanaListadoPaseosXDestino extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Destino");
	private JTextField textField;
	private DefaultTableModel modeloTabla;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoPaseosXDestino frame = new VentanaListadoPaseosXDestino();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaListadoPaseosXDestino() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Listado de Paseos por Destino");
        setBounds(100, 100, 800, 400);
        setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Listado de Paseos por Destino");
		labelTitulo.setBounds(227, 10, 380, 22);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(labelTitulo);
        
        JPanel panelDestino = new JPanel();
        panelDestino.setBounds(237, 43, 235, 48);
        contentPane.add(panelDestino);
        panelDestino.add(lblNewLabel);
        
        textField = new JTextField();
        panelDestino.add(textField);
        textField.setColumns(10);
        
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
            scrollPane.setBounds(50, 110, 700, 200); 
            contentPane.add(scrollPane);
        
 
        
		
		
	}
}
