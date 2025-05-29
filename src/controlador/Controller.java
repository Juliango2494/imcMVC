package controlador;

import data.ModeloDatos;
import modelos.PacienteDAO;
import modelos.PacienteDTO;
import vistas.VentanaMenu;
import vistas.VentanaPrincipal;
import vistas.VistaConsulta;
import vistas.VistaListado;

public class Controller {

	private ModeloDatos myDB;
	private PacienteDAO pacienteDAO;
	private VentanaMenu ventanaMenu;
	private VentanaPrincipal ventanaPrincipal;
	private VistaConsulta ventanaConsulta;
	private VistaListado ventanaLista;

	public void setModeloDatos(ModeloDatos myDB) {
		this.myDB=myDB;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO=pacienteDAO;
		
	}

	public void setVentanaMenu(VentanaMenu ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
		
	}

	public void mostrarVentana(int ventana) {
		switch (ventana) {
		case 1:
			ventanaMenu.setVisible(true);
			ventanaPrincipal.setVisible(false);
			ventanaConsulta.setVisible(false);
			ventanaLista.setVisible(false);
			break;
			
		case 2:
			ventanaPrincipal.setVisible(true);
			ventanaMenu.setVisible(false);
			ventanaConsulta.setVisible(false);
			ventanaLista.setVisible(false);
			break;
			
		case 3:
			ventanaConsulta.setVisible(true);
			ventanaPrincipal.setVisible(false);
			ventanaMenu.setVisible(false);
			ventanaLista.setVisible(false);
			break;
			
		case 4:
			ventanaLista.setVisible(true);
			ventanaPrincipal.setVisible(false);
			ventanaMenu.setVisible(false);
			ventanaConsulta.setVisible(false);
			break;
		}
		
	}

	public String registrarPaciente(PacienteDTO nuevoPaciente) {
		String msj = pacienteDAO.registrarPaciente(nuevoPaciente);
		return msj;
	}

	public void setVentanaConsulta(VistaConsulta ventanaConsulta) {
		this.ventanaConsulta=ventanaConsulta;
		// TODO Auto-generated method stub
		
	}

	public PacienteDTO consultaBD(String nombre) {
		return pacienteDAO.consultaPaciente(nombre);
		// TODO Auto-generated method stub
	}

	public void setVentanaLista(VistaListado ventanaLista) {
		this.ventanaLista=ventanaLista;
		// TODO Auto-generated method stub
		
	}

	public String listaPacientes() {
		String listaPacientes=pacienteDAO.listaPacientes();
		return listaPacientes;
		// TODO Auto-generated method stub
	}




}
