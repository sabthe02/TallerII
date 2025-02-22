package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaListadoGeneralMinivanes extends JInternalFrame {
    private JTable table;
    private DefaultTableModel modeloTabla;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoGeneralMinivanes frame = new VentanaListadoGeneralMinivanes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaListadoGeneralMinivanes() {
        // Configuración básica del frame
        setTitle("Listado general de Minivanes");
        setBounds(100, 100, 800, 400); 
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        
        getContentPane().setLayout(new BorderLayout(0, 10));
        
        JLabel lblTitulo = new JLabel("Listado general de Minivanes");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(lblTitulo, BorderLayout.NORTH);
        
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Matrícula", "Marca", "Modelo", 
                "Cantidad de Asientos", "Cantidad de Paseos Asignados"
            }
        ) {
            /**
			 * 
			 */
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
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    

    
    // Método para limpiar la tabla
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}