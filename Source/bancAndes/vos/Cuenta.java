package bancAndes.vos;

public class Cuenta {
	
	/**
	 * Estado abierto de cuenta
	 */
	public static final String ESTADO_ACTIVO = "Activo";
	
	/**
	 * Estado cerrado de cuenta
	 */
	public static final String ESTADO_CERRADO = "Cerrado";
	
	/**
	 * Numero de cuenta
	 */
	private int numeroCuenta;
	
	/**
	 * Estado de la cuenta
	 */
	private String estado;
	
	/**
	 * Tipo de cuenta
	 */
	private String tipo;
	
	/**
	 * Saldo de la cuenta
	 */
	private double Saldo;
	
	/**
	 * Identificador unico del cliente dueño de la cuenta
	 */
	private int idCliente;
	
	/**
	 * Identificador unico de la oficina a la cual esta vinculada la cuenta
	 */
	private int idOficina;

	/**
	 * Metodo que se encarga de crear una cuenta del banco
	 * @param numeroCuenta Numero de cuenta 
	 * @param estado Estado de la cuenta
	 * @param tipo Tipo de cuenta
	 * @param saldo Saldo del producto
	 * @param idCliente Identificador unico del cliente dueño del producto
	 * @param idOficina Identificador de la ofinicina de la cual fue creada la cuenta
	 */
	public Cuenta(int numeroCuenta, String estado, String tipo,
			double saldo, int idCliente, int idOficina) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.estado = estado;
		this.tipo = tipo;
		this.Saldo = saldo;
		this.idCliente = idCliente;
		this.idOficina = idOficina;
	}

	/**
	 * Metodo que se encarga de retornar el numero de cuenta
	 * @return Numero de la cuenta 
	 */
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Metodo que se encarga de asignar el numero de cuenta al producto
	 * @param Numero de cuenta a asginar al producto bancario
	 */
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Metodo que se encarga de retornar el estado de la cuenta
	 * @return estado de la cuenta
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo que se encarga de asignar un estado al producto
	 * @param estado que se le quiere ser asignado a la cuenta
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Metodo que se encarga de retornar el tipo de la cuenta bancaria
	 * @return tipo de cuenta 
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo que se encarga de asignar el tipo de la cuenta
	 * @param tipo de cuenta que se requiere
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metod que se encarga de retornar el saldo actual del producto
	 * @return saldo de la cuenta
	 */
	public double getSaldo() {
		return Saldo;
	}

	/**
	 * Metodo que se encarga de asignar el saldo actual de la cuenta
	 * @param saldo que se quiera asignar a la cuenta
	 */
	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	/**
	 * Metodo que se encarga de retornar el identificador del cliente titular de la cuenta
	 * @return identificador del cliente titular de la cuenta
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Metodo que se encarga de asignar el identificador del cliente titular
	 * @param identificador unico del cliente titular de la cuenta
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Metodo que se encarga de retornar el identificador de la oficina
	 * @return identificador unico de la oficina a la cual esta asociada la cuenta
	 */
	public int getIdOficina() {
		return idOficina;
	}

	/**
	 * Metod que se encarga de asignar el identificador unico de la oficina a la cual esta registrada la cuenta
	 * @param identidicador de la oficina a la cual esta vinculada la cuenta
	 */
	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}
	
	
}
