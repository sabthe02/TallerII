package sistema.grafica;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.Controladores.ControladorListadoGeneralMinivanes;
import sistema.logica.ValueObject.VOMinivanListado;
import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaListadoGeneralMinivanes extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JInternalFrame frmListadoGeneralMinivanes;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private ControladorListadoGeneralMinivanes controlador;
	private JFrame jframePrinc;

	public VentanaListadoGeneralMinivanes(JFrame jframePadre) {
		super("Listado general de Minivanes", true, true, true, true);
		frmListadoGeneralMinivanes = this;
		setTitle("Listado general de Minivanes");
		setBounds(50, 80, 800, 389);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		jframePrinc = jframePadre;

		modeloTabla = new DefaultTableModel(new Object[][] {}, new String[] { "Matrícula", "Marca", "Modelo",
				"Cantidad de Asientos", "Cantidad de Paseos Asignados" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(modeloTabla);
		table.setBackground(new Color(240, 240, 240));
		table.setGridColor(Color.GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 29, 784, 286);
		getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setBounds(346, 325, 85, 21);
		getContentPane().add(btnNewButton);

		JButton btnVerPaseos = new JButton("Ver Paseos");
		btnVerPaseos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() >= 0) {

					VentanaListadoPaseosMinivan v = new VentanaListadoPaseosMinivan(
							table.getValueAt(table.getSelectedRow(), 0).toString());
					jframePrinc.add(v);
					v.setVisible(true);

				} else {
					mostrarError("Primero debe seleccionar una minivan de la tabla.");

				}

			}
		});
		btnVerPaseos.setBounds(653, 0, 117, 29);
		getContentPane().add(btnVerPaseos);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListadoGeneralMinivanes.setVisible(false);
			}
		});

		controlador = new ControladorListadoGeneralMinivanes(this);

		ArrayList<VOMinivanListado> lista = controlador.obtenerListado();
		if (lista != null) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			lista.forEach(arg0 -> model.addRow(new Object[] { arg0.getMatricula(), arg0.getMarca(), arg0.getModelo(),
					arg0.getCantidadAsientos(), arg0.getCantidadPaseos() }));
		}

	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(frmListadoGeneralMinivanes, "error: " + mensaje);
	}

	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
	}
}