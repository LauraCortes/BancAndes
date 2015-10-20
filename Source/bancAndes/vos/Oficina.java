package bancAndes.vos;

import java.util.ArrayList;

public class Oficina {
	
	/**
	 * Identificador de la oficina
	 */
	private String identificador;
	
	/**
	 * Nombre de la oficina
	 */
	private String nombre;
	
	/**
	 * Telefono de contacto de la oficina
	 */
	private String telefono;
	
	/**
	 * Direccion de la oficina 
	 */
	private String direccion;
	
	/**
	 * Identificador del gerente encargado de la oficina
	 */
	private String idGerOfi;
	
	/**
	 * Listado de clientes afiliados en la oficina
	 */
	@SuppressWarnings("unused")
	private ArrayList<Cliente> clientes;

	/**
	 * Metodo que se encarga de crear una nueva oficina
	 * @param idP de la oficina
	 * @param nombre de la oficina
	 * @param telefono de contacto de la oficina
	 * @param direccion de domicilio de la oficina
	 * @param idGerente identificador del gerente encargado de la oficina
	 */
	public Oficina(String idP, String nombre, String telefono,
			String direccion, String idGerente) {
		super();
		this.identificador = idP;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.idGerOfi = idGerente;
		this.clientes = null;
	}

	/**
	 * Metodo que se encarga de retornar el identificador de la oficina
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Metodo que se encarga de asignar el identificador de la oficina
	 * @param identificador a asignar 
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Metodo que se encarga de retornar el nombre de la oficina
	 * @return nombre a asignarle a la oficina
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que se encarga de asignar el nommbre a la oficina
	 * @param nombre de la oficina a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que se encarga de retornar el telefono de la oficina
	 * @return telefono de la oficina
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo que se encarga de asignar el telefono de la oficina
	 * @param telefono a asignarle a la oficina
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo que se encarga de retornar la direccion de domicilio de la oficina
	 * @return direccion de la oficina
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo que se encarga de asignarle la direccion a la oficina
	 * @param direccion de la oficina que se quiere asignar
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo que se encarga de retornar el identificador del gerente encargado
	 * @return identificador del gerente encargado
	 */
	public String getIdGerOfi() {
		return idGerOfi;
	}

	/**
	 * Metodo que se encarga de asignar el identificador del gerente encragado
	 * @param identificador del gerente encargado
	 */
	public void setIdGerOfi(String idGerOfi) {
		this.idGerOfi = idGerOfi;
	}
	
	
}
