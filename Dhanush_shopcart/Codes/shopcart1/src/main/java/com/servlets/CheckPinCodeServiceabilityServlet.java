package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkPinCodeServiceability")
public class CheckPinCodeServiceabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Extract product ID and pin code from the request parameters
		String productId = request.getParameter("productId");
		String pincode = request.getParameter("pincode");

		// Perform the pin code serviceability check
		boolean isServiceable = checkServiceability(productId, pincode);

		// Send the response back to the client
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(isServiceable ? "true" : "false");
		out.flush();
	}

	private boolean checkServiceability(String productId, String pincode) {
		if (pincode.equals("530045")) {
			return true;
		} else {
			return false;
		}
	}

}
