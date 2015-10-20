package bancAndes.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import bancAndes.fachado.BancAndes;
import bancAndes.vos.Administrador;
import bancAndes.vos.Cliente;
import bancAndes.vos.Cuenta;
import bancAndes.vos.Empleado;
import bancAndes.vos.Oficina;
import bancAndes.vos.OperacionCuenta;
import bancAndes.vos.OperacionPrestamo;
import bancAndes.vos.Prestamo;
import bancAndes.vos.PuntoAtencion;
import bancAndes.vos.Usuario;


public class ConsultaDAO {

	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	public static final String ARCHIVO_CONEXION = "./Conexion/conexion.properties";
	private final static String CLIENTE ="Cliente";
	private final static String GERENTE_GENERAL ="Gerente General";
	private final static String ADMINISTRADOR ="Administrador";
	private final static String GERENTE_OFICINA ="Gerente Oficina";
	private final static String CAJERO ="Cajero";


	//----------------------------------------------------
	//Atributos
	//----------------------------------------------------

	/**
	 * tipo de usuario
	 */
	//private String tipoUsuarioSesion;

	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;

	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuarioBD;

	/**
	 * clave de conexión a la base de datos.
	 */
	private String claveDB;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 * @return 
	 */
	public ConsultaDAO() 
	{		
	}

	// -------------------------------------------------
	// Métodos
	// -------------------------------------------------

	/**
	 * obtiene ls datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
	public void inicializar(String ruta)
	{
		try
		{
			File arch= new File(ruta);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream( arch );

			prop.load( in );
			in.close( );

			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
			// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
			usuarioBD = prop.getProperty("usuario");	// "s2501aXX";
			claveDB = prop.getProperty("clave");	// "c2501XX";
			@SuppressWarnings("unused")
			final String driver = prop.getProperty("driver");

			establecerConexion(cadenaConexion, usuarioBD, claveDB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Método que se encarga de crear la conexión con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexión con la base de datos.
	 */
	private void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");


		try {

			conexion = DriverManager.getConnection(
					url, usuario,
					clave);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (conexion != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

	/**
	 *Cierra la conexión activa a la base de datos. Además, con=null.
	 * @param con objeto de conexión a la base de datos
	 * @throws SistemaCinesException Si se presentan errores de conexión
	 */
	public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
		}
	} 

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna todos los usuarios registrados en el banco.
	 * @return ArrayList lista de usuarios.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Usuario> darUsuarios() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList< Usuario> usuarioss = new ArrayList< Usuario>();    

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS");
			while(rs.next()){

				String idP = rs.getString("IDENTIFICADOR");

				String nombreP = rs.getString("NOMBRE");

				String logIn = rs.getString("LOGIN");

				String clave = rs.getString("CLAVE");

				String rol = rs.getString("ROL");

				String tipoId = rs.getString("TIPO_ID");

				String numId = rs.getString("NUM_ID");

				String nacionalidad = rs.getString("NACIONALIDAD");

				String direccion = rs.getString("DIRECCION");

				String email = rs.getString("EMAIL");

				String ciudad = rs.getString("CIUDAD");

				String departamento = rs.getString("DEPARTAMENTO");

				String telefono = rs.getString("TELEFONO");

				String codigoPostalP = rs.getString("CODIGO_POSTAL");


				Usuario nuevo = new Usuario(idP, nombreP, logIn, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento); 

				while (usuarioss.size()< 100){					
					usuarioss.add(nuevo);
				}

				System.out.println("----------------Usuario----------------");
				//				System.out.println(idP+"\n"+ nombreP+"\n"+ logIn+"\n"+ clave+"\n"+ rol+"\n"+ tipoId+"\n"+ numId+"\n"+ nacionalidad+"\n"+ direccion+"\n"+ email+"\n"+ ciudad+"\n"+ telefono+"\n"+ codigoPostalP+"\n"+ departamento);

			}

			System.out.println("Total cargados: "+usuarioss.size());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM USUARIOS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return usuarioss;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna todas las cuentas registradas en el banco.
	 * @return ArrayList lista de cuentas.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Cuenta> darCuentas() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList< Cuenta> cuentas = new ArrayList<Cuenta>();    

		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM CUENTAS");
			while(rs.next()){


				int numeroCuenta = rs.getInt("NUMERO_CUENTA");

				String tipo = rs.getString("TIPO");

				String estado = rs.getString("ESTADO");

				double saldo = rs.getInt("SALDO");

				int idCliente = rs.getInt("ID_CLIENTE");

				int idOficina = rs.getInt("ID_OFICINA");

				Cuenta nuevo = new Cuenta(numeroCuenta, estado, tipo, saldo, idCliente, idOficina); 

				while (cuentas.size()< 100){					
					cuentas.add(nuevo);
				}

				System.out.println("----------------Cuenta----------------");
				//				System.out.println(numeroCuenta+"\n"+ estado+"\n"+ tipo+"\n"+ saldo+"\n"+ idCliente+"\n"+ idOficina);

			}

			//			System.out.println("Total cargados: "+cuentas.size());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM CUENTAS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return cuentas;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna los usuarios de tipo cliente registrados en el banco.
	 * @return ArrayList lista de clientes.
	 * La lista contiene los videos ordenados alfabeticamente
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Cliente> darClientes() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList< Cliente> clientes = new ArrayList<Cliente>();    

		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICADOR");

				String tipo = rs.getString("TIPO_CLIENTE");

				String idOficina = rs.getString("ID_OFICINA");
				
				String idJefe = rs.getString("JEFE");

			
				Cliente nuevo = new Cliente(idP, tipo, idOficina,idJefe, null, null, null, null,
						null, null, null, null, null, null, null, null, null); 

				while (clientes.size()< 100){					
					clientes.add(nuevo);
				}

				System.out.println("----------------Cliente----------------");
				//				System.out.println(idP+"\n"+ tipo+"\n"+idOficina);

			}

			System.out.println("Total cargados: "+clientes.size());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM CLIENTES");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return clientes;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna al administrador.
	 * @return ArrayList lista que contiene elementos tipo VideosValue.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public Administrador darAdmin() throws Exception
	{
		PreparedStatement prepStmt = null;    

		Administrador nuevo = null;

		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM ADMINISTRADORES");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICADOR");

				nuevo = new Administrador(idP, null, null, null, null, null, 
						null, null, null, null, null, null, null, null, null); 

				System.out.println("----------------Administrador----------------");
				//				System.out.println(idP);
				//				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM ADMINISTRADORES");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna los empleados registrados en el banco.
	 * @return ArrayList lista que contiene los usuarios de tipo empleado.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Empleado> darEmpleados() throws Exception
	{
		PreparedStatement prepStmt = null;    

		ArrayList<Empleado> nuevo = new ArrayList<Empleado>();

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM EMPLEADOS");
			while(rs.next()){


				int idP = Integer.parseInt(rs.getString("IDENTIFICADOR"));

				String tipoEmpleado = rs.getString("TIPO_EMPLEADO");

				
				Empleado nuevoEmpleado = new Empleado(""+idP,tipoEmpleado,null, null, null, null, 
						null, null, null, null, null, null, null, null, null); 

				System.out.println("----------------EMPLEADOS----------------");
				System.out.println(idP+"\n"+tipoEmpleado);
				while (nuevo.size()<100){
					nuevo.add(nuevoEmpleado);
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM EMPLEADOS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna las oficinas.
	 * @return ArrayList lista que contiene las oficinas.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Oficina> darOficinas() throws Exception
	{
		PreparedStatement prepStmt = null;    

		ArrayList<Oficina> nuevo = new ArrayList<Oficina>();

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM OFICINAS");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICACION");

				String nombre = rs.getString("NOMBRE");

				String telefono = rs.getString("TELEFONO");

				String direccion = rs.getString("DIRECCION");

				String idGerente = rs.getString("ID_GER_OFICINA");

				Oficina nuevaOfi = new Oficina(idP, nombre, telefono, direccion, idGerente); 

				System.out.println("----------------Oficinas----------------");
				//				System.out.println(idP+"\n"+ nombre+"\n"+ telefono+"\n"+ direccion+"\n"+ idGerente);
				while (nuevo.size()<100){
					nuevo.add(nuevaOfi);
				}

			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SELECT * FROM OFICINAS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna las operaciones realizadas sobre cuentas.
	 * @return ArrayList lista que contiene las operaciones registradas sobre las cuentas.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<OperacionCuenta> darOpCuentas() throws Exception
	{
		PreparedStatement prepStmt = null;    

		ArrayList<OperacionCuenta> nuevo = new ArrayList<OperacionCuenta>();

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM OPERACIONES_CUENTAS");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICADOR");

				String valor = rs.getString("VALOR");

				String horaS = rs.getString("HORA");

				String fechaS = rs.getString("FECHA");


				String idCuenta = rs.getString("ID_CUENTA");

				String idPuntoAt = rs.getString("ID_PUNTO");

				String accion = rs.getString("ACCION");

				OperacionCuenta nuevaOfi = new OperacionCuenta(idP, valor, fechaS, horaS,idCuenta,idPuntoAt, accion); 

				System.out.println("----------------OP_CUENTAS----------------");
				//System.out.println(idP+"\n"+ valor+"\n"+ fecha+"\n"+ hora+"\n"+idCuenta+"\n"+ idPuntoAt);
				while (nuevo.size()<100){
					nuevo.add(nuevaOfi);
				}

			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SSELECT * FROM OPERACIONES_CUENTAS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna las operaciones realizadas sobre los prestamos.
	 * @return ArrayList lista que contiene las operaciones registradas de los prestamos.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<OperacionPrestamo> darOpPrestamo() throws Exception
	{
		PreparedStatement prepStmt = null;    

		ArrayList<OperacionPrestamo> nuevo = new ArrayList<OperacionPrestamo>();

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM OPERACIONES_PRESTAMOS");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICADOR");

				String valor = rs.getString("VALOR");

				String horaS = rs.getString("HORA");
				DateFormat format = new SimpleDateFormat("hh/mm/ss");
				Date hora = (Date) format.parse(horaS);

				String fechaS = rs.getString("FECHA");
				DateFormat format1 = new SimpleDateFormat("dd/mm/yy");
				Date fecha = (Date) format1.parse(fechaS);

				String idPrestamo = rs.getString("ID_PRESTAMO");

				String idPuntoAt = rs.getString("ID_PUNTO");
				String accion = rs.getString("ACCION");

				OperacionPrestamo nuevaOfi = new OperacionPrestamo(idP, valor, fechaS, horaS, idPrestamo, idPuntoAt, accion); 

				System.out.println("----------------OP_PRESTAMOS----------------");
				//				System.out.println(idP+"\n"+ valor+"\n"+ fecha+"\n"+ hora+"\n"+idPrestamo+"\n"+ idPuntoAt);
				while (nuevo.size()<100){
					nuevo.add(nuevaOfi);
				}

			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SSELECT * FROM OPERACIONES_PRESTAMOS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna los prestamos.
	 * @return ArrayList lista con todos los prestamos.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<Prestamo> darPrestamos() throws Exception
	{
		PreparedStatement prepStmt = null;    

		ArrayList<Prestamo> nuevo = new ArrayList<Prestamo>();

		try {

			establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM PRESTAMOS");
			while(rs.next()){


				String idP = rs.getString("IDENTIFICADOR");

				String monto = rs.getString("MONTO");

				String interes = rs.getString("INTERES");

				String numCuotas = rs.getString("NUMERO_CUOTAS");

				String diaPago = rs.getString("DIA_PAGO");

				String valorCuota = rs.getString("VALOR_CUOTA");

				String saldo = rs.getString("SALDO");

				String estado = rs.getString("ESTADO");

				String tipoPrestamo = rs.getString("TIPO_PRESTAMO");

				String idOficina = rs.getString("ID_OFICINA");

				String idCliente = rs.getString("ID_CLIENTE");

				Prestamo nuevaOfi = new Prestamo(idP, monto, interes, numCuotas, diaPago, valorCuota, saldo, estado, tipoPrestamo, idOficina, idCliente); 

				System.out.println("----------------PRESTAMOS----------------");
				//				System.out.println(idP+"\n"+ monto+"\n"+ interes+"\n"+ numCuotas+"\n"+ diaPago+"\n"+ valorCuota+"\n"+ saldo+"\n"+ estado+"\n"+ tipoPrestamo+"\n"+ idOficina+"\n"+ idCliente);

				while (nuevo.size()<100){
					nuevo.add(nuevaOfi);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SSELECT * FROM PRESTAMOS");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	

		return nuevo;
	}

	/**
	 * Método que se encarga de realizar la consulta en la base de datos
	 * y retorna los puntos de atencion.
	 * @return ArrayList lista de puntos de atencion.
	 * @throws Exception se lanza una excepción si ocurre un error en
	 * la conexión o en la consulta. 
	 */
	public ArrayList<PuntoAtencion> darPuntosAtencion() throws Exception{
		PreparedStatement prepStmt = null;    
		ArrayList<PuntoAtencion> nuevo = new ArrayList<PuntoAtencion>();
		try {
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM PUNTOS_ATENCION");
			while(rs.next()){
				String idP = rs.getString("IDENTIFICADOR");
				String tipo = rs.getString("TIPO");
				String localizacion = rs.getString("LOCALIZACION");
				String idOficina = rs.getString("ID_OFICINA");
				String idCajero = rs.getString("ID_CAJERO");
				PuntoAtencion nuevoPunto = new PuntoAtencion(idP, tipo, localizacion, idOficina, idCajero); 
				System.out.println("----------------PUNTOS DE ATENCION----------------");
				System.out.println(idP+"\n"+ tipo+"\n"+ localizacion+"\n"+ idOficina+"\n"+ idCajero);
				while (nuevo.size()<100){
					nuevo.add(nuevoPunto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SSELECT * FROM PUNTOS_ATENCION");
			throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally {
			if (prepStmt != null) {
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {
					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}	
		return nuevo;
	}

	public String Login(int id) throws Exception
	{

		PreparedStatement prepStmt = null;
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			prepStmt = conexion.prepareStatement("select * from clientes where identificador="+id);			
			ResultSet rs = prepStmt.executeQuery();
			rs.next();
			if(rs.getRow()==0)
			{
				PreparedStatement prepStmt2 = conexion.prepareStatement("select * from administradores where identificador="+id);			
				ResultSet rs2 = prepStmt2.executeQuery();
				rs2.next();
				if(rs2.getRow()==0)
				{
					PreparedStatement prepStmt3 = conexion.prepareStatement("select * from empleados where identificador="+id);			
					ResultSet rs3 = prepStmt3.executeQuery();
					rs3.next();
					if(rs3.getRow()==0)
					{
						return "";
					}
					else
					{
						String empleado = rs.getString("TIPO_EMPLEADO");
						if(empleado.equalsIgnoreCase("gerente general"))
						{
							//tipoUsuarioSesion = GERENTE_GENERAL;
						}
						else if(empleado.equalsIgnoreCase("gerente oficina"))
						{
							//tipoUsuarioSesion = GERENTE_OFICINA;
						}
						else
						{
							//tipoUsuarioSesion = CAJERO;
						}
					}
				}
				else
				{
					//tipoUsuarioSesion = ADMINISTRADOR;
				}
			}
			else
			{
				//tipoUsuarioSesion = CLIENTE;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}	
		//return tipoUsuarioSesion;
		return null;
	}

	public boolean RF1AgregarCliente(String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento, String tipoCliente, String idOficina) throws Exception
	{
		PreparedStatement prepStmt = null;
		boolean agregoUsuario = false;
		boolean agregoCliente = false;
		try
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(identificador) as MAXID FROM USUARIOS";
			ResultSet r = statement.executeQuery(sql);
			System.out.println("3");
			if(r.next())
			{
				int identificador = r.getInt("MAXID") + 1 ;

				System.out.println("vamos bien");
				sql = "INSERT INTO USUARIOS (IDENTIFICADOR, LOGIN, CLAVE, ROL, TIPO_ID, NUM_ID, NOMBRE, NACIONALIDAD, DIRECCION, EMAIL, CIUDAD, TELEFONO, DEPARTAMENTO, CODIGO_POSTAL) "
						+ "VALUES ("+identificador+",'"+logIn+"','"+clave+"','"+rol+"','"+tipoId+"','"+numId+"','"+nombre+"','"+nacionalidad
						+"','"+direccion+"','"+email+"','"+ciudad+"','"+telefono+"','"+departamento+"','"+codigoPostal+"')";
				System.out.println("4");
				agregoUsuario = statement.execute(sql);
				sql = "INSERT INTO CLIENTES (IDENTIFICADOR, TIPO_CLIENTE, ID_OFICINA)"
						+ "VALUES ("+identificador+",'"+tipoCliente+"','"+idOficina+"')";

				agregoCliente = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoCliente;


	}

	public boolean RF1AgregarEmpleado( String tipoEmpleado, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento) throws Exception {


		PreparedStatement prepStmt = null;
		boolean agregoUsuario = false;
		boolean agregoEmpleado = false;
		try
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(identificador) as MAXID FROM USUARIOS";
			ResultSet r = statement.executeQuery(sql);
			if(r.next())
			{
				int identificador = r.getInt("MAXID") + 1 ;

				System.out.println("vamos bien");
				sql = "INSERT INTO USUARIOS (IDENTIFICADOR, LOGIN, CLAVE, ROL, TIPO_ID, NUM_ID, NOMBRE, NACIONALIDAD, DIRECCION, EMAIL, CIUDAD, TELEFONO, DEPARTAMENTO, CODIGO_POSTAL) "
						+ "VALUES ("+identificador+",'"+logIn+"','"+clave+"','"+rol+"','"+tipoId+"','"+numId+"','"+nombre+"','"+nacionalidad
						+"','"+direccion+"','"+email+"','"+ciudad+"','"+telefono+"','"+departamento+"','"+codigoPostal+"')";
				agregoUsuario = statement.execute(sql);

				sql = "INSERT INTO EMPLEADOS (IDENTIFICADOR, TIPO_EMPLEADO) "
						+ "VALUES ("+identificador+",'"+tipoEmpleado+"')";

				agregoEmpleado = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoEmpleado;


	}

	public ArrayList<Cuenta> RF5(int cuenta) throws Exception
	{
		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;
		ArrayList<Cuenta> resp = new ArrayList<Cuenta>();
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			prepStmt = conexion.prepareStatement("UPDATE CUENTAS SET ESTADO='Cerrado', SALDO=0 WHERE NUMERO_CUENTA="+cuenta);
			prepStmt.executeQuery();
			prepStmt2 = conexion.prepareStatement("SELECT * FROM CUENTAS WHERE NUMERO_CUENTA="+cuenta);
			ResultSet rs = prepStmt2.executeQuery();
			while(rs.next())
			{
				int numCuenta = rs.getInt("NUMERO_CUENTA");
				String tipo = rs.getString("TIPO");
				String estado = rs.getString("ESTADO");
				double saldo = rs.getDouble("SALDO");
				int idCliente = rs.getInt("ID_CLIENTE");
				int idOficina = rs.getInt("ID_OFICINA");
				Cuenta nuevo = new Cuenta(numCuenta, estado, tipo, saldo, idCliente, idOficina);
				resp.add(nuevo);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return resp;
	}

	public ArrayList<OperacionCuenta> RF6(String cuenta, String accion, int valor) throws Exception
	{
		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;
		PreparedStatement prepStmt3 = null;
		ArrayList<OperacionCuenta> operacion = new ArrayList<OperacionCuenta>();
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			java.util.Date date = new java.util.Date();
			String fecha = dateFormat.format(date);
			String[] mod = fecha.split("/");
			String[] mod2 = mod[2].split(" ");
			String[] hora = mod2[1].split(":");
			if(accion.equalsIgnoreCase("consignar"))
			{
				System.out.println("vamos bien");
				prepStmt = conexion.prepareStatement("INSERT INTO OPERACIONES_CUENTAS VALUES "
						+ "((SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS) "
						+ "+1,"+ valor+","+hora[0]+hora[1]+",TO_DATE('"+mod[0]+"/"+mod[1]+"/"+mod2[0]+"','"
						+ "DD/MM/YY'),"+cuenta+",1,'Consignar')");
				prepStmt.executeQuery();
				System.out.println("vamos bien 2");
				prepStmt2 = conexion.prepareStatement("UPDATE CUENTAS "
						+ "SET SALDO = SALDO + (SELECT VALOR "
						+ "FROM OPERACIONES_CUENTAS "
						+ "WHERE OPERACIONES_CUENTAS.IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS)) "
						+ "WHERE NUMERO_CUENTA = (SELECT ID_CUENTA "
						+ "FROM OPERACIONES_CUENTAS "
						+ "WHERE OPERACIONES_CUENTAS.IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS))");
				prepStmt2.executeQuery();
				System.out.println("vamos bien 3");
				prepStmt3 = conexion.prepareStatement("SELECT * FROM OPERACIONES_CUENTAS WHERE IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_CUENTAS)");
				ResultSet rs = prepStmt3.executeQuery();
				System.out.println("vamos bien 4");
				while (rs.next()) 
				{
					String iden = rs.getString("IDENTIFICADOR");
					String valor2 = rs.getString("VALOR");
					String hora2 = rs.getString("HORA");
					String fecha2 = rs.getString("FECHA");
					String idCuenta = rs.getString("ID_CUENTA");
					String idPunto = rs.getString("ID_PUNTO");
					String accion2 = rs.getString("ACCION");

					OperacionCuenta nuevo = new OperacionCuenta(iden, valor2, fecha2, hora2, idCuenta, idPunto, accion2);
					operacion.add(nuevo);
				}

			}
			else if(accion.equalsIgnoreCase("retirar"))
			{
				prepStmt = conexion.prepareStatement("INSERT INTO OPERACIONES_CUENTAS VALUES "
						+ "((SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS) "
						+ "+1,"+ valor+","+hora[0]+hora[1]+",TO_DATE('"+mod[0]+"/"+mod[1]+"/"+mod2[0]+"','"
						+ "DD/MM/YY'),"+cuenta+",1,'Retirar')");
				prepStmt.executeQuery();
				prepStmt2 = conexion.prepareStatement("UPDATE CUENTAS "
						+ "SET SALDO = SALDO - (SELECT VALOR "
						+ "FROM OPERACIONES_CUENTAS "
						+ "WHERE OPERACIONES_CUENTAS.IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS)) "
						+ "WHERE NUMERO_CUENTA = (SELECT ID_CUENTA "
						+ "FROM OPERACIONES_CUENTAS "
						+ "WHERE OPERACIONES_CUENTAS.IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_CUENTAS))");
				prepStmt2.executeQuery();
				prepStmt3 = conexion.prepareStatement("SELECT * FROM OPERACIONES_CUENTA WHERE IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_CUENTAS)");
				ResultSet rs = prepStmt3.executeQuery();
				while (rs.next()) 
				{
					String iden = rs.getString("IDENTIFICADOR");
					String valor2 = rs.getString("VALOR");
					String hora2 = rs.getString("HORA");
					String fecha2 = rs.getString("FECHA");
					String idCuenta = rs.getString("ID_CUENTA");
					String idPunto = rs.getString("ID_PUNTO");
					String accion2 = rs.getString("ACCION");
					OperacionCuenta nuevo = new OperacionCuenta(iden, valor2, fecha2, hora2, idCuenta, idPunto, accion2);
					operacion.add(nuevo);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return operacion;
	}

	public boolean RF7RegistrarPrestamo(int montoPrestamo, int interes, int numCuotas, int diaPago,
			int valorCuota, String tipoPrestamo,int idGerenteOficina, int idCliente) throws Exception
	{
		PreparedStatement prepStmt = null;
		boolean agregoPrestamo = false;
		try	
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(IDENTIFICADOR) as MAXID FROM PRESTAMOS";
			ResultSet r = statement.executeQuery(sql);
			if(r.next())
			{

				int identificador = r.getInt("MAXID") + 1 ;
				System.out.println("vamos bien");
				sql = "INSERT INTO PRESTAMOS (IDENTIFICADOR, MONTO, INTERES, NUMERO_CUOTAS, DIA_PAGO, VALOR_CUOTA, SALDO, ESTADO, TIPO_PRESTAMO, ID_OFICINA, ID_CLIENTE) "
						+ "VALUES ("+identificador+","+montoPrestamo+","+interes+","+numCuotas+","+diaPago+","+valorCuota+","+montoPrestamo+","+Prestamo.APROBADO+","+tipoPrestamo+","+idGerenteOficina+","+idCliente+")";
				agregoPrestamo = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoPrestamo;		
	}

	public ArrayList<OperacionPrestamo> RF8(String prestamo, String accion) throws Exception
	{
		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;
		PreparedStatement prepStmt3 = null;
		PreparedStatement prepStmt4 = null;
		ArrayList<OperacionPrestamo> operaciones = null;
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			java.util.Date date = new java.util.Date();
			String fecha = dateFormat.format(date);
			String[] mod = fecha.split("/");
			String[] mod2 = mod[2].split(" ");
			String[] hora = mod2[1].split(":");
			if(accion.equalsIgnoreCase("Pagar Cuota"))
			{
				prepStmt = conexion.prepareStatement("INSERT INTO OPERACIONES_PRESTAMOS "
						+ "VALUES((SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_PRESTAMOS)+1,null,"+hora[0]+hora[1]+","
						+ "TO_DATE('"+mod[0]+"/"+mod[1]+"/"+mod2[0]+"','dd/mm/yy'),"+prestamo+",1,'Pagar Cuota')");
				prepStmt.executeQuery();
				prepStmt2 = conexion.prepareStatement("UPDATE PRESTAMOS SET MONTO = MONTO - VALOR_CUOTA "
						+ "WHERE IDENTIFICADOR = (SELECT OPERACIONES_PRESTAMOS.ID_PRESTAMO "
						+ "FROM OPERACIONES_PRESTAMOS "
						+ "WHERE OPERACIONES_PRESTAMOS.IDENTIFICADOR = "
						+ "(SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_PRESTAMOS))");
				prepStmt2.executeQuery();
				prepStmt3 = conexion.prepareStatement("SELECT * FROM OPERACIONES_PRESTAMOS WHERE IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_PRESTAMOS)");
				ResultSet rs = prepStmt3.executeQuery();
				while (rs.next()) 
				{
					String iden = rs.getString("IDENTIFICADOR");
					String valor2 = rs.getString("VALOR");
					String hora2 = rs.getString("HORA");
					String fecha2 = rs.getString("FECHA");
					String idPrestamo = rs.getString("ID_PRESTAMO");
					String idPunto = rs.getString("ID_PUNTO");
					String accion2 = rs.getString("ACCION");
					OperacionPrestamo nuevo = new OperacionPrestamo(iden, valor2, hora2, fecha2, idPrestamo, idPunto,accion2);
					operaciones.add(nuevo);
				}
			}
			else if(accion.equalsIgnoreCase("Pagar Cuota Extraordinaria"))
			{
				prepStmt = conexion.prepareStatement("INSERT INTO OPERACIONES_PRESTAMOS "
						+ "VALUES((SELECT MAX(IDENTIFICADOR) "
						+ "FROM OPERACIONES_PRESTAMOS)+1,null,1300,"
						+ "TO_DATE('06/06/06','dd/mm/yy'),"+prestamo+",1,'Pagar Cuota Extraordinaria')");
				prepStmt.executeQuery();
				prepStmt2 = conexion.prepareStatement("UPDATE PRESTAMOS SET MONTO = MONTO - VALOR_CUOTA "
						+ "WHERE IDENTIFICADOR = (SELECT OPERACIONES_PRESTAMOS.ID_PRESTAMO "
						+ "FROM OPERACIONES_PRESTAMOS "
						+ "WHERE OPERACIONES_PRESTAMOS.IDENTIFICADOR = "
						+ "(SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_PRESTAMOS))");
				prepStmt2.executeQuery();
				prepStmt3 = conexion.prepareStatement("UPDATE PRESTAMOS "
						+ "SET NUMERO_CUOTAS=NUMERO_CUOTAS-1 "
						+ "WHERE IDENTIFICADOR = (SELECT OPERACIONES_PRESTAMOS.ID_PRESTAMO "
						+ "FROM OPERACIONES_PRESTAMOS WHERE OPERACIONES_PRESTAMOS.IDENTIFICADOR = "
						+ "(SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_PRESTAMOS))");
				prepStmt3.executeQuery();
				prepStmt4 = conexion.prepareStatement("SELECT * FROM OPERACIONES_PRESTAMOS WHERE IDENTIFICADOR = (SELECT MAX(IDENTIFICADOR) FROM OPERACIONES_PRESTAMOS)");
				ResultSet rs = prepStmt4.executeQuery();
				while (rs.next()) 
				{
					String iden = rs.getString("IDENTIFICADOR");
					String valor2 = rs.getString("VALOR");
					String hora2 = rs.getString("HORA");
					String fecha2 = rs.getString("FECHA");
					String idPrestamo = rs.getString("ID_PRESTAMO");
					String idPunto = rs.getString("ID_PUNTO");
					String accion2 = rs.getString("ACCION");
					OperacionPrestamo nuevo = new OperacionPrestamo(iden, valor2, hora2, fecha2, idPrestamo, idPunto,accion2);
					operaciones.add(nuevo);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return operaciones;
	}

	public ArrayList<Prestamo> RF9(int prestamo) throws Exception
	{
		PreparedStatement prepStmt = null;
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			prepStmt = conexion.prepareStatement("SELECT SALDO FROM PRESTAMOS WHERE IDENTIFICADOR="+prestamo);
			ResultSet rs = prepStmt.executeQuery();
			rs.next();
			int saldo = rs.getInt("SALDO");
			if(saldo==0)
			{
				PreparedStatement prepStmt2 = conexion.prepareStatement("UPDATE PRESTAMOS SET ESTADO='Cerrado' WHERE IDENTIFICADOR ="+prestamo);

				ResultSet rs2 = prepStmt2.executeQuery();
				while(rs.next())
				{
					String identificador = rs2.getString("IDENTIFICADOR");
					String monto = rs2.getString("MONTO");
					String interes = rs2.getString("INTERES");
					String numCuotas = rs2.getString("NUMERO_CUOTAS");
					String diaPago = rs2.getString("DIA_PAGO");
					String valorCuota = rs2.getString("VALOR_CUOTA");
					String saldo2 = rs2.getString("SALDO");
					String estado = rs2.getString("ESTADO");
					String tipoPrestamo = rs2.getString("TIPO_PRESTAMO");
					String idOficina = rs2.getString("ID_OFICINA");
					String idCliente = rs2.getString("ID_CLIENTE");
					Prestamo nuevo = new Prestamo(identificador, monto, interes, numCuotas, diaPago, valorCuota, saldo2, estado, tipoPrestamo, idOficina, idCliente);
					prestamos.add(nuevo);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return prestamos;
	}

	public boolean actualizarCuentas (String monto, String idCuentaOrigen, String idCuentaDestino){
		boolean resp = false;

		return resp;
	}

	public Usuario buscarUsuarioPorEmail(String email) throws Exception {

		PreparedStatement prepStmt = null;

		Usuario usuario = null;
		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS WHERE EMAIL = '"+email+"'");
			if(rs.next()){

				String idP = rs.getString("IDENTIFICADOR");

				String nombreP = rs.getString("NOMBRE");

				String logIn = rs.getString("LOGIN");

				String clave = rs.getString("CLAVE");

				String rol = rs.getString("ROL");

				String tipoId = rs.getString("TIPO_ID");

				String numId = rs.getString("NUM_ID");

				String nacionalidad = rs.getString("NACIONALIDAD");

				String direccion = rs.getString("DIRECCION");


				String ciudad = rs.getString("CIUDAD");

				String departamento = rs.getString("DEPARTAMENTO");

				String telefono = rs.getString("TELEFONO");

				String codigoPostalP = rs.getString("CODIGO_POSTAL");


				usuario = new Usuario(idP, nombreP, logIn, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento); 

			}

		} catch (SQLException e) {
			e.printStackTrace();
			//throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return usuario;
	}

	public Usuario buscarUsuarioPorLogin(String login) throws Exception {

		PreparedStatement prepStmt = null;

		Usuario usuario = null;
		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS WHERE LOGIN = '"+login+"'");
			if(rs.next()){

				String idP = rs.getString("IDENTIFICADOR");

				String nombreP = rs.getString("NOMBRE");

				String clave = rs.getString("CLAVE");

				String rol = rs.getString("ROL");

				String tipoId = rs.getString("TIPO_ID");

				String numId = rs.getString("NUM_ID");

				String nacionalidad = rs.getString("NACIONALIDAD");

				String direccion = rs.getString("DIRECCION");

				String email = rs.getString("EMAIL");

				String ciudad = rs.getString("CIUDAD");

				String departamento = rs.getString("DEPARTAMENTO");

				String telefono = rs.getString("TELEFONO");

				String codigoPostalP = rs.getString("CODIGO_POSTAL");


				usuario = new Usuario(idP, nombreP, login, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento); 

			}

		} catch (SQLException e) {
			e.printStackTrace();
			//throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return usuario;
	}

	public Usuario buscarUsuarioPorID(String id) throws Exception{

		PreparedStatement prepStmt = null;

		Usuario usuario = null;
		try {

			//establecerConexion(cadenaConexion, usuarioBD, claveDB);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS WHERE IDENTIFICADOR = '"+id+"'");
			if(rs.next()){

				String idP = rs.getString("IDENTIFICADOR");

				String login = rs.getString("LOGIN");

				String nombreP = rs.getString("NOMBRE");

				String clave = rs.getString("CLAVE");

				String rol = rs.getString("ROL");

				String tipoId = rs.getString("TIPO_ID");

				String numId = rs.getString("NUM_ID");

				String nacionalidad = rs.getString("NACIONALIDAD");

				String direccion = rs.getString("DIRECCION");

				String email = rs.getString("EMAIL");

				String ciudad = rs.getString("CIUDAD");

				String departamento = rs.getString("DEPARTAMENTO");

				String telefono = rs.getString("TELEFONO");

				String codigoPostalP = rs.getString("CODIGO_POSTAL");

				if(rol.equals(BancAndes.ROL_CLIENTE))
				{
					ResultSet rs2 = s.executeQuery("SELECT * FROM CLIENTES WHERE IDENTIFICADOR = '"+id+"'");
					if(rs2.next()){
						usuario = new Cliente(idP, rs2.getString("TIPO_CLIENTE"), rs2.getString("ID_OFICINA"),
								rs2.getString("JEFE"), nombreP, login, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento);
					}
				}
				else if(rol.equals(BancAndes.ROL_EMPLEADO))
				{
					ResultSet rs2 = s.executeQuery("SELECT * FROM EMPLEADOS WHERE IDENTIFICADOR = '"+id+"'");
					if(rs2.next()){
						usuario = new Empleado(idP, rs2.getString("TIPO_EMPLEADO"), nombreP, login, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento); 					
					}
				}
				else
				{
					usuario = new Usuario(idP, nombreP, login, clave, rol, tipoId, numId, nacionalidad, direccion, email, ciudad, telefono, codigoPostalP, departamento);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//throw new Exception("ERROR = ConexionBD: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					closeConnection(conexion);
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConexionBD: loadRow() =  cerrando una conexión.");
				}
			}
		}		
		return usuario;
	}

	public boolean agregarOficina(String nombre, String direccion,
			String telefono, String idGerenteOficina) throws Exception{
		PreparedStatement prepStmt = null;
		boolean agregoOficina = false;
		try
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(identificacion) as MAXID FROM OFICINAS";
			ResultSet r = statement.executeQuery(sql);
			if(r.next())
			{
				int identificador = r.getInt("MAXID") + 1 ;

				System.out.println("vamos bien");
				sql = "INSERT INTO OFICINAS (IDENTIFICACION, DIRECCION,ID_GER_OFICINA, TELEFONO, NOMBRE ) "
						+ "VALUES ("+identificador+",'"+direccion+"','"+idGerenteOficina+"','"+telefono+"','"+nombre+"')";
				agregoOficina = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoOficina;

	}

	public boolean RF3AgregarPuntoAtencion(String tipoPuntoAtencion, String localizacion, String idOficina, String idCajero) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement prepStmt = null;
		boolean agregoPuntoAtencion = false;
		try
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(identificador) as MAXID FROM PUNTOS_ATENCION";
			ResultSet r = statement.executeQuery(sql);
			if(r.next())
			{
				int identificador = r.getInt("MAXID") + 1 ;

				System.out.println("vamos bien");
				sql = "INSERT INTO PUNTOS_ATENCION (IDENTIFICADOR, TIPO, LOCALIZACION, ID_OFICINA, ID_CAJERO) "
						+ "VALUES ("+identificador+",'"+tipoPuntoAtencion+"','"+localizacion+"','"+idOficina+"','"+idCajero+"')";
				agregoPuntoAtencion = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoPuntoAtencion;
	}

	public boolean RF4RegistrarCuenta(String tipoCuenta, String idCliente,
			String idGerenteOficina, String monto) throws Exception {
		PreparedStatement prepStmt = null;
		boolean agregoCuenta = false;
		try	
		{

			Statement statement = conexion.createStatement();
			String sql = "SELECT MAX(NUMERO_CUENTA) as MAXID FROM CUENTAS";
			ResultSet r = statement.executeQuery(sql);
			if(r.next())
			{

				int identificador = r.getInt("MAXID") + 1 ;
				System.out.println("vamos bien");
				sql = "INSERT INTO CUENTAS (NUMERO_CUENTA, TIPO, ESTADO, SALDO, ID_CLIENTE, ID_OFICINA) "
						+ "VALUES ("+identificador+",'"+tipoCuenta+"','"+Cuenta.ESTADO_ACTIVO+"','"+monto+"','"+idCliente+"','"+idGerenteOficina+"')";
				agregoCuenta = statement.execute(sql);

			}


		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!" +e.getMessage());
		}
		finally
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				}
				catch (SQLException exception) 
				{

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
				}
			}
			closeConnection(conexion);
		}
		return agregoCuenta;
	}
	
	public ArrayList<OperacionCuenta> RF10(String cuenta, int valor)
	{
		ArrayList<OperacionCuenta> respuesta = new ArrayList<OperacionCuenta>();
		PreparedStatement prepStmt = null;
		try
		{
			establecerConexion(cadenaConexion, usuarioBD, claveDB);
			respuesta = RF6(cuenta, "retirar", valor);
		}
		catch(Exception e)
		{
			System.out.println("ERROR RF10");
		}
		return respuesta;
		
	}

//	public boolean actualizarOperacionCuenta(int monto, String idOrigen, String idDestino, String accion) throws Exception{
//
//		PreparedStatement prepStmt = null;
//		boolean actualizar = false;
//		try
//		{
//			if(accion.equals(OperacionCuenta.OPERACION_RETIRO)){
//				Statement statement = conexion.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT SALDO FROM CUENTAS WHERE NUMERO_CUENTA ="+idOrigen);
//				if(rs.next()){
//					int saldo = rs.getInt("SALDO");
//					if(monto < saldo){
//						String sql = "UPDATE CUENTAS SET SALDO = SALDO -"+monto+" WHERE NUMERO_CUENTA="+idOrigen;
//						actualizar = statement.execute(sql);
//					}
//					else{
//						throw new Exception("El monto excede el saldo actual disponible");
//					}
//				}
//			}
//			else if(accion.equals(OperacionCuenta.OPERACION_CONSIGNACION)){
//				Statement statement = conexion.createStatement();
//				Statement statement2 = conexion.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT SALDO FROM CUENTAS WHERE NUMERO_CUENTA ="+idOrigen);
//				if(rs.next()){
//
//					int saldo = rs.getInt("SALDO");
//
//					if(monto<saldo){
//						String sql = "UPDATE CUENTAS SET SALDO = SALDO -"+monto+" WHERE NUMERO_CUENTA="+idOrigen;
//						actualizar = statement.execute(sql);
//						String sql2 = "UPDATE CUENTAS SET SALDO = SALDO +"+monto+" WHERE NUMERO_CUENTA="+idDestino;
//						actualizar = statement2.execute(sql2);
//					}
//					else{
//						throw new Exception("El monto excede el saldo actual disponible");
//
//					}
//				}
//			}
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
//		}
//		finally 
//		{
//			if (prepStmt != null) 
//			{
//				try 
//				{
//					prepStmt.close();
//				}
//				catch (SQLException exception) 
//				{
//
//					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
//				}
//			}
//			closeConnection(conexion);
//		}
//		return actualizar;
//	}
	//TODO
//	public boolean actualizarOperacionPrestamo(int monto, String idOrigen, String idDestino, String accion) throws Exception{
//
//		PreparedStatement prepStmt = null;
//		boolean actualizar = false;
//		try
//		{
//			if(accion.equals(OperacionCuenta.OPERACION_RETIRO)){
//				Statement statement = conexion.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT SALDO FROM CUENTAS WHERE NUMERO_CUENTA ="+idOrigen);
//				if(rs.next()){
//					int saldo = rs.getInt("SALDO");
//					if(monto < saldo){
//						String sql = "UPDATE CUENTAS SET SALDO = SALDO -"+monto+" WHERE NUMERO_CUENTA="+idOrigen;
//						actualizar = statement.execute(sql);
//					}
//					else{
//						throw new Exception("El monto excede el saldo actual disponible");
//					}
//				}
//			}
//			else if(accion.equals(OperacionCuenta.OPERACION_CONSIGNACION)){
//				Statement statement = conexion.createStatement();
//				Statement statement2 = conexion.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT SALDO FROM CUENTAS WHERE NUMERO_CUENTA ="+idOrigen);
//				if(rs.next()){
//
//					int saldo = rs.getInt("SALDO");
//
//					if(monto<saldo){
//						String sql = "UPDATE CUENTAS SET SALDO = SALDO -"+monto+" WHERE NUMERO_CUENTA="+idOrigen;
//						actualizar = statement.execute(sql);
//						String sql2 = "UPDATE CUENTAS SET SALDO = SALDO +"+monto+" WHERE NUMERO_CUENTA="+idDestino;
//						actualizar = statement2.execute(sql2);
//					}
//					else{
//						throw new Exception("El monto excede el saldo actual disponible");
//
//					}
//				}
//			}
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
//		}
//		finally 
//		{
//			if (prepStmt != null) 
//			{
//				try 
//				{
//					prepStmt.close();
//				}
//				catch (SQLException exception) 
//				{
//
//					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiï¿½n.");
//				}
//			}
//			closeConnection(conexion);
//		}
//		return actualizar;
//	}

	public static void main(String[] args) 
	{
		System.out.println("main");

		ConsultaDAO conexion = new ConsultaDAO();
		try {
			conexion.RF9(1);
			System.out.println("se hizo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}
	}



}
