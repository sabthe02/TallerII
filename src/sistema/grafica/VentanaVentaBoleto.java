package sistema.grafica;

import javax.swing.*;
import java.awt.*;

public class VentanaVentaBoleto extends JInternalFrame {
    private JTextField txtCodigoPaseo;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtCelular;
    private JButton btnRegistrar;

    public VentanaVentaBoleto() {
    	setResizable(true);
    	setIconifiable(true);
    	setClosable(true);
        setSize(400, 250);
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
        txtEdad = new JTextField();
        txtEdad.setBounds(197, 92, 187, 26);
        getContentPane().add(txtEdad);

        JLabel label_3 = new JLabel("Número de Celular:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
        label_3.setBounds(30, 138, 167, 36);
        getContentPane().add(label_3);
        txtCelular = new JTextField();
        txtCelular.setBounds(197, 138, 187, 26);
        getContentPane().add(txtCelular);

        btnRegistrar = new JButton("Registrar Compra");
        btnRegistrar.setBounds(133, 184, 118, 26);
        getContentPane().add(btnRegistrar);
    }
}
