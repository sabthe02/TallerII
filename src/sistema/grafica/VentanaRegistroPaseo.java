package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.*;

public class VentanaRegistroPaseo extends JInternalFrame {

    private JTextField textFieldCodigo;
    private JTextField textFieldDestino;
    private JTextField textFieldPrecioBase;
    private JSpinner spinnerHoraRegreso, spinnerMinutoRegreso;
    private JSpinner spinnerHoraPartida, spinnerMinutoPartida;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaRegistroPaseo frame = new VentanaRegistroPaseo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaRegistroPaseo() {
        setTitle("Registro de Paseo");
        setBounds(100, 100, 500, 300);
        //setLocationRelativeTo(null); // Centrar la ventana
        getContentPane().setLayout(null); 


        JLabel labelTituloRegistro = new JLabel("Registro de Paseos");
        labelTituloRegistro.setBounds(180, 10, 150, 14);
        getContentPane().add(labelTituloRegistro);

        JLabel labelCodigo = new JLabel("Código:");
        labelCodigo.setBounds(20, 40, 60, 14);
        getContentPane().add(labelCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(90, 37, 100, 20);
        textFieldCodigo.setToolTipText("Ingrese el código del paseo");
        getContentPane().add(textFieldCodigo);

        JLabel labelDestino = new JLabel("Destino:");
        labelDestino.setBounds(20, 70, 60, 14);
        getContentPane().add(labelDestino);

        textFieldDestino = new JTextField();
        textFieldDestino.setBounds(90, 67, 100, 20);
        getContentPane().add(textFieldDestino);

        JLabel labelPrecioBase = new JLabel("Precio Base:");
        labelPrecioBase.setBounds(20, 100, 80, 14);
        getContentPane().add(labelPrecioBase);

        textFieldPrecioBase = new JTextField();
        textFieldPrecioBase.setBounds(90, 97, 100, 20);
        getContentPane().add(textFieldPrecioBase);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(90, 140, 100, 25);
        getContentPane().add(btnRegistrar);

        JLabel labelHoraRegreso = new JLabel("Hora de Regreso:");
        labelHoraRegreso.setBounds(230, 40, 100, 14);
        getContentPane().add(labelHoraRegreso);

        spinnerHoraRegreso = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1)); // 0-23 
        spinnerHoraRegreso.setBounds(330, 37, 40, 20);
        getContentPane().add(spinnerHoraRegreso);

        JLabel labelMinutoRegreso = new JLabel("Minuto:");
        labelMinutoRegreso.setBounds(380, 40, 50, 14);
        getContentPane().add(labelMinutoRegreso);

        spinnerMinutoRegreso = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1)); // 0-59 
        spinnerMinutoRegreso.setBounds(430, 37, 40, 20);
        getContentPane().add(spinnerMinutoRegreso);

        JLabel labelHoraPartida = new JLabel("Hora de Partida:");
        labelHoraPartida.setBounds(230, 70, 100, 14);
        getContentPane().add(labelHoraPartida);

        spinnerHoraPartida = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1)); // 0-23 
        spinnerHoraPartida.setBounds(330, 67, 40, 20);
        getContentPane().add(spinnerHoraPartida);

        JLabel labelMinutoPartida = new JLabel("Minuto:");
        labelMinutoPartida.setBounds(380, 70, 50, 14);
        getContentPane().add(labelMinutoPartida);

        spinnerMinutoPartida = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1)); // 0-59
        spinnerMinutoPartida.setBounds(430, 67, 40, 20);
        getContentPane().add(spinnerMinutoPartida);
    }
}
