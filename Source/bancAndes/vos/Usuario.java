package bancAndes.vos;

public class Usuario {
	
	
	public static final String CEDULA = "Cédula";
	public static final String PASAPORTE = "Pasaporte";
	public static final String TARJETA_DE_IDENTIDAD = "Tarjeta de identidad";
	public static final String PERSONA_NATURAL = "Persona Natural";
	public static final String PERSONA_JURIDICA = "Persona Jurídica";
	/**
	 * Identificador del usuario
	 */
	private String identificador;
	
	/**
	 * Nombre del usuario
	 */
	private String nombre;
	
	/**
	 * Login del usuario
	 */
	private String logIn;
	
	/**
	 * Clave del usuario
	 */
	private String clave;
	
	/**
	 * Rol del usuario, 
	 */
	private String rol;
	
	/**
	 * Tipo de documento de identidad del usuario
	 */
	private String tipoId;
	
	/**
	 * Numero de documento de identificacion
	 */
	private String numId; 
	
	/**
	 * Nacionalidad del usuario
	 */
	private String nacionalidad;
	
	/**
	 * Direccion de residencia del usuario
	 */
	private String direccion;
	
	/**
	 * Correo electronico del usuario
	 */
	private String email;
	
	/**
	 * Ciudad de residencia del usuario
	 */
	private String ciudad;
	
	/**
	 * Telefono de contacto del usuario
	 */
	private String telefono;
	
	/**
	 * Codigo postal del usuario
	 */
	private String codigoPostal;
	
	/**
	 * Departamento o estado de residencia
	 */
	private String departamento;

	/**
	 * Metodo constructor de la clase Usuario
	 * @param id identificador unico del usuario
	 * @param nombre nombre del usuario
	 * @param logIn Log in del usuario para acceder a portal Web
	 * @param clave Clave de ingreso a la cuenta en el portal Web
	 * @param rol rol del usuario
	 * @param tipoId Tipo de identificacion del usuario 
	 * @param numId Numero de identificacion del usuario
	 * @param nacionalidad Nacionalidad del usuario
	 * @param direccion Direccion de residencia del usuario
	 * @param email Correo electronico del usuario
	 * @param ciudad Ciudad de residencia del usuario
	 * @param telefono Telefono de contacto del usuario
	 * @param codigoPostal Codigo postal del usuario
	 * @param departamento Departamento o estado de residencia del usuario
	 */
	public Usuario(String id, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento) {
		super();
		this.identificador = id;
		this.nombre = nombre;
		this.logIn = logIn;
		this.clave = clave;
		this.rol = rol;
		this.tipoId = tipoId;
		this.numId = numId;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.email = email;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
	}

	/**
	 * Metodo que retorna el identificador del usuario
	 * @return el identificador del usuario
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Metodo que asigna el identificador del usuario
	 * @param id para asignar al usuario
	 */
	public void setId(String id) {
		this.identificador = id;
	}

	/**
	 * Metodo que retorna el nombre del usuario
	 * @return nombre de usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que asigna el nombre del usuario
	 * @param nombre para asignar al usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que retorna el Log in del usuario
	 * @return logIn del usuario
	 */
	public String getLogIn() {
		return logIn;
	}

	/**
	 * Metodo que asigna el logIn del usuario
	 * @param logIn a asignar al usuario
	 */
	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}

	/**
	 * Metodo que retorna la clave de acceso a la plataforma Web
	 * @return clave del usuario
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo para asignar la clave de ingreso del usuario
	 * @param clave a asignar al usuario para el ingreso a la plataforma Web
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Metodo que retorna el rol del usuario
	 * @return rol del usuario
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Metodo que asigna el rol del usuario
	 * @param rol que se asigna al usuario
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Metodo que retorna el tipo de identificacion
	 * @return el tipo de identificacion ciudadana del usuario
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * Metodo que asigna el tipo de identificacion del usuario
	 * @param tipo de identificacion que se asignara al usuario
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Metodo que retorna el numero de identificacion ciudadana
	 * @return numero de identificacion del usuario
	 */
	public String getNumId() {
		return numId;
	}

	/**
	 * Metodo que asigna el numero de identificacion
	 * @param numero de identificacion a asignar al usuario
	 */
	public void setNumId(String numId) {
		this.numId = numId;
	}

	/**
	 * Metodo que retorna la nacionalidad del usuario
	 * @return nacionalidad del usuario
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * Metodo que asigna la nacionalidad del usuario
	 * @param nacionalidad a asginar del usuario
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Metodo que retorna la direccion de residencia del usuario
	 * @return direccion del usuario
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo que asigna la direccion de residencia del usuario
	 * @param direccion a asignar al usuario 
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo que retorna el corrreo electronico del usuario
	 * @return correo electronico del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que asigna el email del usuario
	 * @param email a asignar al usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que retorna la ciudad del usuario
	 * @return ciudad de residencia del usuario
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Metodo que asigna la ciudad de residencia del usuario
	 * @param ciudad a asignar al usuario
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Metodo que retorna el telofono de contacto del usuario
	 * @return telefono de contacto del usuario
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo que asigna el telefono de contacto del usuario
	 * @param telefono a asignar del usuario
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo que retorna el codigo postal del usuario
	 * @return codigo Postal del usuario
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Metodo que asigna el codigo postal del usuario
	 * @param codigo postal a asignar al usuario
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Metodo que retorna el departamento o estado de residencia del usuario
	 * @return departamento o estado de residencia del usuario
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Metodo que asigna el departamento o estado de residencia del usuario
	 * @param departamento o esstado de residencia a asignar del estado
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	
}
