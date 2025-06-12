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
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class VistaConsulta extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNombre;
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
	private JComboBox<String> comboBox;
	private JButton btnCargar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 347);
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
		
		textArea = new JTextArea();
		textArea.setColumns(5);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(26, 108, 300, 151);
		contentPane.add(scrollPane);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(170, 268, 110, 21);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(this);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(69, 268, 94, 21);
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
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un paciente"}));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		comboBox.setBounds(164, 59, 260, 26);
		contentPane.add(comboBox);
		
		btnCargar = new JButton("Cargar Lista");
		btnCargar.setBounds(458, 61, 110, 21);
		contentPane.add(btnCargar);
		btnCargar.addActionListener(this);
		
		comboBox.addActionListener(this);
		
		btnEliminar.addActionListener(this);
	}
	public void setController(Controller myController) {
		this.myController=myController;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnLimpiar) {
			limpiar();	
		}
		if (e.getSource()==btnRegresar) {
			System.out.println("Accediento a ventana menu");
			limpiar();
			myController.mostrarVentana(1);
		}
		if (e.getSource()==btnEliminar) {
			String resultado= myController.eliminarPaciente(txtNombrePac.getText());
			limpiar();	
		}
		if (e.getSource()==btnCargar) {
			cargarRegistros();
			System.out.println("Cargando registros para combobox");
		}
		if (e.getSource()==btnModificar) {
			PacienteDTO pacMod=new PacienteDTO();
			System.out.println("seteando DTO modificado, paciente "+txtNombrePac.getText());
			pacMod.setNombre(txtNombrePac.getText());
			pacMod.setEdad(Integer.parseInt(txtEdad.getText()));
			pacMod.setPeso(Double.parseDouble(txtPeso.getText()));
			pacMod.setTalla(Double.parseDouble(txtTalla.getText()));
			pacMod.calcularImc();
			pacMod.condicion(pacMod.getImc());
			String msj=myController.modificarPaciente(txtNombrePac.getText(), pacMod);
			System.out.println(msj);
			limpiar();
			String regMod="<<<<<<<---REGISTRO MODIFICADO--->>>>>>>";
			regMod+="\n________________________________________";
			regMod+="\nNombre: "+pacMod.getNombre();
			regMod+="\nEdad: "+pacMod.getEdad()+" años.";
			regMod+="\nPeso: "+pacMod.getPeso()+" kg.";
			regMod+="\nAltura: "+pacMod.getTalla()+"m.";
			regMod+="\nIMC: "+pacMod.getImc();
			regMod+="\nCondicion: "+pacMod.getCondicion();
			textArea.setText(regMod);
			
		}
		if (e.getSource()==comboBox) {
			if (comboBox.getSelectedItem()!=null) {
				if (comboBox.getSelectedIndex()!=0) {
					String nomb=(String) comboBox.getSelectedItem();
					PacienteDTO pac=null;
					limpiar();
					try {
						pac=myController.consultaBD(nomb);
						
						String msj="<<<<< PACIENTE SELECCIONADO >>>>>\n";
						msj+="\n__________________________";
						msj+="\nNOMBRE: "+pac.getNombre();
						msj+="\nEDAD: "+pac.getEdad()+" años.";
						msj+="\nPESO: "+pac.getPeso()+" kg";
						msj+="\nTALLA: "+pac.getTalla()+" cm";
						msj+="\nIMC: "+pac.getImc();
						msj+="\nCONDICION: "+pac.getCondicion();
						
						textArea.setText(msj);
						txtNombrePac.setText(pac.getNombre());
						txtEdad.setText(String.valueOf(pac.getEdad()));
						txtPeso.setText(String.valueOf(pac.getPeso()));
						txtTalla.setText(String.valueOf(pac.getTalla()));
					
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("Error obteniendo DTO comboBox pintar lbl");
					}
				}
			}
		}
		
	}
	private void cargarRegistros() {
		// TODO Auto-generated method stub
		ArrayList<PacienteDTO> lista=myController.listaPacientes();
		llenarCombo(lista);
		
	}
	private void limpiar() {
		// TODO Auto-generated method stub
		//textField.setText("");
		textArea.setText("");
		txtNombrePac.setText("");
		txtEdad.setText("");
		txtPeso.setText("");
		txtTalla.setText("");
		
	}
	private void llenarCombo(ArrayList<PacienteDTO> lista) {
		comboBox.removeAllItems();
		comboBox.addItem("Seleccione");
		String item="";
		for (int i = 0; i < lista.size(); i++) {
			item=lista.get(i).getNombre();
			comboBox.addItem(item);
		}
	}
}
