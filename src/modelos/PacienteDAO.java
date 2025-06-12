package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Controller;
import data.ModeloDatos;
import data.Conection;

public class PacienteDAO {

	private Controller myController;
	private ModeloDatos myDb;
	Connection connection = null;
	Conection conection = null;
	PreparedStatement preStatement = null;

	public void setController(Controller myController) {
		this.myController=myController;
	}
	public PacienteDAO(){
		myDb=new ModeloDatos();
	};
	
	/*private String conectar() {
		Conection conection = new Conection();
		String resultado=conection.conectar();
		if (resultado.equals("conectado")) {
			connection=conection.getConnection();
			preStatement=null;			
		}else {
			JOptionPane.showMessageDialog(null, resultado,"error",JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	};*/
	private String conectar() {
	    Conection conection = Conection.getInstance(); // Usar singleton
	    connection = conection.getConnection(); // Obtener la conexión existente

	    String resultado;

	    if (connection != null) {
	        resultado = "conectado";
	        preStatement = null;
	    } else {
	        resultado = "error al conectar";
	        JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return resultado;
	}

	
	//<<<<<<<-----CONEXION A BASE DE DATOS SQL----->>>>>>>
	
	public String registrarSql(PacienteDTO paciente) throws SQLException{
		String resultado ="";
		if (!conectar().equals("conectado")) {
			return "error en registrarSql no conectado";
		}
		String consulta="INSERT INTO pacientes (nom_pac,edad,peso,talla,imc,condicion_pac)"
				+ " VALUES (?,?,?,?,?,?);"; 
		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, paciente.getNombre());
			preStatement.setInt(2, paciente.getEdad());
			preStatement.setDouble(3, paciente.getPeso());
			preStatement.setDouble(4, paciente.getTalla());
			preStatement.setDouble(5, paciente.getImc());
			preStatement.setString(6, paciente.getCondicion());
			preStatement.execute();
			resultado="ok registrarSQL en DAO";
			
		} catch (SQLException e) {
			System.out.println("No se pudo registrar el paciente, verifique que el registro no exista, SQLException: "+ e.getMessage());
			resultado="error en registrarSQL-SQLException";
		}catch (Exception e) {
			System.out.println("No se pudo registrar el paciente, exception: "+e.getMessage());
			resultado="error en registrarSQL -exception";
		}finally {
			if (preStatement != null) preStatement.close();
		}
		return resultado;
	};
	
	public PacienteDTO consultaSQL(String nombre) throws SQLException{
		PacienteDTO pacienteTemp=null;
		
		if (!conectar().equals("conectado")) {
			return pacienteTemp;
		}
		
		ResultSet result=null;
		
		String consulta="SELECT nom_pac,edad,peso,talla,imc,condicion_pac FROM pacientes where nom_pac= ?;";
		
		try {
			preStatement=connection.prepareStatement(consulta);
			preStatement.setString(1, nombre);
			result=preStatement.executeQuery();
			if (result.next()) {
				pacienteTemp=new PacienteDTO();
				pacienteTemp.setNombre(result.getString("nom_pac"));
				pacienteTemp.setEdad(result.getInt("edad"));
				pacienteTemp.setPeso(result.getDouble("peso"));
				pacienteTemp.setTalla(result.getDouble("talla"));
				pacienteTemp.setImc(result.getDouble("imc"));
				pacienteTemp.calcularImc();
				pacienteTemp.condicion(pacienteTemp.getImc());
				
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del paciente- SQLException: "+e.getMessage());
		}finally {
			if (result != null) result.close();
		    if (preStatement != null) preStatement.close();
		}
		return pacienteTemp;
	};
	
	public ArrayList<PacienteDTO> listaSQL()throws SQLException{                    //<<<<<---REVISAR METODO, ACA RETORNA UN ARRAYLIST / MODELO DE DATOS SOLO IMPRIMIA--->>>>>
		ArrayList<PacienteDTO> listaPacientes=new ArrayList<PacienteDTO>();
		
		if (!conectar().equals("conectado")) {
			System.out.println( "error de conexion al consultar lista");			
		}
		
		ResultSet resultado=null;
		PacienteDTO pacienteTemp=null;
		
		String consulta="SELECT nom_pac,edad,peso,talla,imc,condicion_pac FROM pacientes;";
		
		try {
			preStatement=connection.prepareStatement(consulta);
			resultado=preStatement.executeQuery();
			
			while (resultado.next()==true) {
				pacienteTemp=new PacienteDTO();
				pacienteTemp.setNombre(resultado.getString("nom_pac"));
				pacienteTemp.setEdad(resultado.getInt("edad"));
				pacienteTemp.setPeso(resultado.getDouble("peso"));
				pacienteTemp.setTalla(resultado.getDouble("talla"));
				pacienteTemp.setImc(resultado.getDouble("imc"));
				pacienteTemp.calcularImc();
				pacienteTemp.condicion(pacienteTemp.getImc());
				listaPacientes.add(pacienteTemp);	
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al consultar lista - SQLException: "+e.getMessage());
		}finally {
			if (resultado != null) resultado.close();
		    if (preStatement != null) preStatement.close();
		}
		return listaPacientes;
	};
	
	public String listaSQLprint() {
		// TODO Auto-generated method stub
		String msj="";
		try {
			ArrayList<PacienteDTO> listaPacientes=listaSQL();
			msj="<<<<<<<<----LISTA DE PACIENTES----->>>>>>>>\n";
			for (PacienteDTO pac : listaPacientes) {
				msj+="\n__________________________";
				msj+="\nNOMBRE: "+pac.getNombre();
				msj+="\nEDAD: "+pac.getEdad()+" años.";
				msj+="\nPESO: "+pac.getPeso()+" kg";
				msj+="\nTALLA: "+pac.getTalla()+" cm";
				msj+="\nIMC: "+pac.getImc();
				msj+="\nCONDICION: "+pac.getCondicion();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msj="error";
		}
		
		return msj;
	}
	
	public String actualizarSQL(String nombre, PacienteDTO paciente)throws SQLException{
		String resultado="";
		
		if (!conectar().equals("conectado")) {
			return "error";
		}
		
		try {
			String consulta="UPDATE pacientes "
					+"SET nom_pac = ? , "
					+"edad = ? , "
					+"peso = ? , "
					+"talla = ? , "
					+"imc = ? , "
					+"condicion_pac = ? "
					+"WHERE nom_pac= ?;";
			preStatement=connection.prepareStatement(consulta);
			
			preStatement.setString(1, paciente.getNombre());
			preStatement.setInt(2, paciente.getEdad());
			preStatement.setDouble(3, paciente.getPeso());
			preStatement.setDouble(4, paciente.getTalla());
			preStatement.setDouble(5, paciente.getImc());
			preStatement.setString(6, paciente.getCondicion());
			System.out.println("Nombre Condicional WHERE nom_pac: "+nombre);
			preStatement.setString(7, nombre);
			int registros= preStatement.executeUpdate();
			System.out.println("DAO modificar / Filas afectadas UPDATE: "+registros);
			
			resultado="Registro Actualizado";
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al actualizar paciente -SQLException: "+e.getMessage());
			resultado="error";
		}finally {
			if (preStatement != null) preStatement.close();
		}
		return resultado;
	};
	
	public String eliminarSQL(String nombre) throws SQLException{
		String respuesta="";
		
		if (!conectar().equals("conectado")) {
			return "error";
		}
		
		try {
			String consulta="DELETE FROM pacientes WHERE nom_pac= ? ;";
			
			preStatement=connection.prepareStatement(consulta);
			preStatement.setString(1, nombre);
			
			preStatement.executeUpdate();
			
			respuesta="ok eliminado";
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al eliminar el registro - SQLException: "+e.getMessage());
			respuesta="error eliminando";
		}finally {
			if (preStatement != null) preStatement.close();
		}
		return respuesta;
	};
	
	
	
	//<<<<<<<<-----CONEXION A MODELO DE DATOS - SIMULACION DE BASE DE DATOS/ NUEVO CRUD ES EL CODIGO DE ARRIBA----->>>>>>>>
	
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
	
	public String modificarPaciente(String nombre, PacienteDTO nuevosDatos) {
	    if (myDb.mapaOperadores.containsKey(nombre)) {
	        myDb.mapaOperadores.put(nombre, nuevosDatos);
	        return "Paciente modificado exitosamente.";
	    } else {
	        return "Error: paciente no encontrado.";
	    }
	};
	
	public String eliminarPaciente(String nombre) {
	    if (myDb.mapaOperadores.containsKey(nombre)) {
	        myDb.mapaOperadores.remove(nombre);
	        return "Paciente eliminado correctamente.";
	    } else {
	        return "Error: paciente no encontrado.";
	    }
	}
	
	
	
}
