package modelos;

import controlador.Controller;
import data.ModeloDatos;

public class PacienteDAO {

	private Controller myController;
	private ModeloDatos myDb;

	public void setController(Controller myController) {
		this.myController=myController;
	}
	public PacienteDAO(){
		myDb=new ModeloDatos();
	};
	
	public String registrarPaciente(PacienteDTO paciente) {
		if (myDb.mapaOperadores.containsKey(paciente.getNombre())==false) {
			myDb.mapaOperadores.put(paciente.getNombre(), paciente);
			return "Registro exitoso";
		}else {
			return "Error, registro existente";
		}
	}
	
	public PacienteDTO consultaPaciente(String nombre) {
		PacienteDTO pacienteTemporal=null;
		if(myDb.mapaOperadores.containsKey(nombre)) {
			pacienteTemporal=myDb.mapaOperadores.get(nombre);
		};
		return pacienteTemporal;
	};
	
	public String listaPacientes() {
		String msj="<<<<<<<  LISTA PACIENTES  >>>>>>>\n";
		for(PacienteDTO pac : myDb.mapaOperadores.values()) {
			msj+="\n__________________________";
			msj+="\nNOMBRE: "+pac.getNombre();
			msj+="\nEDAD: "+pac.getEdad()+" años.";
			msj+="\nPESO: "+pac.getPeso()+" kg";
			msj+="\nTALLA: "+pac.getTalla()+" cm";
			msj+="\nIMC: "+pac.getImc();
		};
		return msj;
		
		
	};
	
}
