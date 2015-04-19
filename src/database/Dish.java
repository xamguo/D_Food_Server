package database;

import java.sql.Blob;


public class Dish {

	private String name;
	private double price;
    private int restaurant_id;
    private Blob image;
    private String comment;

	public Dish(String name, double price, int restaurant_id, Blob image,
			String comment) {
		super();
		this.name = name;
		this.price = price;
		this.restaurant_id = restaurant_id;
		this.image = image;
		this.comment = comment;
	}
	public Dish(String name, double price, Blob image,
			String comment) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", price=" + price + ", restaurant_id="
				+ restaurant_id + ", image=" + image + ", comment=" + comment
				+ "]";
	}
}
