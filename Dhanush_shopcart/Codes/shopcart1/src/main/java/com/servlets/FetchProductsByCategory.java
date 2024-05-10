package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectionClass;

@WebServlet("/fetchProductsByCategory")
public class FetchProductsByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int selectedCategory = Integer.parseInt(request.getParameter("category"));
		String change = "";
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SC_Products WHERE product_cat_id = ?");
			pstmt.setInt(1, selectedCategory);
			ResultSet rs = pstmt.executeQuery();

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
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(change);
	}

}
