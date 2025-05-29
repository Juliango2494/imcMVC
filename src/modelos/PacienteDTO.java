package modelos;

public class PacienteDTO {
	private String nombre;
	private int edad;
	private double peso;
	private double talla;
	private double imc;
	private String condicion;
	
	public PacienteDTO() {
		System.out.println("Creando persona sin parametros");
	};
	public PacienteDTO(String nombre, int edad, double peso, double talla) {
		System.out.println("Creando persona con parametros");
		this.nombre=nombre;
		this.edad=edad;
		this.peso=peso;
		this.talla=talla;
		this.imc=0;
		this.condicion="";
	};
	public String getNombre() {
		return this.nombre;
	};
	public void setNombre(String nombre) {
		this.nombre=nombre;
	};
	public int getEdad() {
		return this.edad;
	};
	public void setEdad(int edad) {
		this.edad=edad;
	};
	public double getPeso() {
		return this.peso;
	};
	public void setPeso(Double peso) {
		this.peso=peso;
	};
	public double getTalla() {
		return this.talla;
	};
	public void setTalla(Double talla) {
		this.talla=talla;
	};
	public double getImc() {
		return this.imc;
	};
	public void setImc(Double imc) {
		this.imc=imc;
	}
	public void calcularImc() {
		double imc = peso/(talla*talla);
		this.imc=imc;
	}
	public void condicion(double imc) {
		if (imc<18) {
			this.condicion="Anorexia";
		}else if (imc>=18&&imc<20) {
			this.condicion="Delgadez";
		}else if (imc>=20&&imc<27) {
			this.condicion="Normalidad";
		}else if (imc>=27&&imc<30) {
			this.condicion="Obesidad (grado 1)";
		}else if (imc>=30&&imc<35) {
			this.condicion="Obesidad (grado 2)";
		}else if (imc>=35&&imc<40) {
			this.condicion="Obesidad (grado 3)";
		}else if (imc>40) {
			this.condicion="Obesidad morbida";
		}
		
	};
	public String getCondicion() {
		return this.condicion;
	}
	

}
