package database;

public class Order {
    @Override
	public String toString() {
		return "Order [restaurant_id=" + restaurant_id + ", dish_id=" + dish_id
				+ ", user_id=" + user_id + ", deliveryman_id=" + deliveryman_id
				+ "]";
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDeliveryman_id() {
		return deliveryman_id;
	}
	public void setDeliveryman_id(int deliveryman_id) {
		this.deliveryman_id = deliveryman_id;
	}
	public Order(int restaurant_id, int dish_id, int user_id, int deliveryman_id) {
		super();
		this.restaurant_id = restaurant_id;
		this.dish_id = dish_id;
		this.user_id = user_id;
		this.deliveryman_id = deliveryman_id;
	}
	private int restaurant_id;
    private int dish_id;
    private int user_id;
    private int deliveryman_id;

    
}
