package sistema.grafica;

import java.awt.EventQueue;


import javax.swing.JInternalFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;


import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import sistema.grafica.Controladores.ControladorRegistroMinivan;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;


public class VentanaRegistroMinivan extends JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    private JTextField txtMatricula;
    private JTextField txtMarca;
    private JTextField textModelo;
    private JLabel lblCantAsientos;
    private JLabel lblNewLabel;
//    private JGradientButton btnAceptar;
//    private JGradientButton btnCancelar
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JFormattedTextField formattedTextField;
    private JInternalFrame fm;
    private ControladorRegistroMinivan controlador;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//                	createAndShowGUI();
                    VentanaRegistroMinivan frame = new VentanaRegistroMinivan();
                    
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaRegistroMinivan() {
        super("Registro Minivan", true, true, true, true);
        
        setBounds(100, 100, 488, 307);
        fm = this;
        
        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        panel.setBackground(new Color(255, 200, 145));
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        
        
        JLabel lblMatricula = new JLabel("Matricula");
        lblMatricula.setBounds(20, 51, 114, 43);
        lblMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(lblMatricula);
        
        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("Formato alfanumerico");
        txtMatricula.setForeground(new Color(0, 0, 0));
        txtMatricula.setBounds(145, 59, 291, 28);
        txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(txtMatricula);
        txtMatricula.setColumns(10);
        
        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMarca.setBounds(20, 92, 114, 43);
        panel.add(lblMarca);
        
        txtMarca = new JTextField();
        txtMarca.setBounds(145, 100, 291, 28);
        txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(txtMarca);
        txtMarca.setColumns(10);
        
        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblModelo.setBounds(20, 133, 114, 43);
        panel.add(lblModelo);
        
        textModelo = new JTextField();
        textModelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textModelo.setColumns(10);
        textModelo.setBounds(145, 138, 291, 28);
        panel.add(textModelo);
        
        lblCantAsientos = new JLabel("Cantidad de asientos");
        lblCantAsientos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCantAsientos.setBounds(20, 175, 126, 43);
        panel.add(lblCantAsientos);
        
        lblNewLabel = new JLabel("Por favor ingresar datos de la nueva minivan");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 13, 267, 13);
        panel.add(lblNewLabel);
        
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter sleepFormatter = new NumberFormatter(format);
        sleepFormatter.setValueClass(Integer.class);
        sleepFormatter.setMinimum(1);
        sleepFormatter.setMaximum(19);
        sleepFormatter.setAllowsInvalid(false);

        sleepFormatter.setCommitsOnValidEdit(true);
        formattedTextField = new JFormattedTextField(sleepFormatter);
        
        formattedTextField.setToolTipText("Formato numero entero de 1 a 19");
        formattedTextField.setBounds(145, 176, 289, 30);
        panel.add(formattedTextField);
         
//        btnAceptar = new JGradientButton();
        btnAceptar = new JButton ("Aceptar");
        btnAceptar.setText("Aceptar");
//        btnAceptar.setForeground(new Color(0, 0, 0));
        btnAceptar.setBackground(Color.GREEN);
		btnAceptar.setBounds(229, 182, 92, 29);

       // btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnAceptar.setBounds(352, 228, 74, 23);

        panel.add(btnAceptar);
        
        
        btnCancelar = new JButton("Cancelar");
        //btnCancelar.setForeground(new Color(192, 192, 192));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnCancelar.setBorder(UIManager.getBorder("Button.border"));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setBounds(268, 228, 74, 23);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	             fm.setVisible(false);	
	        }
        });
        panel.add(btnCancelar);
        
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	List<String> errores = validarCampos();
            	if (errores.isEmpty()) {
	                String matricula = txtMatricula.getText();
	                String marca = txtMarca.getText();
	                String modelo = textModelo.getText();
	                int asientos = Integer.parseInt(formattedTextField.getText());
	                
	                controlador = new ControladorRegistroMinivan(VentanaRegistroMinivan.this);
	                if (controlador.RegistrarMinivan(matricula, marca, modelo, asientos)) {
	                	fm.setVisible(false);	
	                }
                }
	            	else {
						String aux = "";
						for (String string : errores) {
							aux += string + "\n";
						}
	
						JOptionPane.showMessageDialog(fm,
								"Los datos no son correctos. \n"
										+ aux);
	                
	            	}
            }
        });
    }
    
	private List<String> validarCampos() {
		List<String> resp = new ArrayList<>();

		if (txtMatricula.getText().trim().equals("")) {
			resp.add("La matricula no puede estar vacio");
		}
		if ((!(txtMatricula.getText()).matches("[A-Za-z0-9]+"))) {
			resp.add("La matricula tiene que ser alfanumerica");
		}

		if (textModelo.getText().trim().equals("")) {
			resp.add("El modelo no puede ser vacio");
		}
		
		if (formattedTextField.getText().trim().equals("")) {
			resp.add("La cantidad de asientos no puede ser vacio");
		}
		return resp;
	}
   
    public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(fm, "error: "+ mensaje);
    }
    
   
}
