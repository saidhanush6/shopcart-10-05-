package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.ProductListDAL;
import com.models.Product;

@WebServlet("/fetchProductsByPage")
public class FetchProductsByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int offset = (page - 1) * pageSize;

		ProductListDAL pdal = new ProductListDAL();

		// Logic to fetch products for the specified page with pagination
		List<Product> products = pdal.getProductsByPage(page, pageSize, offset);

		// Convert products to HTML
		StringBuilder productsHtml = new StringBuilder();
		for (Product product : products) {
			productsHtml.append("<div class='product'>");
			productsHtml.append("<img src='" + product.getImageUrl() + "' alt='Product Image'>");
			productsHtml.append("<div class='product-details'>");
			productsHtml.append("<h3>" + product.getProductName() + "</h3>");
			productsHtml.append("<p>$" + product.getPrice() + "</p>");
			productsHtml.append(
					"<button class='add-to-cart-btn' onclick='addItemToCart(\"" + product.getProductId() + "\", \""
							+ product.getProductName() + "\", \"" + product.getPrice() + "\")'>Add to Cart</button>");
			productsHtml.append("</div></div>");
		}

		// Send products HTML as the response
		response.setContentType("text/html");
		response.getWriter().write(productsHtml.toString());
	}

}
