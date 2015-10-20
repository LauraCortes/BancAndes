package bancAndes.fachado;

import java.util.ArrayList;

import bancAndes.dao.ConsultaDAO;
import bancAndes.vos.Administrador;
import bancAndes.vos.Cliente;
import bancAndes.vos.Cuenta;
import bancAndes.vos.Empleado;
import bancAndes.vos.OperacionCuenta;
import bancAndes.vos.OperacionPrestamo;
import bancAndes.vos.Prestamo;
import bancAndes.vos.Usuario;

public class BancAndes 
{

	public static final String ADMIN_USER = "aarmstrong0";
	public static final String ADMIN_CLAVE = "nMEY0T";

	/**
	 * Rol del usuario como administrador
	 */
	public static final String ROL_ADMINISTRADOR = "Administrador";

	/**
	 * Rol del usuario como cliente
	 */
	public static final String ROL_CLIENTE = "Cliente";

	/**
	 * Rol del usuario como empleado
	 */
	public static final String ROL_EMPLEADO = "Empleado";

	/**
	 * Rol del empleado como gerente general
	 */
	private static final String ROL_EMPLEADO_GERENTE_GENERAL = "Gerente General";

	/**
	 * Rol del empleado como gerente de oficina
	 */
	private static final String ROL_EMPLEADO_GERENTE_OFICINA = "Gerente Oficina";

	/**
	 * Rol del empleado como cajero
	 */
	private static final String ROL_EMPLEADO_CAJERO = "Cajero";


	private ConsultaDAO dao;

	private static BancAndes instance;

	private Usuario user;

	public static BancAndes getInstance()
	{
		if(instance == null)
		{
			instance = new BancAndes();
		}
		return instance;
	}

	private BancAndes()
	{
		dao = new ConsultaDAO();
		inicializarRuta(ConsultaDAO.ARCHIVO_CONEXION);
	}

	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}

	public String login(int id)
	{
		try 
		{
			return dao.Login(id);
		} 
		catch (Exception e) {
		
			e.printStackTrace();
		}
		return "";
	}

	public String loginUsuario(String login, String clave) throws Exception
	{
		String mensaje = "";
		if(login.equals(ADMIN_USER))
		{
			if(clave.equals(ADMIN_CLAVE))
			{
				user = new Administrador(null, null, null, ADMIN_USER, ADMIN_CLAVE, ROL_ADMINISTRADOR, null, null, null, null, null, null, null, null, null);
				mensaje = "Sesión de administrador";
			}
			else
			{
				user = null;
				mensaje = "Clave incorrecta";
			}
		}
		else
		{
			Usuario usuario = dao.buscarUsuarioPorLogin(login);
			if(usuario == null)
			{
				mensaje = "El usuario no existe";
			}
			else if(usuario.getClave().equals(clave))
			{
				user = usuario;
				mensaje = "Sesión de usuario: "+user.getRol();
			}
			else
			{
				user = null;
				mensaje = "Clave incorrecta";
			}
		}
		return mensaje;
	}

	public boolean agregarEmpleado(String tipoEmpleado, String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento) throws Exception
	{


		boolean resp = false;
		if(user != null && user.getRol().equals(ROL_ADMINISTRADOR)){

			if(dao.buscarUsuarioPorLogin(logIn) != null)
			{
				throw new Exception("El usuario con el login "+logIn +" ya está registrado");
			}
			if(dao.buscarUsuarioPorEmail(email) != null)
			{
				throw new Exception("El usuario con el email "+email +" ya está registrado");
			}
			if(tipoEmpleado.equals(ROL_EMPLEADO_CAJERO) || tipoEmpleado.equals(ROL_EMPLEADO_GERENTE_GENERAL) ||
					tipoEmpleado.equals(ROL_EMPLEADO_GERENTE_OFICINA)){

				resp = dao.RF1AgregarEmpleado(tipoEmpleado, nombre, logIn, clave,
						rol, tipoId, numId, nacionalidad,
						direccion, email, ciudad, telefono,
						codigoPostal, departamento);

			}
		}
		else
		{
			throw new Exception("No tiene permisos para agregar empleados");
		}
		return resp;			
	}

	@SuppressWarnings("unused")
	public boolean agregarOficina(String nombre, String direccion, String telefono, String idGerenteOficina) throws Exception
	{
		boolean respuesta = false;
		
		Empleado gerenteOficina = (Empleado)dao.buscarUsuarioPorID(idGerenteOficina);
		System.out.println(gerenteOficina.getRol());
		
		if(gerenteOficina == null)
		{
			throw new Exception ("El gerente no existe");
		}
		else if( !gerenteOficina.getTipoEmpleado().equals(ROL_EMPLEADO_GERENTE_OFICINA))
		{
			throw new Exception ("El usuario no tiene permisos para agregar una oficina");
		}
		
		dao.agregarOficina(nombre, direccion, telefono, idGerenteOficina);
		
		return respuesta;
	}

	@SuppressWarnings("unused")
	public boolean agregarCliente(String nombre, String logIn, String clave,
			String rol, String tipoId, String numId, String nacionalidad,
			String direccion, String email, String ciudad, String telefono,
			String codigoPostal, String departamento, String tipoCliente, String idGerenteOficina) throws Exception
	{
		boolean resp = false;
		System.out.println("1");
		
		Empleado gerenteOficina = (Empleado)dao.buscarUsuarioPorID(idGerenteOficina);
		
		System.out.println(gerenteOficina.getRol());
		
		if(gerenteOficina == null)
		{
			throw new Exception ("El gerente no existe");
		}
		else if( !gerenteOficina.getTipoEmpleado().equals(ROL_EMPLEADO_GERENTE_OFICINA))
		{
			throw new Exception ("El usuario no tiene permisos para agregar una oficina");
		}
		if(tipoCliente.equals(Cliente.NATURAL) || tipoCliente.equals(Cliente.JURIDICO)){
			System.out.println("2");
				resp = dao.RF1AgregarCliente(nombre, logIn, clave,
						rol, tipoId, numId, nacionalidad,
						direccion, email, ciudad, telefono,
						codigoPostal, departamento,tipoCliente, idGerenteOficina);

		}
			
		
		else
		{
			throw new Exception("No tiene permisos para agregar Cliente");
		}
		return resp;			
	}
	
	public boolean RegistrarPuntoAtencion(String tipoPuntoAtencion, String localizacion, 
			String idOficina, String idCajero) throws Exception
	{
		boolean resp = false;
		if(user != null && user.getRol().equals(ROL_ADMINISTRADOR)){
			resp = dao.RF3AgregarPuntoAtencion(tipoPuntoAtencion,  localizacion,  idOficina, idCajero);
			System.out.println("Exito");
		}
		else
		{
			throw new Exception("No tiene permisos para agregar empleados");
		}
		return resp;			
	}


	@SuppressWarnings("unused")
	public boolean registroCuenta(String tipoCuenta, String idCliente, String idGerenteOficina, String monto ) throws Exception
	{
		boolean resp = false;
		System.out.println("1");
		
		Empleado gerenteOficina = (Empleado)dao.buscarUsuarioPorID(idGerenteOficina);
		
		System.out.println(gerenteOficina.getRol());
		
		if(gerenteOficina == null)
		{
			throw new Exception ("El gerente no existe");
		}
		else if( !gerenteOficina.getTipoEmpleado().equals(ROL_EMPLEADO_GERENTE_OFICINA))
		{
			throw new Exception ("El usuario no tiene permisos para agregar una oficina");
		}
		resp = dao.RF4RegistrarCuenta(tipoCuenta, idCliente, idGerenteOficina, monto);

		return resp;			
	}

	public ArrayList<Cuenta> cerrarCuenta(int cuenta)
	{
		ArrayList<Cuenta> cuentas = null;
		try
		{
			cuentas = dao.RF5(cuenta);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cuentas;
	}

	public ArrayList<OperacionCuenta> registrarOperacionCuenta(String cuenta, String accion, int valor)
	{
		ArrayList<OperacionCuenta> operaciones = null;
		try
		{
			operaciones = dao.RF6(cuenta, accion, valor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return operaciones;
	}

	@SuppressWarnings("unused")
	public boolean registrarPrestamo(int montoPrestamo, int interes, int numCuotas, int diaPago,
			int valorCuota, String tipoPrestamo,int idGerenteOficina, int idCliente) throws Exception
	{
		boolean resp = false;
		System.out.println("1");
		String idGerente = String.valueOf(idGerenteOficina);
		Empleado gerenteOficina = (Empleado)dao.buscarUsuarioPorID(idGerente);
		
		System.out.println(gerenteOficina.getRol());
		
		if(gerenteOficina == null)
		{
			throw new Exception ("El gerente no existe");
		}
		else if( !gerenteOficina.getTipoEmpleado().equals(ROL_EMPLEADO_GERENTE_OFICINA))
		{
			throw new Exception ("El usuario no tiene permisos para agregar una oficina");
		}
		resp = dao.RF7RegistrarPrestamo(montoPrestamo, interes, numCuotas, diaPago, valorCuota, tipoPrestamo, idGerenteOficina, idCliente);

		return resp;	
	}

	public ArrayList<OperacionPrestamo> registrarOperacionSobrePrestamo(String prestamo, String accion)
	{
		ArrayList<OperacionPrestamo> prestamos = null;
		try
		{
			prestamos = dao.RF8(prestamo, accion);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return prestamos;
	}

	public ArrayList<Prestamo> cerrarPrestamo(int prestamo)
	{
		ArrayList<Prestamo> prestamos = null;
		try
		{
			prestamos = dao.RF9(prestamo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return prestamos;
	}
	
//	public boolean registrarOpEnCuenta (int idResponsable ,int monto, String idOrigen, String idDestino, String accion) throws Exception{
//		boolean resp = false;
//		System.out.println("1");
//		String idUsuario = String.valueOf(idResponsable);
//		
//		Usuario usuario = dao.buscarUsuarioPorID(idUsuario);
//		
//		System.out.println(usuario.getRol());
//		if(usuario == null)
//		{
//			throw new Exception ("El Usuario no existe");
//		}
//		if(!usuario.getRol().equals(ROL_EMPLEADO_CAJERO)){
//			throw new Exception ("El usuario no tiene permisos para agregar una oficina");
//		}
//		resp = dao.actualizarOperacionCuenta( monto, idOrigen, idDestino, accion);
//
//		return resp;	
//	}

//	public boolean registrarOpEnPrestamo (int idResponsable ,int monto, String idOrigen, String idDestino, String accion) throws Exception{
//	boolean resp = false;
//	System.out.println("1");
//	String idUsuario = String.valueOf(idResponsable);
//	
//	Usuario usuario = dao.buscarUsuarioPorID(idUsuario);
//	
//	System.out.println(usuario.getRol());
//	if(usuario == null)
//	{
//		throw new Exception ("El Usuario no existe");
//	}
//	if(!usuario.getRol().equals(ROL_EMPLEADO_CAJERO)){
//		throw new Exception ("El usuario no tiene permisos para agregar una oficina");
//	}
//	resp = dao.actualizarOperacionPrestamo( monto, idOrigen, idDestino, accion);
//
//	return resp;	
//}
	public static void main(String[] args) 
	{
		System.out.println("main BancAndes");

		BancAndes b = getInstance();
		String login = ADMIN_USER;
		String clave = ADMIN_CLAVE;
		
		
		/*
		 * AGREGAR UN EMPLEADO
		 *

		try {
			String mensaje = b.loginUsuario(login, clave);
			System.out.println(mensaje);
			boolean resp = b.agregarEmpleado(ROL_EMPLEADO_GERENTE_OFICINA, "Pepe2", "pepeempl2", "123456", ROL_EMPLEADO, Usuario.CEDULA, "1233", "colombiana", "direccion1","pepe2@bancandes.edu.co" , "Bogotá", "12345679", "110111", "Cundinamarca");

			System.out.println(resp);

			System.out.println("se hizo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 */

		/*
		 * Agregar una Oficina
		 *
		try {
			String mensaje = b.loginUsuario(login, clave);
			b.agregarOficina("Miboo", "46 Alpine Way", "123456", "993");
			System.out.println(mensaje);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		 */ 
		
		/*
		 * Agregar Cliente
		
		login = "pepeempl2";
		clave = "123456";
		try {
			String mensaje = b.loginUsuario(login, clave);
			b.agregarCliente("Luisa", "Lu", "1234", ROL_CLIENTE, Cliente.CEDULA, "10182531", "Colombiana", "Calle 119", "lu@gmail.com", "Bogota", "3007474746", "110111", "Cundinamarca", Cliente.NATURAL, "993");
			System.out.println(mensaje);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		*/
		
		/*
		 * Registrar punto de atencion
		 * 
		 *
		login = "aarmstrong0";
		clave = "nMEY0T";
		try {
			String mensaje = b.loginUsuario(login, clave);
			b.RF3RegistrarPuntoAtencion("Puesto atencion", "asdc", "33", "283");
			System.out.println(mensaje);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		*/
		
		/*
		 * Registrar Cuenta
		 * 
		 */	
		login = "pepeempl2";
		clave = "123456";
		try {
			String mensaje = b.loginUsuario(login, clave);
			b.registroCuenta("CDT", "994", "993", "10000");
			System.out.println(mensaje);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		/*
		 * Registrar Prestamo
		 * 
		 *
		login = "pepeempl2";
		clave = "123456";
		try {
			String mensaje = b.loginUsuario(login, clave);
			int monto = 50000;
			int interes = 21;
			int numCuotas = 25;
			int dia = 15;
			int valorCuota = 15000;
			int idGere = 105;
			int idCliente = 853;
			b.registrarPrestamo(monto, interes , numCuotas, dia, valorCuota, Prestamo.VIVIENDA, idGere, idCliente);
			System.out.println(mensaje);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		*/
		
		
		
	
		
		
	}
}
