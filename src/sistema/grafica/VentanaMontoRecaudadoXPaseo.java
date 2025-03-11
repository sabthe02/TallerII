package sistema.grafica;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
	private JInternalFrame fm;

	/**
	 * Create the frame.
	 */
	public VentanaMontoRecaudadoXPaseo() {
        setBounds(420, 100, 602, 301);
        fm = this;
        setTitle("Monto Recaudado para un Paseo");
        setBackground(new Color(255, 200, 145));
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        getContentPane().setLayout(null);

        JLabel labelTitulo = new JLabel("Ingrese el codigo de un paseo para ver el monto recaudado por el");
        labelTitulo.setBounds(90, 10, 400, 30);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(labelTitulo);

        JPanel panelCodigo = new JPanel();
        panelCodigo.setBounds(40, 63, 300, 48);
        panelCodigo.setLayout(null);

        JLabel labelCodigo = new JLabel("Código de Paseo:");
        labelCodigo.setBounds(30, 10, 130, 20);
        labelCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelCodigo.add(labelCodigo);

        textFieldCodigoPaseo = new JTextField(15);
        textFieldCodigoPaseo.setBounds(160, 10, 113, 20);
        textFieldCodigoPaseo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelCodigo.add(textFieldCodigoPaseo);
        getContentPane().add(panelCodigo);
        
        panelResultado = new JPanel();
        panelResultado.setBounds(40, 134, 500, 48);
        panelResultado.setLayout(null);
        getContentPane().add(panelResultado);

        JLabel labelMontoTitulo = new JLabel("Monto Recaudado:");
        labelMontoTitulo.setBounds(46, 10, 200, 30);
        labelMontoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelResultado.add(labelMontoTitulo);

        labelResultado = new JLabel("0.00");
        labelResultado.setBounds(198, 10, 177, 30);
        labelResultado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelResultado.setHorizontalAlignment(SwingConstants.RIGHT);
        panelResultado.add(labelResultado);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(450, 73, 90, 25);
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnAceptar.setBorder(UIManager.getBorder("Button.border"));
        btnAceptar.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				String codigoPaseo = textFieldCodigoPaseo.getText();
				
					List<String> errores = validarCampos();
					if (errores.isEmpty()) {
						controlador = new ControladorMontoRecaudadoXPaseo(VentanaMontoRecaudadoXPaseo.this);
						double montoRecaudado = 0;
						montoRecaudado = controlador.MontoRecaudadoPorPaseo(codigoPaseo);
						labelResultado.setText(String.format("%.2f", montoRecaudado));
					}
					else {
						String aux = "";
						for (String string : errores) {
							aux += string + "\n";
						}

						JOptionPane.showMessageDialog(null,
								"Los datos no son correctos. \n"
										+ aux);
				}
			}
        	
        });
        
        getContentPane().add(btnAceptar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnCancelar.setBorder(UIManager.getBorder("Button.border"));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setBounds(350, 73, 90, 25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	             fm.setVisible(false);	
	        }
        });
        getContentPane().add(btnCancelar);

	}
	
	private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (textFieldCodigoPaseo.getText().trim().equals("")) {
			resp.add("El codigo del Paseo no puede estar vacio.");
		}
		if ((!(textFieldCodigoPaseo.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("El codigo del Paseo tiene que ser alfanumerico");
		}

		return resp;
	}
	
    public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(getContentPane(), "error: "+ mensaje);
    }
}
