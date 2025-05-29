package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import modelos.PacienteDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VistaConsulta extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNombre;
	private JButton btnConsultar;
	private JTextArea textArea;
	private JButton btnRegresar;
	private Controller myController;
	private JButton btnLimpiar;
	private JScrollPane scrollPane;
	private JTextField txtNombrePac;
	private JTextField txtEdad;
	private JTextField txtPeso;
	private JTextField txtTalla;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consultar Paciente");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitulo.setBounds(144, 24, 137, 13);
		contentPane.add(lblTitulo);
		
		lblNombre = new JLabel("Nombre del paciente");
		lblNombre.setBounds(26, 67, 138, 13);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(164, 64, 195, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(369, 63, 103, 21);
		contentPane.add(btnConsultar);
		btnConsultar.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setColumns(5);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(26, 108, 300, 101);
		contentPane.add(scrollPane);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(171, 219, 110, 21);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(70, 219, 94, 21);
		contentPane.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		
		JLabel lblNombrePac = new JLabel("Nombre");
		lblNombrePac.setBounds(379, 106, 45, 13);
		contentPane.add(lblNombrePac);
		
		txtNombrePac = new JTextField();
		txtNombrePac.setBounds(443, 103, 156, 19);
		contentPane.add(txtNombrePac);
		txtNombrePac.setColumns(10);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(443, 132, 156, 19);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(443, 161, 156, 19);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		txtTalla = new JTextField();
		txtTalla.setBounds(443, 190, 156, 19);
		contentPane.add(txtTalla);
		txtTalla.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(379, 135, 45, 13);
		contentPane.add(lblEdad);
		
		JLabel lblPeso = new JLabel("Peso (kg)");
		lblPeso.setBounds(379, 164, 45, 13);
		contentPane.add(lblPeso);
		
		JLabel lblTalla = new JLabel("Talla (m)");
		lblTalla.setBounds(379, 193, 45, 13);
		contentPane.add(lblTalla);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(379, 219, 102, 21);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(496, 219, 103, 21);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(this);
	}
	public void setController(Controller myController) {
		this.myController=myController;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnConsultar) {
			consultar();
		}
		if (e.getSource()==btnLimpiar) {
			limpiar();	
		}
		if (e.getSource()==btnRegresar) {
			System.out.println("Accediento a ventana menu");
			limpiar();
			myController.mostrarVentana(1);
		}
		if (e.getSource()==btnModificar) {
			System.out.println("Creando objeto modificado PacienteDTO");
			PacienteDTO nuevoPaciente = new PacienteDTO();
			nuevoPaciente.setNombre(txtNombrePac.getText());
			nuevoPaciente.setEdad(Integer.parseInt(txtEdad.getText()));
			nuevoPaciente.setPeso(Double.parseDouble(txtPeso.getText()));
			nuevoPaciente.setTalla(Double.parseDouble(txtTalla.getText()));
			nuevoPaciente.calcularImc();
			nuevoPaciente.condicion(nuevoPaciente.getImc());
			
			String resultado= myController.modificarPaciente(txtNombrePac.getText(),nuevoPaciente);
			System.out.println(resultado);
				
			consultar();
		}
		if (e.getSource()==btnEliminar) {
			String resultado= myController.eliminarPaciente(txtNombrePac.getText());
			limpiar();	
		}
		
	}
	private void consultar() {
		// TODO Auto-generated method stub
		System.out.println("Consultando Modelo de datos...");
		String nombre=textField.getText();
		System.out.println(nombre);
		PacienteDTO pac=myController.consultaBD(nombre);
		if (pac!=null) {
			System.out.println("se obtuvo el objeto "+pac);
			String paciente="<<<<<"+pac.getNombre()+">>>>>";
			paciente+="\nNombre: "+pac.getNombre();
			paciente+="\nEdad: "+pac.getEdad();
			paciente+="\nPeso: "+pac.getPeso();
			paciente+="\nTalla: "+pac.getTalla();
			paciente+="\nIMC: "+pac.getImc();
			paciente+="\n\nCONCLUSION: <<"+pac.getCondicion()+">>";
			
			txtNombrePac.setText(pac.getNombre());
			txtEdad.setText(String.valueOf(pac.getEdad()));
			txtPeso.setText(String.valueOf(pac.getPeso()));
			txtTalla.setText(String.valueOf(pac.getTalla()));
			
			textArea.setText(paciente);
		}else {
			System.out.println("No se obtubo el objeto");
		}
		
	}
	private void limpiar() {
		// TODO Auto-generated method stub
		textField.setText("");
		textArea.setText("");
		txtNombrePac.setText("");
		txtEdad.setText("");
		txtPeso.setText("");
		txtTalla.setText("");
		
	}
}
