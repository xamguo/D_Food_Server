package database;


public class Driver {
	public static void main(String []args){
		DbConnection db=new DbConnection();
		//db.insertRestaurant("guo",2, 1, 4);
		System.out.println(db.search(1, 1, 5).toString()); 
	}
}
