package bancAndes.vos;

public class PuntoAtencion {
	
	/**
	 * Tipo punto de atencion : Cajero
	 */
	public static final String TIPO_CAJERO = "Cajero";
	
	/**
	 * Tipo punto de atencion : Punto atencion
	 */
	public static final String TIPO_PUNTO = "Punto atencion";

	public static final Object TIPO_CONSIGNACION = null;
	
	private String identificador;
	
	private String tipo;
	
	private String localizacion;
	
	private String idOficina;
	
	private String idCajero;

	/**
	 * @param idP
	 * @param tipo
	 * @param localizacion
	 * @param idOficina
	 * @param idCajero
	 */
	public PuntoAtencion(String idP, String tipo, String localizacion,
			String idOficina, String idCajero) {
		super();
		this.identificador = idP;
		this.tipo = tipo;
		this.localizacion = localizacion;
		this.idOficina = idOficina;
		this.idCajero = idCajero;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * @return the idOficina
	 */
	public String getIdOficina() {
		return idOficina;
	}

	/**
	 * @param idOficina the idOficina to set
	 */
	public void setIdOficina(String idOficina) {
		this.idOficina = idOficina;
	}

	/**
	 * @return the idCajero
	 */
	public String getIdCajero() {
		return idCajero;
	}

	/**
	 * @param idCajero the idCajero to set
	 */
	public void setIdCajero(String idCajero) {
		this.idCajero = idCajero;
	}
	
	
}
