package sistema.grafica;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class VentanaListadoBoletosVendidosXPaseo extends JInternalFrame {
    private JTextField textFieldCodigoPaseo;
    private ButtonGroup grupoBoletos;  
    private JRadioButton radiobuttonComun;
    private JRadioButton radiobuttonEspecial;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoBoletosVendidosXPaseo frame = new VentanaListadoBoletosVendidosXPaseo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaListadoBoletosVendidosXPaseo() {
    	
        setTitle("Listado de Boletos Vendidos para un Paseo");
        setBounds(100, 100, 800, 400);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        JPanel PanelPrincipal = new JPanel();
        PanelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        PanelPrincipal.setLayout(null);
        
        JLabel labelTitulo = new JLabel("Listado de Boletos Vendidos para un Paseo");
        labelTitulo.setBounds(202, 20, 380, 22);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        PanelPrincipal.add(labelTitulo);
        
        JPanel panelCodigo = new JPanel();
        panelCodigo.setBounds(192, 42, 400, 33);
        panelCodigo.setMaximumSize(new Dimension(400, 33));
        
        JLabel labelCodigo = new JLabel("Código de Paseo:");
        labelCodigo.setBounds(55, 8, 113, 17);
        labelCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldCodigoPaseo = new JTextField(15);
        textFieldCodigoPaseo.setBounds(173, 5, 171, 23);
        textFieldCodigoPaseo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCodigo.setLayout(null);
        
        panelCodigo.add(labelCodigo);
        panelCodigo.add(textFieldCodigoPaseo);
        PanelPrincipal.add(panelCodigo);
        
        JPanel panelTipoBoleto = new JPanel();
        panelTipoBoleto.setBounds(192, 75, 400, 100);
        panelTipoBoleto.setLayout(new BoxLayout(panelTipoBoleto, BoxLayout.Y_AXIS));
        panelTipoBoleto.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Tipo de Boleto",
            TitledBorder.LEFT,
           TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        panelTipoBoleto.setMaximumSize(new Dimension(400, 100));
        
        grupoBoletos = new ButtonGroup();
        panelTipoBoleto.setLayout(new BorderLayout(0, 0));
        radiobuttonComun = new JRadioButton("Común");
        radiobuttonComun.setBounds(20, 5, 71, 25);
        radiobuttonEspecial = new JRadioButton("Especial");
        radiobuttonEspecial.setBounds(111, 5, 79, 25);
        
        radiobuttonComun.setFont(new Font("Arial", Font.PLAIN, 14));
        radiobuttonEspecial.setFont(new Font("Arial", Font.PLAIN, 14));
        grupoBoletos.add(radiobuttonComun);
        grupoBoletos.add(radiobuttonEspecial);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(null);
        radioPanel.add(radiobuttonComun);
        radioPanel.add(radiobuttonEspecial);
        panelTipoBoleto.add(radioPanel);
        
        PanelPrincipal.add(panelTipoBoleto);
        
        getContentPane().add(PanelPrincipal, BorderLayout.CENTER);
    }
}