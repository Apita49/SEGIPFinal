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
 * Servlet implementation class RegistroCI
 */
@WebServlet("/CI_Registro")
public class RegistroCI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroCI() {
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
				.processFile("C:\\Paulo\\ProyectoIngSoft\\resources\\registroCI.php");
		writer.print(text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("nombre");
		String ciudad = request.getParameter("profesion");
		String fechaVenc = request.getParameter("fecha");
		String serie = request.getParameter("estado");
		String seccion = request.getParameter("direccion");
		if (id != null && ciudad != null && fechaVenc != null
				&& seccion != null && serie != null) {
			InteraccionBase db = InteraccionBase.getInstance();
			db.registrarCI(id, ciudad, fechaVenc, serie, seccion);
		}
		response.sendRedirect("http://localhost:8081/ProyectoIngSoft/CI_Registro");
	}

}
