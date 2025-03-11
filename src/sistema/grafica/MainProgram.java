package sistema.grafica;

import sistema.grafica.Controladores.Respaldo;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Dimension;


public class MainProgram {

	private JFrame frame;
	private Respaldo respaldo;
	private JDesktopPane desktopPane;

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
		this.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(new Color(255, 198, 170));
		frame.setFont(new Font("Arial", Font.ITALIC, 14));
		frame.setTitle("Paseos del Sol S.A");
		frame.setBounds(50, 100, 1450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 200, 145));
		desktopPane.setBorder(UIManager.getBorder("EditorPane.border"));
		ImageIcon imagen = new ImageIcon("PaseosDelSolSA.png");
		JLabel labelImagen = new JLabel(new ImageIcon("./src/PaseosDelSolSA.png"));
		labelImagen.setAlignmentY(2.0f);
		labelImagen.setAlignmentX(2.0f);
		labelImagen.setBackground(new Color(240, 240, 240));
		labelImagen.setBounds(258, 46, 867, 405);
		desktopPane.add(labelImagen,JDesktopPane.DEFAULT_LAYER);
		frame.setContentPane(desktopPane); 
		
		
		JLabel labelTitulo = new JLabel("Paseos del Sol S.A");
		labelTitulo.setBounds(614, 10, 173, 22);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(labelTitulo);  
		 
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMaximumSize(new Dimension(5, 5));
		menuBar.setBorder(UIManager.getBorder("MenuBar.border"));
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.setOpaque(false);
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Minivans");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu.setPreferredSize(new Dimension(150, 50));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Nueva");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistroMinivan v = new VentanaRegistroMinivan();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listado General");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoGeneralMinivanes v = new VentanaListadoGeneralMinivanes(frame);
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listado Paseos Por Minivan");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosMinivan v = new VentanaListadoPaseosMinivan();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Paseos");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_1.setPreferredSize(new Dimension(150, 50));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Agregar");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroPaseo v = new VentanaRegistroPaseo();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado Paseos por Destino");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosXDestino v = new VentanaListadoPaseosXDestino(frame);
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado Disponible por Boletos");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosDispBoletos v = new VentanaListadoPaseosDispBoletos(frame);
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Listado por Destino");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoPaseosXDestino v = new VentanaListadoPaseosXDestino(frame);
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Monto Recaudado por Paseo");
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMontoRecaudadoXPaseo v = new VentanaMontoRecaudadoXPaseo();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		
		
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_2 = new JMenu("Boletos");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.setPreferredSize(new Dimension(150, 50));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Comprar Boleto");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVentaBoleto v = new VentanaVentaBoleto();
				frame.getContentPane().add(v);
				v.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Boletos Vendidos por Paseo");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoBoletosVendidosXPaseo v = new VentanaListadoBoletosVendidosXPaseo();
				frame.getContentPane().add(v);
				v.setVisible(true);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2_1 = new JMenu("Respaldo");
		mnNewMenu_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2_1.setPreferredSize(new Dimension(150, 50));
		menuBar.add(mnNewMenu_2_1);
		
		JMenuItem mntmRespaldo = new JMenuItem("Respaldar");
		mntmRespaldo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmRespaldo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			respaldo = new Respaldo(MainProgram.this); 
			respaldo.Respaldar();
		}
	});
		mnNewMenu_2_1.add(mntmRespaldo);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setVisible (boolean b) {
		frame.setVisible(b);
	}
	
	public void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(this.getFrame(), "error: "+ mensaje);
    }

	public Respaldo getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(Respaldo respaldo) {
		this.respaldo = respaldo;
	}

}
