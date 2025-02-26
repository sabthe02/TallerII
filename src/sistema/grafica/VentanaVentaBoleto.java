package sistema.grafica;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorRegistroMinivan;
import sistema.grafica.Controladores.ControladorVentaBoleto;
import sistema.logica.Excepciones.BoletosNoDisponibles;
import sistema.logica.Excepciones.CelularMayorQue1000;
import sistema.logica.Excepciones.MenorDe0;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOMinivan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class VentanaVentaBoleto extends JInternalFrame {
    private static final long serialVersionUID = 2198985395810256235L;
	private JTextField txtCodigoPaseo;
    private JTextField txtNombre;
    private JButton btnRegistrar;
    private JInternalFrame fm;
    private ControladorVentaBoleto controlador;
    private JFormattedTextField txtCelular;
    private JFormattedTextField txtEdad;
    

    public VentanaVentaBoleto() {
    	fm = this;
    	setResizable(true);
    	setIconifiable(true);
    	setClosable(true);
    	setBounds(50, 80, 485, 264);
        setTitle("Venta de Boletos");
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Código de Paseo:");
        label.setFont(new Font("Tahoma", Font.BOLD, 10));
        label.setBounds(30, 0, 131, 36);
        getContentPane().add(label);
        txtCodigoPaseo = new JTextField();
        txtCodigoPaseo.setBounds(197, 6, 187, 26);
        getContentPane().add(txtCodigoPaseo);

        JLabel label_1 = new JLabel("Nombre del Turista:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        label_1.setBounds(30, 47, 187, 36);
        getContentPane().add(label_1);
        txtNombre = new JTextField();
        txtNombre.setBounds(197, 46, 187, 26);
        getContentPane().add(txtNombre);

        JLabel label_2 = new JLabel("Edad:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
        label_2.setBounds(30, 94, 93, 36);
        getContentPane().add(label_2);

        JLabel label_3 = new JLabel("Número de Celular:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
        label_3.setBounds(30, 138, 167, 36);
        getContentPane().add(label_3);

        btnRegistrar = new JButton("Registrar Compra");
        btnRegistrar.setBounds(197, 176, 118, 26);
        getContentPane().add(btnRegistrar);
        
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter sleepFormatter = new NumberFormatter(format);
        sleepFormatter.setValueClass(Integer.class);
        sleepFormatter.setMinimum(1);
        sleepFormatter.setMaximum(110);
        sleepFormatter.setAllowsInvalid(false);

        sleepFormatter.setCommitsOnValidEdit(true);
        txtEdad = new JFormattedTextField(sleepFormatter);
        txtEdad.setBounds(197, 93, 187, 29);
        txtEdad.setToolTipText("Formato numero entero entre 1 y 110");
        getContentPane().add(txtEdad);
        
        
        txtCelular = new JFormattedTextField();
        txtCelular.setToolTipText("Formato numero entero mayor a 0");
        txtCelular.setBounds(197, 138, 187, 28);
        getContentPane().add(txtCelular);
        
        btnRegistrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String codigo = txtCodigoPaseo.getText();
			int edad = Integer.parseInt(txtEdad.getText().trim());
			
			List<String> errores = validarCampos();
			if (errores.isEmpty()) {
            	controlador = new ControladorVentaBoleto(VentanaVentaBoleto.this);
            	VOCompraBoleto vo = new VOCompraBoleto();
            	vo.setCodigoPaseo(codigo);
				vo.setNombre(txtNombre.getText());
				vo.setEdad(edad); 
				vo.setCelular(txtCelular.getText());
				controlador.ComprarBoleto(vo);
				JOptionPane.showMessageDialog(fm, "Se hizo la compra del boleto correctamente.");
				// Por alguna razón tira esto a pesar
				//de no pasar las validaciones, además hay que agregar el tema si es especial o no a la ventana y el VO, si no no se
				//puede usar para el listado
            	fm.setVisible(false);	
            }
			else {
				String aux = "";
				for (String string : errores) {
					aux += string + "\n";
				}

				JOptionPane.showMessageDialog(null,
						"Los datos no son correctos.\n"
								+ aux);

			}
								

			
		}
	});
        }
    
    
    private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (txtCodigoPaseo.getText().trim().equals("")) {
			resp.add("El codigo del Paseo no puede estar vacio.");
		}
		if ((!(txtCodigoPaseo.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("El codigo del Paseo tiene que ser alfanumerico");
		}

		if (txtNombre.getText().trim().equals("")) {
			resp.add("El nombre del turista no puede estar vacio.");
		}
		if (txtEdad.getText().trim().equals("")) {
			resp.add("La edad no puede estar vacia");
		}
		if (!(isNumeric(txtCelular.getText().trim()))) {
			resp.add("El celular tiene que ser un numero");
		}

		return resp;
	}
    
  
        private static boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

   
        
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, "error: " + mensaje);
	}
    }

