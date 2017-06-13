package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.InteraccionBase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		String password = request.getParameter("pswrd");
		InteraccionBase ib = InteraccionBase.getInstance();
		Boolean checkUser = false;
		checkUser = ib.comprobarLogin(user, new Integer(password));
		if (checkUser) {
			Boolean checkAdmin = false;
			checkAdmin = ib.comprobarAdmin(new Integer(password));
			if (checkAdmin) {
				response.sendRedirect("http://localhost:8081/ProyectoIngSoft/Empleados");
			} else {
				response.sendRedirect("http://localhost:8081/ProyectoIngSoft/Empleado_Opciones");
			}
		} else {
			response.sendRedirect("http://localhost:8081/ProyectoIngSoft/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
