package sistema.grafica;

import sistema.grafica.Controladores.Respaldo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainProgram {

	private JFrame frame;
	private Respaldo respaldo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgram window = new MainProgram();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainProgram() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 903, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Minivans");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Nueva");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistroMinivan v = new VentanaRegistroMinivan();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listado General");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoGeneralMinivanes v = new VentanaListadoGeneralMinivanes();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listado Paseos Por Minivan");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosMinivan v = new VentanaListadoPaseosMinivan();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Paseos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Agregar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroPaseo v = new VentanaRegistroPaseo();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado Completo");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoGeneralMinivanes v = new VentanaListadoGeneralMinivanes();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado Disponible por Boletos");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosDispBoletos v = new VentanaListadoPaseosDispBoletos();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Listado por Destino");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosXDestino v = new VentanaListadoPaseosXDestino();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_2 = new JMenu("Boletos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Comprar Boleto");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVentaBoleto v = new VentanaVentaBoleto();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Boletos Vendidos por Paseo");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoBoletosVendidosXPaseo v = new VentanaListadoBoletosVendidosXPaseo();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2_1 = new JMenu("Respaldo");
		menuBar.add(mnNewMenu_2_1);
		
		JMenuItem mntmRespaldo = new JMenuItem("Respaldar");
		mntmRespaldo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			respaldo = new Respaldo();
			try {
				respaldo.Respaldar();
				String mensaje = "Respaldado con exito";
				JOptionPane.showMessageDialog(frame, mensaje);
				}
			catch (Exception ex) {
				String mensaje = "Error al respaldar";
				JOptionPane.showMessageDialog(frame, mensaje);
			}
		}
	});
		mnNewMenu_2_1.add(mntmRespaldo);
		
	}

}
