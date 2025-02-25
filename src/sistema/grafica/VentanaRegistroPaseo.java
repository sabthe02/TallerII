package sistema.grafica;
import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorRegistroMinivan;
import sistema.grafica.Controladores.ControladorRegistroPaseo;
import sistema.logica.Excepciones.*;
import sistema.logica.ValueObject.VOMinivan;
import sistema.logica.ValueObject.VOPaseo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

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
	private JTextField txtDestino;
	private ControladorRegistroPaseo controlador;
	JFormattedTextField formattedTextHoraPartida;
	JFormattedTextField formattedTextHoraRegreso;
	JFormattedTextField formattedTextPrecio;
	JInternalFrame fm;


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
		fm = this;
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


		
		
		txtDestino = new JTextField();
		txtDestino.setToolTipText("");
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setColumns(10);
		txtDestino.setBounds(208, 107, 130, 26);
		panel.add(txtDestino);

		//Había probado si se podía restringir a un LocalTime, pero no lo pude hacer andar :-(
//		NumberFormat format = NumberFormat.getInstance();
//        format.setGroupingUsed(true);
//        NumberFormatter sleepFormatter = new NumberFormatter(format);
//        sleepFormatter.setValueClass(LocalTime.class);
//        sleepFormatter.setMinimum(1);
//        sleepFormatter.setMaximum(59);
//        sleepFormatter.setAllowsInvalid(false);
//        sleepFormatter.setCommitsOnValidEdit(true);
//        formattedTextHoraPartida = new JFormattedTextField(sleepFormatter);
			try {
				formattedTextHoraPartida = new JFormattedTextField(new MaskFormatter("##:##"));
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Error de parseo");
			}
			formattedTextHoraPartida.setToolTipText("Formato hh::mm");
			formattedTextHoraPartida.setBounds(208, 58, 130, 26);
			panel.add(formattedTextHoraPartida);

	//Misma cosa acá :-(	
//			NumberFormat format2 = NumberFormat.getInstance();
//	        format2.setGroupingUsed(false);
//	        NumberFormatter sleepFormatter2 = new NumberFormatter(format2);
//	        sleepFormatter2.setValueClass(LocalTime.class);
//	        sleepFormatter2.setMinimum(1);
//	        sleepFormatter2.setMaximum(59);
//	        sleepFormatter2.setAllowsInvalid(false);
//	        sleepFormatter2.setCommitsOnValidEdit(true);
//	        formattedTextHoraRegreso = new JFormattedTextField(sleepFormatter2);
	        
		try {
			formattedTextHoraRegreso = new JFormattedTextField(new MaskFormatter("##:##"));
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Error de parseo");
		}	
			formattedTextHoraRegreso.setToolTipText("Formato hh::mm");
			formattedTextHoraRegreso.setBounds(208, 84, 130, 26);
			panel.add(formattedTextHoraRegreso);
			
// Había probado para restringir el formato a float, pero no me funcionó, capaz es algo choto porque en Minivan con Inteder funcionó
//			NumberFormat format3 = NumberFormat.getNumberInstance();
//	        format3.setGroupingUsed(false);
//	        NumberFormatter sleepFormatter3 = new NumberFormatter(format3);
//	        sleepFormatter3.setValueClass(Double.class);
//	        sleepFormatter3.setMinimum(1);
//	        sleepFormatter3.setMaximum(99999999);
//	        sleepFormatter3.setAllowsInvalid(false);
//	        sleepFormatter3.setCommitsOnValidEdit(true);
//	        formattedTextPrecio = new JFormattedTextField(sleepFormatter3);
//	        formattedTextPrecio.setColumns(8);


			formattedTextPrecio = new JFormattedTextField();
			panel.add(formattedTextPrecio);
			formattedTextPrecio.setToolTipText("Formato numero");
			formattedTextPrecio.setBounds(208, 134, 130, 26);

			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(validarCampos())
					{
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
						VOPaseo vo = new VOPaseo();
						vo.setCodigo(txtCodigoPaseo.getText());
						vo.setDestino(txtDestino.getText());
						vo.setPrecioBase(Double.parseDouble(formattedTextPrecio.getText()));
						vo.setHoraPartida(LocalTime.parse(formattedTextHoraPartida.getText(), formatter));
						vo.setHoraRegreso(LocalTime.parse(formattedTextHoraRegreso.getText(), formatter));
						
						controlador = new ControladorRegistroPaseo(VentanaRegistroPaseo.this);
						controlador.RegistrarPaseo(vo);
						fm.setVisible(false);
					}
				}
			});
			btnAceptar.setBounds(229, 182, 92, 29);
			panel.add(btnAceptar);

	}
	
	
	private boolean validarCampos()
	{
		boolean validado = false;
		
		if (txtCodigoPaseo.getText().matches("[A-Za-z0-9]+")) {	
			validado = true;
        }
        else {
        	JOptionPane.showMessageDialog(VentanaRegistroPaseo.this, "Ingresar matricula alfanumerica");
        }
		
		return validado;
	}
	
	public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(this, "error: "+ mensaje);
    }
}
