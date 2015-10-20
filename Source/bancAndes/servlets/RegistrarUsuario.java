package bancAndes.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bancAndes.fachado.BancAndes;
import bancAndes.vos.Cuenta;


public class RegistrarUsuario extends ServletTemplate {
	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;


	public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		String h=request.getParameter("trabajo");
		PrintWriter out = response.getWriter( );
		if(h==null)
		{
			out.println("				<section id=\"intro\" class=\"container\">");
			out.println("					<div class=\"panel-body\">");
			out.println("					<div class=\"row\">");
			out.println("                <div class=\"col-lg-12\">");
			out.println("                    <div class=\"panel panel-default\">");
			out.println("                            <div class=\"row\">");
			out.println("                                <div class=\"col-lg-6\">");
			out.println("                                    <form role=\"form\">		");
			out.println("										<div class=\"form-group\" >");
			out.println("                                            <label>Cancelar una cuenta</label>");
			out.println("           								</div>");
			out.println("                						<div class=\"form-group\">");
			out.println("                                            <label>Cuenta:</label>");
			out.println("                                            <input type=\"number\" name=\"id\" class=\"form-control\" required></input>");
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
			int id = Integer.valueOf(request.getParameter("id"));
			ArrayList<Cuenta> r=BancAndes.getInstance().cerrarCuenta(id);
			out.println("				<section id=\"intro\" class=\"container\">");
			out.println("					<div class=\"panel-body\">");
			out.println("					<div class=\"row\">");
			out.println("                <div class=\"col-lg-12\">");
			out.println("                    <div class=\"panel panel-default\">");
			out.println("                            <div class=\"row\">");
			out.println("                                <div class=\"col-lg-6\">");
			out.println("                                    <form role=\"form\">		");
			out.println("                                            <label>Cuenta cancelada</label>");
			out.println("                                    <table class=\"table table-striped\" name=\"myTable\">");
			out.println("				                        <tr>");
			out.println("				                        <tr>");
			out.println("					                        <th>Numero cuenta</th>");
			out.println("                                            <th>Estado</th>  ");
			out.println("                                            <th>Tipo</th>  ");
			out.println("                                            <th>Saldo</th>  ");
			out.println("                                            <th>Id oficina</th>  ");
			out.println("				                        </tr>");

			for(int i=0;i<r.size();i++)
			{
				out.println("				                        <tr>");
				out.println("					                        <th>"+r.get(i).getNumeroCuenta()+"</th>");
				out.println("                                            <th>"+r.get(i).getEstado()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getTipo()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getSaldo()+"</th>  ");
				out.println("                                            <th>"+r.get(i).getIdOficina()+"</th>  ");
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
