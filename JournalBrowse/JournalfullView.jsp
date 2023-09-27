<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.JournalBrowse.JournalSearchbean" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.JournalBrowse.JournalSearchbean" type="Lib.Auto.JournalBrowse.JournalSearchbean">
</jsp:useBean>


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


<form method="get" name="query" action="./JournalSearch"  ONSUBMIT="return validate(this)">
<br><br>

 <center>
<h2 >Full View</h2>
</center>
    
   <table align="center">
            
           
            <tr>
           <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Journal Code</td>
              <td Class="addedit" align="left">:&nbsp;<%=beanobject.getJnlCode()%>
              </td>
            </tr>
           <tr>
              <td Class="addedit" align="left">Journal Name</td>
             <td Class="addedit" align="left">:&nbsp;<%=beanobject.getJnlName()%></td>
              </td>
            </tr>
            
            <tr>
              <td Class="addedit" align="left">Type</td>
              <td Class="addedit" align="left">:&nbsp;<%=beanobject.getDocument()%></td>
            </tr>
            <tr>
           </tr>
           
            <tr>
			<td Class="addedit" align="left">Publisher</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getPublisher()%></td>
	         </tr>
	         
	         <tr>
			<td Class="addedit" align="left">Frequency</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getFrequency()%></td>
	         </tr>
	         
	         <tr>
			<td Class="addedit" align="left">Department</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getDepartment()%></td>
	         </tr>
            
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Category</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getCountry()%></td>
	         </tr>
	         
	         <tr>
			<td Class="addedit" align="left">ISSN</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getIssn()%></td>
			
	         </tr>
	         
	         <tr>
			<td Class="addedit" align="left">Subject</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getSubject()%></td>
	         </tr>
	         
	         <tr>
			<td Class="addedit" align="left">Location</td>			
			<td Class="addedit" align="left">:&nbsp;<%=beanobject.getLocation()%></td>
	         </tr>
	        
	         
            <tr>
           </tr>
            
            
            <tr>
              <td Class="addedit" align="left">URL</td>
            <td Class="addedit" align="left"><a href="<%=beanobject.getUrl()%>" target="_blank">:&nbsp;<%=beanobject.getUrl()%></a></td>      
            
            </tr>
               <tr>
           </tr>
              
            <%
            String cont=beanobject.getAddField1();
            if((!cont.isEmpty()&& (cont != null))){ 
            %>
			<tr>
           </tr>
			<tr>
              <td Class="addedit" align="left">Content</td>
<!--            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/Contents/Book/<%=beanobject.getAddField1()%> " target=_base>Click to Contents</a></td>-->
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/JOURNAL ISSUES/<%=beanobject.getAddField1()%> " target=_base>Click to Contents</a></td>                                      
            </tr>
            
            <%
            }
                      

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






