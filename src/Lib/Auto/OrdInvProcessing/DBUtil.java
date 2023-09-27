package Lib.Auto.OrdInvProcessing;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil implements Serializable {
	
	public static void closeResultSet(ResultSet rs) 
	{
	try{
		if(rs!=null){
			rs.close();
		}
		rs=null;	
	}
	catch(SQLException e){
	e.printStackTrace();	
	}
	}
	public static void closeStatement(Statement st){
		try{
			if(st!=null){
				st.close();
			}
			st=null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void closePreparedStatement(PreparedStatement pstmt){
		
		try{
			if(pstmt!=null){
				pstmt.close();
			}
			pstmt=null;
		}catch(SQLException e){
		e.printStackTrace();	
		}
	}
	
	public static void closeConnection(Connection con){
	try{
		if(con!=null){
			con.close();
		}
		con=null;
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	}
	
}
