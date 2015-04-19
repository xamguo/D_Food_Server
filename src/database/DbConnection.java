package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class DbConnection {
	private Connection myConnection;
	private PreparedStatement statement;
	private String dbUsername;
	private String dbPassword;

	public DbConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			//String db = "jdbc:mysql://localhost:3306/dfood";
			String db = "jdbc:mysql://localhost:3306/dfood";
			myConnection = DriverManager.getConnection(db, "root", "admin");
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertUser(String username, String password, String address){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String query= "INSERT INTO `dfood`.`users` (`username`,`psswd`,`address`,`reg_date`) VALUES (?,?,?,?);";
		try
		{
			statement = myConnection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, address);
			statement.setString(4, dateFormat.format(date));
			statement.executeUpdate();
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertDeliveryman(String username, String password, String address){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String query= "INSERT INTO `dfood`.`deliverymen` (`username`,`psswd`,`address`,`reg_date`) VALUES (?,?,?,?);";
		try
		{
			statement = myConnection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, address);
			statement.setString(4, dateFormat.format(date));
			statement.executeUpdate();
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertDish(String name, String comment, double price, int restaurant_id, Blob image){
		String query= "INSERT INTO `dfood`.`dishes` (`name`,`comments`,`price`,`restaurant_id`,`image`) VALUES (?,?,?,?,?);";
		try
		{
			statement = myConnection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, comment);
			statement.setDouble(3, price);
			statement.setInt(4, restaurant_id);
			statement.setBlob(5, image);
			statement.executeUpdate();
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertRestaurant(String name, double latitude, double longitude, int rating){
		String query= "INSERT INTO `dfood`.`restaurants` (`name`,`latitude`,`longitude`,`rating`) VALUES (?,?,?,?);";
		try
		{
			statement = myConnection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDouble(2, latitude);
			statement.setDouble(3, longitude);
			statement.setInt(4, rating);
			statement.executeUpdate();
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertOrder(int user_id, int dish_id, int restaurant_id){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String query= "INSERT INTO `dfood`.`orders` (`user_id`,`dish_id`,`restaurant_id`,`reg_date`) VALUES (?,?,?,?);";
		try
		{
			statement = myConnection.prepareStatement(query);
			statement.setInt(1, user_id);
			statement.setInt(2, dish_id);
			statement.setInt(3, restaurant_id);
			statement.setString(4, dateFormat.format(date));
			statement.executeUpdate();
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean checkUser(String Username, String Password)
	{
		boolean res = false;
		try 
		{
			statement = myConnection.prepareStatement("SELECT username, psswd FROM dfood.users WHERE username=? AND psswd=?");
			statement.setString(1, Username);
			statement.setString(2, Password);
			ResultSet myRs =  statement.executeQuery();
			while(myRs.next()){
                dbUsername = myRs.getString("username");
                dbPassword = myRs.getString("psswd");

                if(dbUsername.equals(Username) && dbPassword.equals(Password)){
    				res=true;
                }
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
		return res;

	}
	public boolean checkDeliveryman(String Username, String Password)
	{
		boolean res = false;
		try 
		{
			statement = myConnection.prepareStatement("SELECT username, psswd FROM dfood.deliverymen WHERE username=? AND psswd=?");
			statement.setString(1, Username);
			statement.setString(2, Password);
			ResultSet myRs =  statement.executeQuery();
			while(myRs.next()){
                dbUsername = myRs.getString("username");
                dbPassword = myRs.getString("psswd");

                if(dbUsername.equals(Username) && dbPassword.equals(Password)){
    				res=true;
                }
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
		return res;

	}
	public ArrayList<Restaurant> search(double currentLatitude, double currentLongitude, double range){
		ArrayList<Restaurant> inRange= new ArrayList<Restaurant>();
		Restaurant aRestaurant;
		try 
		{
			statement = myConnection.prepareStatement("SELECT name, latitude, longitude, rating FROM dfood.restaurants");
			
			ResultSet myRs =  statement.executeQuery();
			while(myRs.next()){
                if(Math.abs(myRs.getDouble("latitude")-currentLatitude)<range || Math.abs(myRs.getDouble("longitude")-currentLatitude)<range){
                	aRestaurant=new Restaurant(myRs.getString("name"), myRs.getDouble("latitude"), myRs.getDouble("longitude"), myRs.getInt("rating"));
                	inRange.add(aRestaurant);
                }
			}
			Collections.sort(inRange);
			Collections.reverse(inRange);
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
		return inRange;		
	}
	public ArrayList<Dish> getDishes(Restaurant aRestaurant){
		ArrayList<Dish> dishes= new ArrayList<Dish>();
		Dish aDish;
		try 
		{
			statement = myConnection.prepareStatement("SELECT name, image, price, comments FROM dfood.dishes WHERE restaurant_id = (SELECT restaurant_id FROM dfood.restaurants WHERE name=?)");
			statement.setString(1, aRestaurant.getName());
			
			ResultSet myRs =  statement.executeQuery();
			while(myRs.next()){
                	aDish=new Dish(myRs.getString("name"), myRs.getDouble("price"), myRs.getBlob("image"), myRs.getString("comments"));
                	dishes.add(aDish);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
		return dishes;			
	}
	
}

