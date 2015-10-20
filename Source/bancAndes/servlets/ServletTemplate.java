
package bancAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bancAndes.fachado.BancAndes;



/**
 * Clase abstacta que implementa un Servlet.
 */
public abstract class ServletTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * constructor de la clase. Llama al constructor de 
	 * su padre.
	 */
	public ServletTemplate() {
		super();

	}

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los metodos get. Invoca el metodo procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepcion de error al escribir la respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		procesarPedido(request, response);
	}

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los mï¿½todos post. Invoca el mï¿½todo procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepciï¿½n de error al escribir la respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		procesarPedido(request, response);
	}

	/**
	 * Procesa el pedido de igual manera para todos
	 * @param request Pedido del cliente
	 * @param response Respuesta del servlet
	 * @throws IOException Excepciï¿½n de error al escribir la respuesta
	 */

	private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		//TODO Si hay otras fachadas, ellas tambien deben inicializar la ruta.

		BancAndes.getInstance().inicializarRuta("C:/Users/sebastian/Documents/6to Semestre"
				+ "/Sistrans/jboss-as-7.1.1.Final/server/default/data/conexion.properties");
		//
		// Comienza con el Header del template
		imprimirHeader( request, response );
		//
		// Escribe el contenido de la pï¿½gina
		escribirContenido( request, response );
		//
		// Termina con el footer del template
		imprimirFooter( response );

	}

	/**
	 * Escribe la cabecera de la pï¿½gina web
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepciï¿½n de error al recibir la respuesta
	 */
	private void imprimirHeader(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//
		// Saca el printer de la repuesta
		PrintWriter out = response.getWriter( );
		//
		// Imprime el header
		out.println("	<head>");
		out.println("		<title>Prodandes</title>");
		out.println("		<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
		out.println("		<meta name=\"description\" content=\"\" />");
		out.println("		<meta name=\"keywords\" content=\"\" />");
		out.println("		<!--[if lte IE 8]><script src=\"css/ie/html5shiv.js\"></script><![endif]-->");
		out.println("		<script src=\"js/jquery.min.js\"></script>");
		out.println("		<script src=\"js/jquery.dropotron.min.js\"></script>");
		out.println("		<script src=\"js/skel.min.js\"></script>");
		out.println("		<script src=\"js/skel-layers.min.js\"></script>");
		out.println("		<script src=\"js/init.js\"></script>");
		out.println("		<noscript>");
		out.println("			<link rel=\"stylesheet\" href=\"css/skel.css\" />");
		out.println("			<link rel=\"stylesheet\" href=\"css/style.css\" />");
		out.println("			<link rel=\"stylesheet\" href=\"css/style-desktop.css\" />");
		out.println("		</noscript>");
		out.println("		<!--[if lte IE 8]><link rel=\"stylesheet\" href=\"css/ie/v8.css\" /><![endif]-->");
		out.println("	</head>");
		out.println("	<body class=\"homepage\">");
		out.println("");
		out.println("		<!-- Header -->");
		out.println("			<div id=\"header-wrapper\">");
		out.println("				<div id=\"header\">");
		out.println("					");
		out.println("					<!-- Logo -->");
		out.println("						<h1>BancAndes</h1>");
		out.println("					");
		out.println("					<!-- Nav -->");
		out.println("						<nav id=\"nav\">");
		out.println("							<ul>");
		out.println("								<li>");
		out.println("									<a href=\"\">Consultar</a>");
		out.println("									<ul>");
		out.println("										<li><a href=\"\">Clientes</a></li>");
		out.println("									</ul>");
		out.println("								</li>");
		out.println("								<li><a href=\"\">Cuenta</a>");
		out.println("									<ul>");
		out.println("                                        <li><a href=\"\">Ver cuentas</a></li>");
		out.println("										<li><a href=\"RF5.htm\">Cerrar Cuenta</a></li>");
		out.println("                                        <li><a href=\"RF6.htm\">Operaciones</a></li>");
		out.println("									</ul>");
		out.println("								</li>");
		out.println("								<li><a href=\"\">Préstamo</a>");
		out.println("									<ul>");
		out.println("										<li><a href=\"\">Ver préstamos</a></li>");
		out.println("                                        <li><a href=\"RF9.htm\">Cerrar Préstamo</a></li>");
		out.println("                                        <li><a href=\"RF8.htm\">Operaciones</a></li>");
		out.println("									</ul>");
		out.println("								</li>");
		out.println("								<li><a href=\"\">Ingresar</a></li>");
		out.println("							</ul>");
		out.println("						</nav>");
	}

	private void imprimirFooter(HttpServletResponse response) throws IOException
	{

		// Saca el writer de la respuesta
		PrintWriter out = response.getWriter( );
		out.println("	</body>");
		out.println("</html>");
	}

	/**
	 * Imprime un mensaje de error
	 * @param respuesta Respuesta al cliente
	 * @param titulo Tï¿½tulo del error
	 * @param mensaje Mensaje del error
	 */
	protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
	{
		respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
		respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
		respuesta.println( "                      <p>Intente la " );
		respuesta.println( "                      operaciï¿½n nuevamente. Si el problema persiste, contacte " );
		respuesta.println( "                      al administrador del sistema.</p>" );
		respuesta.println( "                      <p><a href=\"index.html\">Volver a la pï¿½gina principal</a>" );
	}

	/**
	 * Imprime un mensaje de error
	 * @param respuesta Respuesta al cliente
	 * @param titulo Tï¿½tulo del error
	 * @param exception Excepciï¿½n de error
	 * @param mensaje Mensaje del error
	 */
	protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
	{
		respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
		respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Informaciï¿½n:<br>" );
		exception.printStackTrace( respuesta );
		respuesta.println( "</p>" );
		respuesta.println( "                      <p>Intente la " );
		respuesta.println( "                      operaciï¿½n nuevamente. Si el problema persiste, contacte " );
		respuesta.println( "                      al administrador del sistema.</p>" );
		respuesta.println( "                      <p><a href=\"index.htm\">Volver a la pï¿½gina principal</a>" );
	}

	/**
	 * Imprime un mensaje de ï¿½xito
	 * @param respuesta Respuesta al cliente
	 * @param titulo Tï¿½tulo del mensaje
	 * @param mensaje Contenido del mensaje
	 */
	protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
	{
		respuesta.println( "                      <p class=\"ok\"><b>Operaciï¿½n exitosa:<br>" );
		respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
		respuesta.println( "                      <p><a href=\"index.htm\">Volver a la pï¿½gina principal</a>" );
	}


	// -----------------------------------------------------------------
	// Mï¿½todos Abstractos
	// -----------------------------------------------------------------
	/**
	 * Escribe el contenido de la pï¿½gina
	 * @param request Pedido del cliente
	 * @param response Respuesta
	 * @throws IOException Excepciï¿½n de error al escribir la respuesta
	 */
	public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}
