package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private  static Connection con=null;
	
	
	private DBConnection() {
   
	}
	
	public static synchronized Connection getInstance(){
	
		try {

			if(con==null){
				Class.forName("org.gjt.mm.mysql.Driver");
				System.out.println("b4 creating Connection");
			//	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
			 }
								} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
		
	}
	public void finalize(){
		try{
			if(con!=null){
			con.close();
			con=null;
			}
		}catch(SQLException e){
			
		}
	}
	
	

	/**
	 * @param args
	 */


}
