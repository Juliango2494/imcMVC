 package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class VistaListado extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller myController;
	private JTextArea textArea;
	private JButton btnRegresar;
	private JButton btnLimpiar;
	private JButton btnGenerar;
	private JScrollPane scrollPane;

	
	public VistaListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListado = new JLabel("Listado de Pacientes");
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListado.setBounds(129, 27, 181, 13);
		contentPane.add(lblListado);
		
		textArea = new JTextArea();
		textArea.setColumns(5);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(40, 58, 241, 301);
		contentPane.add(scrollPane);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(316, 338, 85, 21);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(316, 298, 85, 21);
		contentPane.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(316, 251, 85, 21);
		contentPane.add(btnGenerar);
		btnGenerar.addActionListener(this);
	}

	public void setController(Controller myController) {
		this.myController=myController;
		
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
			String listaPacientes=myController.listaPacientes();
			textArea.setText(listaPacientes);
		}
	}

	private void limpiar() {
		textArea.setText("");
		// TODO Auto-generated method stub
		
	}
}
