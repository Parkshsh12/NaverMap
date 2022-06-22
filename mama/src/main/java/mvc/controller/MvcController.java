package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naver.NaverMap;

public class MvcController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		if (command.equals("/search.do")) {
			getjapyo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
		
	}
	public void getjapyo(HttpServletRequest request) {
		String juso = request.getParameter("juso");
		NaverMap nm = new NaverMap();
		double[] abc = nm.getCoordinate(juso);
		double x = abc[0];
		double y = abc[1];
		request.setAttribute("juso1", x);
		request.setAttribute("juso2", y);
	}
}
