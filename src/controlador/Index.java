package controlador;

import data.ModeloDatos;
import modelos.PacienteDAO;
import vistas.VentanaMenu;
import vistas.VentanaPrincipal;
import vistas.VistaConsulta;
import vistas.VistaListado;

public class Index {
	
	public static void main (String[] Arg) {
		
		Controller myController=new Controller();
		ModeloDatos myDB=new ModeloDatos();
		PacienteDAO pacienteDAO=new PacienteDAO();
		VentanaMenu ventanaMenu=new VentanaMenu();
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		VistaConsulta ventanaConsulta=new VistaConsulta();
		VistaListado ventanaLista=new VistaListado();
		
		myController.setModeloDatos(myDB);
		myController.setPacienteDAO(pacienteDAO);
		myController.setVentanaMenu(ventanaMenu);
		myController.setVentanaPrincipal(ventanaPrincipal);
		myController.setVentanaConsulta(ventanaConsulta);
		myController.setVentanaLista(ventanaLista);
		
		myDB.setController(myController);
		pacienteDAO.setController(myController);
		ventanaMenu.setController(myController);
		ventanaPrincipal.setController(myController);
		ventanaConsulta.setController(myController);
		ventanaLista.setController(myController);
		
		myController.mostrarVentana(1);
		
	};

}
