package bancAndes.vos;

public class Prestamo {
	
	public static final String APROBADO = "'Aprobado'";

	public static final String VIVIENDA = "'Vivienda'";

	private String identificador;
	
	private String monto;
	
	private String Stringeres;
	
	private String numCuotas;
	
	private String diaPago;
	
	private String valorCuota;
	
	private String saldo;
	
	private String estado;
	
	private String tipo;
	
	private String idOficina;
	
	private String idCliente;

	/**
	 * @param identificador
	 * @param monto
	 * @param Stringeres
	 * @param numCuotas
	 * @param diaPago2
	 * @param valorCuota
	 * @param saldo
	 * @param estado
	 * @param tipo
	 * @param idOficina
	 * @param idCliente
	 */
	public Prestamo(String identificador, String monto, String Stringeres,
			String numCuotas, String diaPago, String valorCuota, String saldo,
			String estado, String tipo, String idOficina, String idCliente) {
		super();
		this.identificador = identificador;
		this.monto = monto;
		this.Stringeres = Stringeres;
		this.numCuotas = numCuotas;
		this.diaPago = diaPago;
		this.valorCuota = valorCuota;
		this.saldo = saldo;
		this.estado = estado;
		this.tipo = tipo;
		this.idOficina = idOficina;
		this.idCliente = idCliente;
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
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the Stringeres
	 */
	public String getStringeres() {
		return Stringeres;
	}

	/**
	 * @param Stringeres the Stringeres to set
	 */
	public void setStringeres(String Stringeres) {
		this.Stringeres = Stringeres;
	}

	/**
	 * @return the numCuotas
	 */
	public String getNumCuotas() {
		return numCuotas;
	}

	/**
	 * @param numCuotas the numCuotas to set
	 */
	public void setNumCuotas(String numCuotas) {
		this.numCuotas = numCuotas;
	}

	/**
	 * @return the diaPago
	 */
	public String getDiaPago() {
		return diaPago;
	}

	/**
	 * @param diaPago the diaPago to set
	 */
	public void setDiaPago(String diaPago) {
		this.diaPago = diaPago;
	}

	/**
	 * @return the valorCuota
	 */
	public String getValorCuota() {
		return valorCuota;
	}

	/**
	 * @param valorCuota the valorCuota to set
	 */
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}

	/**
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
