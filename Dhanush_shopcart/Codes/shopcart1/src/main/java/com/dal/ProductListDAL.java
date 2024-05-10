package com.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.ConnectionClass;
import com.models.Product;

public class ProductListDAL implements ProductsDAO {
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT * FROM SC_Products";
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// Iterate over the result set and create Product objects
			while (rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setCategoryId(rs.getInt("product_cat_id"));
				product.setProductId(rs.getInt("product_id"));
				product.setPrice(rs.getDouble("product_price"));
				product.setHsnCode(rs.getString("hsn_code"));
				product.setImageUrl(rs.getString("image_url"));
				product.setGst(rs.getString("gst"));
				productList.add(product);
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return productList;
	}

	public List<Product> getProductsByPage(int page, int pageSize, int offset) {
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT * FROM SC_Products LIMIT " + pageSize + " OFFSET " + offset;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// Iterate over the result set and create Product objects
			while (rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setCategoryId(rs.getInt("product_cat_id"));
				product.setProductId(rs.getInt("product_id"));
				product.setPrice(rs.getDouble("product_price"));
				product.setHsnCode(rs.getString("hsn_code"));
				product.setImageUrl(rs.getString("image_url"));
				productList.add(product);
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return productList;
	}
}
