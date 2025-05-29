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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 300);
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
		scrollPane.setBounds(26, 108, 446, 101);
		contentPane.add(scrollPane);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(171, 219, 110, 21);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(70, 219, 94, 21);
		contentPane.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
	}
	public void setController(Controller myController) {
		this.myController=myController;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnConsultar) {
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
				
				textArea.setText(paciente);
			}else {
				System.out.println("No se obtubo el objeto");
			}
		}
		if (e.getSource()==btnLimpiar) {
			limpiar();	
		}
		if (e.getSource()==btnRegresar) {
			System.out.println("Accediento a ventana menu");
			limpiar();
			myController.mostrarVentana(1);
		}
		
	}
	private void limpiar() {
		// TODO Auto-generated method stub
		textField.setText("");
		textArea.setText("");
		
	}
}
