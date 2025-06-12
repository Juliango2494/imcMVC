package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

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
		//String msj = pacienteDAO.registrarPaciente(nuevoPaciente);
		String msj="";
		try {
			msj = pacienteDAO.registrarSql(nuevoPaciente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msj="error controller registrarPaciente";
		}
		return msj;
	}

	public void setVentanaConsulta(VistaConsulta ventanaConsulta) {
		this.ventanaConsulta=ventanaConsulta;
		// TODO Auto-generated method stub
		
	}

	public PacienteDTO consultaBD(String nombre) {
		//return pacienteDAO.consultaPaciente(nombre);
		try {
			return pacienteDAO.consultaSQL(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en controller consultaBD SQLException: "+e.getMessage());
			return null;
		}
		// TODO Auto-generated method stub
	}

	public void setVentanaLista(VistaListado ventanaLista) {
		this.ventanaLista=ventanaLista;
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PacienteDTO> listaPacientes() {
		//String listaPacientes=pacienteDAO.listaPacientes();
		ArrayList<PacienteDTO> listaPacientes=null;
		try {
			listaPacientes= pacienteDAO.listaSQL();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al traer arraylist pacientes");
			
		}
		return listaPacientes;
		// TODO Auto-generated method stub
	}
	
	

	public String modificarPaciente(String nombre, PacienteDTO nuevoPaciente) {
		//String msj = pacienteDAO.modificarPaciente(nombre, nuevoPaciente);
		String msj;
		try {
			msj = pacienteDAO.actualizarSQL(nombre, nuevoPaciente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msj="error en controller modificarPaciente";
		}
		return msj;
	}

	public String eliminarPaciente(String nombre) {
		//String msj = pacienteDAO.eliminarPaciente(nombre);
		String msj;
		try {
			msj = pacienteDAO.eliminarSQL(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msj="error en controller eliminarPaciente";
		}
		return msj;
		// TODO Auto-generated method stub
	}

	public String imprimirLista() {
		// TODO Auto-generated method stub
		String listaPacientes;
		listaPacientes= pacienteDAO.listaSQLprint();
		return listaPacientes;
	}




}
