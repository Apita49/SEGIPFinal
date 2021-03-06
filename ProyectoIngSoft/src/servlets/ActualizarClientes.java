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
 * Servlet implementation class ActualizarClientes
 */
@WebServlet("/Clientes_Actualizar")
public class ActualizarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarClientes() {
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
				.processFile("C:\\Paulo\\ProyectoIngSoft\\resources\\actualizar.php");
		writer.print(text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String foto = request.getParameter("upfile");
		String user = request.getParameter("nombre");
		String id = request.getParameter("id");
		String profesion = request.getParameter("profesion");
		String fecha = request.getParameter("fecha");
		String estadoCivil = request.getParameter("estado");
		String direccion = request.getParameter("direccion");
		if (foto != null && user != null && id != null && profesion != null
				&& fecha != null && estadoCivil != null && direccion != null) {
			InteraccionBase db = InteraccionBase.getInstance();
			db.actualizarCliente(id, user, fecha, profesion, estadoCivil,
					direccion, foto);
		}
		response.sendRedirect("http://localhost:8081/ProyectoIngSoft/Clientes_Actualizar");
	}

}
