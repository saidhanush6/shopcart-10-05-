package com.models;

public class Product {

	private String productName;
	private int categoryId;
	private int productId;
	private double price;
	private String hsnCode;
	private String imageUrl;
	private String pincode;
	public String gst;

	public Product(String productName, int categoryId, int productId, double price, String hsnCode, String imageUrl,
			String pincode, String gst) {
		super();
		this.productName = productName;
		this.categoryId = categoryId;
		this.productId = productId;
		this.price = price;
		this.hsnCode = hsnCode;
		this.imageUrl = imageUrl;
		this.pincode = pincode;
		this.gst = gst;
	}

	public Product() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

}
