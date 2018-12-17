package it.unisa.ofvd.control;

import java.io.IOException;
import javax.servlet.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.ofvd.model.AccountsModel;
import it.unisa.ofvd.model.dao.AccountsDao;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String role = request.getParameter("role");
			String redirectedPage = "/login.jsp";
			try {
				AccountsDao accountDao = new AccountsDao();
				
				AccountsModel account = accountDao.login(email, password);

				if(account != null) {
					request.getSession().setAttribute("accountRole", account);	
					redirectedPage = "/dataset.jsp";				
				}
				
			} catch (Exception e) {
				request.getSession().removeAttribute("account"); 
				request.setAttribute("error", e.getMessage());
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectedPage);
			dispatcher.forward(request, response);			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
