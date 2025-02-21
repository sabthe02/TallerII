package sistema.grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JButton;

public class VentanaRegistroPaseo {

	private JFrame frmRegistroPaseo;
	private JTextField txtCodigoPaseo;
	private JTextField txtPrecioBase;
	private JTextField txtDestino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroPaseo window = new VentanaRegistroPaseo();
					window.frmRegistroPaseo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroPaseo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistroPaseo = new JFrame();
		frmRegistroPaseo.setTitle("Registro Paseo");
		frmRegistroPaseo.setBounds(100, 100, 450, 300);
		frmRegistroPaseo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroPaseo.getContentPane().setLayout(null);
		
		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setToolTipText("Alfanumerico");
		txtCodigoPaseo.setForeground(new Color(0, 0, 0));
		txtCodigoPaseo.setBounds(141, 42, 285, 30);
		frmRegistroPaseo.getContentPane().add(txtCodigoPaseo);
		txtCodigoPaseo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo de paseo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 49, 131, 13);
		frmRegistroPaseo.getContentPane().add(lblNewLabel);
		
		JLabel lblTitulo = new JLabel("Por favor ingresar los datos de un nuevo paseo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setBounds(10, 10, 416, 13);
		frmRegistroPaseo.getContentPane().add(lblTitulo);
		
		JLabel lblHoraDePartida = new JLabel("Hora de partida");
		lblHoraDePartida.setToolTipText("");
		lblHoraDePartida.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraDePartida.setBounds(10, 85, 131, 13);
		frmRegistroPaseo.getContentPane().add(lblHoraDePartida);
		
		JLabel lblHoraDeRegreso = new JLabel("Hora de regreso");
		lblHoraDeRegreso.setToolTipText("");
		lblHoraDeRegreso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraDeRegreso.setBounds(10, 121, 131, 13);
		frmRegistroPaseo.getContentPane().add(lblHoraDeRegreso);
		
		JLabel lblPrecioBase = new JLabel("Precio base");
		lblPrecioBase.setToolTipText("");
		lblPrecioBase.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioBase.setBounds(10, 156, 131, 13);
		frmRegistroPaseo.getContentPane().add(lblPrecioBase);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setToolTipText("");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDestino.setBounds(10, 192, 131, 13);
		frmRegistroPaseo.getContentPane().add(lblDestino);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(184, 225, 85, 21);
		frmRegistroPaseo.getContentPane().add(btnAceptar);
		
		txtPrecioBase = new JTextField();
		txtPrecioBase.setToolTipText("Formato Numero");
		txtPrecioBase.setForeground(new Color(0, 0, 0));
		txtPrecioBase.setColumns(10);
		txtPrecioBase.setBounds(141, 149, 285, 30);
		frmRegistroPaseo.getContentPane().add(txtPrecioBase);
		
		txtDestino = new JTextField();
		txtDestino.setToolTipText("");
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setColumns(10);
		txtDestino.setBounds(141, 185, 285, 30);
		frmRegistroPaseo.getContentPane().add(txtDestino);
		
		
		
		JFormattedTextField formattedTextHoraPartida = new JFormattedTextField();
		
//		protected MaskFormatter createFormatter(String s) {
//		    MaskFormatter formatter = null;
//		    try {
//		        formatter = new MaskFormatter(s);
//		    } catch (java.text.ParseException exc) {
//		        System.err.println("formatter is bad: " + exc.getMessage());
//		        System.exit(-1);
//		    }
//		    return formatter;
//		}
		    
		formattedTextHoraPartida.setBounds(141, 78, 285, 30);
		frmRegistroPaseo.getContentPane().add(formattedTextHoraPartida);
		
		JFormattedTextField formattedTextHoraRegreso = new JFormattedTextField();
		formattedTextHoraRegreso.setBounds(141, 114, 285, 30);
		frmRegistroPaseo.getContentPane().add(formattedTextHoraRegreso);
		
//		btnAceptar.addActionListener(
//				new ActionListener() {
//					public void actionPerformed (ActionEvent e) {
//						String codigo = txtCodigoPaseo.getText();
//
//					}
//
//				} 
//				);
//	}


	}
}
