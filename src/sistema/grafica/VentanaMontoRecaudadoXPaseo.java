package sistema.grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class VentanaMontoRecaudadoXPaseo extends JInternalFrame {

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
	private JTextField textField;
	private JPanel panelResultado;
	private JLabel labelResultado;

	/**
	 * Create the frame.
	 */
	public VentanaMontoRecaudadoXPaseo() {
        setBounds(100, 100, 800, 400);
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

        JButton btnAceptar = new JButton("Consultar Monto");
        btnAceptar.setBounds(320, 130, 160, 30);
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        getContentPane().add(btnAceptar);

        // Panel de resultado
        panelResultado = new JPanel();
        panelResultado.setBounds(150, 180, 500, 120);
        panelResultado.setLayout(null);
        getContentPane().add(panelResultado);

        // Etiqueta para mostrar el resultado
        JLabel labelMontoTitulo = new JLabel("Monto Recaudado:");
        labelMontoTitulo.setBounds(50, 40, 200, 30);
        labelMontoTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelResultado.add(labelMontoTitulo);

        labelResultado = new JLabel("0.00");
        labelResultado.setBounds(201, 40, 177, 30);
        labelResultado.setFont(new Font("Arial", Font.BOLD, 18));
        labelResultado.setHorizontalAlignment(SwingConstants.RIGHT);
        panelResultado.add(labelResultado);

	}
}
