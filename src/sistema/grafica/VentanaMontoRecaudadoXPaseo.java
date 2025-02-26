package sistema.grafica;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import sistema.grafica.Controladores.ControladorMontoRecaudadoXPaseo;


public class VentanaMontoRecaudadoXPaseo extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMontoRecaudadoXPaseo frame = new VentanaMontoRecaudadoXPaseo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField textFieldCodigoPaseo;
	private JPanel panelResultado;
	private JLabel labelResultado;
	private ControladorMontoRecaudadoXPaseo controlador;

	/**
	 * Create the frame.
	 */
	public VentanaMontoRecaudadoXPaseo() {
        setBounds(50, 80, 800, 400);
        setTitle("Monto Recaudado para un Paseo");
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        getContentPane().setLayout(null);

        JLabel labelTitulo = new JLabel("Monto Recaudado para un Paseo");
        labelTitulo.setBounds(200, 20, 400, 30);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(labelTitulo);

        JPanel panelCodigo = new JPanel();
        panelCodigo.setBounds(200, 71, 400, 48);
        panelCodigo.setLayout(null);

        JLabel labelCodigo = new JLabel("Código de Paseo:");
        labelCodigo.setBounds(30, 10, 130, 20);
        labelCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCodigo.add(labelCodigo);

        textFieldCodigoPaseo = new JTextField(15);
        textFieldCodigoPaseo.setBounds(160, 10, 200, 25);
        textFieldCodigoPaseo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCodigo.add(textFieldCodigoPaseo);
        getContentPane().add(panelCodigo);
        
        panelResultado = new JPanel();
        panelResultado.setBounds(150, 180, 500, 120);
        panelResultado.setLayout(null);
        getContentPane().add(panelResultado);

        JLabel labelMontoTitulo = new JLabel("Monto Recaudado:");
        labelMontoTitulo.setBounds(50, 40, 200, 30);
        labelMontoTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelResultado.add(labelMontoTitulo);

        labelResultado = new JLabel("0.00");
        labelResultado.setBounds(201, 40, 177, 30);
        labelResultado.setFont(new Font("Arial", Font.BOLD, 18));
        labelResultado.setHorizontalAlignment(SwingConstants.RIGHT);
        panelResultado.add(labelResultado);

        JButton btnAceptar = new JButton("Consultar Monto");
        btnAceptar.setBounds(320, 130, 160, 30);
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String codigoPaseo = textFieldCodigoPaseo.getText();
				
				if(!codigoPaseo.isEmpty()) {
					if (codigoPaseo.matches("[A-Za-z0-9]+")) {
						controlador = new ControladorMontoRecaudadoXPaseo(VentanaMontoRecaudadoXPaseo.this);
						double montoRecaudado = 0;
						montoRecaudado = controlador.MontoRecaudadoPorPaseo(codigoPaseo);
						labelResultado.setText(String.format("%.2f", montoRecaudado));
					}
					else {
						JOptionPane.showMessageDialog(VentanaMontoRecaudadoXPaseo.this, "Ingrese codigo alfanumerico");
					}
				} else {
					JOptionPane.showMessageDialog(VentanaMontoRecaudadoXPaseo.this, "Ingrese el codigo del paseo");
				}
			}
        	
        });
        
        getContentPane().add(btnAceptar);

	}
	
    public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(getContentPane(), "error: "+ mensaje);
    }
	
}
