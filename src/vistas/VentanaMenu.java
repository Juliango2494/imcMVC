package vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import modelos.PacienteDAO;
import modelos.PacienteDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCrearPaciente;
	private JButton btnConsultarPaciente;
	private Controller myController;
	private JButton btnListadoDePacientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setController(Controller myController) {
		this.myController=myController;
	}

	/**
	 * Create the frame.
	 */
	public VentanaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Calculadora IMC");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMenuPrincipal.setBounds(159, 25, 133, 13);
		contentPane.add(lblMenuPrincipal);
		
		btnCrearPaciente = new JButton("Crear Paciente");
		btnCrearPaciente.setBounds(149, 65, 153, 21);
		contentPane.add(btnCrearPaciente);
		btnCrearPaciente.addActionListener(this);
		
		btnConsultarPaciente = new JButton("Consultar Paciente");
		btnConsultarPaciente.setBounds(149, 112, 153, 21);
		contentPane.add(btnConsultarPaciente);
		btnConsultarPaciente.addActionListener(this);
		
		btnListadoDePacientes = new JButton("Listado de Pacientes");
		btnListadoDePacientes.setBounds(149, 160, 153, 21);
		contentPane.add(btnListadoDePacientes);
		btnListadoDePacientes.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnCrearPaciente) {
			System.out.println("Accediento a ventana principal");
			myController.mostrarVentana(2);
		}
		if(e.getSource()==btnConsultarPaciente) {
			System.out.println("Accediendo a ventana consulta");
			myController.mostrarVentana(3);
		}
		if(e.getSource()==btnListadoDePacientes) {
			System.out.println("Accediendo a ventana Listado");
			myController.mostrarVentana(4);
		}
		
	}

	

	
}
