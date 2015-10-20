package bancAndes.vos;

public class OperacionCuenta {
	
	/**
	 * Operacion de retiro de dinero
	 */
	public static final String OPERACION_RETIRO = "Retiro";
	
	/**
	 * Operacion de consignacion de dinero
	 */
	public static final Object OPERACION_CONSIGNACION = "Consignacion";
	
	/**
	 * Identificador unico de la operacion
	 */
	private String identificador;
	
	/**
	 * Valor de la operacion 
	 */
	private String valor;
	
	/**
	 * Fecha en la que se hizo la operacion
	 */
	private String fecha;
	
	/**
	 * Hora en la que se realiza la operacion
	 */
	private String hora;
	
	/**
	 * Identificacion de la cuenta que genera la operacion
	 */
	private String idCuenta;
	
	/**
	 * Identificacion del punto desde el que se realizo la operacion
	 */
	private String idPunto;
	
	/**
	 * Accion realizada en la operacion
	 */
	private String accion;

	/**
	 * Metodo que se encarga de crear una operacion de cuentas
	 * @param identificador unico de la operacion
	 * @param valor por el que se genera la operacion
	 * @param fecha en la que se realiza la operacion 
	 * @param hora en la que se realiza la operacion
	 * @param identificador de la cuenta que realiza la operacion
	 * @param identificacion del punto en que se genera la operacion
	 */
	public OperacionCuenta(String identificador, String valor, String fecha,
			String hora, String idCuenta, String idPunto, String accion) {
		super();
		this.identificador = identificador;
		this.valor = valor;
		this.fecha = fecha;
		this.hora = hora;
		this.idCuenta = idCuenta;
		this.idPunto = idPunto;
		this.accion = accion;
	}

	/**
	 * Metodo que retorna el identificador de la operacion
	 * @return identificador unico de la operacion
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Metodo que se encarga de asignar el identificador a la operacion
	 * @param identificador de la operacion a ser asignado
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Metodo que retorna el valor de la operacion
	 * @return valor por el cual se hace la operacion
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Metodo que se encarga de asignar el valor por el cual se realiza la operacion 
	 * @param valor que se le asigna a la operacion 
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Metodo que retorna la fecha en la cual se realizo la operacion
	 * @return fecha de la operacion
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Metodo que se encarga de asignar la fecha de la operacion
	 * @param fecha a asignar a la operacion, en la cual se realiza la misma
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo que se encarga de retornar la hora en la cual se realiza la operacion
	 * @return hora de la operacion
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Metodo que asigna la hora a la cual se realizo la operacion
	 * @param hora a asignarle a la operacion
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Metodo que se encarga de retornar el identificador de la cuenta
	 * @return identificador de la cuenta que realiza la operacion
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Metodo que se encarga de asignar el identificador de la cuenta responsable de la operacion
	 * @param identificador de la cuenta
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * Metodo que se encarga de retornar el identificador del punto de atencion donde se realizo la operacion
	 * @return identificador del punto de atencion
	 */
	public String getIdPunto() {
		return idPunto;
	}

	/**
	 * Metodo que se encarga de asignar el identificador del punto de atencion de donde se realizo la operacion
	 * @param identificador del punto de atencion
	 */
	public void setIdPunto(String idPunto) {
		this.idPunto = idPunto;
	}
	

}
