 package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import modelos.PacienteDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaListado extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller myController;
	private JButton btnRegresar;
	private JButton btnLimpiar;
	private JButton btnGenerar;
	private JScrollPane scrollPane;
	private JTable table;

	
	public VistaListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListado = new JLabel("Listado de Pacientes");
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListado.setBounds(275, 20, 181, 13);
		contentPane.add(lblListado);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 58, 641, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"nombre", "edad", "peso", "altura", "imc", "condicion"
			}
		));
		scrollPane.setViewportView(table);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(228, 338, 85, 21);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(134, 338, 85, 21);
		contentPane.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(39, 338, 85, 21);
		contentPane.add(btnGenerar);
		btnGenerar.addActionListener(this);
	}

	public void setController(Controller myController) {
		this.myController=myController;
		
	}
	
	private void llenarTablaPacientes() {
	    // Obtenemos el modelo actual de la tabla
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    
	    // Limpiar tabla antes de llenar
	    model.setRowCount(0); 

	    // Obtener lista de pacientes desde el controlador
	    for (PacienteDTO paciente : myController.listaPacientes()) {
	        Object[] fila = new Object[] {
	            paciente.getNombre(),
	            paciente.getEdad(),
	            paciente.getPeso(),
	            paciente.getTalla(),
	            paciente.getImc(),
	            paciente.getCondicion()
	        };
	        model.addRow(fila);
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnRegresar) {
			System.out.println("Limpiando y volviendo a menu");
			limpiar();
			myController.mostrarVentana(1);
		}
		if (e.getSource()==btnLimpiar) {
			System.out.println("Limpiando");
			limpiar();
		}
		if (e.getSource()==btnGenerar) {
			System.out.println("Generando listado...");
			//String listaPacientes=myController.imprimirLista();
			//textArea.setText(listaPacientes);
			System.out.println("Generando listado...");
		    llenarTablaPacientes();
		}
	}

	private void limpiar() {
		//textArea.setText("");
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Esto borra todas las filas de la tabla
		
	}
}
