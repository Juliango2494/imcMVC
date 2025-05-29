package data;

import java.util.HashMap;

import controlador.Controller;
import modelos.PacienteDTO;

public class ModeloDatos {

	private Controller myController;

	public void setController(Controller myController) {
		this.myController=myController;
		
	}
	public HashMap<String, PacienteDTO> mapaOperadores;
	
	public ModeloDatos() {
		mapaOperadores=new HashMap<String, PacienteDTO>();
	}
}
