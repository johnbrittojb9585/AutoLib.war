<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.QBSearch.QuestionSearchBean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
 

<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body >

<%

ArrayList sc=new ArrayList();

sc=(ArrayList)request.getAttribute("SearchArrylist");

Iterator it=sc.iterator();
%>




  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>


    <h2>Full View</h2>

    
    <%
    while(it.hasNext()){
    	QuestionSearchBean view=(QuestionSearchBean) it.next();
    
    %>
   
    <table align="center">
            <tr>
              <td Class="addedit" align="left">QBCode</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getQcode()%>     </td>
            </tr>           
            
           <tr>
              <td Class="addedit" align="left">Date of Entry</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getQdate()%></td>
            </tr>
           
            <tr>            
              <td Class="addedit" align="left">University</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getUniname()%></td>
            </tr>
           
         
            
            <tr>            
              <td Class="addedit" align="left">Subject Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSname()%></td>
            </tr>
            
            <tr>            
              <td Class="addedit" align="left">Dept Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDname()%></td>
            </tr>
            
            <tr>            
              <td Class="addedit" align="left">Course</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getQcourse()%></td>
            </tr>
            
            <tr>            
              <td Class="addedit" align="left">Year</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getQyear()%></td>
            </tr>
            
            <tr>            
              <td Class="addedit" align="left">Month</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getQmonth()%></td>
            </tr>
              <tr>            
              <td Class="addedit" align="left">Semester</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSemester()%></td>
            </tr>
            
<%
            String cont=view.getContents();
            if((!cont.equals(""))&&(!cont.equals(null))){
            %>
			<tr>
           </tr>
			<tr>
            <td Class="addedit" align="left">Content</td>
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/QUESTION BANK/<%=view.getContents()%> " target=_base>Click to Contents</a></td>            
            </tr>
            	
            <%
            }
                        
}		
			sc.clear();

			%>

</table>

    </form>
    
</body>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	location.href="./simpleSearch.jsp";
}
</script>

</html>






