package vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import modelos.PacienteDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private Controller myController;
	private JButton btnCrearPaciente;
	private JButton btnLimpiarCampo;
	private JLabel lblProcesos;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnRegresar;

	public void setController(Controller myController) {
		this.myController=myController;
	}

	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Calculadora IMC");
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Informacion del paciente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(134, 10, 200, 13);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(46, 44, 61, 13);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(101, 41, 96, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(224, 44, 61, 13);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(264, 41, 96, 19);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(46, 77, 61, 13);
		contentPane.add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(101, 74, 96, 19);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setBounds(224, 77, 61, 13);
		contentPane.add(lblAltura);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(264, 74, 96, 19);
		contentPane.add(txtAltura);
		txtAltura.setColumns(10);
		/*
		textArea = new JTextArea();
		textArea.setBounds(46, 137, 342, 88);
		contentPane.add(textArea);
		*/
		textArea = new JTextArea();
		textArea.setColumns(5);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(46, 137, 342, 88);
		contentPane.add(scrollPane);
		
		lblProcesos = new JLabel();
		lblProcesos.setBounds(46,200,342,21);
		contentPane.add(lblProcesos);
		
		btnCrearPaciente = new JButton("Crear Paciente");
		btnCrearPaciente.setBounds(45, 106, 162, 21);
		contentPane.add(btnCrearPaciente);
		btnCrearPaciente.addActionListener(this);
		
		btnLimpiarCampo = new JButton("Limpiar Campos");
		btnLimpiarCampo.setBounds(222, 106, 162, 21);
		contentPane.add(btnLimpiarCampo);
		btnLimpiarCampo.addActionListener(this);
		
		btnRegresar= new JButton("Regresar");
		btnRegresar.setBounds(46,250,342,30);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnCrearPaciente) {
			System.out.println("Creando objeto PacienteDTO");
			PacienteDTO nuevoPaciente = new PacienteDTO();
			nuevoPaciente.setNombre(txtNombre.getText());
			nuevoPaciente.setEdad(Integer.parseInt(txtEdad.getText()));
			nuevoPaciente.setPeso(Double.parseDouble(txtPeso.getText()));
			nuevoPaciente.setTalla(Double.parseDouble(txtAltura.getText()));
			nuevoPaciente.calcularImc();
			nuevoPaciente.condicion(nuevoPaciente.getImc());
			
			String resultado= myController.registrarPaciente(nuevoPaciente);
			if (resultado=="ok registrarSQL en DAO") {
				String msj="<<<<<<Registro de paciente "+txtNombre.getText()+">>>>>>";
				msj+="\nNombre: "+nuevoPaciente.getNombre();
				msj+="\nEdad: "+nuevoPaciente.getEdad();
				msj+="\nPeso: "+nuevoPaciente.getPeso();
				msj+="\nTalla: "+nuevoPaciente.getTalla();
				msj+="\nIMC: "+nuevoPaciente.getImc();
				msj+="\n\nCONCLUSION: <<"+nuevoPaciente.getCondicion()+">>";
				textArea.setText(msj);
			}
			System.out.println(resultado);
			lblProcesos.setText(resultado);
		}
		if (e.getSource()==btnLimpiarCampo) {
			limpiarCampo();
		}
		if (e.getSource()==btnRegresar) {
			System.out.println("Accediento a ventana menu");
			limpiarCampo();
			myController.mostrarVentana(1);
		}
	}


	private void limpiarCampo() {
		txtNombre.setText("");
		txtEdad.setText("");
		txtPeso.setText("");
		txtAltura.setText("");
		lblProcesos.setText("");
		textArea.setText("");
	}

	
}
