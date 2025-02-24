package sistema.grafica;
import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import sistema.grafica.Controladores.ControladorRegistroPaseo;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOPaseo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;

public class VentanaRegistroPaseo extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	//private JInternalFrame frmRegistroPaseo;
	
	private JTextField txtCodigoPaseo;
	private JTextField txtPrecioBase;
	private JTextField txtDestino;
	private ControladorRegistroPaseo controlador;
	JFormattedTextField formattedTextHoraPartida;
	JFormattedTextField formattedTextHoraRegreso;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroPaseo window = new VentanaRegistroPaseo();
					window.setVisible(true);
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
		super("Registro Paseos", true, true, true, true);
        setBounds(100, 100, 485, 264);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		
		txtCodigoPaseo = new JTextField();
		txtCodigoPaseo.setToolTipText("Alfanumerico");
		txtCodigoPaseo.setForeground(new Color(0, 0, 0));
		txtCodigoPaseo.setBounds(208, 34, 130, 26);
		panel.add(txtCodigoPaseo);
		txtCodigoPaseo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo de paseo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(61, 38, 137, 15);
		panel.add(lblNewLabel);

		JLabel lblTitulo = new JLabel("Por favor ingresar los datos de un nuevo paseo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setBounds(96, 6, 311, 16);
		panel.add(lblTitulo);

		JLabel lblHoraDePartida = new JLabel("Hora de partida");
		lblHoraDePartida.setToolTipText("");
		lblHoraDePartida.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraDePartida.setBounds(61, 62, 137, 15);
		panel.add(lblHoraDePartida);

		JLabel lblHoraDeRegreso = new JLabel("Hora de regreso");
		lblHoraDeRegreso.setToolTipText("");
		lblHoraDeRegreso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraDeRegreso.setBounds(61, 84, 137, 15);
		panel.add(lblHoraDeRegreso);

		JLabel lblPrecioBase = new JLabel("Precio base");
		lblPrecioBase.setToolTipText("");
		lblPrecioBase.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioBase.setBounds(61, 138, 137, 15);
		panel.add(lblPrecioBase);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setToolTipText("");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDestino.setBounds(61, 111, 137, 15);
		panel.add(lblDestino);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validarCampos())
				{
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
					VOPaseo vo = new VOPaseo();
					vo.setCodigo(txtCodigoPaseo.getText());
					vo.setDestino(txtDestino.getText());
					vo.setPrecioBase(Double.parseDouble(txtPrecioBase.getText()));
					vo.setHoraPartida(LocalTime.parse(formattedTextHoraPartida.getText(), formatter));
					vo.setHoraRegreso(LocalTime.parse(formattedTextHoraRegreso.getText(), formatter));
					
					try {
						controlador.RegistrarPaseo(vo);
						JOptionPane.showMessageDialog(null, "Se ingreso el paseo correctamente.");

					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MinivanNoExiste e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					} catch (PrecioMenorCero e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		btnAceptar.setBounds(229, 182, 92, 29);
		panel.add(btnAceptar);

		txtPrecioBase = new JTextField();
		txtPrecioBase.setToolTipText("Formato Numero");
		txtPrecioBase.setForeground(new Color(0, 0, 0));
		txtPrecioBase.setColumns(10);
		txtPrecioBase.setBounds(208, 132, 130, 26);
		panel.add(txtPrecioBase);

		
		
		txtDestino = new JTextField();
		txtDestino.setToolTipText("");
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setColumns(10);
		txtDestino.setBounds(208, 107, 130, 26);
		panel.add(txtDestino);

		
		
		try {
			formattedTextHoraPartida = new JFormattedTextField(new MaskFormatter("##:##"));
			formattedTextHoraPartida.setBounds(208, 58, 130, 26);
			panel.add(formattedTextHoraPartida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			formattedTextHoraRegreso = new JFormattedTextField(new MaskFormatter("##:##"));
			formattedTextHoraRegreso.setBounds(208, 84, 130, 26);
			panel.add(formattedTextHoraRegreso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

		controlador = new ControladorRegistroPaseo(this);


	}
	
	
	private boolean validarCampos()
	{
		boolean validado = true;
		
		
		return validado;
	}
}
