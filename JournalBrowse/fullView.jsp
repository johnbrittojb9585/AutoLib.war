<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.JournalBrowse.JournalSearchbean" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


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

sc=(ArrayList)request.getAttribute("JnlIssueArrylist");

Iterator it=sc.iterator();
%>




<form method="get" name="query" action="./JournalSearch"  ONSUBMIT="return validate(this)">
<br><br>

 <center>
<h2 >Full View</h2>
</center>
    
    <%
    while(it.hasNext()){
    	JournalSearchbean view=(JournalSearchbean) it.next();    
    %>
   <table align="center">
            <tr>
              <td Class="addedit" align="left">Access No</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getJnlIssueAccNo()%>     </td>
            </tr>
           
            <tr>
           <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Journal Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getJnlName()%>
              </td>
            </tr>
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Frequency</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getFrequency()%>  </td>
            </tr>
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Country</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getCountry()%></td>
	         </tr>
            <tr>
           </tr>
            <tr>
              <td Class="addedit" align="left">Type</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDocument()%></td>
            </tr>
               <tr>
           </tr>
              
            
            <tr>
              <td Class="addedit" align="left">Year</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getIssueYear()%></td>
            </tr>
            <tr>
           </tr>
            <tr>
              <td Class="addedit" align="left">Month</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getIssueMonth()%></td>
            </tr>
            <tr>
           </tr>
            <tr>
              <td Class="addedit" align="left">Issue No</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getIssueNo()%></td>
            </tr>
            <tr>
           </tr>
            <tr>
              <td Class="addedit" align="left">Volume No</td>
             <td Class="addedit" align="left">:&nbsp;<%=view.getIssueVolume()%></td>
            </tr>
            <tr>
           </tr>
			<tr>
              <td Class="addedit" align="left">Received_Date</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getReceiveDate()%></td>
            </tr>
            <tr>
              <td Class="addedit" align="left">Availability</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getAvailability()%></td>
            </tr>
            
            <%
            String cont=view.getAddField1();
            if((!cont.isEmpty()&& (cont != null))){ 
            %>
			<tr>
           </tr>
			<tr>
              <td Class="addedit" align="left">Content</td>
<!--            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/Contents/Book/<%=view.getAddField1()%> " target=_base>Click to Contents</a></td>-->
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/JOURNAL ISSUES/<%=view.getAddField1()%> " target=_base>Click to Contents</a></td>                                      
            </tr>
            
            <%
            }
                      
}		
			sc.clear();

			%>

</table>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>

 </center>
    </form>

</body>
<script>
function back()
{
	history.go(-1);
}
</script>

</html>






