package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.ProductListDAL;
import com.dal.ProductsDAO;
import com.models.Product;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductsDAO pdao = new ProductListDAL();
		List<Product> products = pdao.getProducts();

		request.setAttribute("products", products);
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response);

	}

}
