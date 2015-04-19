package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import database.DbConnection;


public class ModelRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModelRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		JSONArray restaurantList = new JSONArray();
		JSONObject restaurant = new JSONObject();
		DbConnection DB = new DbConnection();
		
		restaurant.put("name", "Xam Buffet");
		restaurant.put("ID", "15781345");
		restaurant.put("location", "515 S Aiken Ave");
		restaurant.put("rating", new Integer(3));
		
		restaurantList.put(restaurant);
		restaurantList.put(restaurant);
		restaurantList.put(restaurant);
		
		  
		result.put("restaurantList", restaurantList);
		result.put("Querry", "time");
		
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(result);
		//out.flush();
  
		/*
		// Set response content type
		response.setContentType("text/html");
		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<html>");	//change Log
		out.println("<h1 align=\"center\">" + "Hello world!" + "</h1></html>");
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
