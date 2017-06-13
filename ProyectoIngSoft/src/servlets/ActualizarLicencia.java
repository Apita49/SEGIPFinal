package servlets;

import interaccionArchivos.FileProcesser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.InteraccionBase;

/**
 * Servlet implementation class ActualizarCI
 */
@WebServlet("/Licencia_Actualizar")
public class ActualizarLicencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarLicencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileProcesser fp = FileProcesser.getInstance();
		PrintWriter writer = response.getWriter();
		String text = fp
				.processFile("C:\\Paulo\\ProyectoIngSoft\\resources\\actualizarLicencia.php");
		writer.print(text);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("nombre");
		String nacionalidad = request.getParameter("profesion");
		String fechaVenc = request.getParameter("fecha");
		String sexo = request.getParameter("estado");
		String fechaEmision = request.getParameter("direccion");
		String grupoSanguineo = request.getParameter("grupo");
		String audifonos = request.getParameter("audifonos");
		String lentes = request.getParameter("lentes");
		if (id != null && nacionalidad != null && fechaVenc != null
				&& sexo != null && fechaEmision != null
				&& grupoSanguineo != null && audifonos != null
				&& lentes != null) {
			InteraccionBase db = InteraccionBase.getInstance();
			db.actualizarLicencia(id, nacionalidad, fechaVenc, sexo,
					fechaEmision, grupoSanguineo, audifonos, lentes);
		}
		response.sendRedirect("http://localhost:8081/ProyectoIngSoft/Licencia_Actualizar");
	}

}
