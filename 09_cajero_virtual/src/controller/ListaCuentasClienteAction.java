package controller;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CuentasService;

/**
 * Servlet implementation class ListaClientesAction
 */
@WebServlet("/ListaCuentasClienteAction")
public class ListaCuentasClienteAction extends HttpServlet {
	@Autowired
	CuentasService service;
	@Override
	public void init(ServletConfig config) throws ServletException {
		//le informa al servidor de aplicaciones que Spring va a realizar inyecci�n
		//de objetos en este servlet
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		super.init(config);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dni=Integer.parseInt(request.getParameter("dni"));
		request.getSession().setAttribute("dni", dni);
		request.setAttribute("cuentas", service.obtenerCuentasNoCliente(dni));
	}

}
