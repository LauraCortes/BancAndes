package bancAndes.vos;

public class Empleado extends Usuario{
	
	
	/**
	 * Tipo del empleado 
	 */
	private String tipoEmpleado;

	/**
	 * Metodo que se encarga de crear un empleado del banco
	 * @param identificador del empleado del banco
	 * @param tipo del empleado 
	 */
	public Empleado(String identificador, String tipoEmpleado, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento) {
		super(identificador, nombre, logIn, clave, rol, tipoId, numId, nacionalidad,
				direccion, email, ciudad, telefono, codigoPostal, departamento);
		this.tipoEmpleado = tipoEmpleado;
	}

	/**
	 * Metodo que se encarga de retornar el tipo de empleado
	 * @return tipo de empleado
	 */
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	/**
	 * Metodo que se encarga de asignar el tipo de empleado 
	 * @param tipo de empleado a asignar
	 */
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	
}
