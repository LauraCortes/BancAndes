package bancAndes.vos;

public class Cliente extends Usuario{
	
	/**
	 * Cliente Juridico
	 */
	public static final String JURIDICO = "Juridico";
	
	/**
	 * Cliente natural
	 */
	public static final String NATURAL = "Natural";
	
	/**
	 * Tipo de cliente ( Jurídica o Natural)
	 */
	private String tipoCliente;
	
	/**
	 * Identificador de la oficina a la cual esta asociado
	 */
	private String idOficina;
	
	/**
	 * Identificador del jefe si tiene;
	 */
	private String idJefe;

	/**
	 * Metodo que se encarga de crear un cliente
	 * @param identificador unico del cliente
	 * @param tipoCliente de cliente 
	 * @param idOficina identificador unico de la oficina a la cual esta vinculado
	 */
	public Cliente(String identificador, String tipoCliente, String idOficina, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento, String idJefe) {
		super(identificador, nombre, logIn, clave, rol, tipoId, numId, nacionalidad, direccion,
				email, ciudad, telefono, codigoPostal, departamento);
		this.tipoCliente = tipoCliente;
		this.idOficina = idOficina;
		this.idJefe = idJefe;
	}

	/**
	 * Metodo que se encarga de retornar el tipo de cliente
	 * @return tipo de cliente asociado al banco
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Metodo que se encarga de asignar el tipo de cliente
	 * @param tipo de cliente vinculado
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * Metodo que se ecnarga de retornar el identificador de la oficina a la cual se vinculo
	 * @return identificador de la oficina desde la cual se vinculo al banco
	 */
	public String getIdOficina() {
		return idOficina;
	}

	/**
	 * Metodo que se encarga de asignar el identificador de la oficina
	 * @param identificador de la oficina a la cual se esta vinculando
	 */
	public void setIdOficina(String idOficina) {
		this.idOficina = idOficina;
	}
	
	/**
	 * Metodo que retorna el id del jefe 
	 * @return el id del jefe
	 */
	public String getIdJefe()
	{
		return idJefe;
	}
	
	public void setIdJefe(String idJefe)
	{
		this.idJefe = idJefe;
	}
}
