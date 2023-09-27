package Lib.Auto.Report;
import java.io.Serializable;
import Common.Security;

//public class ReportFormat implements Serializable {

	
				
	//public  static void ReportFormat(){
		
		/*if(report_type.equals("listing"))
				{
				strsqlq = "select * from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' order by  "+order;
			out.print(strsqlq);
			//rs=st.executeQuery(strsqlq);
			pstmt=con.prepareStatement(strsqlq);
			rs=pstmt.executeQuery();
			if(!rs.next())
			{
			out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
			//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

			}
			else
			{
			rs.beforeFirst();
			sno=1;
			out.print("<center><font size=5>ISSUE REPORT</center></font>");
			out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
			out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
			if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
			if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
			if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
			if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
			if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
			if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
			//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
			out.print("</table>");
			out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
			out.print("<tr><th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th></tr>");

			while(rs.next())
			{
				out.print("<tr><td><font size=2>"+sno+"</td>");
				out.print("<td>"+rs.getString("Member_Code")+"</td>");
				out.print("<td>"+rs.getString("Access_No")+"</td>");
				out.print("<td>"+rs.getString("Title")+"</td>");
				out.print("<td>"+getdate(rs.getString("Issue_Date"))+"</td>");
				out.print("<td>"+getdate(rs.getString("Due_Date"))+"</td>");
				out.print("<td>"+rs.getString("dept_name")+"</td>");
				out.print("<td>"+rs.getString("group_name")+"</td>");
				out.print("<td>"+rs.getString("course_name")+"</td>");
				out.print("</tr>");
				sno=sno+1;
			}
					out.print("</table>");
			}
			}
			if(report_type.equals("breakup"))
			{
			total=0;
			strsqlq="select count(*) as count_book, issue_date from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' group by issue_date order by issue_date";
			out.print(strsqlq);
			//rs=st.executeQuery(strsqlq);
			pstmt=con.prepareStatement(strsqlq);
			rs=pstmt.executeQuery();
			if(!rs.next())
			{
			out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
			//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

			}
			else
			{
			rs.beforeFirst();
			sno=1;
			out.print("<center><font size=5>ISSUE REPORT</center></font>");
			out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
			out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
			out.print("<tr><th>Sno</th><th>IssueDate</th><th>No Of Books</th></tr>");

			while(rs.next())
			{
		
					 out.print("<tr><td><font size=2>"+sno+"</td>");
					 out.print("<td>"+getdate(rs.getString("Issue_Date"))+"</td>");
					 out.print("<td>"+rs.getInt("count_book")+"</td>");
					 out.print("</tr>");
					 sno=sno+1;
			

		}
		 rs.beforeFirst();
		 while(rs.next())
		{
		
		
					int bookcount=rs.getInt("count_book");
					sno=sno+1;
					total=total+bookcount;
	*/
		//}
		/*out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");
*//*
		}

		}



			if(report_type.equals("cumulative"))
			{
			total=0;
			strsqlq="select count(*) as count_book, issue_date from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' group by issue_date order by issue_date";
			out.print(strsqlq);
			//rs=st.executeQuery(strsqlq);
			pstmt=con.prepareStatement(strsqlq);
			rs=pstmt.executeQuery();
			if(!rs.next())
			{
			out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
			//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

			}
			else
			{
			rs.beforeFirst();
			sno=1;
			out.print("<center><font size=5>ISSUE REPORT</center></font>");
			out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
			out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


			while(rs.next())
			{
		
				int bookcount=rs.getInt("count_book");
							   sno=sno+1;
							   total=total+bookcount;
		}
				 out.print("</table>");
				out.print("<br>");
			out.print("<br>");
			out.print("<br>");
			out.print("<font face=times new roman size=4><center>No Of "+txtdoctype+":  "+total+"</center></font>");

			}

			}

		
		
		
		
		
		
				}
}
*/