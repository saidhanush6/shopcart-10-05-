package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectionClass;

/**
 * Servlet implementation class FetchProductsByPrice
 */
@WebServlet("/fetchProductsByPrice")
public class FetchProductsByPrice extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int selectedCategory = Integer.parseInt(request.getParameter("category"));
		int selectedPrice = Integer.parseInt(request.getParameter("sortOrder"));

		String change = "";
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			if (selectedCategory == 0) {
				if (selectedPrice == 0) {
					rs = stmt.executeQuery("SELECT * FROM SC_Products order by product_price asc");
				} else {
					rs = stmt.executeQuery("SELECT * FROM SC_Products order by product_price desc");
				}
			} else {
				if (selectedPrice == 0) {
					rs = stmt.executeQuery("SELECT * FROM SC_Products WHERE product_cat_id = " + selectedCategory
							+ " order by product_price asc");
				} else {
					rs = stmt.executeQuery("SELECT * FROM SC_Products WHERE product_cat_id = " + selectedCategory
							+ " order by product_price desc");
				}
			}
			while (rs.next()) {
				change += "<div class='product'>";
				change += "<img src='" + rs.getString("image_url") + "' alt='" + rs.getString("product_name") + "'>";
				change += "<div class='product-details'>";
				change += "<h3>" + rs.getString("product_name") + "</h3>";
				change += "<p>" + rs.getDouble("product_price") + "</p>";
				change += "<button>Add to Cart</button>";
				change += "</div>";
				change += "</div>";
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(change);
	}

}
