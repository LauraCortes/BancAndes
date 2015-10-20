package bancAndes.vos;

public class Administrador extends Usuario {
	
	/**
	 * Identificador del administrador
	 */
	private String identificador;
	
	/**
	 * Metodo constructor del administrador
	 * @param identificador unico del administrador
	 */
	public Administrador(String identificador, String id, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento) {
		super(id, nombre, logIn, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostal, departamento);
		this.identificador = identificador;
	}

	/**
	 * Metodo que retorna el identificador del administrador
	 * @return identificador unico del administrador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Metodo que se encarga de asignar el identificador al administrador
	 * @param identificador a asignarle al administrador
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	

}
