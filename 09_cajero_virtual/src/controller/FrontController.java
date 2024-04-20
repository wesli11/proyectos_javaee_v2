package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="";
		String option=request.getParameter("option");
		switch(option) {
			case "doListaClientes":
				request.getRequestDispatcher("ListaClientesAction").include(request, response);
				url="seleccioncliente.jsp";
				break;
			case "doListaCuentas":
				request.getRequestDispatcher("ListaCuentasClienteAction").include(request, response);
				url="seleccioncuenta.jsp";
				break;
			case "doRegistrarCuenta":
				request.getRequestDispatcher("RegistrarCuentaAction").include(request, response);
				url="menu.html";
				break;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
