package bancAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bancAndes.fachado.BancAndes;
import bancAndes.vos.OperacionCuenta;


public class RegistrarPrestamo extends ServletTemplate {
	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Escribe el contenido de la pagina
	 * @param request Pedido del cliente
	 * @param response Respuesta
	 * @throws IOException Excepcion de error al escribir la respuesta
	 */
	public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		String h=request.getParameter("trabajo");
		PrintWriter out = response.getWriter( );
		if(h==null){

			out.println("				<section id=\"intro\" class=\"container\">");
			out.println("					<div class=\"panel-body\">");
			out.println("					<div class=\"row\">");
			out.println("                <div class=\"col-lg-12\">");
			out.println("                    <div class=\"panel panel-default\">");
			out.println("                            <div class=\"row\">");
			out.println("                                <div class=\"col-lg-6\">");
			out.println("                                    <form role=\"form\">		");
			out.println("										<div class=\"form-group\" >");
			out.println("                                            <label>Accion:</label>");
			out.println("                                            	<select multiple class=\"form-control\" name=\"accion\" style=\"width: 250px;\">");
			out.println("<option> Consignar </option>");
			out.println("<option> Retirar </option>");
			out.println("                                            	</select>");
			out.println("           								</div>");
			out.println("                						<div class=\"form-group\">");
			out.println("                                            <label>Numero prestamo:</label>");
			out.println("                                            <input type=\"number\" name=\"id\" class=\"form-control\" required></input>");
			out.println("                						</div>");
			out.println("                						<div class=\"form-group\">");
			out.println("                                            <label>Monto:</label>");
			out.println("                                            <input type=\"number\" name=\"monto\" class=\"form-control\" required></input>");
			out.println("                						</div>");
			out.println("                                    <form>");
			out.println("    	                               <input type=\"hidden\" name=\"trabajo\" value=\"termino\"/>");
			out.println("                                        <button onclick=\"RF14.htm\">Enviar</button>");
			out.println("                                    </form>");
			out.println("                					</form>");
			out.println("                         		</div>");
			out.println("               				</div>");
			out.println("               			</div>");
			out.println("               		</div>");
			out.println("               		</div>");
			out.println("               		</div>");
			out.println("           		</section>");
		}
		else
		{
			
			String accion = request.getParameter("accion");
			String id = request.getParameter("id");
			int monto = Integer.valueOf(request.getParameter("monto"));
			ArrayList<OperacionCuenta> r=BancAndes.getInstance().registrarOperacionCuenta(id,accion,monto);
			out.println("				<section id=\"intro\" class=\"container\">");
			out.println("					<div class=\"panel-body\">");
			out.println("					<div class=\"row\">");
			out.println("                <div class=\"col-lg-12\">");
			out.println("                    <div class=\"panel panel-default\">");
			out.println("                            <div class=\"row\">");
			out.println("                                <div class=\"col-lg-6\">");
			out.println("                                    <form role=\"form\">		");
			out.println("                                    <table class=\"table table-striped\" name=\"myTable\">");
			out.println("				                        <tr>");
			out.println("				                        <tr>");
			out.println("					                        <th>Id</th>");
			out.println("                                            <th>Valor</th>  ");
			out.println("                                            <th>Fecha</th>  ");
			out.println("                                            <th>Hora</th>  ");
			out.println("                                            <th>Id cuenta</th>  ");
			out.println("                                            <th>Id punto</th>  ");
			out.println("				                        </tr>");

			for(int i=0;i<r.size();i++)
			{
				out.println("				                        <tr>");
				out.println("					                        <th>"+r.get(i).getIdentificador()+"</th>");
				out.println("                                            <th>"+r.get(i).getValor()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getFecha()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getHora()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getIdCuenta()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getIdPunto()+"</th>  ");
				out.println("				                        </tr>");
			}
			out.println("                                    </table>");
			out.println("                         		</div>");
			out.println("               				</div>");
			out.println("               			</div>");
			out.println("               		</div>");
			out.println("               		</div>");
			out.println("               		</div>");
			out.println("           		</section>");
		}
	}
}

