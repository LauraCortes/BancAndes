package test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

public class UnicidadTest extends TestCase
{
	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	private static final String ARCHIVO_CONEXION = "/../conexion.properties";

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
	private String tipoUsuario;

	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;

	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuario;

	/**
	 * clave de conexión a la base de datos.
	 */
	private String clave;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 * @return 
	 */
	public UnicidadTest() 
	{		
		inicializar("./Conexion/conexion.properties");
	}

	// -------------------------------------------------
	// Métodos
	// -------------------------------------------------

	/**
	 * obtiene ls datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
	public void inicializar(String path)
	{
		try
		{
			File arch= new File(path+ARCHIVO_CONEXION);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream( arch );

			prop.load( in );
			in.close( );

			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
			// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
			usuario = prop.getProperty("usuario");	// "s2501aXX";
			clave = prop.getProperty("clave");	// "c2501XX";
			@SuppressWarnings("unused")
			final String driver = prop.getProperty("driver");
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

	public void testUsuario() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR) FROM USUARIOS");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO USUARIOS "
					+ "VALUES("+pk+",'a','a','Cliente','cedula','123456789','a','a','aca','a','a','123456','a',12345)");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla usuarios está ese ID");
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
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO USUARIOS "
					+ "VALUES("+pk+",'a','a','Cliente','cedula','123456789','a','a','aca','a','a','123456','a',12345)");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en Usuario");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void testClientes() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR) FROM CLIENTES");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO CLIENTES VALUES ("+pk+",'Juridico',150)");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla clientes está ese ID");
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

		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO CLIENTES VALUES ("+pk+",'Juridico',150)");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en Clientes");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void testAdministradores() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR) FROM ADMINISTRADORES");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO ADMINISTRADORES VALUES ("+pk+")");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla administradores está ese ID");
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

		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO ADMINISTRADORES VALUES ("+pk+")");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en Administradores");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void testEmpleados() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR) FROM EMPLEADOS");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO EMPLEADOS VALUES ("+pk+",'Cajero')");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla empleados está ese ID");
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

		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO EMPLEADOS VALUES ("+pk+",'Cajero')");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en empleados");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void testCuentas() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(NUMERO_CUENTA) FROM CUENTAS");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO CUENTAS VALUES ("+pk+",'Ahorros','Activo',100000,501,1)");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla cuentas está ese ID");
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

		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO CUENTAS VALUES ("+pk+",'Ahorros','Activo',100000,501,1)");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en Cuentas");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void testPrestamos() throws Exception
	{
		PreparedStatement prepStmt = null;
		int pk = 0;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			PreparedStatement consulID = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR) FROM PRESTAMOS");
			ResultSet rs = consulID.executeQuery();
			rs.next();
			pk = (rs.getInt(1)+1);
			prepStmt = conexion.prepareStatement("INSERT INTO PRESTAMOS VALUES ("+pk+",50000,20,24,24,2555555,40000000,'Aprobado','Automovil',105,853)");
			prepStmt.execute();
			System.out.println("sirve");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Falla porque en la tabla prestamos está ese ID");
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

		try
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("INSERT INTO PRESTAMOS VALUES ("+pk+",50000,20,24,24,2555555,40000000,'Aprobado','Automovil',105,853)");
			prepStmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Error PK en prestamos");
		}
		finally
		{
			if(prepStmt != null)
			{
				try
				{
					prepStmt.close();
				}
				catch(SQLException e)
				{
					//Debe pasar por aca.
				}
			}
			try 
			{
				closeConnection(conexion);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
