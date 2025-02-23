package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaRegistroMinivan extends JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    private JTextField txtCantAsientos;
    private JTextField txtMatricula;
    private JTextField txtMarca;
    private JTextField textModelo;
    private JLabel lblCantAsientos;
    private JLabel lblNewLabel;
    private JButton btnAceptar;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create and show the frame
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
        
        setBounds(100, 100, 450, 300);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblMatricula = new JLabel("Matricula");
        lblMatricula.setBounds(10, 28, 114, 43);
        lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblMatricula);
        
        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("Formato alfanumerico");
        txtMatricula.setForeground(new Color(0, 0, 0));
        txtMatricula.setBounds(125, 36, 301, 28);
        txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(txtMatricula);
        txtMatricula.setColumns(10);
        
        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMarca.setBounds(10, 69, 114, 43);
        panel.add(lblMarca);
        
        txtMarca = new JTextField();
        txtMarca.setBounds(125, 77, 301, 28);
        txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(txtMarca);
        txtMarca.setColumns(10);
        
        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblModelo.setBounds(10, 110, 114, 43);
        panel.add(lblModelo);
        
        textModelo = new JTextField();
        textModelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textModelo.setColumns(10);
        textModelo.setBounds(125, 115, 301, 28);
        panel.add(textModelo);
        
        lblCantAsientos = new JLabel("Cantidad de asientos");
        lblCantAsientos.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblCantAsientos.setBounds(10, 152, 114, 43);
        panel.add(lblCantAsientos);
        
        txtCantAsientos = new JTextField();
        txtCantAsientos.setToolTipText("Formato de numero entero");
        txtCantAsientos.setForeground(new Color(0, 0, 0));
        txtCantAsientos.setBounds(125, 159, 301, 28);
        txtCantAsientos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(txtCantAsientos);
        txtCantAsientos.setColumns(10);
        
 
        lblNewLabel = new JLabel("Por favor ingresar datos de la nueva minivan");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 13, 267, 13);
        panel.add(lblNewLabel);
        

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(199, 217, 114, 36);
        panel.add(btnAceptar);
        
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String marca = txtMarca.getText();
                String modelo = textModelo.getText();
                String cantAsientos = txtCantAsientos.getText();
                JOptionPane.showMessageDialog(VentanaRegistroMinivan.this, matricula);
            }
        });
    }

}
