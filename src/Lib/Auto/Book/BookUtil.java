package Lib.Auto.Book;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;



public class BookUtil {
	
	public static int scode(HttpServletRequest request,Connection con,PreparedStatement Prest,ResultSet rs,ResultSet rs1){
		int sub=0; 
		try{
		

            if(request.getParameter("Sub").equals("1") && request.getParameter("subName").equalsIgnoreCase("Nil")){

           	 sub=Integer.parseInt(request.getParameter("Sub"));
             }
            else{

           	     sub=Integer.parseInt(request.getParameter("Sub"));
		        	String sub_find="select sub_code from subject_mas where sub_name=?";
		        	Prest=con.prepareStatement(sub_find);
		        	Prest.setString(1,request.getParameter("subName"));
		        	rs1=Prest.executeQuery();
		        	if(rs1.next()){
		        	sub=rs1.getInt("sub_code");	
		        	}
		        	else{

		        		String sub_max="select max(sub_code)+1 as sub_max  from subject_mas";
		        		Prest=con.prepareStatement(sub_max);
		        		rs=Prest.executeQuery();
		        		if(rs.next()){
		        			sub=rs.getInt("sub_max");

		        			String sub_new="insert into subject_mas(Sub_code,Sub_name) values(?,?)";
		        	    Prest=con.prepareStatement(sub_new);
		        	    Prest.setInt(1,sub);
		        	    Prest.setString(2,request.getParameter("subName"));

		        	    Prest.executeUpdate();
		        			
		        		}
		        	}
		        	   
            }
          
             
		} catch(Exception e){
			
		}
		return sub;
			
	}
	public static int brcode(HttpServletRequest request,Connection con,PreparedStatement Prest,ResultSet rs,ResultSet rs1){
		int branch=0; 
		try{
		

            if(request.getParameter("Branch").equals("1") && request.getParameter("branchName").equalsIgnoreCase("Nil")){

            	branch=Integer.parseInt(request.getParameter("Branch"));
             }
            else{

            	branch=Integer.parseInt(request.getParameter("Branch"));
		        	String branch_find="select branch_code from branch_mas where branch_name=?";
		        	Prest=con.prepareStatement(branch_find);
		        	Prest.setString(1,request.getParameter("branchName"));
		        	rs1=Prest.executeQuery();
		        	if(rs1.next()){
		        		branch=rs1.getInt("branch_code");	
		        	}
		        	else{

		        		String branch_max="select max(branch_code)+1 as branch_max  from branch_mas";
		        		Prest=con.prepareStatement(branch_max);
		        		rs=Prest.executeQuery();
		        		if(rs.next()){
		        			branch=rs.getInt("branch_max");

		        			String branch_new="insert into branch_mas(branch_code,branch_name) values(?,?)";
		        	    Prest=con.prepareStatement(branch_new);
		        	    Prest.setInt(1,branch);
		        	    Prest.setString(2,request.getParameter("branchName"));

		        	    Prest.executeUpdate();
		        			
		        		}
		        	}
		        	   
            }
          
             
		} catch(Exception e){
			
		}
		return branch;
			
	}
	
	public static int dcode(HttpServletRequest request,Connection con,PreparedStatement Prest,ResultSet rs,ResultSet rs1){
		int dept=0; 
		try{
		

            if(request.getParameter("Dept").equals("1") && request.getParameter("deptName").equalsIgnoreCase("Nil")){

            	dept=Integer.parseInt(request.getParameter("Dept"));
             }
            else{

            	dept=Integer.parseInt(request.getParameter("Dept"));
		        	String dept_find="select dept_code from department_mas where dept_name=?";
		        	Prest=con.prepareStatement(dept_find);
		        	Prest.setString(1,request.getParameter("deptName"));
		        	rs1=Prest.executeQuery();
		        	if(rs1.next()){
		        		dept=rs1.getInt("dept_code");	
		        	}
		        	else{

		        		String dept_max="select max(dept_code)+1 as dept_max  from department_mas";
		        		Prest=con.prepareStatement(dept_max);
		        		rs=Prest.executeQuery();
		        		if(rs.next()){
		        			dept=rs.getInt("dept_max");

		        		String dept_new="insert into department_mas(dept_code,dept_name,short_desc) values(?,?,?)";
		        	    Prest=con.prepareStatement(dept_new);
		        	    Prest.setInt(1,dept);
		        	    Prest.setString(2,request.getParameter("deptName"));
		        	    Prest.setString(3,"");
		        	   
		        	    Prest.executeUpdate();
		        			
		        		}
		        	}
		        	   
            }
          
             
		} catch(Exception e){
			
		}
		return dept;
			
	}
	

	public static int spcode(HttpServletRequest request,Connection con,PreparedStatement Prest,ResultSet rs,ResultSet rs1){
		int sup=0; 
		try{
		

            if(request.getParameter("Sup").equals("2") && request.getParameter("supName").equalsIgnoreCase("Nil")){

            	sup=Integer.parseInt(request.getParameter("Sup"));
             }
            else{

            	sup=Integer.parseInt(request.getParameter("Sup"));
		        	String sup_find="select sp_code from sup_pub_mas where sp_name=?";
		        	Prest=con.prepareStatement(sup_find);
		        	Prest.setString(1,request.getParameter("supName"));
		        	rs1=Prest.executeQuery();
		        	if(rs1.next()){
		        		sup=rs1.getInt("sp_code");	
		        	}
		        	else{

		        		String sp_max="select max(sp_code)+1 as sp_max  from sup_pub_mas";
		        		Prest=con.prepareStatement(sp_max);
		        		rs=Prest.executeQuery();
		        		if(rs.next()){
		        			sup=rs.getInt("sp_max");
		        			String sup_new="insert into sup_pub_mas(sp_code,sp_name,sp_type) values(?,?,?)";
		        	    Prest=con.prepareStatement(sup_new);
		        	    Prest.setInt(1,sup);
		        	    Prest.setString(2,request.getParameter("supName"));
		        	    Prest.setString(3,"SUPPLIER");
		        	    Prest.executeUpdate();
		        			
		        		}
		        	}
		        	   
            }
          
             
		} catch(Exception e){
			
		}
		return sup;
			
	}
	
	public static int sbcode(HttpServletRequest request,Connection con,PreparedStatement Prest,ResultSet rs,ResultSet rs1){
		int pub=0; 
		try{
		

            if(request.getParameter("Pub").equals("1") && request.getParameter("pubName").equalsIgnoreCase("Nil")){
            	pub=Integer.parseInt(request.getParameter("Pub"));
             }
            else{
            	pub=Integer.parseInt(request.getParameter("Pub"));
		        	String pub_find="select sp_code from sup_pub_mas where sp_name=?";
		        	Prest=con.prepareStatement(pub_find);
		        	Prest.setString(1,request.getParameter("pubName"));
		        	rs1=Prest.executeQuery();
		        	if(rs1.next()){
		        		pub=rs1.getInt("sp_code");	
		        	}
		        	else{

		        		String pub_max="select max(sp_code)+1 as pub_max  from sup_pub_mas";
		        		Prest=con.prepareStatement(pub_max);
		        		rs=Prest.executeQuery();
		        		if(rs.next()){
		        			pub=rs.getInt("pub_max");
		        			String pub_new="insert into sup_pub_mas(sp_code,sp_name,sp_type) values(?,?,?)";
		        	    Prest=con.prepareStatement(pub_new);
		        	    Prest.setInt(1,pub);
		        	    Prest.setString(2,request.getParameter("pubName"));
		        	    Prest.setString(3,"PUBLISHER");
		        	    Prest.executeUpdate();
		        			
		        		}
		        	}
		        	   
            }
          
             
		} catch(Exception e){
			
		}
		return pub;
			
	}
	
	 
}
